package com.taras.chornyi.north.countries.service;

import com.taras.chornyi.north.countries.dto.IpVigilanteDTO;

public interface ServiceCompositeIntegration {

    IpVigilanteDTO getCountryByIp(String ip);
    
}
