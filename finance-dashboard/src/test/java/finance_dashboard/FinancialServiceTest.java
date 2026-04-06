package finance_dashboard;

import finance_dashboard.dto.FinancialRecordDto;
import finance_dashboard.dto.RecordType;
import finance_dashboard.entities.FinancialRecord;
import finance_dashboard.repositories.FinancialRecordRepository;
import finance_dashboard.services.serviceImpl.FinancialServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
@ExtendWith(MockitoExtension.class)
public class FinancialServiceTest {
    @Mock
    private FinancialRecordRepository repository;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private FinancialServiceImpl service;

    @Test
    void testCreateRecord() {

        // Arrange
        FinancialRecord record = new FinancialRecord();
        record.setAmount(1000.0);
        record.setType(RecordType.INCOME);
        record.setDate(LocalDate.now());
        record.setNote("This is test");
        record.setCategory("Food");

        FinancialRecordDto dto = new FinancialRecordDto();
        dto.setAmount(1000.0);
        dto.setType(RecordType.INCOME);
        dto.setDate(LocalDate.now());
        dto.setNote("This is test");
        dto.setCategory("Food");

        // Mock mapping
        Mockito.when(mapper.map(dto, FinancialRecord.class)).thenReturn(record);
        Mockito.when(repository.save(record)).thenReturn(record);
        Mockito.when(mapper.map(record, FinancialRecordDto.class)).thenReturn(dto);

        // Act
        FinancialRecordDto saved = service.create(dto);

        // Assert
        Assertions.assertEquals(1000.0, saved.getAmount());
        Assertions.assertEquals("Food", saved.getCategory());
    }
}