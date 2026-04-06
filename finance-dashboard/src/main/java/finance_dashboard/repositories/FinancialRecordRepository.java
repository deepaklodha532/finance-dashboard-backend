package finance_dashboard.repositories;

import finance_dashboard.dto.RecordType;
import finance_dashboard.entities.FinancialRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, String> {

    List<FinancialRecord> findByCategory(String category);

    List<FinancialRecord> findByType(RecordType type);

    List<FinancialRecord> findByDateBetween(LocalDate start, LocalDate end);

    List<FinancialRecord> findByCategoryAndType(String category, RecordType type);
}