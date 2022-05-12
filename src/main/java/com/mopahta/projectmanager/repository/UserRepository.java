package com.mopahta.projectmanager.repository;

import com.mopahta.projectmanager.model.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @NonNull
    List<User> findAll();

    // https://github.com/spring-projects/spring-data-jpa/issues/2472
    List<User> findAllByRolesContains(String role);
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    List<User> findAllByActiveIsTrue();
}
