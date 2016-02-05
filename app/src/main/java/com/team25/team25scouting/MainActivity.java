package com.team25.team25scouting;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;

import DataHolder.Autonomous;
import DataHolder.DatabaseWriter;
import DataHolder.Defense;
import DataHolder.Intro;
import DataHolder.PostGame;
import DataHolder.Team;
import DataHolder.TeleOp;

public class MainActivity extends AppCompatActivity {

    private Main_Page mainPage = Main_Page.newInstance();
    private Gen_info genInfo =  Gen_info.newInstance();
    private Auto_Fragment af = Auto_Fragment.newInstance();
    private TeleOpFragment tele = TeleOpFragment.newInstance();
    private PostGameFrag post = PostGameFrag.newInstance();
    private Rule_Fragment rf = null;
    private Autonomous auto = null;
    private Intro intro = null;
    private TeleOp teleOp = null;
    private PostGame pg = null;
    public ArrayList<Defense> d_present = null;
    public Team team = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initialize();
    }

    public void goToGen(){
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, genInfo, "General")
                .addToBackStack(null)
                .commit();
    }

    public void goToAuto(Intro i, ArrayList<Defense> list){
        this.intro = i;
        this.d_present = list;
        setUpD_List();
        Defense low_bar = new Defense("Low Bar", 0, 0, getApplicationContext());
        try{
            InputStream ims = this.getAssets().open("Low Bar.png");
            Drawable d = Drawable.createFromStream(ims, null);
            Bitmap b = ((BitmapDrawable)d).getBitmap();
            low_bar.setBitmap(b);
        }catch (Exception e){
            e.printStackTrace();
        }
        d_present.add(low_bar);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, af, "Auto")
                .addToBackStack(null)
                .commit();


    }

    public void goToRules(){
        rf = Rule_Fragment.newInstance();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, rf, "Rule")
                .addToBackStack(null)
                .commit();
    }

    public void setUpD_List(){
        for(int i = 0; i < d_present.size(); i++){
            Defense temp = d_present.get(i);
            try{
                InputStream ims = getApplicationContext().getAssets().open(temp.getName() + ".png");
                Drawable d = Drawable.createFromStream(ims, null);
                Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
                temp.setBitmap(bitmap);
            }catch (Exception e){
                e.printStackTrace();
            }
            d_present.remove(i);
            d_present.add(i, temp);
        }
    }

    public void goToTele(Autonomous a){
        this.auto = a;
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, tele, "TeleOp")
                .addToBackStack(null)
                .commit();

    }

    public void goToPost(TeleOp t){
        this.teleOp = t;
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, post, "Post Game")
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void onBackPressed(){
        if(getFragmentManager().findFragmentByTag("main") != null && getFragmentManager().findFragmentByTag("Auto")==null){
            Log.i("tag", "In General Fragment");
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, mainPage)
                    .addToBackStack(null)
                    .commit();
        }else if(getFragmentManager().findFragmentByTag("main")!=null && getFragmentManager().findFragmentByTag("TeleOp") ==null){
            Log.i("tag", "In Autonomous Fragment");
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, genInfo)
                    .addToBackStack(null)
                    .commit();
        }else if(getFragmentManager().findFragmentByTag("TeleOp")!=null && getFragmentManager().findFragmentByTag("Post Game") == null){
            Log.i("tag", "In Tele-Op Fragment");
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, af)
                    .addToBackStack(null)
                    .commit();
        }else if(getFragmentManager().findFragmentByTag("Post Game") == null && teleOp != null){
            Log.i("tag", "In Post Game fragment");
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, tele)
                    .addToBackStack(null)
                    .commit();
        }else if(rf!=null){
            Log.i("tag", "In Rules Fragment");
            rf = null;
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, mainPage)
                    .addToBackStack(null)
                    .commit();
        }else{
            Log.i("tag","Went to SuperOnBackPressed");
            getFragmentManager().popBackStackImmediate(null, getFragmentManager().POP_BACK_STACK_INCLUSIVE);
            super.onBackPressed();
        }
    }

    private void initialize(){
        getFragmentManager().popBackStackImmediate(null, getFragmentManager().POP_BACK_STACK_INCLUSIVE);
        tele = TeleOpFragment.newInstance();
        post = PostGameFrag.newInstance();
        af = Auto_Fragment.newInstance();
        genInfo = Gen_info.newInstance();

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mainPage, "main")
                .addToBackStack(null)
                .commit();
    }

    public void finish(PostGame p){
        this.pg = p;
        team = new Team(intro, auto, teleOp, pg);
        DatabaseWriter writer = new DatabaseWriter(team, getApplicationContext());
        intro = null;
        auto = null;
        teleOp = null;
        pg = null;
        writer.write();
        initialize();
    }

}
