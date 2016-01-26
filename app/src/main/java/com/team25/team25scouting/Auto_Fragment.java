package com.team25.team25scouting;


import android.media.Image;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import DataHolder.Autonomous;
import DataHolder.Defense;

public class Auto_Fragment extends Fragment {
    public RadioButton breach, reach;
    public Spinner sp;
    public Button incHigh, incLow, decHigh, decLow, goNext;
    public TextView shotHigh, shotLow;
    public ImageView pic;
    public int shotsMadeHigh = 0, shotsMadeLow = 0;

    public Auto_Fragment() {

    }

    public static Auto_Fragment newInstance() {
        Auto_Fragment fragment = new Auto_Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auto, container, false);
        getActivity().setTitle("Add Record");
        pic = (ImageView)view.findViewById(R.id.def_pic);
        breach = (RadioButton)view.findViewById(R.id.defBreach);
        reach = (RadioButton)view.findViewById(R.id.defReach);
        breach.setVisibility(View.INVISIBLE);
        incHigh = (Button)view.findViewById(R.id.incHigh);
        incLow = (Button)view.findViewById(R.id.incLow);
        decHigh = (Button)view.findViewById(R.id.decHigh);
        decLow = (Button)view.findViewById(R.id.decLow);
        shotLow = (TextView)view.findViewById(R.id.shotsLow_A);
        shotHigh = (TextView)view.findViewById(R.id.shotsHigh_A);
        goNext = (Button)view.findViewById(R.id.goToTele);

        final MainActivity ma = (MainActivity)getActivity();
        ArrayList<Defense> list = ma.d_present;
        ArrayList<String> temp = new ArrayList<String>();
        for(int i = 0; i<list.size(); i++){
            temp.add(list.get(i).getName());
        }
        sp = (Spinner)view.findViewById(R.id.defenseContainer);
        sp.setVisibility(View.INVISIBLE);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext()
                , android.R.layout.simple_spinner_item, temp);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<Defense> list = ma.d_present;
                Defense d = null;
                for(int i =0; i<list.size();i++ ){
                    if(list.get(i).getName().equals(parent.getSelectedItem().toString())){
                        d= list.get(i);
                    }
                }
                pic.setImageBitmap(d.returnBitmap());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        reach.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    breach.setVisibility(View.VISIBLE);
                } else {
                    breach.setVisibility(View.INVISIBLE);
                    breach.setChecked(false);
                    sp.setVisibility(View.INVISIBLE);
                }
            }
        });

        breach.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sp.setVisibility(View.VISIBLE);
                }else {
                    sp.setVisibility(View.INVISIBLE);
                }
            }
        });

        incHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shotsMadeHigh < 3) {
                    shotsMadeHigh++;
                    shotHigh.setText("" + shotsMadeHigh);
                }
            }
        });

        incLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shotsMadeLow < 3) {
                    shotsMadeLow++;
                    shotLow.setText("" + shotsMadeLow);
                }
            }
        });

        decHigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shotsMadeHigh > 0) {
                    shotsMadeHigh--;
                    shotHigh.setText("" + shotsMadeHigh);
                }
            }
        });

        decLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shotsMadeLow > 0) {
                    shotsMadeLow--;
                    shotLow.setText("" + shotsMadeLow);
                }
            }
        });

        goNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String defense = (String) sp.getSelectedItem();
                Autonomous auto = new Autonomous(defense, shotsMadeHigh, shotsMadeLow, breach.isChecked(), reach.isChecked());
                ma.goToTele(auto);
            }
        });

        return view;
    }

}
