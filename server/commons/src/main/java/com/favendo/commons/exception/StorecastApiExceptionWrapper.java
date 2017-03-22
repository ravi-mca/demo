package com.favendo.commons.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "error", "error_description", "error_field" })
public class StorecastApiExceptionWrapper {

	@JsonProperty(value = "error")
	private String error;

	@JsonProperty(value = "error_description")
	private String errorDescription;

	@JsonProperty(value = "error_field")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String errorField;

	@JsonProperty(value = "code")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String code;

	@JsonProperty(value = "message")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String message;

	public StorecastApiExceptionWrapper() {
		super();
	}


	public StorecastApiExceptionWrapper(final String error) {
		this.error = error;
	}

	public StorecastApiExceptionWrapper(final String error, final String errorDescription) {
		this.error = error;
		this.errorDescription = errorDescription;
	}

	public StorecastApiExceptionWrapper(final String error, final String errorDescription, final String errorField) {
		this.error = error;
		this.errorDescription = errorDescription;
		this.errorField = errorField;
	}

	public StorecastApiExceptionWrapper(final String error, final String errorDescription, final String code,
										final String message) {
		this.error = error;
		this.errorDescription = errorDescription;
		this.code = code;
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(final String error) {
		this.error = error;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(final String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public String getErrorField() {
		return errorField;
	}

	public void setErrorField(final String errorField) {
		this.errorField = errorField;
	}
}
