package br.com.ciceroednilson.service;

import br.com.ciceroednilson.controller.http.JwtRequest;

public interface JwtAuthenticationService {

    String generateToken(JwtRequest jwtRequest);

}
