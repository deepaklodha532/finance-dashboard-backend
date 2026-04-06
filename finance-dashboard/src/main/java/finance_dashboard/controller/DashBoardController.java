package finance_dashboard.controller;

import finance_dashboard.dto.FinancialRecordDto;
import finance_dashboard.entities.FinancialRecord;
import finance_dashboard.services.DashboardService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
@Tag(name = "Dashboard Management ", description = "Operation related to Dashboard management ")
public class DashBoardController {

    private DashboardService service ;

    public DashBoardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/summary")
    public Map<String, Double> summary(){
        Map<String, Double> map = new HashMap<>() ;
        map.put("Total Income ", service.getTotalIncome());
        map.put("Total Expense", service.getTotalExpense());
        map.put("net Balance", service.getNetBalance()) ;

        return map;
    }

    @GetMapping("/category")
    public Map<String, Double> categoryWise(){
        return service.getCategoryWiseTotal() ;
    }

    @GetMapping("/recent")
    public List<FinancialRecordDto> recent(){
        return service.getRecentTransaction();
    }

    @GetMapping("/monthly")
    public Map<Month, Double> monthly(){
        return service.getMonthlyTrends();
    }
}
