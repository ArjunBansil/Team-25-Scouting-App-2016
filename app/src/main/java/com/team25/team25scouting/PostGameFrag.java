package com.team25.team25scouting;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import DataHolder.PostGame;


public class PostGameFrag extends Fragment {

    private OnFragmentInteractionListener mListener;

    public EditText comments;
    private Button goToFin;

    public PostGameFrag() {
    }


    public static PostGameFrag newInstance() {
        PostGameFrag fragment = new PostGameFrag();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post, container, false);
        comments = (EditText)view.findViewById(R.id.comments);
        goToFin = (Button)view.findViewById(R.id.goToFin);

        final MainActivity ma = (MainActivity)getActivity();

        goToFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comm = "";
                if(!comments.getText().toString().equals("")){
                    comm = comments.getText().toString();
                }
                PostGame p = new PostGame(comm);
                ma.finish(p);
            }
        });



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
