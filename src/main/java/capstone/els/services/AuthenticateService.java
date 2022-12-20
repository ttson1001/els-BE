package capstone.els.services;

import capstone.els.dto.auth.LoginDTO;
import capstone.els.dto.auth.LoginResponseDTO;

public interface AuthenticateService {
    LoginResponseDTO login(LoginDTO loginDTO);
}
