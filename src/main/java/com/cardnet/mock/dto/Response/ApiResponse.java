package com.cardnet.mock.dto.Response;

import java.util.ArrayList;
import java.util.List;

public class ApiResponse<T> {

    public T Response;
    public List<Object> Errors;

    public ApiResponse(T response) {
        this.Response = response;
        this.Errors = new ArrayList<>();
    }
}
