package finance_dashboard.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
@Data
public class UserDto {
    private String id;
    @NotNull(message = "name is required")
    private String  name;

    @Pattern(regexp = "^[a-z0-9][-a-z0-9._]+@([-a-z0-9]+\\.)+[a-z]{2,5}$", message = "Invalid User Email !!")
    @NotBlank(message = "Email is required !!")
//    @Email(message = "unique email is required")
    private String email ;
    @NotNull(message = "password is required")
    private String password ;
    @NotNull(message = " active user for true or false is required")
    private boolean active = true;

}
