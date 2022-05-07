package com.mopahta.projectmanager.repository;

import com.mopahta.projectmanager.model.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @NonNull
    List<User> findAll();
    User findByUsername(String username);
    List<User> findAllByRolesContains(String role);
}
