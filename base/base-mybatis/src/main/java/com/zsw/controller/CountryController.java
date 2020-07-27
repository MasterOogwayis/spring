package com.zsw.controller;

import com.zsw.persistence.entity.Country;
import com.zsw.service.CountryService;
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
@RequestMapping("country")
public class CountryController {

    private CountryService countryService;


    @GetMapping("get")
    public Country get(@RequestParam("id") String id) {
        return this.countryService.getById(id);
    }

}
