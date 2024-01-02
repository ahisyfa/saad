package com.github.ahisyfa.saad.saad.config;

import com.github.ahisyfa.saad.saad.entity.User;
import com.github.ahisyfa.saad.saad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Ahmad Isyfalana Amin
 * @version $Id: SamalaAuthenticationProvider.java, v 0.1 2023-12-30  14.55 Ahmad Isyfalana Amin Exp $
 */
@Component
public class SaadAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        Optional<User> userByUsername = userService.findUserByUsername(username);

        if (userByUsername.isPresent()) {
            User presentedUser = userByUsername.get();
            if (passwordEncoder.matches(pwd, presentedUser.getPassword())) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                String commaSeparatedRoles = presentedUser.getRoles();
                String[] roles = commaSeparatedRoles.split(",");

                for (String role : roles) {
                    authorities.add(new SimpleGrantedAuthority(role));
                }

                return new UsernamePasswordAuthenticationToken(username, pwd, authorities);
            } else {
                throw new BadCredentialsException("Invalid password!");
            }

        } else {
            throw new BadCredentialsException("No user registered with this details!");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}