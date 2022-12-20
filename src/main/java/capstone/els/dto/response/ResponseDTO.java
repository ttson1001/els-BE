package capstone.els.dto.response;

import capstone.els.enumCode.ErrorCode;
import capstone.els.enumCode.SuccessCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private Object data;
    private ErrorCode errorCode;
    private SuccessCode successCode;
}
