package com.ykdl.tangyoubang.RestClient;

import com.ykdl.tangyoubang.RestClient.interceptor.HttpBasicAuthenticatorInterceptor;

import org.androidannotations.annotations.rest.Accept;
import org.androidannotations.annotations.rest.Delete;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Head;
import org.androidannotations.annotations.rest.Options;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Put;
import org.androidannotations.annotations.rest.RequiresAuthentication;
import org.androidannotations.annotations.rest.RequiresCookie;
import org.androidannotations.annotations.rest.RequiresCookieInUrl;
import org.androidannotations.annotations.rest.RequiresHeader;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.annotations.rest.SetsCookie;
import org.androidannotations.api.rest.MediaType;
import org.androidannotations.api.rest.RestClientErrorHandling;
import org.androidannotations.api.rest.RestClientHeaders;
import org.androidannotations.api.rest.RestClientRootUrl;
import org.androidannotations.api.rest.RestClientSupport;
import org.androidannotations.api.rest.RestErrorHandler;
import org.springframework.http.ContentCodingType;
import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Set;

/**
 * Created by yuanwenfei on 2014/5/9.
 */
@Rest(rootUrl = "www.baidu.com",converters = {MappingJackson2HttpMessageConverter.class, MappingJacksonHttpMessageConverter.class, GsonHttpMessageConverter.class, FormHttpMessageConverter.class, ByteArrayHttpMessageConverter
.class, StringHttpMessageConverter.class},interceptors = {HttpBasicAuthenticatorInterceptor.class})
public  interface TybApi extends RestClientRootUrl, RestClientHeaders,RestClientSupport,RestClientErrorHandling {

/*    @Get("/events/{id}")
    @Post("/events?key={id}")
    @Put("/events?key={id}")
    @Delete("/events?key={id}")*/
//    @Accept(MediaType.ALL) //注意：@Accept 只能用于 @Get and @Post 方法
//    @Accept(MediaType.MULTIPART_FORM_DATA) //注意：@Accept 只能用于 @Get and @Post 方法 .用户表单上传文件


//    @RequiresHeader({"head1", "head2"})
//    @SetsCookie({"username", "password"})
//    @RequiresAuthentication  //注意：设置它的同时，需要创建setAuthentication(HttpAuthentication httpAuthentication) 或者 setHttpBasicAuth(String username, String password)
//    @RequiresCookie({"session"})
//    @RequiresCookieInUrl({"session"})//将设置的Cookie追加到url中，来代替添加到header
    @Get("")
    String getUser();

    /**
     * Send a HEAD HTTP Method request. @Head annotated methods must return HttpHeaders.
     * @return
     */
//    @Head("/events")
//    HttpHeaders getEventHeader();
//    @Head("/events/{year}/{location}")
//    HttpHeaders getEventHeader2(String location, int year);

    /**
     * Send a OPTIONS HTTP Method request. @Options annotated methods must return a set of HttpMethod
     * @return
     */
//    @Options("/events")
//    Set<HttpMethod> getEventOptions();
//    @Options("/events/{year}/{location}")
//    Set<HttpMethod> getEventOptions(String location, int year);

    /**
     * You can also return a ResponseEntity parameterized by the expected result type, which gives you access to to the response context. For instance,
     * it could be useful to check the HTTP headers of the response.
     * @param location
     * @param year
     * @return
     */
//    @Get("/events/{year}/{location}")
//    ResponseEntity<String> getEvents(String location, int year);




    /******************************华丽的分割线**********************************/
//    @Override
//    void setRootUrl(String s);
//    @Override
//    String getRootUrl();
//
//    @Override
//    void setRestTemplate(RestTemplate restTemplate);
//    @Override
//    RestTemplate getRestTemplate();
//
//    @Override
//    void setHeader(String s, String s2);
//    @Override
//    String getHeader(String s);
//
//    @Override
//    void setAuthentication(HttpAuthentication httpAuthentication);
//
//    @Override
//    void setHttpBasicAuth(String username, String password);
//
//    @Override
//    void setCookie(String s, String s2);





}
