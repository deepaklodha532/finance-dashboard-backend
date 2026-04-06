package finance_dashboard.entities;


import finance_dashboard.dto.RecordType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Schema(description = "Details About the user ")
public class FinancialRecord {

    @Schema(description = "financialRecord Id")
    @Id
    private String id;
    @Schema(description = "Amount ")
    private Double amount;

    @Schema(description = "Record Type ID")
    @Enumerated(EnumType.STRING)
    private RecordType type;

    @Schema(description = "category")
    private String category;
    @Schema(description = "date")
    private LocalDate date;
    @Schema(description = "note")
    private String note;

}
