package breezy.modules;

import breezy.services.BreezyService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

public class WebClientModule {

    private static final String BREEZY_BASE_URL = "https://api.breezy.hr/v3/";


    private static OkHttpClient getHttpClient() {
        var basicAuthInterceptor =
                new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request();

                        Request.Builder builder =
                                originalRequest
                                        .newBuilder()
                                        .header(
                                                "Authorization",
                                                "12c0c209-6302-499c-8519-a83f47903761");

                        Request newRequest = builder.build();
                        return chain.proceed(newRequest);
                    }
                };
        return new OkHttpClient()
                .newBuilder()
                .addInterceptor(basicAuthInterceptor)
                .retryOnConnectionFailure(false)
                .build();
    }

    public static BreezyService getBreezyService() {
        var objectMapper =
        new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        var jacksonConverterFactory = JacksonConverterFactory.create(objectMapper);
        return new Retrofit.Builder()
                .baseUrl(BREEZY_BASE_URL)
                .addConverterFactory(jacksonConverterFactory)
                .client(getHttpClient())
                .build()
                .create(BreezyService.class);
    }
}