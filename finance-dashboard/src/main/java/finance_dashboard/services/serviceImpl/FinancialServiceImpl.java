package finance_dashboard.services.serviceImpl;

import finance_dashboard.dto.FinancialRecordDto;
import finance_dashboard.dto.RecordType;
import finance_dashboard.entities.FinancialRecord;
import finance_dashboard.exception.ResourceNotFoundException;
import finance_dashboard.repositories.FinancialRecordRepository;
import finance_dashboard.services.FinancialService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FinancialServiceImpl implements FinancialService {

    private FinancialRecordRepository repository ;

    private ModelMapper mapper ;

    public FinancialServiceImpl(FinancialRecordRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public FinancialRecordDto create(FinancialRecordDto record) {
        if(record.getAmount() <=0){
            throw new RuntimeException("Amount must be positive ") ;
        }
        record.setId(UUID.randomUUID().toString());
        FinancialRecord saved =  repository.save(mapper.map(record,FinancialRecord.class) );
        return mapper.map(saved,  FinancialRecordDto.class) ;
    }

    @Override
    public List<FinancialRecordDto> getAll(int pageNumber, int pageSize,String sortBy ,String sortDir) {
        // Default sorting field
        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "date";
        }

        // Allowed fields validation
        List<String> allowedFields = List.of("date", "amount", "category");
        if (!allowedFields.contains(sortBy)) {
            sortBy = "date";
        }

        // Sort direction
        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<FinancialRecord> all = repository.findAll(pageable);

        return all.stream()
                .map(record -> mapper.map(record, FinancialRecordDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public FinancialRecordDto getById(String id) {
        FinancialRecord financialRecord = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resource not found given id "));

        return mapper.map(financialRecord, FinancialRecordDto.class);
    }

    @Override
    public FinancialRecordDto update(String id, FinancialRecordDto updated) {
        FinancialRecord financialRecord = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Resource not found Exception "));

        financialRecord.setAmount(updated.getAmount());
        financialRecord.setDate(updated.getDate());
        financialRecord.setType(updated.getType());
        financialRecord.setNote(updated.getNote());
        financialRecord.setCategory(updated.getCategory());
        FinancialRecord financialRecord2 =  repository.save(financialRecord) ;
        return mapper.map(financialRecord2, FinancialRecordDto.class);
    }

    @Override
    public void delete(String id) {
        FinancialRecord financialRecord = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Resource not found Exception "));
        repository.delete(financialRecord);

    }

    @Override
    public List<FinancialRecordDto> filter(String category, RecordType type, LocalDate startDate, LocalDate endDate) {

        List<FinancialRecord> recors = repository.findAll().stream()
                .filter(r-> category==null || r.getCategory().equalsIgnoreCase(category))
                .filter(r-> type==null || r.getType()==type)
                .filter(r-> (startDate==null || !r.getDate().isBefore(startDate)))
                .filter(r-> (endDate==null || !r.getDate().isAfter(endDate)))
                .toList();

        return  recors.stream().map(record -> mapper.map(record, FinancialRecordDto.class)).collect(Collectors.toList());
    }
}
