package com.mikejuliet.java_backend.services.service_impl;

import com.mikejuliet.java_backend.services.PythonAPI_interfaceService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class pythonApiInterfaceImpl implements PythonAPI_interfaceService {

    private static HttpEntity<MultiValueMap<String,String>> requestEntity ;


    private static HttpMethod method ;
    private static HttpHeaders headers = new HttpHeaders();

    private final RestTemplate restTemplate;



    public pythonApiInterfaceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void emailLogin(String email, String password) {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("email", email);
        multiValueMap.add("password", password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);  // Use FORM_URLENCODED for form data

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(multiValueMap, headers);

        restTemplate.exchange("http://127.0.0.1:9001/python-scripts/email-login/email/" + email + "/password/" + password, HttpMethod.POST, requestEntity, String.class);
    }
}
