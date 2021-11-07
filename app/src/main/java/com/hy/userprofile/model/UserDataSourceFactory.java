package com.hy.userprofile.model;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.hy.userprofile.network.service.UserDataService;


public class UserDataSourceFactory extends DataSource.Factory {

  private com.hy.userprofile.model.UserDataSource userDataSource;
  private UserDataService userDataService;
  private MutableLiveData<com.hy.userprofile.model.UserDataSource> mutableLiveData;

  public UserDataSourceFactory(UserDataService userDataService) {
    this.userDataService = userDataService;
    mutableLiveData = new MutableLiveData<>();
  }

  @Override
  public DataSource create() {

    userDataSource = new com.hy.userprofile.model.UserDataSource(userDataService);
    mutableLiveData.postValue(userDataSource);
    return userDataSource;
  }

  public MutableLiveData<com.hy.userprofile.model.UserDataSource> getMutableLiveData() {
    return mutableLiveData;
  }
}

