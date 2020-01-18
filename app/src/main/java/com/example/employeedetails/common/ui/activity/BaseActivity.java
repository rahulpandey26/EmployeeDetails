package com.example.employeedetails.common.ui.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.example.employeedetails.R;
import butterknife.BindView;
import butterknife.ButterKnife;


public abstract class BaseActivity extends AppCompatActivity {

    @BindView(R.id.progress_bar_container)  FrameLayout mProgressbarContainer;
    @BindView(R.id.layout_container) FrameLayout mFragmentLayoutContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_base_activity);
        ButterKnife.bind(this);
        getLayoutInflater().inflate(getLayoutResourceId(), mFragmentLayoutContainer);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    protected abstract int getLayoutResourceId();

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void showProgressBar() {
        if (mProgressbarContainer != null) {
            mProgressbarContainer.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgressBar() {
        if (mProgressbarContainer != null) {
            mProgressbarContainer.setVisibility(View.GONE);
        }
    }
}
