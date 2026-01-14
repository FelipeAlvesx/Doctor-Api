package med.voll.api.service;

import med.voll.api.domain.users.User;
import med.voll.api.domain.users.UserDataLogin;
import med.voll.api.domain.users.UserRespository;
import med.voll.api.infra.security.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserRespository userRespository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public TokenResponse login(UserDataLogin userDataLogin){

        /* -> Transforma os dados recebidos na request no objeto esperado para a funcao de auth (Instanciando essa funcao */
        var authenticationToken = new UsernamePasswordAuthenticationToken(userDataLogin.username(), userDataLogin.password());
        var authentication = authenticationManager.authenticate(authenticationToken);

        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());

        return new TokenResponse(tokenJWT);
    }


    public void register(UserDataLogin userDataLogin){
        String encryptedPassword = passwordEncoder.encode(userDataLogin.password());

        var user = new User(userDataLogin.username(), encryptedPassword);
        userRespository.save(user);
    }


}
