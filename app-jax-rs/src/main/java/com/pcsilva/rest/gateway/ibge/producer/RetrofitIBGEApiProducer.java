package com.pcsilva.rest.gateway.ibge.producer;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.pcsilva.rest.infra.Parametros;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Named
@RequestScoped
public class RetrofitIBGEApiProducer {

	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");	

	@Inject
	private Parametros parametros;

	@Produces
	@RetrofitIBGEApi
	public Retrofit producesRetrofit() {

		HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
		logging.setLevel(Level.BODY);
		
		Interceptor accessTokenInterceptor = new Interceptor() {
			@Override
			public Response intercept(Chain chain) throws IOException {
				Request newRequest = chain.request().newBuilder().addHeader("Content-Type", "application/json; charset=utf-8").build();
				return chain.proceed(newRequest);
			}
		};

		OkHttpClient client = new OkHttpClient.Builder().connectTimeout(5, TimeUnit.MINUTES)
				.writeTimeout(5, TimeUnit.MINUTES).readTimeout(5, TimeUnit.MINUTES).addInterceptor(logging)
				.addInterceptor(accessTokenInterceptor).build();

		return new Retrofit.Builder()
				.client(client)
			    .baseUrl(parametros.getIbgeApi() + "/")
			    .addConverterFactory(JacksonConverterFactory.create())
			    .build();

	}

}