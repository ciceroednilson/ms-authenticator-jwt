package br.com.ciceroednilson.service.impl;

import br.com.ciceroednilson.controller.http.JwtRequest;
import br.com.ciceroednilson.service.JwtAuthenticationService;
import br.com.ciceroednilson.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtAuthenticationServiceImpl implements JwtAuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public String generateToken(final JwtRequest jwtRequest) {
        authenticate(jwtRequest.getEmail(), jwtRequest.getPassword());
        final UserDetails userDetails = userServiceImpl.loadUserByUsername(jwtRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    private void authenticate(final String email, final String password) throws DisabledException, BadCredentialsException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (final DisabledException e) {
            throw e;
        } catch (final BadCredentialsException e) {
            throw e;
        }
    }
}
