package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.users.UserDataLogin;
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

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid UserDataLogin userDataLogin){

        /* -> Transforma os dados recebidos na request no objeto esperado para a funcao de auth (Instanciando essa funcao */
        var token = new UsernamePasswordAuthenticationToken(userDataLogin.username(), userDataLogin.password());
        var authentication = authenticationManager.authenticate(token);

        return ResponseEntity.ok().build();

    }


}
