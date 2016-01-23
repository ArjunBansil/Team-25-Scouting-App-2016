package com.team25.team25scouting;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.InputStream;
import java.util.ArrayList;

import DataHolder.Autonomous;
import DataHolder.Defense;
import DataHolder.Intro;
import DataHolder.PostGame;
import DataHolder.TeleOp;

public class MainActivity extends AppCompatActivity {

    private Main_Page mainPage = new Main_Page();
    private Gen_info genInfo = new Gen_info();
    private Auto_Fragment af = new Auto_Fragment();
    private TeleOpFragment tele = new TeleOpFragment();
    private Autonomous auto;
    private Intro intro;
    private TeleOp teleOp;
    private PostGame pg;
    public ArrayList<Defense> d_present;

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
        d_present.add(new Defense("Low Bar", 0,0,getApplicationContext()));
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, af, "Auto")
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

    private void initialize(){
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mainPage, "main")
                .addToBackStack(null)
                .commit();
    }

}
