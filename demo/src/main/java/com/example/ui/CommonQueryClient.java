package com.example.ui;
/**
 * @author ZhangShaowei on 2017/5/11 19:04
 */

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * CommonQueryClient
 *
 * @author ZhangShaowei on 2017/5/11 19:04
 **/
@FeignClient("api-server")
public interface CommonQueryClient {

    /**
     * @param mark mark
     * @param querySn  querySn
     * @param exportSn exportSn
     * @return CommonQueryConfigDTO
     */
    @RequestMapping(value = "getCheckedCqConfig", method = RequestMethod.POST)
    CommonQueryConfigDTO getCheckedCommonQueryConfig(
            @RequestParam("mark") String mark,
            @RequestParam("querySn") String querySn,
            @RequestParam("exportSn") String exportSn);

}
