package com.team25.team25scouting;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joanzapata.pdfview.PDFView;


public class Rule_Fragment extends Fragment {


    public Rule_Fragment() {
        // Required empty public constructor
    }

    public static Rule_Fragment newInstance() {
        Rule_Fragment fragment = new Rule_Fragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rule, container, false);
        PDFView pdfView = (PDFView)view.findViewById(R.id.pdfView);

        String assetName = "game_manual.pdf";
        pdfView.fromAsset(assetName)
                .defaultPage(1)
                .showMinimap(true)
                .enableSwipe(true)
                .load();

        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
