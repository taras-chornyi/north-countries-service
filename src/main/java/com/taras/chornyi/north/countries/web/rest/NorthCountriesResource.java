package com.taras.chornyi.north.countries.web.rest;

import com.taras.chornyi.north.countries.service.NorthCountriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;          

/**
 * Controller for view countries by IP address.
 *
 * @author Taras Chornyi
 */
@Slf4j
@RestController
@RequestMapping("/")
public class NorthCountriesResource {

    private final NorthCountriesService northCountriesService;

    public NorthCountriesResource(NorthCountriesService northCountriesService) {
        this.northCountriesService = northCountriesService;
    }

    @GetMapping("/northcountries")
    public ResponseEntity getCountriesByIPAddress(@RequestParam(value="ip") List<String> ips) {
        log.info("REST request to get a list of countries {}", ips);
        try {
            return new ResponseEntity<>(northCountriesService.getNorthCountries(ips), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
