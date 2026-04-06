package finance_dashboard.entities;

import finance_dashboard.dto.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
//@Schema annotation provides metadata for models or properties
@Schema(description = "Details about the  user ")
public class User {

    @Schema(description = "Unique identifier of the user ",example = "1")
    @Id
    private String id;
    @Schema(description = "User Full Name ",example = "Thakur Deepak Singh Lodha")
    private String  name;
    @Column(unique = true)
    private String email ;
    private String password ;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Schema(description = "user is active true", example = "true or false")
    private boolean active = true;



}
