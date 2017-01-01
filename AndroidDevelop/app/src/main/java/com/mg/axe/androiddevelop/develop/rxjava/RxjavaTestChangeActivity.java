package com.mg.axe.androiddevelop.develop.rxjava;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.mg.axe.androiddevelop.R;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/22 0022.
 */
public class RxjavaTestChangeActivity extends AppCompatActivity {

    /*
     * “终于要到牛逼的地方了，不管你激动不激动，反正我是激动了。”
     * 这句话是作者原话，本宝宝表示吓得不轻
     * 我决定把作者的例子都跑一遍 看看我能理解多少
     * 变换
     */

    private ImageView mImg;

    private List<Student> students;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_test_layout);
        mImg = (ImageView) findViewById(R.id.iv_test);
//        getBitmap();
        students = initStudent();

        //map show studentName
        showStudentsCourseByFatMap();

    }

    /**
     * map 的用法
     */
    private void getBitmap() {
        Observable.just(R.drawable.jmtest).map(new Func1<Integer, Bitmap>() {
            @Override
            public Bitmap call(Integer integer) {
                Log.i("rxjava","one");

                return getBitMap(integer);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        Log.i("rxjava","two");
                        mImg.setImageBitmap(bitmap);
                    }
                });
    }

    private Bitmap getBitMap(int resid){
        return BitmapFactory.decodeResource(getResources(),resid);
    }

    /**
     * 展示学生名字
     */
    private void showStudentname(){
        Observable.from(students).map(new Func1<Student, String>() {
            @Override
            public String call(Student student) {
                return student.getName();
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i("rxjava",s);
            }
        });
    }

    /**
     * 展示学生的课程 by map
     */
    private void showStudentsCourseByMap(){
        Observable.from(students).map(new Func1<Student, List<String>>() {
            @Override
            public List<String> call(Student student) {
                return student.getCourse();
            }
        }).subscribe(new Action1<List<String>>() {
            @Override
            public void call(List<String> strings) {
                for (int i=0;i<strings.size();i++){
                    Log.i("rxjava",strings.get(i));
                }
            }
        });
    }

    /**
     * 一对多的转化
     */
    private void showStudentsCourseByFatMap(){
        Observable.from(students).flatMap(new Func1<Student, Observable<String>>() {
            @Override
            public Observable<String> call(Student student) {
                return Observable.from(student.getCourse());
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i("rxjava_test",s);
            }
        });
    }

    private List<Student> initStudent(){
        List<Student> students = new ArrayList<>();

        Student student1 = new Student();
        List<String> cous = new ArrayList<>();
        cous.add("java");
        cous.add("C#");
        cous.add("Android");
        student1.setName("hasiki");
        student1.setCourse(cous);

        Student student2 = new Student();
        List<String> cous2 = new ArrayList<>();
        cous2.add("java");
        cous2.add("C#");
        cous2.add("Android");
        student2.setName("naruto");
        student2.setCourse(cous2);


        Student student3 = new Student();

        List<String> cous3 = new ArrayList<>();
        cous3.add("java");
        cous3.add("C#");
        cous3.add("Android");
        student3.setName("king");
        student3.setCourse(cous3);

        students.add(student1);
        students.add(student2);
        students.add(student3);
        return students;
    }



    private class Student{
        private String name;
        private List<String> course;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getCourse() {
            return course;
        }

        public void setCourse(List<String> course) {
            this.course = course;
        }
    }
}
