package com.mg.axe.androiddevelop.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mg.axe.androiddevelop.R;
import com.mg.axe.androiddevelop.Utils.GameUtil;
import com.mg.axe.androiddevelop.Utils.ImagePiece;
import com.mg.axe.androiddevelop.Utils.ImageSplitterUtil;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.WeakHashMap;

/**
 * Created by Administrator on 2016/9/10 0010.
 */
public class PuzzleLayout extends RelativeLayout implements View.OnClickListener{


    public static final int TIME_CHANGE = 10;
    public static final int NEXTLEVE = 11;
    /** 游戏等级 **/
    private int level =1 ;
    /** 游戏界面每行的个数 **/
    private int mCount = 3;
    /**
     * 容器的内边距
     */
    private int mPadding;

    /**
     * 每张图片的边距
     */
    private int mMagin = 3;

    /**
     * 存储所有的image
     */
    private ImageView[] mGamePuzzleItems;

    /**
     * 每张图片的宽度
     */
    private int mItemWidth;

    /**
     * 游戏的图片
     */
    private Bitmap mBitmap;

    /**
     * 游戏面板宽度
     */
    private int mWidth;

    /**
     * 游戏的时间 (秒)
     */
    private int mTime;

    /**游戏是否停止状态**/
    private boolean isPause;

    private List<ImagePiece> mitemButms;

    /** 对是否已经初始化判断 **/
    private boolean isOnce;

    /**游戏状态判断**/
    private boolean isGameSuccess;
    private boolean isGameOver;

    private ImageView mFrist;
    private ImageView mSecond;

    private boolean isannmin;

    public PuzzleLayout(Context context) {
        this(context, null);
    }

