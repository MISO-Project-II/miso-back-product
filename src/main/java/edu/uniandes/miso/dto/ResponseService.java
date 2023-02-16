package edu.uniandes.miso.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseService {
    private String message;
    private Boolean success;
    private Object result;
    private Long idSport;

}
