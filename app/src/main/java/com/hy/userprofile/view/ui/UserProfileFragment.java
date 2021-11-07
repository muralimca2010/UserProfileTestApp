package com.hy.userprofile.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputLayout;
import com.hy.userprofile.R;
import com.hy.userprofile.databinding.FragmentUserProfileBinding;
import com.hy.userprofile.model.User;
import com.hy.userprofile.viewmodel.MainActivityViewModel;

/**
 * A fragment representing a list of Items.
 */
public class UserProfileFragment extends Fragment {

    private User user;
    private FragmentUserProfileBinding userProfileFragmentBinding;
    private MainActivityViewModel updateUserViewModel;

    LinearLayoutCompat account_type;
    EditText account_url;
    TextInputLayout eti_account_type;
    EditText et_name;
    EditText et_email;
    EditText et_username;
    EditText et_mob;
    EditText et_address;
    EditText et_account_type;
    EditText et_member;
    TextView edit_btn;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */

    public static UserProfileFragment newInstance() {
        return new UserProfileFragment();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
//        User user = getArguments().getParcelable("user");
        userProfileFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_profile, container, false);
        userProfileFragmentBinding.setLifecycleOwner(this);
        View view = userProfileFragmentBinding.getRoot();






        account_type = view.findViewById(R.id.etv_account_type);
        account_url = view.findViewById(R.id.et_account_type);
        eti_account_type = view.findViewById(R.id.eti_account_type);
        et_name = view.findViewById(R.id.et_name);
        et_email = view.findViewById(R.id.et_email);
        et_username = view.findViewById(R.id.et_username);
        et_mob = view.findViewById(R.id.et_mob);
        et_address = view.findViewById(R.id.et_address);
        et_account_type = view.findViewById(R.id.et_account_type);
        et_member = view.findViewById(R.id.et_member);
        edit_btn  = view.findViewById(R.id.edit_btn);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        updateUserViewModel = ViewModelProviders.of(requireActivity()).get(MainActivityViewModel.class);


        ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class).getData().observe(getActivity(), new Observer<User>() {
            @Override
            public void onChanged(@Nullable User usersFromLiveData) {

                User  users = usersFromLiveData;
                userProfileFragmentBinding.setUserDetail(user);
            }
        });




        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((edit_btn.getText().toString().trim().equals("Edit"))) {
                    edit_btn.setText("    Update    ");
                    et_name.setEnabled(true);
                    et_email.setEnabled(true);
                    et_username.setEnabled(true);
                    et_mob.setEnabled(true);
                    et_address.setEnabled(true);
                    et_account_type.setEnabled(true);
                    et_member.setEnabled(true);
                } else {

                    edit_btn.setText("    Edit    ");
                    et_name.setEnabled(false);
                    et_email.setEnabled(false);
                    et_username.setEnabled(false);
                    et_mob.setEnabled(false);
                    et_address.setEnabled(false);
                    et_account_type.setEnabled(false);
                    et_member.setEnabled(false);
//                    updateUserViewModel.sendUpdateRequest(et_name.getText().toString(),
//                            et_email.getText().toString());

                }
            }
        });

        account_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url_ = account_url.getText().toString();
                callWebView(url_);
            }
        });

        eti_account_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url_ = account_url.getText().toString();
                callWebView(url_);
            }
        });

        account_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url_ = account_url.getText().toString();
                callWebView(url_);
            }
        });

    }



    private void callWebView(String account_url) {
        Intent i = new Intent(getActivity(), WebViewActivity.class);
        i.putExtra("isUrl", account_url);
        startActivity(i);
    }
}