package com.zsw;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2020/9/25 14:05
 */
@AllArgsConstructor
@RequestMapping("user")
@RestController
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("add")
    public User save(@RequestBody User user) {
        if (user.getId() != null) {
            User old = this.userRepository.getOne(user.getId());
            old.setName(user.getName());
            old.setAge(user.getAge());
            return this.userRepository.save(old);
        }
        return this.userRepository.save(user);
    }


}
