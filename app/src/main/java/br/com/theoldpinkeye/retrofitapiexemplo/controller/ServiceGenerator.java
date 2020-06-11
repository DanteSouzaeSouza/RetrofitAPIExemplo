package br.com.theoldpinkeye.retrofitapiexemplo.controller;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
  public static final String API_BASE_URL = "https://api.github.com";

  private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

  private static Retrofit.Builder builder =
      new Retrofit.Builder()
          .baseUrl(API_BASE_URL)
          .addConverterFactory(GsonConverterFactory.create());

  public static <S> S createService(Class<S> serviceClass) {
    return createService(serviceClass, null);
  }

  public static <S> S createService(Class<S> serviceClass, final String authToken) {
    if (authToken != null) {
      httpClient.interceptors().add(chain -> {
        Request original = chain.request();

        // Request customization
        Request.Builder requestBuilder = original.newBuilder()
            .header("Authorization", authToken)
            .method(original.method(), original.body());

        Request request = requestBuilder.build();
        return chain.proceed(request);
      });
    }

    OkHttpClient client = httpClient.build();
    Retrofit retrofit = builder.client(client).build();
    return retrofit.create(serviceClass);
  }
}
