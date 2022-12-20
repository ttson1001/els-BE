package capstone.els.serviceImpls;

import capstone.els.dto.auth.LoginDTO;
import capstone.els.dto.auth.LoginResponseDTO;
import capstone.els.enities.User;
import capstone.els.jwt.JwtConfig;
import capstone.els.services.AuthenticateService;
import capstone.els.services.UserService;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticateServiceImpl implements AuthenticateService {

    private final AuthenticationManager authenticationManager;
    private final UserService userServices;
    private final JwtConfig jwtConfig;

    private final PasswordEncoder passwordEncoder;


    @Override
    public LoginResponseDTO login(LoginDTO loginDTO) {
        LoginResponseDTO loginResponseDTO = null;
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authentication);
        if (authenticate.isAuthenticated()) {
            User userAuthenticated = userServices.findByEmail(authenticate.getName());
            String token = Jwts.builder().setSubject(authenticate.getName())
                    .claim(("authorities"), authenticate.getAuthorities()).claim("id", userAuthenticated.getId()).claim("role" ,userAuthenticated.getRole().getName())
                    .setIssuedAt((new Date())).setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(1)))
                    .signWith(jwtConfig.secretKey()).compact();

            loginResponseDTO = LoginResponseDTO.builder()
                    .email(userAuthenticated.getEmail())
                    .token(jwtConfig.getTokenPrefix() + token).build();
        } else throw new RuntimeException("AUTHENTICATE_FAIL");
        return loginResponseDTO;
    }
}
