package com.team25.team25scouting;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Main_Page.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Main_Page#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Main_Page extends Fragment {

    CardView sharing, delete;


    private OnFragmentInteractionListener mListener;

    public Main_Page() {
        // Required empty public constructor
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
        ImageView img = (ImageView)view.findViewById(R.id.sharingPic);
        ImageView img2 = (ImageView)view.findViewById(R.id.deletePic);
        sharing = (CardView)view.findViewById(R.id.sharing);
        delete = (CardView)view.findViewById(R.id.delete);

        sharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "Scouting App");

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
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.addRecord);
        final MainActivity ma = (MainActivity)getActivity();
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
