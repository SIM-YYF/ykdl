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
@Rest(rootUrl = "https://ajax.googleapis.com",converters = {MappingJackson2HttpMessageConverter.class, GsonHttpMessageConverter.class, FormHttpMessageConverter.class, ByteArrayHttpMessageConverter
.class, StringHttpMessageConverter.class},interceptors = {HttpBasicAuthenticatorInterceptor.class})
public  interface TybApi extends RestClientHeaders,RestClientSupport,RestClientErrorHandling {
    @Get("/ajax/services/search/web?v=1.0&q={query}")
    String getUser(String query);
}
