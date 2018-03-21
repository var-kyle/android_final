package com.afinal.group.best.androidfinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LaunchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        //OC Transpo open handler
        Button btn = this.findViewById(R.id.btnOCTranspo);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaunchActivity.this, OCTranspoMainActivity.class);
                startActivity(intent);
            }
        });

        //Intake form open handler
        btn = this.findViewById(R.id.btnIntake);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(LaunchActivity.this, IntakeFormMainActivity.class);
                //startActivity(intent);
            }
        });

        //Movie info open handler
        btn = this.findViewById(R.id.btnMovieInfo);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(LaunchActivity.this, MovieInfoMainActivity.class);
                //startActivity(intent);
            }
        });

        //Multiple choice quiz open handler
        btn = this.findViewById(R.id.btnMultipleChoice);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(LaunchActivity.this, MultipleChoiceMainActivity.class);
                //startActivity(intent);
            }
        });

    }
}
