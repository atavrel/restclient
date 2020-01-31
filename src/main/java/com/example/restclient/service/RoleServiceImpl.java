package com.example.restclient.service;

import com.example.restclient.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class RoleServiceImpl implements RoleService {

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override // экспериментально, проверить
    public List<Role> getAll() {
        ResponseEntity<Role[]> responseEntity = restTemplate.getForEntity("http://localhost:8075/api/roles", Role[].class);
        List<Role> roles = null;
        if (!responseEntity.getStatusCode().isError()) {
            roles = Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
        }
        return roles;
    }
}