package com.rl.a_juang;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rl.a_juang.fragment.FuWu_fragment;
import com.rl.a_juang.fragment.GeRenZhongXin_Fragment;
import com.rl.a_juang.fragment.HuoDong_Fragment;
import com.rl.a_juang.fragment.ShouYe_Fragment;
import com.rl.a_juang.fragment.XinWeng_Fragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottomnavigation)
    BottomNavigationView bottomnavigation;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    private FragmentTransaction fragmentTransaction;
    private List<Fragment> fragmentList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initBottomNavig();
        fragmentList.add(new FuWu_fragment());
        fragmentList.add(new ShouYe_Fragment());
        fragmentList.add(new GeRenZhongXin_Fragment());
        fragmentList.add(new HuoDong_Fragment());
        fragmentList.add(new XinWeng_Fragment());
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, fragmentList.get(1)).commit();

    }

    private void initBottomNavig() {
        bottomnavigation.setOnNavigationItemSelectedListener(item -> {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.action_main:
                    fragmentTransaction.replace(R.id.framelayout, fragmentList.get(1)).commit();
                    break;
                case R.id.action_2:
                    fragmentTransaction.replace(R.id.framelayout, fragmentList.get(0)).commit();
                    break;
                case R.id.action_3:
                    fragmentTransaction.replace(R.id.framelayout, fragmentList.get(2)).commit();
                    break;
                case R.id.action_4:
                    fragmentTransaction.replace(R.id.framelayout, fragmentList.get(3)).commit();
                    break;
                case R.id.action_5:
                    fragmentTransaction.replace(R.id.framelayout, fragmentList.get(4)).commit();
                    break;
            }
            return true;
        });
    }


}
