package com.zsw.conf.oauth2;

import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * ResourceConfiguration
 *
 * 使用 test {@link com.zsw.orm.utils.ExportCert} 生成公钥
 *
 *
 *
 * @author ZhangShaowei on 2018/5/14 9:25
 **/
//@Configuration
//@EnableResourceServer
//@ConfigurationProperties(prefix = "com.zsw.resource")
public class ResourceConfiguration extends ResourceServerConfigurerAdapter {

//    /**
//     *
//     */
//    private String resourceId;
//
//    /**
//     *
//     */
//    private String verifierKeyFile;
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) {
//        resources.resourceId(this.resourceId).stateless(true);
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        // @formatter:off
//        http
//                // Since we want the protected resources to be accessible in the UI as well we need
//                // session creation to be allowed (it's disabled by default in 2.0.6)
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
////                    .and()
////                    .requestMatchers().anyRequest()
//                .and()
//                .anonymous()
//                .and()
//                .authorizeRequests()
////                    .antMatchers("/product/**").access("#oauth2.hasScope('select') and hasRole('ROLE_USER')")
//                .antMatchers("/resource/save").authenticated();//配置order访问控制，必须认证过后才可以访问
//        // @formatter:on
//    }
//
//    @Bean
//    @Qualifier("tokenStore")
//    public TokenStore tokenStore() {
//        return new JwtTokenStore(accessTokenConverter());
//    }
//
//    /**
//     * token converter
//     *
//     * @return
//     */
//    @Bean
//    public JwtAccessTokenConverter accessTokenConverter() {
//        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
////        KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource(this.keyStoreFile), this.keyStorePwd.toCharArray())
////                .getKeyPair(this.keyPair);
////                new JwtAccessTokenConverter() {
////            /***
////             * 重写增强token方法,用于自定义一些token返回的信息
////             */
////            @Override
////            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
////                String userName = authentication.getUserAuthentication().getName();
////                user user = (user) authentication.getUserAuthentication().getPrincipal();// 与登录时候放进去的UserDetail实现类一直查看link{ResourceSecurityConfiguration}
////                /** 自定义一些token属性 ***/
////                final Map<String, Object> additionalInformation = new HashMap<>();
////                additionalInformation.put("userName", userName);
////                additionalInformation.put("roles", user.getAuthorities());
////                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
////                OAuth2AccessToken enhancedToken = super.enhance(accessToken, authentication);
////                return enhancedToken;
////            }
////
////        };
//        Resource resource = new ClassPathResource(verifierKeyFile);
//        String publicKey;
//        try {
//            publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        SignatureVerifier rsaVerifier = new RsaVerifier(publicKey);
//        accessTokenConverter.setVerifier(rsaVerifier);
//        // 测试用,资源服务使用相同的字符达到一个对称加密的效果,生产时候使用RSA非对称加密方式
////        accessTokenConverter.setSigningKey("123");
//        return accessTokenConverter;
//    }
//
//    /**  */
//    public String getResourceId() {
//        return resourceId;
//    }
//
//    /**  */
//    public void setResourceId(String resourceId) {
//        this.resourceId = resourceId;
//    }
//
//    /**  */
//    public String getVerifierKeyFile() {
//        return verifierKeyFile;
//    }
//
//    /**  */
//    public void setVerifierKeyFile(String verifierKeyFile) {
//        this.verifierKeyFile = verifierKeyFile;
//    }
}
