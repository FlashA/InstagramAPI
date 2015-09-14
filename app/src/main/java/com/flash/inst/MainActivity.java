package com.flash.inst;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.flash.inst.API.InstagramAPI;
import com.flash.inst.InstagramUtils.Constant;
import com.flash.inst.InstagramUtils.Instagram;
import com.flash.inst.InstagramUtils.InstagramSession;
import com.flash.inst.Models.UserPicture;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private final static int spaceBetweenCards = 10;

    private List<CardItem> cardItems;

    private RecyclerView recyclerView_cart;
    private RecyclerView recyclerView_searchResult;

    private InstagramSession mInstagramSession;
    private Instagram mInstagram;

    //кнопка для передач чего либо в след активити

    private SpacesItemDecoration itemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mInstagram  = new Instagram(this);
        mInstagramSession	= mInstagram.getSession();

        mInstagramSession = new InstagramSession(getApplicationContext());

        if (mInstagramSession.isActive()) {
            setContentView(R.layout.activity_main);

            itemDecoration = new SpacesItemDecoration(spaceBetweenCards);   //используется для создания отступа между элементами в RecyclerView
            recyclerView_cart=(RecyclerView)findViewById(R.id.recyclerView_cart);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            recyclerView_cart.addItemDecoration(itemDecoration);
            recyclerView_cart.setLayoutManager(linearLayoutManager);

            recyclerView_searchResult=(RecyclerView)findViewById(R.id.recyclerView_searchResult);
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
            recyclerView_searchResult.addItemDecoration(itemDecoration);
            recyclerView_searchResult.setLayoutManager(staggeredGridLayoutManager);

            initializeData();                       //заполнение данных в список
            initializeAdapter();                    //отображение данных из списка в RecyclerView


            getData("2110588221");
            Log.d("my_app", "isActive");
        } else{
            mInstagram.authorize();
            Log.d("my_app", "noActive");
        }
    }

    public void getData(String userID){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constant.API_URL).build();

        InstagramAPI instApi = restAdapter.create(InstagramAPI.class);

        instApi.getPhotosId(userID, mInstagramSession.getAccessToken(), new Callback<UserPicture>() {
            @Override
            public void success(UserPicture userPicture, Response response) {
                Toast.makeText(getApplicationContext(), userPicture.lowResolution, Toast.LENGTH_SHORT).show();
                Log.d("my_app", userPicture.lowResolution);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("my_app", error.toString());

            }
        });
    }

    private void initializeData(){
        cardItems = new ArrayList<>();
        cardItems.add(new CardItem(R.drawable.bmw));
        cardItems.add(new CardItem(R.drawable.cats));
        cardItems.add(new CardItem(R.drawable.railway));
        cardItems.add(new CardItem(R.drawable.bmw));
        cardItems.add(new CardItem(R.drawable.cats));
        cardItems.add(new CardItem(R.drawable.railway));
        cardItems.add(new CardItem(R.drawable.bmw));
        cardItems.add(new CardItem(R.drawable.cats));
        cardItems.add(new CardItem(R.drawable.railway));
        cardItems.add(new CardItem(R.drawable.bmw));
        cardItems.add(new CardItem(R.drawable.cats));
        cardItems.add(new CardItem(R.drawable.railway));
    }

    private void initializeAdapter(){
        RecyclerViewCartAdapter adapterCart = new RecyclerViewCartAdapter(cardItems);
        recyclerView_cart.setAdapter(adapterCart);
        RecyclerViewSearchAdapter adapterSearch = new RecyclerViewSearchAdapter(cardItems);
        recyclerView_searchResult.setAdapter(adapterSearch);
    }
}
