package com.example.employeedetails.common.ui;

import android.app.Application;
import com.facebook.drawee.backends.pipeline.Fresco;

public class UserDetailsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
