package com.hostel.payloads;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
@Builder
public class ApiResponse<T> {
    public String response;
    public boolean isSuccess;
    public HttpStatusCode httpStatusCode;
}
