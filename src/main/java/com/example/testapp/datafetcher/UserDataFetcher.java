package com.example.testapp.datafetcher;

import com.example.testapp.entity.UserEntity;
import com.example.testapp.entity.dto.CreateUserInput;
import com.example.testapp.entity.dto.UpdateUserInput;
import com.example.testapp.entity.dto.User;
import com.example.testapp.repository.UserRepository;
import com.netflix.graphql.dgs.*;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@DgsComponent
@RequiredArgsConstructor
public class UserDataFetcher {

    private final UserRepository userRepository;

    @DgsQuery
    public List<User> users() {

        var userEntities = userRepository.findAll();

        return userEntities.stream()
                .map(this::mapToUser)
                .collect(Collectors.toList());
    }

    //we can even pass arguments without InputArgument annotation but practice is to use the annotation
    @DgsQuery
    public User user(@InputArgument Long id) {

        return userRepository.findById(id).map(this::mapToUser)
                .orElseThrow(DgsEntityNotFoundException::new);
    }

    @DgsMutation
    public User createUser(@InputArgument CreateUserInput input) {

        var newUserEntity = UserEntity.builder()
            .name(input.getName())
            .email(input.getEmail())
            .build();

       return mapToUser(userRepository.save(newUserEntity));
    }

    @DgsMutation
    public User updateUser(@InputArgument Long id, @InputArgument UpdateUserInput input) {

        var existingUserEntity = userRepository.findById(id)
                .orElseThrow(DgsEntityNotFoundException::new);

        assertNameIsNotNull(existingUserEntity, input.getName());
        assertEmailIsNotNull(existingUserEntity, input.getEmail());

        return mapToUser(userRepository.save(existingUserEntity));
    }

    @DgsMutation
    public User deleteUser(@InputArgument Long id) {

        var existingUserEntity = userRepository.findById(id)
                .orElseThrow(DgsEntityNotFoundException::new);

        userRepository.deleteById(id);

        return mapToUser(existingUserEntity);
    }

    private User mapToUser(UserEntity userEntity) {

        return User.builder()
            .id(userEntity.getId())
            .name(userEntity.getName())
            .email(userEntity.getEmail())
            .build();

    }

    private void assertNameIsNotNull(UserEntity existingUserEntity, String name) {

        if (name != null) {
            existingUserEntity.setName(name);
        }
    }

    private void assertEmailIsNotNull(UserEntity existingUserEntity, String email) {

        if (email != null) {
            existingUserEntity.setEmail(email);
        }
    }
}
