package br.com.ciceroednilson.service.impl;

import br.com.ciceroednilson.controller.http.UserRequest;
import br.com.ciceroednilson.controller.http.UserResponse;
import br.com.ciceroednilson.repository.UserRepository;
import br.com.ciceroednilson.repository.entity.UserEntity;
import br.com.ciceroednilson.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    private final static String ENABLE = "S";

    @Autowired
    public UserServiceImpl(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(final UserRequest userRequest) {
        final UserEntity userEntity = buildUserEntity(userRequest);
        userRepository.save(userEntity);
    }

    @Override
    public List<UserResponse> findAll() {
        final Iterable<UserEntity> users = userRepository.findAll();
        return buildListOfUsers(users);
    }

    private List<UserResponse> buildListOfUsers(final Iterable<UserEntity> users) {
        final List<UserResponse> usersResponse = new ArrayList<>();
        users.forEach(user -> {
            usersResponse.add(buildUserResponse(user));
        });
        return usersResponse;
    }

    private UserResponse buildUserResponse(final UserEntity userEntity) {
        return UserResponse
                .builder()
                .email(userEntity.getEmail())
                .id(userEntity.getId())
                .enable(ENABLE.equals(userEntity.getEnable()))
                .name(userEntity.getName())
                .registerDate(userEntity.getRegisterDate())
                .build();
    }

    private UserEntity buildUserEntity(final UserRequest userRequest) {
        return UserEntity
                .builder()
                .email(userRequest.getEmail())
                .enable("S")
                .name(userRequest.getName())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final UserEntity userEntity = userRepository.findByEmail(username);
        if (Objects.isNull(userEntity)) {
            throw new UsernameNotFoundException("User not found with username: ".concat(username));
        }
        if (!ENABLE.equals(userEntity.getEnable())) {
            throw new UsernameNotFoundException(String.format("User %s disable!", username));
        }
        return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getPassword(),
                new ArrayList<>());
    }
}
