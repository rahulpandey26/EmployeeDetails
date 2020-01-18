package com.example.employeedetails.common.util;

import com.example.employeedetails.user.model.UserResponse;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface APIInterface {

    @GET
    Call<UserResponse> getUserDetails(@Url String url,
                                      @QueryMap(encoded = true) Map<String, Integer> queries);

}
