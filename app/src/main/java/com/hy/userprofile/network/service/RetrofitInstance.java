package com.hy.userprofile.network.service;

import com.hy.userprofile.AppUtil;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class  RetrofitInstance {
  private static Retrofit retrofit = null;

  public static com.hy.userprofile.network.service.UserDataService getService() {

    if (retrofit == null) {
      retrofit = new Retrofit
          .Builder()
          .baseUrl(AppUtil.BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build();
    }

    return retrofit.create(com.hy.userprofile.network.service.UserDataService.class);
  }
}
