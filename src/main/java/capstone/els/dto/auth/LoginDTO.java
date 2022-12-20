package capstone.els.dto.auth;

import capstone.els.constants.validationSize.ValidationSize;
import capstone.els.constants.validation_message.ValidationMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    @Email(message = ValidationMessage.EMAIL_VALID_MESSAGE_WHEN_LOGIN)
    private String email;

    @Size(min = ValidationSize.PASSWORD_MIN, max = ValidationSize.PASSWORD_MAX, message = ValidationMessage.PASSWORD_VALID_MESSAGE)
    private String password;
}
