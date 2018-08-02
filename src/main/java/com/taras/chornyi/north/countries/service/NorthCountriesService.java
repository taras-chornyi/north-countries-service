package com.taras.chornyi.north.countries.service;

import com.taras.chornyi.north.countries.dto.ResponseDTO;

import java.util.List;

/**
 * North countries service interface
 *
 * @author Taras Chornyi
 */
public interface NorthCountriesService {

    ResponseDTO getNorthCountries(List<String> ips);

}
