package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.users.User;
import med.voll.api.domain.users.UserDataLogin;
import med.voll.api.infra.security.TokenResponse;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class AuthController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid UserDataLogin userDataLogin){

        /* -> Transforma os dados recebidos na request no objeto esperado para a funcao de auth (Instanciando essa funcao */
        var authenticationToken = new UsernamePasswordAuthenticationToken(userDataLogin.username(), userDataLogin.password());
        var authentication = authenticationManager.authenticate(authenticationToken);

        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenResponse(tokenJWT));
    }

}
