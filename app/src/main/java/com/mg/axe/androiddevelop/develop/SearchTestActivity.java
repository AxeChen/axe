package com.mg.axe.androiddevelop.develop;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;

import com.mg.axe.androiddevelop.R;
import com.mg.axe.androiddevelop.view.SearchViewLayout;

/**
 * Created by Administrator on 2016/10/23 0023.
 */
public class SearchTestActivity extends AppCompatActivity {

    private SearchViewLayout  searchViewLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_test);
        searchViewLayout = (SearchViewLayout) findViewById(R.id.search_view_container);
        searchViewLayout.setExpandedContentSupportFragment(this,new TestFragement());

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            //这里写下你漂亮测量代码就好
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        ViewTreeObserver observer = searchViewLayout.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                searchViewLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int width = searchViewLayout.getMeasuredWidth();
            }
        });

        int widthSpce = View.MeasureSpec.makeMeasureSpec(100, View.MeasureSpec.EXACTLY);
        int heightSpce = View.MeasureSpec.makeMeasureSpec(100, View.MeasureSpec.EXACTLY);
        searchViewLayout.measure(widthSpce,heightSpce);
    }
}
