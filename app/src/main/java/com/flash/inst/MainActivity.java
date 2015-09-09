package com.flash.inst;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static int spaceBetweenCards = 10;

    private List<CardItem> cardItems;

    private RecyclerView recyclerView_cart;
    private RecyclerView recyclerView_searchResult;

    //кнопка для передач чего либо в след активити

    private SpacesItemDecoration itemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
