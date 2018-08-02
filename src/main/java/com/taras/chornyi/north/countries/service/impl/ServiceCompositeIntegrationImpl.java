package com.taras.chornyi.north.countries.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taras.chornyi.north.countries.config.ApplicationProperties;
import com.taras.chornyi.north.countries.dto.IpVigilanteDTO;
import com.taras.chornyi.north.countries.service.ServiceCompositeIntegration;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;

import java.io.IOException;

/**
 * Service data composite service which used as client for the Vigilante API.
 *
 * @author Taras Chornyi
 */
@Slf4j
@Service
public class ServiceCompositeIntegrationImpl implements ServiceCompositeIntegration {

    @Autowired
    private ApplicationProperties properties;

    @Autowired
    private RestOperations restTemplate;

    @Autowired
    private ObjectMapper mapper;
    
    @Override
    public IpVigilanteDTO getCountryByIp(String ip) {
        val url = properties.getVigilanteApi() + ip;
        log.debug("Get data from URL: {}", url);

        try {
            val response = restTemplate.getForEntity(url, String.class);
            return mapper.readValue(response.getBody(), new TypeReference<IpVigilanteDTO>() {});
        } catch (HttpClientErrorException | IOException e) {
            log.debug("Error during receive data from external service {}", e.getMessage());
            throw new IllegalArgumentException("Error! Wrong address of the external service: " + properties.getVigilanteApi());
        }
    }

}
