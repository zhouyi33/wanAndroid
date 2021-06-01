package com.example.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainFragment extends Fragment {

    private RecyclerView rv;
    private SwipeRefreshLayout sw;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //使用recyclerview
        //线性布局，获得内容
        rv = getView().findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        refresh();
        sw = getView().findViewById(R.id.sw);
        sw.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }


    void refresh(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //网络请求
                    URL url = new URL("https://www.wanandroid.com/article/list/1/json");
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    connection.setConnectTimeout(3000);
                    connection.setReadTimeout(3000);

                    connection.connect();
                    InputStreamReader inputStream = new InputStreamReader(connection.getInputStream());
                    BufferedReader brd = new BufferedReader(inputStream);

                    StringBuilder stringBuilder = new StringBuilder();
                    String s;
                    while ((s =brd.readLine())!=null){
                        stringBuilder.append(s);
                    }
                    //转换为gson
                    String result = stringBuilder.toString();
                    Log.e("abc", result+"0");

                    Gson gson = new Gson();
                    Data data = gson.fromJson(result, Data.class);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            rv.setAdapter(new RecyclerViewAdapter(data.data.datas, getActivity()));
                            sw.setRefreshing(false);

                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                    return;
                }

            }
        }).start();
    }


}