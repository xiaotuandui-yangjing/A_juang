package com.rl.a_juang.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rl.a_juang.R;

/**
 * 作者：杨景 时间： 2020/10/15 18:46
 */
public class FuWu_fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.f_shouye, null);
        TextView textView = new TextView(getActivity());
        textView.setText("saasdsad");
        return textView;
    }


}
