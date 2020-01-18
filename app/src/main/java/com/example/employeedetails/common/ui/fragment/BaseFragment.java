package com.example.employeedetails.common.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.employeedetails.R;
import com.example.employeedetails.common.presenter.BasePresenter;
import com.example.employeedetails.common.ui.activity.BaseActivity;
import com.example.employeedetails.common.view.BaseFragmentView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BaseFragment extends Fragment implements BaseFragmentView {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.progress_bar_container)  View mViewProgressBarContainer;
    @BindView(R.id.network_error_layout)  View mNetworkErrorLayout;
    private int mLayoutId;
    private BasePresenter mPresenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        FrameLayout fragmentLayoutContainer = view.findViewById(R.id.layout_container);
        inflater.inflate(mLayoutId, fragmentLayoutContainer);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    protected void setLayout(int layoutId) {
        mLayoutId = layoutId;
    }

    @Override
    public void showFullScreenProgressDialog() {
        if (getActivity() != null && getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).showProgressBar();
        }
    }

    @Override
    public void hideFullScreenProgressDialog() {
         if (getActivity() != null && getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).hideProgressBar();
        }
    }

    @Override
    public void showNoNetworkAlert() {
        if (getActivity() != null && !getActivity().isDestroyed()) {
            hideFullScreenProgressDialog();
            hideProgressBar();
            mNetworkErrorLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showFullScreenNetworkAlert() {

    }

    @Override
    public void showFullScreenError() {

    }

    public int isNetWorkAlertVisible() {
        return mNetworkErrorLayout.getVisibility();
    }

    @Override
    public void showProgressBar() {
        if (mViewProgressBarContainer != null) {
            mViewProgressBarContainer.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgressBar() {
        if (mViewProgressBarContainer != null) {
            mViewProgressBarContainer.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError() {
        hideFullScreenProgressDialog();
        hideProgressBar();
    }

    public void showServerError() {
        hideFullScreenProgressDialog();
        hideProgressBar();
    }

    public BasePresenter getPresenter() {
        return mPresenter;
    }

    public void setPresenter(BasePresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        hideFullScreenProgressDialog();
        super.onDestroy();
    }
}
