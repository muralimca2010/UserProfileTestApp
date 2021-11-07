package com.hy.userprofile.network.repo;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.hy.userprofile.model.User;
import com.hy.userprofile.model.UserResponse;
import com.hy.userprofile.network.service.RetrofitInstance;
import com.hy.userprofile.network.service.UserDataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserRepository {
  private ArrayList<User> users = new ArrayList<>();
  private MutableLiveData<List<User>> mutableLiveData = new MutableLiveData<>();
  private Application application;

  public UserRepository(Application application) {
    this.application = application;
  }

  public MutableLiveData<List<User>> getMutableLiveData() {

    final UserDataService userDataService = RetrofitInstance.getService();

    Call<UserResponse> call = userDataService.getUsers();

    call.enqueue(new Callback<UserResponse>() {
      @Override
      public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
        UserResponse UserResponse = response.body();

        if (userDataService != null && UserResponse.getUsers() != null) {

          users = (ArrayList<User>) UserResponse.getUsers();
          mutableLiveData.setValue(users);
        }
      }

      @Override
      public void onFailure(Call<UserResponse> call, Throwable t) {

      }
    });

    return mutableLiveData;
  }
}
