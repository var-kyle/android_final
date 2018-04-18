package com.afinal.group.best.androidfinal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



/**
 //  OptometristForm.java
 //• Author: Keith Morris
 //• Course: CST2335 – Android
 //• Assignment: Final
 //• Date: 2018-04-18
 //• Professor: Torunski
 //• Purpose: Display Optometrist patient intake fields for the purpose of saving to a database(which is not fully implemented).
 **/
public class OptometristForm extends Activity {

    /**
     * Widget that activates the Toast message.
     */
    private Button submit;


    /**
     * onCreate method houses the code that enables the submit button to display a Toast message when selected.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optometrist_form);

        submit = (Button) findViewById(R.id.submitButtonOpto);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "No Connection, Submission Failed.", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }

        });

    }
}
