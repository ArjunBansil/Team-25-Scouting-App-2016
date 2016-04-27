package com.team25.team25scouting;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class Main_Page extends Fragment {

    CardView sharing, delete, rules;


    private OnFragmentInteractionListener mListener;

    public Main_Page() {

    }

    public static Main_Page newInstance() {
        Main_Page fragment = new Main_Page();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_main, container, false);
        getActivity().setTitle("Main Page");
        ImageView img = (ImageView)view.findViewById(R.id.sharingPic);
        ImageView img2 = (ImageView)view.findViewById(R.id.deletePic);
        ImageView img3 = (ImageView)view.findViewById(R.id.rulePic);
        sharing = (CardView)view.findViewById(R.id.sharing);
        delete = (CardView)view.findViewById(R.id.delete);
        rules = (CardView)view.findViewById(R.id.rules);
        final MainActivity ma = (MainActivity)getActivity();

        sharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    File directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "Scouting App");

                    if(!directory.exists()){
                        Log.i("tag", "Directory doesn't exist");
                        boolean b = directory.mkdir();
                        if(b){
                            Log.i("tag", "Directory created");
                        }else if(!b){
                            Log.i("tag", "Directory not CREATED");
                        }
                    }

                    File file = new File(directory, "ScoutingInfo.csv");
                    if(file.length() > 0){
                        Intent share = new Intent();
                        share.setAction(Intent.ACTION_SEND);
                        share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                        share.setType("text/plain");
                        Intent chooser = Intent.createChooser(share, "Scouting App");
                        startActivity(chooser);
                    }else{
                        Log.e("tag", "File doesn't exist");
                        Snackbar.make(view, "File is not long enough or not created", Snackbar.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Snackbar snackbar = Snackbar.make(view, "File is not long enough, or it doesn't exist", Snackbar.LENGTH_SHORT);
                    View temp = snackbar.getView();
                    TextView t = (TextView)view.findViewById(android.support.design.R.id.snackbar_text);
                    t.setTextColor(Color.RED);
                    snackbar.show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PasswordEntry p = new PasswordEntry();
                p.show(getFragmentManager().beginTransaction(), "Confirm Password");

            }
        });

        rules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ma.goToRules();
            }
        });

        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.addRecord);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view, "Button was pressed", Snackbar.LENGTH_SHORT).show();
                ma.goToGen();

            }
        });

        try{
            InputStream ims = view.getContext().getAssets().open("sharing.png");
            Drawable d = Drawable.createFromStream(ims, null);
            img.setImageDrawable(d);

            ims = view.getContext().getAssets().open("delete.jpg");
            d = Drawable.createFromStream(ims, null);
            img2.setImageDrawable(d);

            ims = view.getContext().getAssets().open("rules.jpg");
            d = Drawable.createFromStream(ims, null);
            img3.setImageDrawable(d);

        }catch (IOException e){
            e.printStackTrace();
        }
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }




    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
