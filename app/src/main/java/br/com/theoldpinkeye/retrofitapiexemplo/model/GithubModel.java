package br.com.theoldpinkeye.retrofitapiexemplo.model;

import br.com.theoldpinkeye.retrofitapiexemplo.api.GithubService;
import br.com.theoldpinkeye.retrofitapiexemplo.controller.ServiceGenerator;
import br.com.theoldpinkeye.retrofitapiexemplo.view.MainViewModel;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubModel {

  private GithubService git;
  private MainViewModel viewModel;


  public GithubModel(MainViewModel viewModel) {
    this.viewModel = viewModel;
    this.git = ServiceGenerator.createService(GithubService.class);

  }

  public void getUser(String username) {

    Call<User> call = git.getUser(username);
    call.enqueue(new Callback<User>() {
      @Override
      public void onResponse(Call<User> call, Response<User> response) {
        User user = response.body();

        if (user == null) {
          //404
          ResponseBody responseBody = response.errorBody();
          if (responseBody != null) {
            try {
              viewModel.setText("responseBody= " + responseBody.string());
            } catch (IOException e) {
              e.printStackTrace();
            }
          } else {
            viewModel.setText("responseBody  = null");
          }
        } else {
          //200
          viewModel.setText("Github Username: " + user.getName() + "\nWebsite: " + user.getBlog()
              + "\nCompany Name: " + user.getCompany());
        }
        viewModel.setPb(false);
      }

      @Override
      public void onFailure(Call<User> call, Throwable t) {
        viewModel.setText("t = " + t.getMessage());
        viewModel.setPb(false);
      }


    });
  }

}
