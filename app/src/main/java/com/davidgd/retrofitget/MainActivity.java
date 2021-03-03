package com.davidgd.retrofitget;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.widget.TextView;

import com.davidgd.retrofitget.Interface.JsonPlaceHolderApi;
import com.davidgd.retrofitget.Model.Posts;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Conexion a nuestro textview de nuestro activity_main.xml
    private TextView mJsonTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Conexion a nuestro textview de nuestro activity_main.xml
        mJsonTxtView = findViewById(R.id.jsonText);

        //Ejecutamos la funcion
        getPosts();
    }

    private  void getPosts(){
        Retrofit retrofit = new Retrofit.Builder()
                //Colocamos la base de nuestra Url https://jsonplaceholder.typicode.com/posts
                //Nota: colocar el / al final o marca error
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Posts>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Posts>> call, Response<List<Posts>> response) {

                //Tipo de problema de autentificacion
                if(!response.isSuccessful()){
                    mJsonTxtView.setText("Codigo: "+response.code());
                    return;
                }

                //Mostrara los datos del JSON
                List<Posts> postsList = response.body();
                for(Posts posts: postsList) {
                    String content = "";
                    content += "userId:"+ posts.getUserId()+ "\n";
                    content += "id:"+ posts.getId()+ "\n";
                    content += "title:"+ posts.getTitle()+ "\n";
                    content += "body:"+ posts.getBody()+ "\n\n";
                    mJsonTxtView.append(content);
                }

            }

            @Override
            public void onFailure(retrofit2.Call<List<Posts>> call, Throwable t) {

                //Nos muestra que fallo
                mJsonTxtView.setText(t.getMessage());

            }
        });

        }
    }