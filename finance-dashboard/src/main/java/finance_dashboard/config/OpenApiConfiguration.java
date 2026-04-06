package finance_dashboard.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Finance Dashboard Backend API",
                version="1.0",
                description = "This API provides backend services for managing financial records, user roles, and dashboard analytics with role-based access control."
        )
)
public class OpenApiConfiguration {

}
