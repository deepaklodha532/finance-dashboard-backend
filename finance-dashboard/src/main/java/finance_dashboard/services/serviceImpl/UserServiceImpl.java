package finance_dashboard.services.serviceImpl;

import finance_dashboard.dto.UserDto;
import finance_dashboard.entities.User;
import finance_dashboard.exception.ResourceNotFoundException;
import finance_dashboard.repositories.UserRepository;
import finance_dashboard.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDto createUser( UserDto userDto) {

        userDto.setId(UUID.randomUUID().toString());
        User user = mapper.map(userDto,User.class) ;
        user.setPassword(encoder.encode(user.getPassword()));
        User saved = userRepository.save(user) ;

        return mapper.map(saved, UserDto.class);
    }

    @Override
    public UserDto getUser(String userId) {
        User user= userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("Resource Not found given userId")) ;
        return mapper.map(user, UserDto.class);
    }

    @Override
    public void deleteUser(String userId) {
        User user  = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Resource not found given id")) ;
        userRepository.delete(user);

    }

    @Override
    public UserDto updateUser(String userId, UserDto userDto) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Resource not found given id"));
        user.setName(userDto.getName());
        user.setActive(userDto.isActive());
        user.setPassword(encoder.encode(userDto.getPassword()));
        User updated=   userRepository.save(user);
        return  mapper.map(updated,UserDto.class) ;
    }

    @Override
    public List<UserDto> getUsers(int pageNumber , int pageSize, String sortDir, String sortBy) {
        Sort sort = sortDir.equalsIgnoreCase("desc")? Sort.by(sortBy).descending() :  Sort.by(sortBy).ascending() ;
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort) ;
        Page<User> all = userRepository.findAll(pageable);
        List<UserDto> userDtos= all.stream().map(user-> mapper.map(user,UserDto.class)).collect(Collectors.toList());
        return  userDtos;

    }
}
