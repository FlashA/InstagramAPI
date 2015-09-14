package com.flash.inst.API;

import com.flash.inst.Models.UserPicture;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by fdh on 12.09.15.
 */
public interface InstagramAPI {
    //https://api.instagram.com/v1/users/2110588221/media/recent/?access_token=1034002143.a6726ae.ea8a968cadff4c56bf3704ffc3ead4ab
    /*@GET("/users/{user}/media/recent/?access_token={token}")
    public void getPhotosId(@Path("user") String user,
                            @Path("token") String token,
                            Callback<UserPicture> response);*/

    @GET("/users/{user}/media/recent/")
    public void getPhotosId(@Path("user") String user,
                            @Query("access_token") String token,
                            Callback<UserPicture> response);
}
