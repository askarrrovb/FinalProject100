package com.example.finalproject100.repository;

import com.example.finalproject100.entity.BagdauletAskarUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BagdauletAskarUserRepository extends JpaRepository<BagdauletAskarUser, Long> {

    Optional<BagdauletAskarUser> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
