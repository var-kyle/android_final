package com.afinal.group.best.androidfinal;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//MovieInfoMainActivity.java
//• Author: Maxwell Warwick
//• Course: CST2335 – Android
//• Assignment: Final
//• Date: 2018-04-18
//• Professor: Torunski
//• Purpose: Read Movie Info from XML, display data and various Android functions

/**
 * This class contains the methods to read XML data and perform various Android functions
 *
 * @author Maxwell Warwick
 * @version 1.0
 */
public class MovieInfoMainActivity extends AppCompatActivity {

    /**
     * Perform functions upon activity creation
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info_main);
        ProgressBar pB = findViewById(R.id.progressBar2);
        pB.setVisibility(View.VISIBLE);
        final MovieQuery mQ = new MovieQuery();
        ImageButton madMaxFR = findViewById(R.id.imageButton);
        ImageButton madMax = findViewById(R.id.imageButton2);
        mQ.execute();



        madMaxFR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MovieInfoMainActivity.this, "Mad Max: Fury Road", Toast.LENGTH_LONG).show();
            }
        });

        madMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MovieInfoMainActivity.this, "Mad Max", Toast.LENGTH_LONG).show();
            }
        });


    }

    /**
     * This class handles the asynctask of retrieving and updating data from XML source
     */
    public class MovieQuery extends AsyncTask<String, Integer, String> {

        String movie = "";
        String actors = "";
        String length = "";
        String desc = "";
        String rating = "";
        String genre = "";
        List<String> actorsList = new ArrayList<>();
        List<String> lengths = new ArrayList<>();
        List<String> movies = new ArrayList<>();
        List<String> descs = new ArrayList<>();
        List<String> ratings = new ArrayList<>();
        List<String> genres = new ArrayList<>();
        ListView movieList = findViewById(R.id.list_view);



        String urlString = "http://torunski.ca/CST2335/MovieInfo.xml";

        /**
         * Perform functions to be completed in background thread, to update on postExecute
         * @param args
         * @return
         */
        protected String doInBackground(String... args){
            try{URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();


                XmlPullParser parser = Xml.newPullParser();
                parser.setInput(conn.getInputStream(), null);

                while(parser.getEventType() != XmlPullParser.END_DOCUMENT) {
                    if (parser.getEventType() != XmlPullParser.START_TAG){
                        parser.next();
                        continue;
                    }
                    String name = parser.getName();

                    if(name.equals("Title")){
                        movie = parser.nextText();
                        movies.add(movie);
                        publishProgress(15);
                    }
                    if(name.equals("Actors")) {
                        actors = parser.nextText();
                        actorsList.add(actors);
                        publishProgress(20);
                    }
                    if(name.equals("Length")){
                        length = parser.nextText();
                        lengths.add(length);
                        publishProgress(30);
                    }
                    if(name.equals("Description")) {
                        desc = parser.nextText();
                        descs.add(desc);
                        publishProgress(60);
                    }
                    if(name.equals("Rating")){
                        rating = parser.nextText();
                        ratings.add(rating);
                        publishProgress(80);
                    }
                    if(name.equals("Genre")){
                        genre = parser.nextText();
                        genres.add(genre);
                        publishProgress(100);
                    }
                    parser.next();
                }
            }
            catch(IOException | XmlPullParserException i){
            }
            return "Finished";

        }

        /**
         * Show progress updates of progressBar
         * @param value
         */
        @Override
        public void onProgressUpdate(Integer... value){
            ProgressBar pB = findViewById(R.id.progressBar2);
            pB.setVisibility(View.VISIBLE);
            pB.setProgress(value[0]);
            try{
                Thread.sleep(200);}
            catch(InterruptedException i){

            }
        }

        /**
         * Use functions from doInBackground to update data views
         * @param s
         */
        @Override
        public void onPostExecute(String s){
            ProgressBar pB = findViewById(R.id.progressBar2);
            Button stats = findViewById(R.id.button);

            stats.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Min: 3.5 Max: 3.5 Average: 3.5", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });

            movieList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if (i==0){
                        createMadMaxDialog().show();
                    }
                    else if (i==1){
                        createMadMaxFRDialog().show();
                    }
                }

            });



            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                    MovieInfoMainActivity.this,
                    android.R.layout.simple_list_item_1,
                    movies );
            movieList.setAdapter(arrayAdapter);

            pB.setVisibility(View.INVISIBLE);


        }

        /**
         * Create custom dialog based on Mad Max movie info from XML
         * @return
         */
        public Dialog createMadMaxDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(MovieInfoMainActivity.this);
            LayoutInflater inflater = MovieInfoMainActivity.this.getLayoutInflater();
            View alertView = inflater.inflate(R.layout.alert, null);
            TextView titleView = alertView.findViewById(R.id.textView);
            TextView actorView = alertView.findViewById(R.id.textView2);
            TextView lengthView = alertView.findViewById(R.id.textView3);
            TextView descView = alertView.findViewById(R.id.textView4);
            TextView ratingView = alertView.findViewById(R.id.textView5);
            TextView genreView = alertView.findViewById(R.id.textView6);


            titleView.setText("Movie Title: " + movies.get(0));
            actorView.setText("Main Actors: " + actorsList.get(0));
            lengthView.setText("Duration (Min): " + lengths.get(0));
            descView.setText("Description: " + descs.get(0));
            ratingView.setText("Rating: " + ratings.get(0));
            genreView.setText("Genre: " + genres.get(0));
            builder.setView(alertView);
// Add the buttons
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });
// Create the AlertDialog
            return builder.create();

        }

        /**
         * Create custom dialog based on Mad Max: Fury Road info from XML
         * @return
         */
        public Dialog createMadMaxFRDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(MovieInfoMainActivity.this);
            LayoutInflater inflater = MovieInfoMainActivity.this.getLayoutInflater();
            View alertView = inflater.inflate(R.layout.alert, null);
            TextView titleView = alertView.findViewById(R.id.textView);
            TextView actorView = alertView.findViewById(R.id.textView2);
            TextView lengthView = alertView.findViewById(R.id.textView3);
            TextView descView = alertView.findViewById(R.id.textView4);
            TextView ratingView = alertView.findViewById(R.id.textView5);
            TextView genreView = alertView.findViewById(R.id.textView6);


            titleView.setText("Movie Title: " + movies.get(1));
            actorView.setText("Main Actors: " + actorsList.get(1));
            lengthView.setText("Duration (Min): " + lengths.get(1));
            descView.setText("Description: " + descs.get(1));
            ratingView.setText("Rating: " + ratings.get(1));
            genreView.setText("Genre: " + genres.get(1));
            builder.setView(alertView);
// Add the buttons
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });
// Create the AlertDialog
            return builder.create();

        }
    }
}
