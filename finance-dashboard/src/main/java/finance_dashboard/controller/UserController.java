package finance_dashboard.controller;


import finance_dashboard.dto.ResponseMessage;
import finance_dashboard.dto.UserDto;
import finance_dashboard.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "User Management", description = "operation related to User management")
public class UserController {


    private UserService userService ;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // @Operation annotation is used to add metadata to an API operation.
    // @ApiResponse annotation specifies the possible HTTP responses for and endpoint
    @Operation(
            summary =  "Get a user by ID",
            description = "Features the details of a user by the unique id ",
            tags = "user operations"
    )
    @ApiResponse(responseCode = "200", description = "User found")
    @ApiResponse(responseCode = "404",description = "user not found")
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
         UserDto created =   userService.createUser(userDto);
         return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Operation(
            summary = "update user by new user details",
            description = "features for update user details but can not updated email",
            tags = "User operation"
    )
    @ApiResponse(responseCode = "200",description = "user update")
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser( @Valid @RequestBody UserDto dto , @PathVariable String userId){
        UserDto updated = userService.updateUser(userId, dto) ;
        return new ResponseEntity<>( updated, HttpStatus.OK ) ;
    }

    @DeleteMapping("/{userId}")
    @Operation(
            summary = "delete user by id",
            description = "features for delete user ",
            tags = "user operation"
    )
    @ApiResponse(responseCode = "204", description = "user deleted successfully")
    public ResponseEntity<ResponseMessage> deleteUser(@Parameter(name = "userId",description = "ID of the user for delete user ",required = true, example = "lfsefwnfwkn")
                                                          @PathVariable String userId){
        userService.deleteUser(userId);
        ResponseMessage message = new ResponseMessage() ;
        message.setMessage("deleted successfully ");
        message.setStatus(true);
        return new ResponseEntity<>(message, HttpStatus.OK) ;

    }

    @Operation(summary = "get user by id",
        description = "features of user found", tags = "user operation ")
    @ApiResponse(responseCode = "404",description = "user not found given id")

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto>  getUser(@Parameter(name = "userId", description = "user id is important for getting user", required = true, example = "dfnkvsnkld")
                                                @PathVariable String userId){
        return new ResponseEntity<>(userService.getUser(userId),HttpStatus.OK)  ;
    }

    @Operation(summary = "get users",
            description = "features of user found", tags = "user operations ")
    @ApiResponse(responseCode = "404",description = "user not found given id")
    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir){
        List<UserDto>    list =  userService.getUsers(pageNumber,pageSize,sortDir,sortBy)  ;
        return new ResponseEntity<>(list, HttpStatus.OK) ;

    }
}
