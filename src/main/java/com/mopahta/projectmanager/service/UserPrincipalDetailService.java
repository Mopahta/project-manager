package com.mopahta.projectmanager.service;

import com.mopahta.projectmanager.model.User;
import com.mopahta.projectmanager.model.UserPrincipal;
import com.mopahta.projectmanager.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailService implements UserDetailsService {

    private UserRepository userRepository;

    public UserPrincipalDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        return new UserPrincipal(user);
    }
}
