package br.com.ciceroednilson.controller;

import br.com.ciceroednilson.controller.http.JwtRequest;
import br.com.ciceroednilson.service.JwtAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/authenticate")
public class JwtController {

    @Autowired
    public JwtAuthenticationService jwtAuthenticationService;

    @PostMapping
    public @ResponseBody ResponseEntity<?> authenticate(@RequestBody JwtRequest jwtRequest) {
        final Map<String, String> mapResponse = new HashMap<>();
        mapResponse.put("token", jwtAuthenticationService.generateToken(jwtRequest));
       return ResponseEntity.ok(mapResponse);
    }
}
