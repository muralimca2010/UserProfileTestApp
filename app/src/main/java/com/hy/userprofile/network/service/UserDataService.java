package com.hy.userprofile.network.service;

import com.hy.userprofile.model.InsertUser;
import com.hy.userprofile.model.InsertUserResp;
import com.hy.userprofile.model.UpdateUserResp;
import com.hy.userprofile.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;


public interface UserDataService {
  @GET("users")
  Call<UserResponse> getUsers();

  @GET("users")
  Call<UserResponse> getUsersWithPaging(@Query("page") long page);

  @POST("users")
  Call<InsertUserResp> createUser(@Body InsertUser insertUser);

  @PUT("users")
  Call<UpdateUserResp> updateUser(@Body InsertUser insertUser);

}
