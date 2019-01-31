package com.zsw.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Test
 *
 * @author ZhangShaowei on 2018/4/8 13:46
 **/
@Api(value = "测试 swagger2", description = "测试Swagger2 实体类参数返回值")
@RequestMapping("test")
@RestController
public class TestController {


    /**
     * doc: http://springfox.github.io/springfox/docs/current/
     *
     * 注意 @ApiImplicitParam 和 @ApiParam的区别
     * {@link io.swagger.annotations.ApiImplicitParam#paramType} 可以是多种类型的参数
     * {@link io.swagger.annotations.ApiParam} 注解原数据，只能是 post
     * 对于自定义的 Class 参数类推荐使用 @ApiParam + @ApiModel，@ApiImplicitParam无法描述对象参数
     *
     * @param testParam
     * @return
     */
    @ApiOperation(value = "test接口", notes = "测试实体类参数/返回值注解问题")
    @ApiParam(name = "testParam", value = "实体参数TestParam", required = true)
//    @ApiImplicitParam(name = "testParam", value = "实体参数TestParam", dataType = "TestParam", required = true)
    @PostMapping
    public TestResponse test(@RequestBody TestParam testParam) {
        return TestResponse.builder().id(testParam.getId()).name(testParam.getName()).age(testParam.getAge()).build();
    }
}
