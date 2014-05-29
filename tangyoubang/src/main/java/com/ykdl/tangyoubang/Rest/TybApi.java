package com.ykdl.tangyoubang.Rest;

import com.ykdl.net.msconverters.MFormHttpMessageConverter;
import com.ykdl.net.msconverters.MGsonHttpMessageConverter;
import com.ykdl.net.msconverters.MMappingJackson2HttpMessageConverter;
import com.ykdl.net.msconverters.MStringHttpMessageConverter;
import com.ykdl.tangyoubang.Rest.interceptor.HttpBasicAuthenticatorInterceptor;
import com.ykdl.tangyoubang.model.protocol.Captcha;

import org.androidannotations.annotations.rest.Accept;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.MediaType;
import org.androidannotations.api.rest.RestClientErrorHandling;
import org.androidannotations.api.rest.RestClientHeaders;
import org.androidannotations.api.rest.RestClientSupport;
import org.androidannotations.api.rest.RestErrorHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by yuanwenfei on 2014/5/9.
 */
@Rest(rootUrl = "http://172.16.22.68:5000",converters = {MMappingJackson2HttpMessageConverter.class, MGsonHttpMessageConverter.class, MFormHttpMessageConverter.class, ByteArrayHttpMessageConverter
.class, MStringHttpMessageConverter.class},interceptors = {HttpBasicAuthenticatorInterceptor.class})
public  interface TybApi extends RestClientHeaders,RestClientSupport,RestClientErrorHandling {
    @Get("/tyb/api/v1/captcha/request")
    Captcha get_captcha();//请求验证码
    @Get("/tyb/api/v1/captcha/show/{captcha_id}")
    @Accept(MediaType.IMAGE_JPEG)
    byte[] show_captcha(String captcha_id);//显示验证码图片流接口
    @Post("tyb/api/v1/captcha/verify?captcha_id={captcha_id}&code={code}")
    ResponseEntity<String> verify_captcha(String captcha_id, String code);//验证验证码接口


}
