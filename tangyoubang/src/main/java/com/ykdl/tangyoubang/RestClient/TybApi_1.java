package com.ykdl.tangyoubang.RestClient;

import com.ykdl.tangyoubang.RestClient.interceptor.HttpBasicAuthenticatorInterceptor;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientErrorHandling;
import org.androidannotations.api.rest.RestClientHeaders;
import org.androidannotations.api.rest.RestClientRootUrl;
import org.androidannotations.api.rest.RestClientSupport;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Created by yuanwenfei on 2014/5/9.
 */
@Rest(rootUrl = "https://ajax.googleapis.com",converters = {MappingJackson2HttpMessageConverter.class, GsonHttpMessageConverter.class, FormHttpMessageConverter.class, ByteArrayHttpMessageConverter
.class, StringHttpMessageConverter.class},interceptors = {HttpBasicAuthenticatorInterceptor.class})
public  interface TybApi_1 extends RestClientRootUrl, RestClientHeaders,RestClientSupport,RestClientErrorHandling {

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
    @Get("/ajax/services/search/web?v=1.0&q={query}")
    String getUser(String query);

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
