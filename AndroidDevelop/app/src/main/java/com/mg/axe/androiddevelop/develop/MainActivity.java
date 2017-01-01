package com.mg.axe.androiddevelop.develop;

import android.app.DownloadManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;

import com.mg.axe.androiddevelop.R;
import com.mg.axe.androiddevelop.download.DPDownloaderManager;
import com.mg.axe.androiddevelop.view.FlowLayout;
import com.mg.axe.androiddevelop.view.MyProgress;
import com.mg.axe.androiddevelop.view.RoundProgress;

public class MainActivity extends AppCompatActivity {

    private MyProgress myProgress;
    private RoundProgress mRoundProgress;
    private String[] values = {"Jack","dsdfsdfdsean","sdfg","dsfg","holdy","axsdfsdfdsfe","tragcsdfge","bsdfgao","fisdfgveV","sifgxsdfgV","Chsdfgdsgao","Yasdfgdsgo","Hsrgsdfgmy","Jusdfgdsgan","Jia"};
    private FlowLayout flowLayout;

    private static final int MSG_UPDATE = 1000;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int progress = myProgress.getProgress();
            myProgress.setProgress(++progress);
            if(progress>=100){
                handler.removeMessages(MSG_UPDATE);
            }

            int progress2 = mRoundProgress.getProgress();
            mRoundProgress.setProgress(++progress2);
            if(progress>=100){
                handler.removeMessages(MSG_UPDATE);
            }
            handler.sendEmptyMessageDelayed(MSG_UPDATE,100);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myProgress = (MyProgress) findViewById(R.id.progress);
        mRoundProgress = (RoundProgress) findViewById(R.id.roundprogress);
        handler.sendEmptyMessage(MSG_UPDATE);
        flowLayout = (FlowLayout) findViewById(R.id.flowlayout);
        addFlowBtn();
    }

    private void addFlowBtn(){
        for (int i = 0;i<values.length;i++){
            Button button = new Button(this);
            ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            button.setText(values[i]);
            flowLayout.addView(button,lp);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
