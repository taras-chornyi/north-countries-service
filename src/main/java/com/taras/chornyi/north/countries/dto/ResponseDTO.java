package com.taras.chornyi.north.countries.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * A DTO representing a response object
 *
 * @author Taras Chornyi
 */
@Data
@Builder
@ApiModel
public class ResponseDTO {

    @ApiModelProperty
    @JsonProperty(value = "northcountries")
    List<String> countries;

}
