package com.challenge.larionbabych.codingchallenge.api;

import com.challenge.larionbabych.codingchallenge.model.Geometry;
import com.challenge.larionbabych.codingchallenge.model.PlaceResponse;
import com.challenge.larionbabych.codingchallenge.model.PlacesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    String KEY = "AIzaSyCVQ3y8oYavT3ru8fe_yuPPnAtxkTOLLcI";
    String BASE_URL = "https://maps.googleapis.com/maps/api/place/";

    @GET("nearbysearch/json")
    Call<PlacesResponse> getByRating(@Query("location") Geometry.Location location,
                                     @Query("rankby") String rankby,
                                     @Query("radius") int radius,
                                     @Query("type") String type,
                                     @Query("key") String key);

    @GET("nearbysearch/json")
    Call<PlacesResponse> getByDistance(@Query("location") Geometry.Location location,
                                       @Query("rankby") String rankby,
                                       @Query("type") String type,
                                       @Query("key") String key);

    @GET("details/json")
    Call<PlaceResponse> getById(@Query("placeid") String placeId,
                                @Query("key") String key);


}