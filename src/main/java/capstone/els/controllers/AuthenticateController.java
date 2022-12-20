package capstone.els.controllers;

import capstone.els.dto.auth.LoginDTO;
import capstone.els.dto.auth.LoginResponseDTO;
import capstone.els.dto.response.ResponseDTO;
import capstone.els.enumCode.ErrorCode;
import capstone.els.enumCode.SuccessCode;
import capstone.els.services.AuthenticateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1.0/auth")

public class AuthenticateController {
    private final AuthenticateService authenticateService;

    @PostMapping("/login")
    @PermitAll
    private ResponseEntity<ResponseDTO> login(@RequestBody LoginDTO loginDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            LoginResponseDTO loginResponseDTO = authenticateService.login(loginDTO);
            if(loginResponseDTO != null){
                responseDTO.setData(loginResponseDTO);
                responseDTO.setSuccessCode(SuccessCode.LOGIN_SUCCESS);
            }else {
            responseDTO.setErrorCode(ErrorCode.LOGIN_ERROR);}
        }catch (Exception e){
            e.printStackTrace();
            responseDTO.setErrorCode(ErrorCode.LOGIN_ERROR);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

}
