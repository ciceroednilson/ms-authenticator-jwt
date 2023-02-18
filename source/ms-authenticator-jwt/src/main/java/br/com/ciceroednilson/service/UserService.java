package br.com.ciceroednilson.service;

import br.com.ciceroednilson.controller.http.UserRequest;
import br.com.ciceroednilson.controller.http.UserResponse;

import java.util.List;

public interface UserService {

    void save(UserRequest userRequest);
    List<UserResponse> findAll();
}
