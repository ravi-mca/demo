package com.favendo.user.ws.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthenticationFailureDto {

	@JsonProperty("errorMesesag")
	private String errorMesesag;

	public String getErrorMesesag() {
		return errorMesesag;
	}

	public void setErrorMesesag(String errorMesesag) {
		this.errorMesesag = errorMesesag;
	}
}
