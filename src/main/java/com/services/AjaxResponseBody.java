package com.services;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by nikita on 14.02.2017.
 */
public class AjaxResponseBody {

    @JsonView(Views.Public.class)
    String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "AjaxResponseBody{" +
                "result='" + result + '\'' +
                '}';
    }
}
