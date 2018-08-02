package com.taras.chornyi.north.countries.web.rest;

import com.taras.chornyi.north.countries.dto.IpVigilanteDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class IpVigilanteServerTest {

    @GetMapping("/ipvigilante/json/{ip}")
    public ResponseEntity getGeographicalByIp(@PathVariable String ip) {
        IpVigilanteDTO dto = getNormalDTO(ip);

        try {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    private IpVigilanteDTO getNormalDTO(String ip) {
        return IpVigilanteDTO.builder()
                .status("success")
                .data(IpVigilanteDTO.DataObject.builder()
                        .ipv4(ip)
                        .continentName("Europe")
                        .countryName("Ukraine")
                        .subdivisionName1("Kyiv City")
                        .city("Kiev")
                        .latitude("50.43330")
                        .longitude("30.51670")
                        .build())
                .build();
    }

}
