package com.team25.team25scouting;


import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.app.DialogFragment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;


public class LeaveConfirmation extends DialogFragment {
    public Dialog dialog;
    public EditText e;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        dialog = new Dialog(getActivity());

        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        dialog.setContentView(R.layout.fragment_leave_confirmation);
        dialog.show();

        Button b = (Button)dialog.findViewById(R.id.c_password);
        e = (EditText)dialog.findViewById(R.id.p_entry);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e.getText().toString().equals(getActivity().getApplicationContext().getResources().getString(R.string.password))){
                    System.exit(0);
                }
            }
        });



        return dialog;
    }


}
