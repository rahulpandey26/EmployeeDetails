package com.example.employeedetails.user.presenter;

import android.content.Context;
import com.example.employeedetails.common.intractor.APICallback;
import com.example.employeedetails.common.presenter.BasePresenter;
import com.example.employeedetails.common.util.Constant;
import com.example.employeedetails.common.view.BaseFragmentView;
import com.example.employeedetails.user.intractor.UserConnectionManager;
import com.example.employeedetails.user.model.UserResponse;
import com.example.employeedetails.user.view.HomeFragmentView;

public class UsersDetailsPresenter extends BasePresenter {

    public UsersDetailsPresenter(BaseFragmentView view) {
        super(view);
    }

    public void getUsersDetails(Context context, int pageNo, int perPage) {
        if (!isNetworkAvailable(context)) {
            showNetworkAlert();
            return;
        }

        UserConnectionManager.getScheduledMeeting(context, pageNo, perPage,
                new APICallback<UserResponse>(context) {

                    @Override
                    public void onResponseSuccess(UserResponse response) {
                        HomeFragmentView view = (HomeFragmentView) getView();
                        if (view == null) {
                            return;
                        }
                        view.onUserListDownloaded(response);
                    }

                    @Override
                    public void onResponseFailure(String errorMessage) {
                         handleError(Constant.UNKNOWN_ERROR);
                    }
                });
    }
}
