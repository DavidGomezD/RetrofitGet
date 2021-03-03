package com.davidgd.retrofitget.Interface;

import com.davidgd.retrofitget.Model.Posts;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("posts") //posts es la parte final de nuestra url https://jsonplaceholder.typicode.com/posts
    Call<List<Posts>> getPosts(); //Posts viene de nuestro Posts.java

}
