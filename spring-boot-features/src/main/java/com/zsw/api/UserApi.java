package com.zsw.api;

import com.zsw.persistence.entity.TestUser;
import com.zsw.pojo.NamesOnly;
import com.zsw.service.TestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ZhangShaowei on 2021/8/26 10:45
 */
@RequestMapping("user")
@RestController
public class UserApi {

    @Autowired
    private TestUserService userService;

    @GetMapping("find")
    public Page<TestUser> findAll(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        return this.userService.findAll(PageRequest.of(page, pageSize, Sort.Direction.DESC, "id"));
    }

    @GetMapping("findByPhone")
    public List<TestUser> findByPhone(@RequestParam("phone") String phone) {
        return this.userService.findByPhone(phone);
    }
    @GetMapping("findByFirstName")
    public List<NamesOnly> findByFirstName(@RequestParam("firstName") String firstName) {
        return this.userService.findByFirstName(firstName);
    }

    @GetMapping("findByAndSort")
    public List<Object[]> findByAndSort(@RequestParam("name") String name) {
        return this.userService.findByAndSort(name);
    }


    @GetMapping("init")
    public Object init(@RequestParam("num") Integer num) {
        this.userService.init(num);
        return "success";
    }

    @GetMapping("init0")
    public Object init0(@RequestParam("num") Integer num) {
        this.userService.insertAndReturnIds(num);
        return "success";
    }

}
