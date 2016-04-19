package com.team25.team25scouting;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import DataHolder.PostGame;


public class PostGameFrag extends Fragment {

    private OnFragmentInteractionListener mListener;

    public EditText comments;
    private Button goToFin;
    public String status = "";
    public RadioGroup radioGroup;

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

        radioGroup = (RadioGroup)view.findViewById(R.id.robo_option);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.robo_option1){
                    status = getResources().getString(R.string.robo_option1);
                    Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R.string.robo_option1), Toast.LENGTH_SHORT)
                            .show();
                }else if(checkedId == R.id.robo_option2){
                    status = getResources().getString(R.string.robo_option2);
                    Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R.string.robo_option2), Toast.LENGTH_SHORT)
                            .show();
                }else if(checkedId == R.id.robo_option3){
                    status = getResources().getString(R.string.robo_option3);
                    Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R.string.robo_option3), Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

        goToFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(radioGroup.getCheckedRadioButtonId() == -1)){
                    String comm = "";
                    if(!comments.getText().toString().equals("")){
                        comm = comments.getText().toString();
                    }
                    PostGame p = new PostGame(comm, status);
                    ma.finish(p);
                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "You haven't entered an option", Toast.LENGTH_SHORT).show();
                }
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
