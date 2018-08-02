package com.taras.chornyi.north.countries.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationConfigTest {

    @Autowired
    private ApplicationProperties properties;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void contextLoads() {
        assertThat(restTemplate).isNotNull();
        assertThat(mapper).isNotNull();
        assertThat(properties.getVigilanteApi()).isEqualTo("http://localhost:8888/ipvigilante/json/");
    }
}
