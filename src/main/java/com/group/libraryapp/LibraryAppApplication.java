package com.group.libraryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // @는 어노테이션으로 다음에는 클래스 이름이 나옴 -> 스프링을 실행시킬때 필요한 설정들을 자동으로 해줌
public class LibraryAppApplication {

  public static void main(String[] args) {
    SpringApplication.run(LibraryAppApplication.class, args); // 스프링 어플리케이션을 시작함.
  }

}
