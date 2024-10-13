package ru.mastkey.cloudservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mastkey.cloudservice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
