package  com.caixa.notaderelease.api.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.caixa.notaderelease.api.model.mysql.User;
import com.caixa.notaderelease.api.security.jwt.JwtUserFactory;
import com.caixa.notaderelease.api.service.UserService;


@Service
public class JwtUserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String matricula) throws UsernameNotFoundException {

    		User user = userService.findByMatricula(matricula);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", matricula));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}