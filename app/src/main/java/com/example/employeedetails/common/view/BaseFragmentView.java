package com.example.employeedetails.common.view;

public interface BaseFragmentView {

    void showFullScreenProgressDialog();

    void hideFullScreenProgressDialog();

    void showProgressBar();

    void hideProgressBar();


    void showNoNetworkAlert();

    void showFullScreenNetworkAlert();

    void showFullScreenError();

    // Default error display.
    void showError();
}
