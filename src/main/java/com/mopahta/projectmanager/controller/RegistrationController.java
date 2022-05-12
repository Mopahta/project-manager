package com.mopahta.projectmanager.controller;

import com.mopahta.projectmanager.dto.UserDTO;
import com.mopahta.projectmanager.exception.InvalidValuesException;
import com.mopahta.projectmanager.exception.UserAlreadyExistsException;
import com.mopahta.projectmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class RegistrationController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public String login(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "signup";
    }

    @PostMapping("")
    public String signup(@ModelAttribute UserDTO userDTO, BindingResult bindingResult, Model model) {
        String passwordMatchResult = userService.checkPasswordsMatch(userDTO);
        if (!passwordMatchResult.isEmpty()) {
            model.addAttribute("user", userDTO);
            ObjectError error = new ObjectError("globalError", passwordMatchResult);
            bindingResult.addError(error);
            return "signup";
        }

        try {
            userService.registerUser(userDTO);
        }
        catch (UserAlreadyExistsException | InvalidValuesException e) {
            //bindingResult.rejectValue("username", "user.username", e.getMessage());
            model.addAttribute("user", userDTO);
            return "signup";
        }

        return "index";
    }
}
