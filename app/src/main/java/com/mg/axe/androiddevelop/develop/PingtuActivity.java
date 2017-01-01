package com.mg.axe.androiddevelop.develop;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.mg.axe.androiddevelop.R;
import com.mg.axe.androiddevelop.view.PuzzleLayout;

/**
 * Created by Administrator on 2016/9/8 0008.
 */
public class PingtuActivity extends AppCompatActivity{

    private PuzzleLayout puzzleLayout;

    private TextView mlevel;
    private TextView mTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puzzlelayout);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        puzzleLayout = (PuzzleLayout) findViewById(R.id.puzzview);

        mlevel = (TextView) findViewById(R.id.tv_level);
        mTime = (TextView) findViewById(R.id.tv_time);
        puzzleLayout.setTimeEnable(true);
        puzzleLayout.setGamePuzzleListener(new PuzzleLayout.GamePuzzleListener() {
            @Override
            public void nextLeve(final int nextLeve) {
                new AlertDialog.Builder(PingtuActivity.this)
                        .setTitle("Game info").setMessage("level up")
                        .setPositiveButton("next level", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                puzzleLayout.nextLeve();
                                mlevel.setText(""+nextLeve);
                            }
                        }).show();
            }

            @Override
            public void timeChange(int currentTime) {
                mTime.setText(currentTime+"");
            }

            @Override
            public void gameover() {

                new AlertDialog.Builder(PingtuActivity.this)
                        .setTitle("Game info").setMessage("Game over!")
                        .setPositiveButton("RESTART", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                puzzleLayout.restart();
                            }
                        })
                        .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).show();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        puzzleLayout.pasue();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(puzzleLayout!=null){
                    puzzleLayout.resume();
        }
    }
}
