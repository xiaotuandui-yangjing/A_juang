package com.rl.a_juang.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.rl.a_juang.R;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：杨景 时间： 2020/10/15 18:46
 */
public class ShouYe_Fragment extends Fragment {


    List<Emp> empList = new ArrayList<>();
    List<Emp> empList2 = new ArrayList<>();
    List<View> viewList = new ArrayList<>();
    private ViewHolder viewHolder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.f_shouye, null);
         viewHolder = new ViewHolder(inflate);

        initView();
        initBanner();
        for (int i = 0; i < 10; i++) {
            empList.add(new Emp("服务" + (i + 1), android.R.drawable.sym_contact_card));
        }
        for (int i = 0; i < 6; i++) {
            empList2.add(new Emp((i + 1) + "主题asdasdsad", android.R.drawable.alert_light_frame));
        }
        for (int i = 0; i < 5; i++) {
            ListView listView = new ListView(getActivity());
            listView.setAdapter(new MyAdapter());
            viewList.add(listView);
        }
        initGridview(empList, viewHolder.gridview);
        initGridview(empList2, viewHolder.gridview2);
        initViewPager();
        return inflate;
    }


    private void initViewPager() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < viewList.size(); i++) {
            arrayList.add("类型" + (i + 1));
        }
        viewHolder.tablelayout.setupWithViewPager(viewHolder.viewpager);
        viewHolder.viewpager.setAdapter(new PagerAdapter() {
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return arrayList.get(position);
            }

            @Override
            public int getCount() {
                return viewList.size();
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

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }
        });
    }

    private void initGridview(List<Emp> list, GridView gridview) {
        gridview.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView = getLayoutInflater().inflate(R.layout.gridview_adapter, null);
                CircleImageView imageView = convertView.findViewById(R.id.circleIndicator);
                TextView textView2 = convertView.findViewById(R.id.textView2);
                if (list.get(position).getTitle().equals("服务10")) {
                    textView2.setText("更多服务");

                } else {
                    textView2.setText(list.get(position).getTitle() + "");
                }
                imageView.setImageResource(list.get(position).getImags());
                return convertView;
            }
        });
    }

    private void initBanner() {
        List<Integer> imageViewList = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            imageViewList.add(android.R.drawable.ic_delete);
            titles.add(i + "");
        }
        viewHolder.banner.setImages(imageViewList);
        viewHolder.banner.isAutoPlay(false);
        viewHolder.banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(getActivity()).load(path).into(imageView);
            }
        });
        viewHolder.banner.setDelayTime(1500);
        viewHolder.banner.start();

        viewHolder.banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                switch (position) {
                    case 0:
                        Toast.makeText(getActivity(), "跳转到activity1", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getActivity(), "跳转到activity2", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getActivity(), "跳转到activity3", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(getActivity(), "跳转到activity4", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void initView() {
        viewHolder.editText2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Toast.makeText(getActivity(), "正在为您跳转到关于" + v.getText().toString() + "的数据", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    class Emp {
        String title;
        int imags;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Emp(String title, int imags) {
            this.title = title;
            this.imags = imags;
        }

        public int getImags() {
            return imags;
        }

        public void setImags(int imags) {
            this.imags = imags;
        }
    }

    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.adapter_layout, null);
            ViewHolder viewHolder = new ViewHolder(convertView);
            viewHolder.textView3.setText("标题");
            viewHolder.textView4.setText("内容。。。。。。。。。。。。。。。");
            viewHolder.textView5.setText("评论总数");
            viewHolder.textView6.setText("2020/1/1");
            return convertView;
        }

        class ViewHolder {
            @BindView(R.id.imageView)
            ImageView imageView;
            @BindView(R.id.textView3)
            TextView textView3;
            @BindView(R.id.textView4)
            TextView textView4;
            @BindView(R.id.textView5)
            TextView textView5;
            @BindView(R.id.textView6)
            TextView textView6;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

    static
    class ViewHolder {
        @BindView(R.id.gridview2)
        GridView gridview2;
        @BindView(R.id.gridview)
        GridView gridview;
        @BindView(R.id.textView)
        TextView textView;
        @BindView(R.id.editText2)
        EditText editText2;
        @BindView(R.id.banner)
        Banner banner;
        @BindView(R.id.tablelayout)
        TabLayout tablelayout;
        @BindView(R.id.viewpager)
        ViewPager viewpager;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    @Override
    public void onDestroy() {
        empList.clear();
        empList2.clear();
        viewList.clear();
        super.onDestroy();
    }
}
