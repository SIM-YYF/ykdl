package com.ykdl.net.Client;


import com.ykdl.net.msconverters.MFormHttpMessageConverter;
import com.ykdl.net.msconverters.MGsonHttpMessageConverter;
import com.ykdl.net.msconverters.MMappingJackson2HttpMessageConverter;
import com.ykdl.net.msconverters.MStringHttpMessageConverter;

import org.springframework.http.ContentCodingType;
import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.InterceptingClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by yuanwenfei on 2014/5/27.
 */
public class Rest {
    public static final String ENCODING = "UTF-8";
    private static HttpHeaders httpBaseHeaders;
    private static HttpAuthentication authHeader;
    private static HttpEntity<?> requestEntity;
    private HttpHeaders requestHeaders;
    private static RestTemplate restTemplate;

    private HttpRequestCommHeaders callback;

    public Rest() {
        initializeAuth();
        initializeHeaders();
        initRestTemplate();
    }

    /**
     * 设置认证信息
     */
    private void initializeAuth() {
        authHeader = new HttpBasicAuthentication("username", "password");
    }

    /**
     * 初始化基本头信息
     */
    private void initializeHeaders() {
        httpBaseHeaders = new HttpHeaders();
//        httpHeaders.setAuthorization(authHeader);
//        httpBaseHeaders.setContentType(MediaType.ALL);
//        httpBaseHeaders.setAccept(Collections.singletonList(MediaType.ALL));
//        httpBaseHeaders.setAcceptEncoding(ContentCodingType.ALL);
    }

    /**
     * 初始化设置公用头信息回调
     */
    public void setHttpRequestCommHeaders(HttpRequestCommHeaders callback){
        this.callback = callback;
    }
    /**
     * 添加附加的头信息
     */
    public void addHttpHeaders(RequestHeaders headers){
        requestHeaders = new HttpHeaders();
        requestHeaders.putAll(headers.httpHeaders);
    }

    private HttpEntity httpEntity() {
        requestEntity = new HttpEntity<Object>(httpBaseHeaders);
        return requestEntity;
    }

    private void initRestTemplate() {
        final ClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                ClientHttpRequest delegate = factory.createRequest(request.getURI(), request.getMethod());
                delegate.getHeaders().putAll(request.getHeaders());
               // delegate.getHeaders().putAll(callback.getCommHeaders());//设置公用的头信息
              //  delegate.getHeaders().putAll(requestHeaders);//设置请求的头信息
                if (body.length > 0) {
                    FileCopyUtils.copy(body, delegate.getBody());
                }
                return delegate.execute();
            }
        });
        restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new InterceptingClientHttpRequestFactory(factory, interceptors));
        List<HttpMessageConverter<?>> mcs = new ArrayList<HttpMessageConverter<?>>();
        MFormHttpMessageConverter formHttpMessageConverter = new MFormHttpMessageConverter();
        MGsonHttpMessageConverter gsonHttpMessageConverter = new MGsonHttpMessageConverter();
        MMappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MMappingJackson2HttpMessageConverter();
        MStringHttpMessageConverter stringHttpMessageConverter = new MStringHttpMessageConverter(Charset.forName(ENCODING));
        stringHttpMessageConverter.setWriteAcceptCharset(false);
        mcs.add(gsonHttpMessageConverter);
        mcs.add(mappingJackson2HttpMessageConverter);
        mcs.add(stringHttpMessageConverter);
        formHttpMessageConverter.setPartConverters(mcs);
        mcs.add(formHttpMessageConverter);
        restTemplate.getMessageConverters().addAll(mcs);
        restTemplate.setErrorHandler(new MResponseErrorHandler());

    }

    ///  get  ///
    public <T> ResponseEntity<T> get(String url, Class<T> aClass, RequestParams requestParams) throws RestClientException {
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity(), aClass, requestParams.multiValueMap);
        return response;
    }
    public <T> ResponseEntity<T> get(String url, Class<T> aClass,RequestHeaders headers, RequestParams requestParams) throws RestClientException {
        addHttpHeaders(headers);
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity(), aClass, requestParams.multiValueMap);
        return response;
    }
    public <T> ResponseEntity<T> get(String url, Class<T> aClass, RequestHeaders headers, Object... uriVariables) throws RestClientException {
        addHttpHeaders(headers);
        ResponseEntity<T>  response = restTemplate.exchange(url, HttpMethod.GET, httpEntity(), aClass, uriVariables);
        return response;
    }
    public <T> ResponseEntity<T> get(String url, Class<T> aClass, Object... uriVariables) throws RestClientException {
        ResponseEntity<T>  response = restTemplate.exchange(url, HttpMethod.GET, httpEntity(), aClass, uriVariables);
        return response;
    }

    ///  post  ///
    public <T> ResponseEntity<T> post(String url, Class<T> aClass, RequestParams requestParams) throws RestClientException {
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity(), aClass, requestParams.multiValueMap);
        return response;
    }
    public <T> ResponseEntity<T> post(String url, Class<T> aClass,RequestHeaders headers, RequestParams requestParams) throws RestClientException {
        addHttpHeaders(headers);
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity(), aClass, requestParams.multiValueMap);
        return response;
    }
    public <T> ResponseEntity<T> post(String url, Class<T> aClass, Object... uriVariables) throws RestClientException {
        ResponseEntity<T>  response = restTemplate.exchange(url, HttpMethod.POST, httpEntity(), aClass, uriVariables);
        return response;
    }
    public <T> ResponseEntity<T> post(String url, Class<T> aClass, RequestHeaders headers, Object... uriVariables) throws RestClientException {
        addHttpHeaders(headers);
        ResponseEntity<T>  response = restTemplate.exchange(url, HttpMethod.POST, httpEntity(), aClass, uriVariables);
        return response;
    }


    /// put  ///
    public <T> ResponseEntity<T> put(String url, Class<T> aClass,RequestHeaders headers, RequestParams requestParams) throws RestClientException {
        addHttpHeaders(headers);
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity(), aClass, requestParams.multiValueMap);
        return response;
    }
    public <T> ResponseEntity<T> put(String url, Class<T> aClass,RequestHeaders headers, Object... uriVariables) throws RestClientException {
        addHttpHeaders(headers);
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity(), aClass, uriVariables);
        return response;
    }
    public <T> ResponseEntity<T> put(String url, Class<T> aClass, RequestParams requestParams) throws RestClientException {
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity(), aClass, requestParams.multiValueMap);
        return response;
    }
    public <T> ResponseEntity<T> put(String url, Class<T> aClass, Object... uriVariables) throws RestClientException {
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity(), aClass, uriVariables);
        return response;
    }


    ///  delete  ///

    public <T> ResponseEntity<T> delete(String url, Class<T> aClass,RequestHeaders headers, RequestParams requestParams) throws RestClientException {
        addHttpHeaders(headers);
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.DELETE, httpEntity(), aClass, requestParams.multiValueMap);
        return response;
    }

    public <T> ResponseEntity<T> delete(String url, Class<T> aClass,RequestHeaders headers, Object... uriVariables) throws RestClientException {
        addHttpHeaders(headers);
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.DELETE, httpEntity(), aClass, uriVariables);
        return response;
    }

    public <T> ResponseEntity<T> delete(String url, Class<T> aClass, RequestParams requestParams) throws RestClientException {
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.DELETE, httpEntity(), aClass, requestParams.multiValueMap);
        return response;
    }

    public <T> ResponseEntity<T> delete(String url, Class<T> aClass, Object... uriVariables) throws RestClientException {
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.DELETE, httpEntity(), aClass, uriVariables);
        return response;
    }



}
