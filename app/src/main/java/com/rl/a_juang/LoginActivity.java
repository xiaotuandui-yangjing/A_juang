package com.rl.a_juang;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：杨景 时间： 2020/10/15 11:19
 */
public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.rag)
    RadioGroup rag;
    private Button bt_wangloushezhi , bt_login;
    private Boolean blg = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
        bt_wangloushezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDialog();
            }
        });
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (blg){
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }
                else {
                    Toast.makeText(LoginActivity.this, "请先设置配置网络", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("网络设置");

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        EditText editText1 = new EditText(this);
        editText1.setHint("ip地址");
        EditText editText2 = new EditText(this);
        editText2.setHint("端口号");
        linearLayout.addView(editText1);
        linearLayout.addView(editText2);

        builder.setView(linearLayout);
        builder.setNegativeButton("保存修改", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences sp = getSharedPreferences("ipconfig", MODE_PRIVATE);
                sp.edit().putString("ip",editText1.getText().toString()+editText2.getText().toString()).apply();
                Toast.makeText(LoginActivity.this, ""+sp.getString("ip","").toString(), Toast.LENGTH_SHORT).show();
                blg = true;
            }
        });
        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void initView() {
        for (int i = 0; i < 5; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setId(i);
            rag.addView(radioButton);
        }
        rag.check(0);

        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                rag.check(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        List<View> viewList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(android.R.drawable.alert_light_frame);
            viewList.add(imageView);
        }
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(android.R.drawable.alert_light_frame);

         bt_wangloushezhi = new Button(this);
        bt_wangloushezhi.setText("网络设置");

         bt_login = new Button(this);
        bt_login.setText("进入主页");

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,getWindowManager().getDefaultDisplay().getHeight()-500);
        imageView.setLayoutParams(layoutParams);
        linearLayout.addView(imageView);
        linearLayout.addView(bt_login);
        linearLayout.addView(bt_wangloushezhi);
        viewList.add(linearLayout);

        viewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view==object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView(viewList.get(position));
            }
        });
    }
}
