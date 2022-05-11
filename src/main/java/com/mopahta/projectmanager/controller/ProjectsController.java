package com.mopahta.projectmanager.controller;

import com.mopahta.projectmanager.dto.UserDTO;
import com.mopahta.projectmanager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("projects")
public class ProjectsController {
    @Autowired
    ProjectService projectService;

    @GetMapping("all")
    public String userProjects(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(authentication.getName());
            model.addAttribute("user", userDTO);

            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            model.addAttribute("projects",
                    projectService.findUserProjects(authorities, authentication.getName()));


            return "projects";
        }
        return "index";
    }
}
