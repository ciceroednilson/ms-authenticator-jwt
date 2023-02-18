package br.com.ciceroednilson.controller.http;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class UserResponse {

    private Integer id;
    private String name;
    private String email;
    private String password;
    private boolean enable;
    private Date registerDate;
}
