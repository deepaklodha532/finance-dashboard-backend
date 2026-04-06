package finance_dashboard.services.serviceImpl;

import finance_dashboard.dto.FinancialRecordDto;
import finance_dashboard.dto.RecordType;
import finance_dashboard.entities.FinancialRecord;
import finance_dashboard.repositories.FinancialRecordRepository;
import finance_dashboard.services.DashboardService;
import finance_dashboard.services.FinancialService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {

    private FinancialRecordRepository repository ;
    private ModelMapper mapper;


    public DashboardServiceImpl(FinancialRecordRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Double getTotalIncome() {

        return repository.findAll().stream()
                .filter(record -> record.getType()== RecordType.INCOME)
                .mapToDouble(FinancialRecord::getAmount)
                .sum()
                ;
    }

    @Override
    public Double getTotalExpense() {

        return  repository.findAll().stream()
                .filter(record -> record.getType() == RecordType.EXPENSE)
                .mapToDouble(FinancialRecord::getAmount)
                .sum();
    }

    @Override
    public Double getNetBalance() {
        return getTotalIncome() - getTotalExpense();
    }

    @Override
    public Map<String, Double> getCategoryWiseTotal() {
        return repository.findAll().stream()
                .collect(Collectors.groupingBy(
                        FinancialRecord::getCategory,
                        Collectors.summingDouble(FinancialRecord :: getAmount)
                )) ;
    }

    @Override
    public List<FinancialRecordDto> getRecentTransaction() {
        List<FinancialRecord> records = repository.findAll().stream()
                .sorted(Comparator.comparing(FinancialRecord::getDate).reversed())
                .limit(5)
                .toList();

        return records.stream().map(record -> mapper.map(record,FinancialRecordDto.class)).collect(Collectors.toList());
    }

    @Override
    public Map<Month, Double> getMonthlyTrends() {

        return repository.findAll().stream()
                .collect(Collectors.groupingBy(r-> r.getDate().getMonth(), Collectors.summingDouble(FinancialRecord::getAmount)));
    }
}
