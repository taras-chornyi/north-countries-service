package com.taras.chornyi.north.countries.web.rest;

import com.taras.chornyi.north.countries.Application;
import com.taras.chornyi.north.countries.config.ApplicationProperties;
import com.taras.chornyi.north.countries.service.NorthCountriesService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class NorthCountriesResourceTest {

    @Autowired
    private ApplicationProperties properties;

    @Autowired
    private NorthCountriesService northCountriesService;

    private MockMvc restNorthCountriesMockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NorthCountriesResource northCountriesResource =
                new NorthCountriesResource(northCountriesService);
        this.restNorthCountriesMockMvc = MockMvcBuilders.standaloneSetup(northCountriesResource).build();
    }

    @Test
    public void testGetCountriesByIPAddress() throws Exception {
        restNorthCountriesMockMvc.perform(get("/northcountries?ip=1.1.1.1&ip=2.2.2.2&ip=3.3.3.3&ip=4.4.4.4&ip=5.5.5.5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.northcountries").value(hasItem("Ukraine")));
    }

    @Test
    public void testGetCountriesWithoutIP() throws Exception {
        restNorthCountriesMockMvc.perform(get("/northcountries"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetCountriesWithUpperLimitOfIp() throws Exception {
        restNorthCountriesMockMvc.perform(get("/northcountries?ip=1&ip=2&ip=3&ip=4&ip=5&ip=6"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetCountriesWithWrongIp() throws Exception {
        restNorthCountriesMockMvc.perform(get("/northcountries?ip=1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.northcountries").isEmpty());
    }

}
