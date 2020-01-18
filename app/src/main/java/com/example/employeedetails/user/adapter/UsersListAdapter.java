package com.example.employeedetails.user.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.employeedetails.R;
import com.example.employeedetails.user.model.Users;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UsersListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Users> mUsersList;

    public UsersListAdapter(List<Users> results) {
        mUsersList = results;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_item_layout, parent, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        setRestaurantView((UserViewHolder) holder, position);
    }

    @Override
    public int getItemCount() {
        return mUsersList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title) TextView mTitle;
        @BindView(R.id.email_id) TextView mEmailId;
        @BindView(R.id.user_image) SimpleDraweeView mUserImage;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private void setRestaurantView(UserViewHolder userViewHolder, final int position) {
        if (mUsersList.size() > 0) {
            userViewHolder.mTitle.setText(String.format("%s %s",
                    mUsersList.get(position).getFirstName(),
                    mUsersList.get(position).getLastName()));
            userViewHolder.mEmailId.setText(mUsersList.get(position).getEmail());
            userViewHolder.mUserImage.setImageURI(Uri.parse(mUsersList.get(position).getAvatar()));

        }
    }
}
