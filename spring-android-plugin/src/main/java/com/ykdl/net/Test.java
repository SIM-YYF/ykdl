package com.ykdl.net;

import android.os.Message;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentCodingType;
import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.SimpleXmlHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by yuanwenfei on 2014/5/27.
 */
public class Test {
    String url = "https://ajax.googleapis.com/ajax/services/search/web?v=1.0&q={query}";
    public void a() {

// Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();
// Add the String message converter
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
// Make the HTTP GET request, marshaling the response to a String
        String result = restTemplate.getForObject(url, String.class, "SpringSource");

    }


    public void gzip(){
        // Add the gzip Accept-Encoding header
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAcceptEncoding(ContentCodingType.GZIP);
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

// Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

// Add the String message converter
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

// Make the HTTP GET request, marshaling the response to a String
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

    }


    public void Retrieving_JSON_data_via_HTTP_GET(){
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAccept(Collections.singletonList(new MediaType("application", "json")));
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

// Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

// Add the Gson message converter
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
//        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());


// Make the HTTP GET request, marshaling the response from JSON to an array of Events
        ResponseEntity<Event[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Event[].class);
        Event[] events = responseEntity.getBody();
    }

    public  void Send_JSON_data_via_HTTP_POST(){
        // Create and populate a simple object to be used in the request
        Message message = new Message();
        message.setId(555);
        message.setSubject("test subject");
        message.setText("test text");

// Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

// Add the Jackson and String message converters
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

// Make the HTTP POST request, marshaling the request to JSON, and the response to a String
        String response = restTemplate.postForObject(url, message, String.class);

    }

    public  void Send_JSON_data_with_header_via_HTTP_POST(){
        // Create and populate a simple object to be used in the request
        Message message = new Message();
        message.setId(555);
        message.setSubject("test subject");
        message.setText("test text");

// Set the Content-Type header
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(new MediaType("application","json"));
        HttpEntity<Message> requestEntity = new HttpEntity<Message>(message, requestHeaders);

// Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

// Add the Jackson and String message converters
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

// Make the HTTP POST request, marshaling the request to JSON, and the response to a String
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        String result = responseEntity.getBody();
    }

    public void Http_Post_MultiValueMap(){
        final String url = getString(R.string.base_uri) + "/sendmessagemap";
        MultiValueMap<String, String>  message = new LinkedMultiValueMap<String, String>();
        message.add("id", editText.getText().toString());
        message.add("subject", editText.getText().toString());
        message.add("text", editText.getText().toString());


        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate(true);

        // Make the network request, posting the message, expecting a String in response from the server
        ResponseEntity<String> response = restTemplate.postForEntity(url, message, String.class);

        // Return the response body to display to the user
        return response.getBody();
    }
   public void Http_Post_String_Data(){
       final String url = getString(R.string.base_uri) + "/sendmessage";
       // Create a new RestTemplate instance
       RestTemplate restTemplate = new RestTemplate();
       restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
       // Make the network request, posting the message, expecting a String in response from the server
       String response = restTemplate.postForObject(url, text, String.class);
       // Return the response body to display to the user
       return response;
   }
   public void Http_Post_Form_Data(){
       Resource resource = new ClassPathResource("res/drawable/spring09_logo.png");
       // populate the data to post
       MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
       formData.add("description", "Spring logo");
       formData.add("file", resource);


       HttpHeaders requestHeaders = new HttpHeaders();

       // Sending multipart/form-data
       requestHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

       // Populate the MultiValueMap being serialized and headers in an HttpEntity object to use for the request
       HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(
               formData, requestHeaders);

       // Create a new RestTemplate instance
       RestTemplate restTemplate = new RestTemplate(true);

       // Make the network request, posting the message, expecting a String in response from the server
       ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity,
               String.class);
       // Return the response body to display to the user
       return response.getBody();
   }
   public void HTTP_Basic_Authentication(){
       // Set the username and password for creating a Basic Auth request
       HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
       HttpHeaders requestHeaders = new HttpHeaders();
       requestHeaders.setAuthorization(authHeader);
       HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

// Create a new RestTemplate instance
       RestTemplate restTemplate = new RestTemplate();

// Add the String message converter
       restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

       try {
           // Make the HTTP GET request to the Basic Auth protected URL
           ResponseEntity<Message> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

           return response.getBody();
       } catch (HttpClientErrorException e) {
//           Log.e(TAG, e.getLocalizedMessage(), e);
           // Handle 401 Unauthorized response
       }

   }

    public void Http_Components_ClientHttpRequestFactory_set_timeout(){
        final String url = getString(R.string.base_uri) + "/delay/{seconds}";
        // Initialize a request factory, setting the request timeout
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setReadTimeout(requestTimeout);
        requestFactory.setConnectTimeout(connectTimeout);
        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        // Perform the HTTP GET request
        String response = restTemplate.getForObject(url, String.class, serverDelay);
        // Return the state from the ResponseEntity
        return response;
    }

    public void Http_Get_Response_Data(){
        final String url = "http://search.twitter.com/search.json?q={query}&rpp=100";

        // Add the gzip Accept-Encoding header to the request
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAcceptEncoding(ContentCodingType.GZIP);

        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        // Perform the HTTP GET request
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(
                requestHeaders), String.class, "SpringSource");

        HttpHeaders headers = response.getHeaders();
        StringBuilder sb = new StringBuilder();
        sb.append("Date: ").append(headers.getFirst("Date")).append("\n");
        sb.append("Status: ").append(headers.getFirst("Status")).append("\n");
        sb.append("Content-Type: ").append(headers.getFirst("Content-Type")).append("\n");
        sb.append("Content-Encoding: ").append(headers.getFirst("Content-Encoding")).append("\n");
        sb.append("Content-Length: ").append(headers.getFirst("Content-Length")).append("\n");

        String results = response.getBody() + "\n";

    }


    public void Http_Get_For_MediaType_Paramters(){
        MediaType mediaType = params[0];
        // The URL for making the GET request
        final String url = getString(R.string.base_uri) + "/state/{abbreviation}";
        // Set the Accept header for "application/json" or "application/xml"
        HttpHeaders requestHeaders = new HttpHeaders();
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(mediaType);
        requestHeaders.setAccept(acceptableMediaTypes);
        // Populate the headers in an HttpEntity object to use for the request
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();
        if (mediaType.equals(MediaType.APPLICATION_JSON)) {
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        } else if (mediaType.equals(MediaType.APPLICATION_XML)) {
            restTemplate.getMessageConverters().add(new SimpleXmlHttpMessageConverter());
        }

        // Perform the HTTP GET request
        ResponseEntity<State> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
                State.class, abbreviation);

        // Return the state from the ResponseEntity
        return responseEntity.getBody();
    }

    public void Http_Authentication{
        // Populate the HTTP Basic Authentitcation header with the username and password
        HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAuthorization(authHeader);
        requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        FormHttpMessageConverter  formHttpMessageConverter = new FormHttpMessageConverter();
        // Create a new RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        try {
            // Make the network request
//            Log.d(TAG, url);
            ResponseEntity<Message> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(requestHeaders), Message.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
//            Log.e(TAG, e.getLocalizedMessage(), e);
            return new Message(0, e.getStatusText(), e.getLocalizedMessage());
        } catch (ResourceAccessException e) {
//            Log.e(TAG, e.getLocalizedMessage(), e);
            return new Message(0, e.getClass().getSimpleName(), e.getLocalizedMessage());
        }
    }

}
