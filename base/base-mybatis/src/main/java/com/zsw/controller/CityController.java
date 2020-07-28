package com.zsw.controller;

import com.zsw.persistence.entity.City;
import com.zsw.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shaowei Zhang on 2020/7/27 21:54
 */
@AllArgsConstructor
@RestController
@RequestMapping("city")
public class CityController {

    private CityService cityService;


    @GetMapping("get")
    public City get(@RequestParam("id") Integer id) {
        return this.cityService.getById(id);
    }

    @GetMapping("getByName")
    public City getByName(@RequestParam("name") String name) {
        return this.cityService.getByName(name);
    }

}
