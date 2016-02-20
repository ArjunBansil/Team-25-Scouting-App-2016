package com.team25.team25scouting;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

/**
 * Created by Arjun Bansil on 1/30/2016.
 */
public class PasswordEntry extends DialogFragment {

    public Dialog dialog;
    public EditText e;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        dialog = new Dialog(getActivity());

        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        dialog.setContentView(R.layout.fragment_password);
        dialog.show();

        Button b = (Button)dialog.findViewById(R.id.confirmPassword);
        e = (EditText)dialog.findViewById(R.id.password_entry);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e.getText().toString().equals(getActivity().getApplicationContext().getResources().getString(R.string.password))){
                    try{
                        File directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"Scouting App");
                        if(!directory.exists()){
                            directory.mkdir();
                            Log.i("tag", "Directoy has been created holmes");
                        }else {
                            Log.i("tag", "Directory is already here fam");
                        }
                        File file = new File(directory, "ScoutingInfo.csv");
                        file.delete();
                        Snackbar.make(dialog.getWindow().getDecorView(), "File deleted", Snackbar.LENGTH_SHORT).show();
                        dialog.cancel();
                    }catch (Exception e){
                       e.printStackTrace();
                    }
                }else{
                    Snackbar.make(dialog.getWindow().getDecorView(), "Wrong Password", Snackbar.LENGTH_SHORT).show();
                }
            }
        });



        return dialog;

    }







}
