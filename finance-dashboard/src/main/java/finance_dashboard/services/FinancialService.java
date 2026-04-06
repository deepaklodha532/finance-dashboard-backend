package finance_dashboard.services;

import finance_dashboard.dto.FinancialRecordDto;
import finance_dashboard.dto.RecordType;
import finance_dashboard.entities.FinancialRecord;
import finance_dashboard.repositories.FinancialRecordRepository;

import java.time.LocalDate;
import java.util.List;

public interface FinancialService {


    FinancialRecordDto create(FinancialRecordDto record) ;

    List<FinancialRecordDto> getAll(int pageNumber, int pageSize, String sortBy , String sortDir);

    FinancialRecordDto getById(String id);

    FinancialRecordDto update(String id, FinancialRecordDto updated) ;

    void delete(String id);

    List<FinancialRecordDto> filter(String category, RecordType type, LocalDate startDate, LocalDate endDate);

}
