package com.team25.team25scouting;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import DataHolder.Defense;
import DataHolder.DefenseAdapter;
import DataHolder.TeleOp;


public class TeleOpFragment extends Fragment {

    private Button goToPost, incHigh, decHigh, incLow, decLow;
    private CheckBox outerworks, courtyard, batter;
    private CheckBox towerBreach, towerClimb;
    public TextView shot_low, shot_high;
    public int shotsLow = 0, shotsHigh = 0;
    public DefenseAdapter da;

    public TeleOpFragment() {
    }

    public static TeleOpFragment newInstance(String param1, String param2) {
        TeleOpFragment fragment = new TeleOpFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tele_op, container, false);

        getActivity().setTitle("Tele-Op");

        goToPost = (Button)view.findViewById(R.id.goToPost);
        incHigh = (Button)view.findViewById(R.id.incHighT);
        incLow = (Button)view.findViewById(R.id.incLowT);
        decHigh = (Button)view.findViewById(R.id.decHighT);
        decLow = (Button)view.findViewById(R.id.decLowT);
        outerworks = (CheckBox)view.findViewById(R.id.outerworks);
        courtyard = (CheckBox)view.findViewById(R.id.courtyard);
        batter = (CheckBox)view.findViewById(R.id.batter);
        towerBreach = (CheckBox)view.findViewById(R.id.towerBreach);
        towerClimb = (CheckBox)view.findViewById(R.id.towerClimb);
        towerClimb.setVisibility(View.INVISIBLE);
        shot_low = (TextView)view.findViewById(R.id.shotsLow_T);
        shot_high = (TextView)view.findViewById(R.id.shotsHigh_T);

        incHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shotsHigh++;
                shot_high.setText(shotsHigh+"");
            }
        });

        decHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shotsHigh > 0){
                    shotsHigh--;
                }
                shot_high.setText(shotsHigh+"");
            }
        });

        incLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shotsLow++;
                shot_low.setText(shotsLow+"");
            }
        });

        decLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(shotsLow > 0){
                    shotsLow--;
                }
                shot_low.setText(""+shotsLow);
            }
        });

        towerBreach.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    towerClimb.setVisibility(View.VISIBLE);
                }else {
                    towerClimb.setVisibility(View.INVISIBLE);
                    towerClimb.setChecked(false);
                }
            }
        });

        //This is the RecyclerView stuff

        RecyclerView recList = (RecyclerView)view.findViewById(R.id.defenseRecView);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recList.setLayoutManager(llm);
        final MainActivity ma = (MainActivity)getActivity();
        da = new DefenseAdapter(ma.d_present);
        recList.setAdapter(da);


        goToPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Defense> l = (ArrayList<Defense>) da.getList();
                String defenses = "";
                if(outerworks.isChecked()){
                    defenses+="Outerworks ";
                }else if(batter.isChecked()){
                    defenses+="Batter ";
                }else if(courtyard.isChecked()){
                    defenses+="Courtyard";
                }
                TeleOp tele = new TeleOp(shotsHigh, shotsLow, l, towerBreach.isChecked(), towerClimb.isChecked(), defenses);
                ma.goToPost(tele);

            }
        });


        return view;
    }

}
