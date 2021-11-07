package com.hy.userprofile.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.AndroidViewModel;

import com.hy.userprofile.model.InsertUser;
import com.hy.userprofile.model.UpdateUserResp;
import com.hy.userprofile.network.service.RetrofitInstance;
import com.hy.userprofile.network.service.UserDataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpdateUserViewModel extends AndroidViewModel {



  @SuppressLint("StaticFieldLeak")
  private final Context context;
  public ObservableInt progressBar;

  public UpdateUserViewModel(@NonNull Application application) {
    super(application);

    this.context = application;
    progressBar = new ObservableInt(View.GONE);


  }


  public void sendUpdateRequest(String name , String job)
  {


    progressBar.set(View.VISIBLE);

//    APIService apiService = RetroClass.getAPIService();
    UserDataService apiService = RetrofitInstance.getService();
    InsertUser modal = new InsertUser(name, job);
    Call<UpdateUserResp> updateresponse = apiService.updateUser(modal);
    updateresponse.enqueue(new Callback<UpdateUserResp>() {
      @Override
      public void onResponse(@NonNull Call<UpdateUserResp> call, @NonNull Response<UpdateUserResp> response) {

        progressBar.set(View.GONE);

        assert response.body() != null;
        showToast(""+ response.body().getName() +"  : Updated Successfully!");


      }

      @Override
      public void onFailure(@NonNull Call<UpdateUserResp> call, @NonNull Throwable t) {
        progressBar.set(View.GONE);
        showToast(""+t.getMessage());

      }
    });




  }


  void showToast(String msg)
  {

    Toast.makeText(context,msg,Toast.LENGTH_LONG).show();

  }

}
