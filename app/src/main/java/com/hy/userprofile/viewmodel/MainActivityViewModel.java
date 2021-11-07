package com.hy.userprofile.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.hy.userprofile.model.User;
import com.hy.userprofile.model.UserDataSource;
import com.hy.userprofile.model.UserDataSourceFactory;
import com.hy.userprofile.network.repo.UserRepository;
import com.hy.userprofile.network.service.RetrofitInstance;
import com.hy.userprofile.network.service.UserDataService;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class MainActivityViewModel extends AndroidViewModel {
  private UserRepository userRepository;


  LiveData<UserDataSource> userDataSourceLiveData;
  private MutableLiveData<User> userMutableLiveData;
  private Executor executor;
  private LiveData<PagedList<User>> usersPagedList;

  public void init()
  {
    userMutableLiveData=new MutableLiveData<User>();

  }

  public MainActivityViewModel(@NonNull Application application) {
    super(application);
    userRepository = new UserRepository(application);

    UserDataService userDataService = RetrofitInstance.getService();
    UserDataSourceFactory factory = new UserDataSourceFactory(userDataService);
    userDataSourceLiveData = factory.getMutableLiveData();

    PagedList.Config config = (new PagedList.Config.Builder())
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(10)
        .setPageSize(20)
        .setPrefetchDistance(4)
        .build();

    executor = Executors.newFixedThreadPool(5);

    usersPagedList = (new LivePagedListBuilder<Long, User>(factory, config))
        .setFetchExecutor(executor)
        .build();
  }

  public LiveData<List<User>> getAllUsers() {

    return userRepository.getMutableLiveData();
  }

  public void sendData(User user) {

    userMutableLiveData.setValue(user);

  }

  public LiveData<User> getData(){
    return userMutableLiveData;
  }

  public LiveData<PagedList<User>> getUsersPagedList() {
    return usersPagedList;
  }
}
