package socNet.Stadium.jwt;

import lombok.Data;

@Data
public class JwtAuthenticationRequest {
    private String username;
    private String password;
}