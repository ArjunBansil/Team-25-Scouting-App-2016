package com.team25.team25scouting;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

/**
 * Created by Arjun Bansil on 1/30/2016.
 */
public class PasswordEntry extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.fragment_password,null))
                .setPositiveButton("Enter Password", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id){
                        File directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"Scouting App");
                        if(!directory.exists()){
                            directory.mkdir();
                            Log.i("tag", "Directoy has been created holmes");
                        }else {
                            Log.i("tag", "Directory is already here fam");
                        }
                        File file = new File(directory, "ScoutingInfo.csv");
                        file.delete();
                        Toast.makeText(getActivity().getApplicationContext(), "File Deleted", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            }
                        }
                );
        return builder.create();

    }







}
