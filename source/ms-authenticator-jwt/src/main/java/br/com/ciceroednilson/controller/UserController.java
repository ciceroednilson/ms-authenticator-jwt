package br.com.ciceroednilson.controller;

import br.com.ciceroednilson.controller.http.UserRequest;
import br.com.ciceroednilson.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserRequest request) {
        this.userService.save(request);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(this.userService.findAll(), HttpStatus.OK);
    }
}
