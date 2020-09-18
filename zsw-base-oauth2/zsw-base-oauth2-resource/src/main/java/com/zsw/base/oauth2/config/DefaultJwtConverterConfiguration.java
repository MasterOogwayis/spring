package com.zsw.base.oauth2.config;

import com.zsw.base.oauth2.ResourceServerProperties;
import com.zsw.base.oauth2.support.AccessTokenConverterCustomizer;
import lombok.Cleanup;
import lombok.SneakyThrows;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.jwt.crypto.sign.SignatureVerifier;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;

/**
 * 配置可从缓存中获取附加信息的 jwtTokenConverter
 * 单独提出来是为了在未引入 BaseCacheDao 所属的包不报错
 *
 * @author ZhangShaowei on 2019/5/24 15:37
 **/
@Configuration
@EnableConfigurationProperties(ResourceServerProperties.class)
public class DefaultJwtConverterConfiguration {

    @Autowired
    private ResourceServerProperties properties;

    @Bean
    @SneakyThrows
    public JwtAccessTokenConverter jwtTokenEnhancer(ObjectProvider<AccessTokenConverterCustomizer> converterCustomizers) {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        String verifierKeyFile = this.properties.getVerifierKeyFile();
        Resource resource = new FileSystemResource(verifierKeyFile);
        @Cleanup InputStream inputStream = resource.getInputStream();
        String publicKey = new String(FileCopyUtils.copyToByteArray(inputStream));
        SignatureVerifier rsaVerifier = new RsaVerifier(publicKey);
        jwtAccessTokenConverter.setVerifier(rsaVerifier);

        // 增加一个自定义扩展，可以自行配置 converter
        converterCustomizers.forEach(converterCustomizer -> converterCustomizer.customize(jwtAccessTokenConverter));
        return jwtAccessTokenConverter;
    }


}
