package com.example.employeedetails.user.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.employeedetails.R;
import com.example.employeedetails.common.util.FragmentHelper;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        loadHomeFragment();
    }

    private void loadHomeFragment() {
        FragmentHelper.replaceFragment(this, HomeFragment.newInstance(),
                R.id.fragment_container, "Home_fragment");
    }
}
