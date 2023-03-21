package socNet.Stadium.jwt;

import lombok.Data;

@Data
public class JwtLoginRequest {

    private String email;
    private String password;
}