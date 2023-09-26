package input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginCinetPayRequest {
    private String code;
    private DataLoginResponse data;
}
