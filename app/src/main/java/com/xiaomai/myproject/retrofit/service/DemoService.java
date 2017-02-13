
package com.xiaomai.myproject.retrofit.service;

import com.xiaomai.myproject.retrofit.bean.ResponseInfo;
import com.xiaomai.myproject.retrofit.bean.User;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by XiaoMai on 2017/2/10 16:30.
 */
public interface DemoService {

    @GET("Test")
    Call<ResponseInfo> testHttpGet();

    @GET("Test")
    Call<ResponseInfo> testHttpGet(@Query("model") String param);

    @GET("Test")
    Call<ResponseInfo> testHttpGet(@QueryMap Map<String, String> params);

    @GET("Test/{name}")
    Call<ResponseInfo> testHttpGetQuery(@Path("name") String apiAction);

    @POST("Test")
    Call<String> uploadUser(@Body User user);

    @FormUrlEncoded
    @POST("Test")
    Call<ResponseInfo> uploadUser(@Field("username") String username, @Field("gender") String male,
            @Field("age") int age);

    @FormUrlEncoded
    @POST("Test")
    Call<String> uploadUser(@FieldMap Map<String, String> params);

    @Headers("Content-type:application/x-www-form-urlencoded;charset=UTF-8")
    @FormUrlEncoded
    @POST("Test")
    Call<ResponseInfo> uploadUserWithHeaders(@Field("username") String username,
            @Field("gender") String male, @Field("age") int age);

    @FormUrlEncoded
    @POST("Test")
    Call<String> uploadUserWithHeader(@Header("Content-Type") String contentType,
            @Field("username") String username, @Field("gender") String male,
            @Field("age") int age);
}
