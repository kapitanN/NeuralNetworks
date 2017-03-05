package com.services;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by nikita on 14.02.2017.
 */
public class AjaxResponseBody {

    @JsonView(Views.Public.class)
    int result;

    public float getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "AjaxResponseBody{" +
                "result='" + result + '\'' +
                '}';
    }
}
