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

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public boolean onCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.toolbar_menu, m );
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem mi){
        switch(mi.getItemId()){
            case R.id.activity1:
                        Intent intent = new Intent(LaunchActivity.this, OCTranspoMainActivity.class);
                        startActivity(intent);
                break;
            case R.id.activity2:
                        //Intent intent = new Intent(LaunchActivity.this, IntakeFormMainActivity.class);
                        //startActivity(intent);
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

    public Dialog onCreateMovieDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
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
