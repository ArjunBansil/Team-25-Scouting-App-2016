package com.team25.team25scouting;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import DataHolder.DefenseAdapter;


public class TeleOpFragment extends Fragment {



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

        RecyclerView recList = (RecyclerView)view.findViewById(R.id.defenseRecView);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recList.setLayoutManager(llm);
        MainActivity ma = (MainActivity)getActivity();
        DefenseAdapter da = new DefenseAdapter(ma.d_present);
        recList.setAdapter(da);




        return view;
    }

}
