//package minhchi.com.security;
//
//import minhchi.com.models.User;
//import minhchi.com.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//public class AuthProvider implements AuthenticationProvider {
//    @Autowired
//    private SecurityUserDetailsService userDetailsService;
//    @Autowired private PasswordEncoder passwordEncoder;
//    @Autowired private UserRepository userRepository;
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        Optional<User> user = userRepository.findUserByUsername(username);
//        if (user.isEmpty()) {
//            throw new BadCredentialsException("Invalid username or password!");
//        }
//        return authentication;
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return true;
//    }
//}
