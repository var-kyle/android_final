package com.afinal.group.best.androidfinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



/**
 //  PatientIntakeFormMain.java
 //• Author: Keith Morris
 //• Course: CST2335 – Android
 //• Assignment: Final
 //• Date: 2018-04-18
 //• Professor: Torunski
 //• Purpose: Displays ListView that is designed to show saved patient information (not functional). Also provides button functionality for
 //* properly navigating through the particular intake form activities.
 **/
public class PatientIntakeFormMain extends Activity {

    /**
     * Widgets that let the useer navigate to the three particular intake forms.
     */
    private Button doctorForm;
    private Button dentistForm;
    private Button optForm;


    /**
     * onCreate houses code that enables navigation between Activities.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_intake_form_main);
        doctorForm = (Button) findViewById(R.id.Button01);
        dentistForm = (Button) findViewById(R.id.Button02);
        optForm = (Button) findViewById(R.id.Button03);

        doctorForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doctorIntent = new Intent(PatientIntakeFormMain.this, DoctorForm.class);
                startActivityForResult(doctorIntent, 50);
            }
        });
        dentistForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doctorIntent = new Intent(PatientIntakeFormMain.this, DentistForm.class);
                startActivityForResult(doctorIntent, 50);
            }
        });
        optForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doctorIntent = new Intent(PatientIntakeFormMain.this, OptometristForm.class);
                startActivityForResult(doctorIntent, 50);
            }
        });


    }
}
