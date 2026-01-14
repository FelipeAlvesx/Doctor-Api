package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.users.UserDataLogin;
import med.voll.api.infra.security.TokenResponse;
import med.voll.api.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class AuthController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid UserDataLogin userDataLogin){
        return ResponseEntity.ok(loginService.login(userDataLogin));
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<?> register(@RequestBody @Valid UserDataLogin userDataLogin){
        loginService.register(userDataLogin);
        return ResponseEntity.status(HttpStatus.CREATED).body("CREATED!");
    }

}
