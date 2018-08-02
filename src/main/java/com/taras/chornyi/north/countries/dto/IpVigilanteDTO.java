package com.taras.chornyi.north.countries.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IpVigilanteDTO {

    private String status;
    @JsonProperty(value = "data")
    private DataObject data;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataObject {
        @JsonProperty(value = "ipv4")
        private String ipv4;
        @JsonProperty(value = "continent_name")
        private String continentName;
        @JsonProperty(value = "country_name")
        private String countryName;
        @JsonProperty(value = "subdivision_1_name")
        private String subdivisionName1;
        @JsonProperty(value = "subdivision_2_name")
        private String subdivisionName2;
        @JsonProperty(value = "city_name")
        private String city;
        @JsonProperty(value = "latitude")
        private String latitude;
        @JsonProperty(value = "longitude")
        private String longitude;
    }

}
