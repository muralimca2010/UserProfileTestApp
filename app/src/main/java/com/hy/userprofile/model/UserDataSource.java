package com.hy.userprofile.model;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.hy.userprofile.network.service.RetrofitInstance;
import com.hy.userprofile.network.service.UserDataService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserDataSource extends PageKeyedDataSource<Long, com.hy.userprofile.model.User> {
  private UserDataService userDataService;

  public UserDataSource(UserDataService userDataService) {
    this.userDataService = userDataService;
  }

  @Override
  public void loadInitial(@NonNull LoadInitialParams<Long> params,
      @NonNull final LoadInitialCallback<Long, com.hy.userprofile.model.User> callback) {

    userDataService = RetrofitInstance.getService();
    Call<com.hy.userprofile.model.UserResponse> call = userDataService.getUsersWithPaging(1);

    call.enqueue(new Callback<com.hy.userprofile.model.UserResponse>() {
      @Override
      public void onResponse(Call<com.hy.userprofile.model.UserResponse> call, Response<com.hy.userprofile.model.UserResponse> response) {

        com.hy.userprofile.model.UserResponse UserResponse = response.body();
        ArrayList<com.hy.userprofile.model.User> users = new ArrayList<>();

        if (UserResponse != null && UserResponse.getUsers() != null) {
          users = (ArrayList<com.hy.userprofile.model.User>) UserResponse.getUsers();

          callback.onResult(users, null, (long) 2);
        }
      }

      @Override
      public void onFailure(Call<com.hy.userprofile.model.UserResponse> call, Throwable t) {

      }
    });
  }

  @Override
  public void loadBefore(@NonNull LoadParams<Long> params,
      @NonNull LoadCallback<Long, com.hy.userprofile.model.User> callback) {

  }

  @Override
  public void loadAfter(@NonNull final LoadParams<Long> params,
      @NonNull final LoadCallback<Long, com.hy.userprofile.model.User> callback) {

    userDataService = RetrofitInstance.getService();
    Call<com.hy.userprofile.model.UserResponse> call = userDataService.getUsersWithPaging(params.key);
    call.enqueue(new Callback<com.hy.userprofile.model.UserResponse>() {
      @Override
      public void onResponse(Call<com.hy.userprofile.model.UserResponse> call, Response<com.hy.userprofile.model.UserResponse> response) {

        com.hy.userprofile.model.UserResponse UserResponse = response.body();
        ArrayList<com.hy.userprofile.model.User> users = new ArrayList<>();

        if (UserResponse != null && UserResponse.getUsers() != null) {
          users = (ArrayList<com.hy.userprofile.model.User>) UserResponse.getUsers();

          callback.onResult(users, params.key + 1);
        }
      }

      @Override
      public void onFailure(Call<com.hy.userprofile.model.UserResponse> call, Throwable t) {

      }
    });
  }
}
