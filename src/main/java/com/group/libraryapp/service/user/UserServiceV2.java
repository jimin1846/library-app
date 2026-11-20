
package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 { // JPA를 활용한 Service 클래스

    private final UserRepository userRepository; // 인터페이스 참조 변수 선언

    public UserServiceV2(UserRepository userRepository) { // 생성자로 스프링 빈 주입 받기
        this.userRepository = userRepository;
    }

 //어노테이션 사용시 아래에 있는 함수가 시작될때 start transaction;을 해준다 (트랜잭션을 시작)
 // 함수가 예외 없이 잘 끝났다면 commit, 혹시라도 문제가 있다면 rollback
@Transactional //   (org.springframework.transaction.annotation
    public void saveUser(UserCreateRequest request) {
        userRepository.save(new User(request.getName(), request.getAge()));
        // throw new IllegalArgumentException();  // 일부러 예외가 나게 하여 트랙잭션의 롤백을 보여줌(이 때문에 유저가 세이브 되지 않음)
    }

@Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        /*return userRepository.findAll().stream()
                .map(user -> new UserResponse(user.getId(), user.getName(), user.getAge()))
                .collect(Collectors.toList());  // 전체 리스트로 반환해줌.*/
        return userRepository.findAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());  // 전체 리스트로 반환해줌.

    }

@Transactional
    public void updateUser(UserUpdateRequest request) {
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);

        user.updateName(request.getName());
        //userRepository.save(user);  // 영속성 컨텍스트의 기능 덕분에 세이브를 하지 않아도 변경을 감지해 자동으로 저장된다.
    }

    @Transactional
    public void deleteUser(String name) {
        User user = userRepository.findByName(name).orElseThrow(IllegalArgumentException::new);  // 유저가 없으면 null이 나오고 있으면 유저가 들어있는채로 나옴.
        /*if(user == null) {
            throw new IllegalArgumentException();
        } */  // 이거 왜 그런건지 모르겠네

        userRepository.delete(user);
    }
}

