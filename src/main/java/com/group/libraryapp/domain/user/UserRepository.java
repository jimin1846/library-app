package com.group.libraryapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> { // JPA 레포지토리를 상속받는것만으로 스프링 빈으로 등록됨.

    Optional <User> findByName(String name);


}
