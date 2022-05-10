package com.mopahta.projectmanager.controller;

import com.mopahta.projectmanager.dto.UserDTO;
import com.mopahta.projectmanager.exception.UserAlreadyExistsException;
import com.mopahta.projectmanager.model.User;
import com.mopahta.projectmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(authentication.getName());
            model.addAttribute("user", userDTO);
        }
        return "index";
    }

    @GetMapping("signup")
    public String login(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "signup";
    }

    @PostMapping("signup")
    public String signup(@ModelAttribute UserDTO userDTO, Model model) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("user", userDTO);
//            return "signup";
//        }

        try {
            userService.registerUser(userDTO);
        }
        catch (UserAlreadyExistsException e) {
            //bindingResult.rejectValue("username", "user.username", e.getMessage());
            model.addAttribute("user", userDTO);
            return "signup";
        }

        return "index";
    }
}
