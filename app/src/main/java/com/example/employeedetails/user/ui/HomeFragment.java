package com.example.employeedetails.user.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.example.employeedetails.R;
import com.example.employeedetails.common.ui.fragment.BaseFragment;
import com.example.employeedetails.user.adapter.UsersListAdapter;
import com.example.employeedetails.user.model.UserResponse;
import com.example.employeedetails.user.model.Users;
import com.example.employeedetails.user.presenter.UsersDetailsPresenter;
import com.example.employeedetails.user.view.HomeFragmentView;
import com.facebook.shimmer.ShimmerFrameLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import butterknife.BindView;

public class HomeFragment extends BaseFragment implements HomeFragmentView {

    @BindView(R.id.swipe_refresh) SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.user_list) RecyclerView mRecyclerView;
    @BindView(R.id.loader) View mLoader;
    @BindView(R.id.user_details_loader)  ShimmerFrameLayout mUserDetailsShimmer;
    private UserResponse mUserResponse;
    private List<Users> mUserList = new ArrayList<>();
    private boolean mIsLoading = false;

    static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setLayout(R.layout.fragment_home);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeListeners();
        setPresenter(new UsersDetailsPresenter(this));
        getUsersList(1, 5, false);
        initScrollListener();
    }

    private void initializeListeners() {
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            getUsersList(1, 5, false);
            mUserList.clear();
            mSwipeRefreshLayout.setRefreshing(false);
        });
    }

    private void getUsersList(int pageNo, int perPage, boolean isFromMoreData) {
        if(!isFromMoreData) {
            mUserDetailsShimmer.setVisibility(View.VISIBLE);
        }
        new UsersDetailsPresenter(this).getUsersDetails(getContext(), pageNo, perPage);
    }

    private void setAdapter() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setAdapter(new UsersListAdapter(mUserList));
    }

    private void initScrollListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!mIsLoading && null != linearLayoutManager) {
                    int visibleItemCount = linearLayoutManager.getChildCount();
                    int totalItemCount = linearLayoutManager.getItemCount();
                    int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
                        //bottom of list, so load more data.
                        loadMoreUsers();
                        mLoader.setVisibility(View.VISIBLE);
                        mIsLoading = true;
                    }
                }
            }
        });
    }

    private void loadMoreUsers() {
        int pageNo = mUserResponse.getPage() + 1;
        if(pageNo <= mUserResponse.getTotalPages()) {
            getUsersList(pageNo, mUserResponse.getPerPage(), true);
        }
    }

    @Override
    public void onUserListDownloaded(UserResponse userResponse) {
        mUserDetailsShimmer.setVisibility(View.GONE);
        mUserResponse = userResponse;
        mUserList.addAll(userResponse.getUserData());
        setAdapter();
        mLoader.setVisibility(View.GONE);
        mIsLoading = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        mUserDetailsShimmer.startShimmerAnimation();
    }

    @Override
    public void onPause() {
        super.onPause();
        mUserDetailsShimmer.stopShimmerAnimation();
    }
}
