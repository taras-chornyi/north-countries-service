package com.taras.chornyi.north.countries.service.impl;

import com.taras.chornyi.north.countries.dto.IpVigilanteDTO;
import com.taras.chornyi.north.countries.dto.ResponseDTO;
import com.taras.chornyi.north.countries.service.NorthCountriesService;
import com.taras.chornyi.north.countries.service.ServiceCompositeIntegration;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * North countries service implementation.
 *
 * @author Taras Chornyi
 */
@Slf4j
@Service
public class NorthCountriesServiceImpl implements NorthCountriesService {

    private static final Pattern PATTERN = Pattern.compile(
            "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

    @Autowired
    private ServiceCompositeIntegration serviceCompositeIntegration;

    /**
     * Get response DTO
     * @param ips list of IP addresses
     * @return response DTO
     */
    @Override
    public ResponseDTO getNorthCountries(List<String> ips) {
        if (ips.size() > 5) {
            throw new IllegalArgumentException("The endpoint should accept at least 1 and maximum 5 ip addresses");
        }

        val list = ips.stream()
                .filter(this::validate)
                .map(serviceCompositeIntegration::getCountryByIp)
                .filter(this::checkOnLatitude)
                .map(dto -> dto.getData().getCountryName())
                .distinct()
                .sorted()
                .collect(Collectors.toCollection(ArrayList::new));

        return ResponseDTO.builder().countries(list).build();
    }

    /**
     * Check on latitude value. The northern hemisphere has range from 0.0 to 90.0
     *
     * @param dto IpVigilanteDTO object
     * @return true if we have northern hemisphere latitude
     */
    private boolean checkOnLatitude(IpVigilanteDTO dto) {
        double value;
        try {
            value = Double.parseDouble(dto.getData().getLatitude());
        } catch (NumberFormatException e) {
            log.debug("Number format error: {}", e);
            value = Double.NaN;
        }
        return !Double.isNaN(value) && value > 0.0 && value <= 90.0;
    }

    /**
     * Validate on IP address
     * @param ip url
     * @return true if IP address is correct
     */
    private boolean validate(final String ip) {
        return PATTERN.matcher(ip).matches();
    }

}
