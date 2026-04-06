package finance_dashboard.controller;

import finance_dashboard.entities.User;
import finance_dashboard.repositories.UserRepository;
import finance_dashboard.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UserRepository repo;

    public AuthController(AuthenticationManager authManager,
                          JwtUtil jwtUtil,
                          UserRepository repo) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.repo = repo;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {

        // 🔥 Authenticate
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );

        User dbUser = repo.findByEmail(user.getEmail()).get();

        String token = jwtUtil.generateToken(
                dbUser.getEmail(),
                dbUser.getRole().name()
        );

        return Map.of("token", token);
    }
}
