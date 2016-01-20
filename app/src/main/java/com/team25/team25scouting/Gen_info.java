package com.team25.team25scouting;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import DataHolder.Intro;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Gen_info.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Gen_info#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Gen_info extends Fragment {

    public EditText teamNum, matchNum, s_name;
    public Intro intro;

    public Gen_info() {

    }

    public static Gen_info newInstance(String param1, String param2) {
        Gen_info fragment = new Gen_info();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_gen, container, false);
        Button go = (Button)view.findViewById(R.id.goToAuto);
        teamNum = (EditText)view.findViewById(R.id.teamNumber);
        s_name = (EditText)view.findViewById(R.id.scoutName);
        matchNum = (EditText)view.findViewById(R.id.matchNumber);
        InputMethodManager imm = (InputMethodManager)view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(teamNum, InputMethodManager.SHOW_IMPLICIT);
        final MainActivity ma = (MainActivity)getActivity();

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((teamNum.getText().toString().equals("")) && s_name.getText().toString().equals("") && matchNum.getText().toString().equals("")){
                    Snackbar.make(view, "Fill in everything", Snackbar.LENGTH_SHORT).show();
                }else{
                    Log.i("tag", teamNum.getText().toString() + " " + s_name.getText().toString() + " " + matchNum.getText().toString());
                    int teamNumber = Integer.parseInt(teamNum.getText().toString());
                    int matchNumber = Integer.parseInt(matchNum.getText().toString());
                    String scoutName = s_name.getText().toString();
                    intro = new Intro(matchNumber, teamNumber, scoutName);
                    ma.goToAuto(intro);
                }
            }
        });
        return view;
    }

    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
