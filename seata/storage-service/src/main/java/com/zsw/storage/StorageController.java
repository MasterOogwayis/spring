package com.zsw.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2021/6/7 13:23
 */
@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;

    @RequestMapping(value = "/deduct", produces = "application/json")
    public Boolean deduct(String commodityCode, Integer count) {
        storageService.deduct(commodityCode, count);
        return true;
    }

}
