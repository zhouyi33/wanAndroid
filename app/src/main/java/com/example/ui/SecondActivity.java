package com.example.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ViewPager2 viewPager2 = findViewById(R.id.vp23);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bnv);

        ArrayList<Fragment> list = new ArrayList<>();

        list.add(new MainFragment());
        list.add(new MineFragment());
        //this为确实生命周期
        viewPager2.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                //返回对应位置
                return list.get(position);
            }

            @Override
            public int getItemCount() {
                //position 范围
                return list.size();
            }
        });
        //监听页面切换
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback(){
            @Override
            public void onPageSelected(int position) {
                //切换选中下方Menu
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }
        }
        );
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //设置点击Menu切换页面
                viewPager2.setCurrentItem(item.getOrder());
                return true;
            }
        });

    }
}