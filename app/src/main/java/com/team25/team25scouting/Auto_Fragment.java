package com.team25.team25scouting;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Auto_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Auto_Fragment extends Fragment {
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


        return view;
    }

}
