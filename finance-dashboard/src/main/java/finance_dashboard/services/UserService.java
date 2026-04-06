package finance_dashboard.services;

import finance_dashboard.dto.UserDto;

import java.util.List;

public interface UserService {

     UserDto createUser(UserDto userDto) ;
     UserDto getUser(String userId);
     void  deleteUser(String userId) ;

     UserDto updateUser(String userId, UserDto userDto) ;
     List<UserDto>  getUsers(int pageNumber, int pageSize, String sortDir, String sortBy) ;

}
