package com.team25.team25scouting;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import DataHolder.Defense;
import DataHolder.DefenseList;
import DataHolder.Intro;

public class Gen_info extends Fragment {

    public EditText teamNum, matchNum, s_name;
    public CheckBox cheval, d_bridge, moat, p_culli, ramp, r_wall, r_terrain, s_port;
    public Intro intro;

    public Gen_info() {

    }

    public static Gen_info newInstance() {
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
        getActivity().setTitle("General Information");
        cheval = (CheckBox)view.findViewById(R.id.cheval);
        d_bridge = (CheckBox)view.findViewById(R.id.d_bridge);
        moat = (CheckBox)view.findViewById(R.id.moat);
        p_culli = (CheckBox)view.findViewById(R.id.p_culli);
        ramp = (CheckBox)view.findViewById(R.id.ramp);
        r_wall = (CheckBox)view.findViewById(R.id.r_wall);
        r_terrain = (CheckBox)view.findViewById(R.id.r_terrain);
        s_port = (CheckBox)view.findViewById(R.id.s_port);
        Button go = (Button)view.findViewById(R.id.goToAuto);
        teamNum = (EditText)view.findViewById(R.id.teamNumber);
        s_name = (EditText)view.findViewById(R.id.scoutName);
        matchNum = (EditText)view.findViewById(R.id.matchNumber);
        InputMethodManager imm = (InputMethodManager)view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(teamNum, InputMethodManager.SHOW_IMPLICIT);
        final MainActivity ma = (MainActivity)getActivity();
        final ImageView img = (ImageView)view.findViewById(R.id.d_pic);

        final ArrayList<CheckBox> c_boxes = new ArrayList<CheckBox>();
        c_boxes.add(cheval);
        c_boxes.add(d_bridge);
        c_boxes.add(moat);
        c_boxes.add(p_culli);
        c_boxes.add(ramp);
        c_boxes.add(r_wall);
        c_boxes.add(r_terrain);
        c_boxes.add(s_port);

        for (int i = 0; i < c_boxes.size(); i++){
            final int count = i;
            c_boxes.get(i).setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    String temp = c_boxes.get(count).getText().toString();
                    String file = temp + ".png";
                    try{
                        InputStream ims = v.getContext().getAssets().open(file);
                        Drawable d = Drawable.createFromStream(ims, null);
                        img.setImageDrawable(d);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    return true;
                }
            });
        }

        cheval.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(p_culli.isChecked()){
                        Toast.makeText(buttonView.getContext(), "Can't choose another Category A defense", Toast.LENGTH_SHORT).show();
                    }
                    p_culli.setChecked(false);
                }
            }
        });

        p_culli.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    if (cheval.isChecked()) {
                        Toast.makeText(buttonView.getContext(), "Can't choose another Category A defense", Toast.LENGTH_SHORT).show();
                    }
                    cheval.setChecked(false);
                }
            }
        });

        moat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(ramp.isChecked()){
                        Toast.makeText(buttonView.getContext(), "Can't choose another Category B defense", Toast.LENGTH_SHORT).show();
                    }
                    ramp.setChecked(false);
                }
            }
        });

        ramp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(moat.isChecked()){
                        Toast.makeText(buttonView.getContext(), "Can't choose another Category B defense", Toast.LENGTH_SHORT).show();
                    }
                    moat.setChecked(false);
                }
            }
        });

        d_bridge.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(s_port.isChecked()){
                        Toast.makeText(buttonView.getContext(), "Can't choose another Category C defense", Toast.LENGTH_SHORT).show();
                    }
                    s_port.setChecked(false);
                }
            }
        });

        s_port.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(d_bridge.isChecked()){
                        Toast.makeText(buttonView.getContext(), "Can't choose another Category C defense", Toast.LENGTH_SHORT).show();
                    }
                    d_bridge.setChecked(false);
                }
            }
        });

        r_wall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(r_terrain.isChecked()){
                        Toast.makeText(buttonView.getContext(), "Can't choose another Category D defense", Toast.LENGTH_SHORT).show();
                    }
                    r_terrain.setChecked(false);
                }
            }
        });

        r_terrain.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(r_wall.isChecked()){
                        Toast.makeText(buttonView.getContext(), "Can't choose another Category D defense", Toast.LENGTH_SHORT).show();
                    }
                    r_wall.setChecked(false);
                }
            }
        });




        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = 0;
                for (int i = 0; i < c_boxes.size(); i++){
                    if(c_boxes.get(i).isChecked()){
                        count++;
                    }
                }
                if((teamNum.getText().toString().equals("")) && s_name.getText().toString().equals("") && matchNum.getText().toString().equals("")){
                    Snackbar.make(view, "Fill in everything", Snackbar.LENGTH_SHORT).show();
                }else if(count != 4){
                    Snackbar.make(view, "Select 4 defenses", Snackbar.LENGTH_SHORT).show();
                }
                else{
                    Log.i("tag", teamNum.getText().toString() + " " + s_name.getText().toString() + " " + matchNum.getText().toString());
                    int teamNumber = Integer.parseInt(teamNum.getText().toString());
                    int matchNumber = Integer.parseInt(matchNum.getText().toString());
                    String scoutName = s_name.getText().toString();
                    intro = new Intro(matchNumber, teamNumber, scoutName);
                    final ArrayList<CheckBox> c_boxes = new ArrayList<CheckBox>();
                    c_boxes.add(cheval);
                    c_boxes.add(d_bridge);
                    c_boxes.add(moat);
                    c_boxes.add(p_culli);
                    c_boxes.add(ramp);
                    c_boxes.add(r_wall);
                    c_boxes.add(r_terrain);
                    c_boxes.add(s_port);

                    ArrayList<Defense> d_list = new ArrayList<Defense>();

                    for(int i = 0; i < c_boxes.size(); i++){
                        if(c_boxes.get(i).isChecked()){
                            Defense temp = new Defense(c_boxes.get(i).getText().toString(),
                                    0,0,v.getContext());
                            d_list.add(temp);
                        }
                    }
                    ma.goToAuto(intro, d_list);
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
        void onFragmentInteraction(Uri uri);
    }
}
