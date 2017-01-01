package com.mg.axe.androiddevelop.develop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mg.axe.androiddevelop.R;

/**
 * Created by Administrator on 2016/10/23 0023.
 */
public class TestFragement extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_main,container,false);
    }
}
