package finance_dashboard.services;

import finance_dashboard.dto.FinancialRecordDto;
import finance_dashboard.entities.FinancialRecord;

import java.time.Month;
import java.util.List;
import java.util.Map;

public interface DashboardService {

    Double getTotalIncome();

    Double getTotalExpense();

    Double getNetBalance();

    Map<String, Double> getCategoryWiseTotal();

    List<FinancialRecordDto> getRecentTransaction();

    Map<Month, Double> getMonthlyTrends();
}
