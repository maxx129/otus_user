package com.kickproject.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

@Validated
public class MainResponse<T> {
    @JsonProperty("result")
    private T result = null;

    public MainResponse result(T result) {
        this.result = result;
        return this;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