    public PuzzleLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PuzzleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //取高和宽中的最小值
        mWidth = Math.min(getMeasuredHeight(), getMeasuredWidth());
        if (!isOnce) {
            //进行切图排序
            initBitmap();
            //这是ImageView的宽高
            initItem();
            checkTimeEnable();
            isOnce = true;
        }
        setMeasuredDimension(mWidth,mWidth);
    }

    /**
     * 进行切图和排序
     */
    private void initBitmap(){
        if(mBitmap == null){
            mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sdhy);
        }
        mitemButms = ImageSplitterUtil.splitImage(mBitmap,mCount);
        // 用 sort 完成乱序
        Collections.sort(mitemButms, new Comparator<ImagePiece>() {
            @Override
            public int compare(ImagePiece lhs, ImagePiece rhs) {
                return Math.random()>0.5?1:-1;
            }
        });
    }

    /**
     * 设置图片的宽高等属性
     */
    private void initItem(){
        mItemWidth = (mWidth-mPadding*2-mMagin*(mCount - 1))/mCount;
        mGamePuzzleItems = new ImageView[mCount*mCount];

        for(int i = 0;i<mGamePuzzleItems.length;i++){
            ImageView item = new ImageView(getContext());
            item.setOnClickListener(this);

            item.setImageBitmap(mitemButms.get(i).getBitmap());
            mGamePuzzleItems[i] = item;
            item.setId(i+1);
            //tag中存放item
            item.setTag(i+"_"+mitemButms.get(i).getIndex());
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(mItemWidth,mItemWidth);

            //设置item横向间隙不是最后一列 通过rightmargin
            //不是最后一列
            if((i+1)%mCount!=0){
                lp.rightMargin = mMagin;
            }
            //不是第一列
            if(i%mCount !=0){
                lp.addRule(RelativeLayout.RIGHT_OF,mGamePuzzleItems[i-1].getId());
            }
            //设置纵向间隙
            if((i+1)>mCount){
                lp.topMargin = mMagin;
                lp.addRule(RelativeLayout.BELOW,mGamePuzzleItems[i-mCount].getId());
            }
            addView(item,lp);
        }
    }

    private void init() {
        mMagin = dp2px(3);
        mPadding = getMinlength(getPaddingBottom(), getPaddingLeft(),getPaddingRight(),getPaddingTop());
    }

    private int getMinlength(int... params) {
        int min = params[0];
        for (int para : params) {
            if (para < min) {
                min = para;
            }
        }
        return min;
    }

    @Override
    public void onClick(View v) {

        if(isannmin){
            return;
        }
        //点的是同一个View
        if(mFrist == v){
            mFrist.setColorFilter(null);
            mFrist = null;
            return;
        }

        if(mFrist == null){
            mFrist = (ImageView) v;
            mFrist.setColorFilter(Color.parseColor("#55FF0000"));
        }else{
            mSecond = (ImageView)v;
            //交换图片
            exChangView();
        }
    }

    private void exChangView(){

        //构造动画层
        setUpAnimLayout();
        ImageView first = new ImageView(getContext());

        final Bitmap fb = mitemButms.get(getImageIdByTag((String) mFrist.getTag())).getBitmap();
        first.setImageBitmap(fb);
        LayoutParams lp = new LayoutParams(mItemWidth,mItemWidth);
        lp.leftMargin = mFrist.getLeft() - mPadding;
        lp.topMargin = mFrist.getTop() - mPadding;
        first.setLayoutParams(lp);
        mAnimLayout.addView(first);


        ImageView second = new ImageView(getContext());
        final Bitmap sb = mitemButms.get(getImageIdByTag((String) mSecond.getTag())).getBitmap();
        second.setImageBitmap(sb);
        LayoutParams lp2 = new LayoutParams(mItemWidth,mItemWidth);
        lp2.leftMargin = mSecond.getLeft() - mPadding;
        lp2.topMargin = mSecond.getTop() - mPadding;
        second.setLayoutParams(lp2);
        mAnimLayout.addView(second);

        //设置动画
        TranslateAnimation animFirst = new TranslateAnimation(0,mSecond.getLeft()-mFrist.getLeft(),0,mSecond.getTop()-mFrist.getTop());
        animFirst.setDuration(300);
        animFirst.setFillAfter(true);
        first.startAnimation(animFirst);

        TranslateAnimation animSecond = new TranslateAnimation(0,-mSecond.getLeft()+mFrist.getLeft(),0,-mSecond.getTop()+mFrist.getTop());
        animSecond.setDuration(300);
        animSecond.setFillAfter(true);
        second.startAnimation(animSecond);

        //动画监听
        animFirst.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isannmin = true;
                mFrist.setVisibility(INVISIBLE);
                mSecond.setVisibility(INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if(mFrist!=null){

                    mFrist.setColorFilter(null);
                }

                String firstTag = (String) mFrist.getTag();
                String secondTag = (String) mSecond.getTag();

                mSecond.setImageBitmap(fb);
                mFrist.setImageBitmap(sb);

                mFrist.setTag(secondTag);
                mSecond.setTag(firstTag);

                mFrist.setVisibility(VISIBLE);
                mSecond.setVisibility(VISIBLE);

                mFrist = mSecond = null;
                mAnimLayout.removeAllViews();
                isannmin = false;

                checkSuccess();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 判断用户是否成功
     */
    public void checkSuccess(){
        boolean isSuccess = true;
        for(int i=0;i<mGamePuzzleItems.length;i++){
            ImageView imageView = mGamePuzzleItems[i];
            if(getImageIndex((String) imageView.getTag())!=i){
                isSuccess = false;
                break;
            }
        }
        if(isSuccess){
            isSuccess = true;
            myHanlder.removeMessages(TIME_CHANGE);
            Toast.makeText(getContext(),"success level up",Toast.LENGTH_SHORT).show();
            myHanlder.sendEmptyMessage(NEXTLEVE);
        }
    }

    private void checkTimeEnable(){
        if(isTimeEnable){
            countTimeBaseLevel();
            myHanlder.sendEmptyMessage(TIME_CHANGE);
        }
    }

    public  void countTimeBaseLevel(){
        mTime = (int) (Math.pow(2,level)*60);
    }

    /**
     * 下一关
     */
    public void nextLeve(){
        this.removeAllViews();
        mAnimLayout = null;
        mCount ++;
        isGameSuccess = false;
        checkTimeEnable();
        initBitmap();
        initItem();
    }

    /**
     * 重新开始
     */
    public void restart(){
        isGameOver = false;
        mCount --;
        nextLeve();
    }

    /**
     * 停止游戏
     */
    public void pasue(){
        isPause = true;
        myHanlder.removeMessages(TIME_CHANGE);
    }

    /**
     * 停止游戏 (非游戏成功或者失败)
     */
    public void resume(){
        if(isPause){
            isPause = false;
            myHanlder.sendEmptyMessage(TIME_CHANGE);
        }
    }

    /**
     * 构造动画层 用于点击之后的动画
     */
    private void setUpAnimLayout(){
        if(mAnimLayout == null){
            mAnimLayout = new RelativeLayout(getContext());
            addView(mAnimLayout);
        }
    }

    private RelativeLayout mAnimLayout;

    private boolean isTimeEnable = false;

    public boolean isTimeEnable() {
        return isTimeEnable;
    }

    /**
     * 设置师傅开启时间
     * @param timeEnable
     */
    public void setTimeEnable(boolean timeEnable) {
        isTimeEnable = timeEnable;
    }

    private GamePuzzleListener gamePuzzleListener;

    public GamePuzzleListener getGamePuzzleListener() {
        return gamePuzzleListener;
    }

    public void setGamePuzzleListener(GamePuzzleListener gamePuzzleListener) {
        this.gamePuzzleListener = gamePuzzleListener;
    }

    public Handler myHanlder = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case TIME_CHANGE:
                    //游戏成功
                    if(isGameSuccess || isGameOver){
                        return;
                    }

                    if(gamePuzzleListener!=null){
                        gamePuzzleListener.timeChange(mTime);
                        if(mTime==0){
                            isGameOver = true;
                            gamePuzzleListener.gameover();
                        }

                    }
                    mTime --;
                    myHanlder.sendEmptyMessageDelayed(TIME_CHANGE,1000);
                    break;
                case NEXTLEVE:
                    level = level +1;
                    if(gamePuzzleListener!=null){
                        gamePuzzleListener.nextLeve(level);
                    }else{
                        nextLeve();
                    }
                    break;
            }
        }
    };

    private int getImageIdByTag(String tag){
        String[] split = tag.split("_");
        return Integer.parseInt(split[0]);
    }

    private int getImageIndex(String tag){
        String[] split = tag.split("_");
        return Integer.parseInt(split[1]);
    }

    //dp px
    protected int dp2px(int dpval) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpval, getResources().getDisplayMetrics());
    }

    /**
     * 游戏状态回调
     */
    public interface GamePuzzleListener{
        void nextLeve(int nextLeve);
        void timeChange(int currentTime);
        void gameover();
    }
}
