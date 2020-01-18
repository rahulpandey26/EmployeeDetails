package com.example.employeedetails.user.intractor;

import android.content.Context;
import com.example.employeedetails.common.intractor.APICallback;
import com.example.employeedetails.common.intractor.APIServiceUtil;
import com.example.employeedetails.common.util.Constant;
import com.example.employeedetails.user.model.UserResponse;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;

public class UserConnectionManager {

    public static void getScheduledMeeting(Context context,
                                           int pageNo, int perPage, APICallback<UserResponse> callback) {
        String endPoint = "";
        Map<String, Integer> queries = new HashMap<>();
        queries.put(Constant.HttpReqParamKey.PAGE, pageNo);
        queries.put(Constant.HttpReqParamKey.PER_PAGE, perPage);
        Call<UserResponse> getCoverDescriptionResponseCall =
                APIServiceUtil.getInstance(context).getApiInterface().getUserDetails(endPoint, queries);
        getCoverDescriptionResponseCall.enqueue(callback);
    }
}
