package finance_dashboard.controller;

import finance_dashboard.dto.FinancialRecordDto;
import finance_dashboard.dto.RecordType;
import finance_dashboard.dto.ResponseMessage;
import finance_dashboard.services.FinancialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import java.time.LocalDate;
import java.util.List;

//@Tag()  annotation groups related operation  under a common name

@RestController
@RequestMapping("/records")
@Tag(name = " Finance Record Management ", description = "Operation related to financeRecord management ")
public class FinancialController {


    private FinancialService service;

    public FinancialController(FinancialService service) {
        this.service = service;
    }

    @Operation(summary = "create finance record",
    description = "features of adding finance record",
    tags = "finance record management")
    @ApiResponse(responseCode = "201", description = "finance record add")
    @PostMapping
    public ResponseEntity<FinancialRecordDto> create(@Valid  @RequestBody FinancialRecordDto record){
        return new ResponseEntity<>(service.create(record) , HttpStatus.CREATED) ;

    }

    @Operation(summary = "Fetch all finance record ",
            description = "finance record can fetch using description ",
            tags = "FinanceRecord"
    )
    @ApiResponse(responseCode = "200",description = "found all records ")
    @GetMapping
    public ResponseEntity<List<FinancialRecordDto>> getAll(
            @RequestParam(value = "pageNumber", defaultValue = "0" , required = false) int pageNumber,
            @RequestParam(value="pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "date", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        return new ResponseEntity<>( service.getAll(pageNumber, pageSize, sortDir, sortBy),HttpStatus.OK) ;
    }

    @Operation(summary = "Fetch all  record by id",
            description = "finance record can fetch using id",
            tags = "FinanceRecord"
    )
    @ApiResponse(responseCode = "200",description = "found all records ")

    @GetMapping("/{id}")
    public ResponseEntity<FinancialRecordDto> get(@PathVariable String id){
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FinancialRecordDto> updated(@PathVariable String id,@Valid @RequestBody FinancialRecordDto updated){
        return new ResponseEntity<>(service.update(id, updated) ,HttpStatus.OK) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> delete(@PathVariable String id){

        service.delete(id);
        ResponseMessage message = new ResponseMessage() ;
        message.setStatus(true);
        message.setMessage("Deleted financial Record successfully ");

        return  new ResponseEntity<>(message, HttpStatus.OK) ;
    }

    @GetMapping("/filter")
    public  ResponseEntity<List<FinancialRecordDto>> filter(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) RecordType type,
            @RequestParam(required = false)LocalDate startDate,
            @RequestParam(required = false)LocalDate endDate
            )
    {
        List<FinancialRecordDto> financialRecordDtos =  service.filter(category,type,startDate,endDate) ;
        return new ResponseEntity<>(financialRecordDtos, HttpStatus.OK) ;
    }
}
