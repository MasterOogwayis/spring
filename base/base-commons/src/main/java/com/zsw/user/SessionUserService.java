package com.zsw.user;

/**
 * 定义一个全局的 用户接口
 * 若使用则可以根据实际鉴权的方式进行用户注入
 *
 * @author ZhangShaowei on 2020/9/9 15:42
 */
public interface SessionUserService {

    /**
     * 获取用户信息
     *
     * @return UserInfo
     */
    UserInfo getUserInfo();

}
