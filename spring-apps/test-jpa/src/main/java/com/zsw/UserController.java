package com.zsw;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.data.domain.Sort.Direction.DESC;

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
            User old = this.userRepository.getById(user.getId());
            old.setName(user.getName());
            old.setAge(user.getAge());
            return this.userRepository.save(old);
        }
        return this.userRepository.save(user);
    }

    @GetMapping("/get/{id}")
    public User get(@PathVariable("id") Long id) {
        return this.userRepository.getById(id);
    }

    @GetMapping("/find")
    public Page<User> find() {
        return this.userRepository.findAll(PageRequest.of(0, 10, Sort.by(DESC, "id")));
    }


}
