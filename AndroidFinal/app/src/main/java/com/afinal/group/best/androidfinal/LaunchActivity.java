package com.afinal.group.best.androidfinal;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

<<<<<<< HEAD



=======
//LaunchActivity.java
//• Author: Maxwell Warwick
//• Course: CST2335 – Android
//• Assignment: Final
//• Date: 2018-04-18
//• Professor: Torunski
//• Purpose: Start activity for the application with toolbar to direct to individual activities

/**
 * This class creates the start activity for the application with a toolbar for directing to
 * activities
 */
>>>>>>> be5e1ebd739a5e849e86fa088abf540e44406f3f
public class LaunchActivity extends AppCompatActivity {

    /**
     * Perform functions upon creation of activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        /**
         * Toolbar for launch activity
         */
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    /**
     * Perform inflation of toolbar_menu layout
     * @param m
     * @return
     */
    public boolean onCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.toolbar_menu, m );
        return true;

    }

    /**
     * Handle tasks based on clicking options in toolbar
     * @param mi
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem mi){
        switch(mi.getItemId()){
            case R.id.activity1:
                        Intent intent = new Intent(LaunchActivity.this, OCTranspoMainActivity.class);
                        startActivity(intent);
                break;
            case R.id.activity2:
                        Intent intent2 = new Intent(LaunchActivity.this, PatientIntakeFormMain.class);
                        startActivity(intent2);
                break;
            case R.id.activity3:
                        Intent intent3 = new Intent(LaunchActivity.this, MovieInfoMainActivity.class);
                        startActivity(intent3);
                break;
            case R.id.activity4:
                        //Intent intent = new Intent(LaunchActivity.this, MultipleChoiceMainActivity.class);
                        //startActivity(intent);
                break;
            case R.id.movie_about:
                onCreateMovieDialog().show();
        }
        return true;

    }

    /**
     * Create dialog with information on the Movie Info Activity
     * @return
     */
    public Dialog onCreateMovieDialog() {
        /**
         * Builder for custom dialog
         */
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        /**
         * Inflater for dialog layout
         */
        LayoutInflater inflater = this.getLayoutInflater();
        /**
         * View containing inflated layout
         */
        View v = inflater.inflate(R.layout.movie_about, null);
        builder.setView(v);
// Add the buttons
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
// Create the AlertDialog
        return builder.create();

    }
}
