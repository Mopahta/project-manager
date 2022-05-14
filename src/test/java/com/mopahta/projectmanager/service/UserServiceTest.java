package com.mopahta.projectmanager.service;

import com.mopahta.projectmanager.dto.UserDTO;
import com.mopahta.projectmanager.exception.InvalidValuesException;
import com.mopahta.projectmanager.exception.NotFoundException;
import com.mopahta.projectmanager.exception.UserAlreadyExistsException;
import com.mopahta.projectmanager.model.User;
import com.mopahta.projectmanager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void checkPasswordsMatch() {
        UserDTO userDTO = new UserDTO();

        userDTO.setPassword("password");
        userDTO.setMatchingPassword("password");

        String result = userService.checkPasswordsMatch(userDTO);

        assertEquals("", result);
    }

    @Test
    void registerUser() throws InvalidValuesException, UserAlreadyExistsException {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("daxao");
        userDTO.setPassword("mesi");

        userService.registerUser(userDTO);

        Mockito.verify(userRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(User.class));
    }

    @Test
    void registerUserFailEmptyPassword() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("daxao");
        userDTO.setPassword("");

        try {
            userService.registerUser(userDTO);
        }
        catch (InvalidValuesException | UserAlreadyExistsException e) {
            Mockito.verify(userRepository, Mockito.times(0))
                .save(ArgumentMatchers.any(User.class));
        }

    }

    @Test
    void registerUserFailExistingUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("hawtstone");
        userDTO.setPassword("1234");

        try {
            userService.registerUser(userDTO);
        }
        catch (InvalidValuesException | UserAlreadyExistsException e) {
            Mockito.verify(userRepository, Mockito.times(0))
                    .save(ArgumentMatchers.any(User.class));
        }

    }
}