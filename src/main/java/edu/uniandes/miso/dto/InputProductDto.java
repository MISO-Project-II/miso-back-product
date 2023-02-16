package edu.uniandes.miso.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InputProductDto {
    private Long idSport;
    private Long idUserCreator;
    private String name;
    private String description;
}
