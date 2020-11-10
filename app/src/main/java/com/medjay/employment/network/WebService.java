package com.medjay.employment.network;

import com.google.gson.JsonObject;
import com.medjay.employment.models.AccessToken;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface WebService {

    @POST("register")
    @Multipart
    Call<AccessToken> register(@PartMap() Map<String, String> partMap,
                              @Part MultipartBody.Part avatar);

    @POST("login")
    @FormUrlEncoded
    Call<AccessToken> login(@Field("email") String email,
                            @Field("password") String password);


}
