package br.com.theoldpinkeye.retrofitapiexemplo.api;

import br.com.theoldpinkeye.retrofitapiexemplo.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubService {

  @GET("/users/{username}")
  Call<User> getUser(@Path("username") String username);

}
