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
        /**
         * Progress Bar showing progress of loading XML data
         */
        ProgressBar pB = findViewById(R.id.progressBar2);
        pB.setVisibility(View.VISIBLE);
        /**
         * Instance of MovieQuery
         */
        final MovieQuery mQ = new MovieQuery();
        /**
         * Image button with Mad Max: Fury Road poster
         */
        ImageButton madMaxFR = findViewById(R.id.imageButton);
        /**
         * Image button with Mad Max poster
         */
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

        /**
         * Temporary string to save current movie title
         */
        String movie = "";
        /**
         * Temporary string to save current main actors
         */
        String actors = "";
        /**
         * Temporary string to save current length
         */
        String length = "";
        /**
         * Temporary string to save current description
         */
        String desc = "";
        /**
         * Temporary string to save current rating
         */
        String rating = "";
        /**
         * Temporary string to save current genre
         */
        String genre = "";
        /**
         * List to save main actors from each movie
         */
        List<String> actorsList = new ArrayList<>();
        /**
         * List to save lengths of each movie
         */
        List<String> lengths = new ArrayList<>();
        /**
         * List to save titles of each movie
         */
        List<String> movies = new ArrayList<>();
        /**
         * List to save descriptions of each movie
         */
        List<String> descs = new ArrayList<>();
        /**
         * List to save ratings of each movie
         */
        List<String> ratings = new ArrayList<>();
        /**
         * List to save genres of each movie
         */
        List<String> genres = new ArrayList<>();
        /**
         * ListView to display movie titles
         */
        ListView movieList = findViewById(R.id.list_view);


        /**
         * URL for XML resource page
         */
        String urlString = "http://torunski.ca/CST2335/MovieInfo.xml";

        /**
         * Perform functions to be completed in background thread, to update on postExecute
         * @param args
         * @return
         */
        protected String doInBackground(String... args){
            try{URL url = new URL(urlString);
                /**
                 * URL Connection for page containing XML
                 */
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                /**
                 * Pull Parser to retrieve XML data
                 */
                XmlPullParser parser = Xml.newPullParser();
                parser.setInput(conn.getInputStream(), null);

                while(parser.getEventType() != XmlPullParser.END_DOCUMENT) {
                    if (parser.getEventType() != XmlPullParser.START_TAG){
                        parser.next();
                        continue;
                    }
                    /**
                     * Name of current tag
                     */
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
            /**
             * Progress bar showing progress of loading XML data
             */
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
            /**
             * ProgressBar showing progress of loading XML data
             */
            ProgressBar pB = findViewById(R.id.progressBar2);
            /**
             * Button to click and view Snackbar Message
             */
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
            /**
             * Builder for custom dialog
             */
            AlertDialog.Builder builder = new AlertDialog.Builder(MovieInfoMainActivity.this);
            /**
             * Inflater for dialog layout
             */
            LayoutInflater inflater = MovieInfoMainActivity.this.getLayoutInflater();
            /**
             * View to display dialog layout
             */
            View alertView = inflater.inflate(R.layout.alert, null);
            /**
             * View of movie title
             */
            TextView titleView = alertView.findViewById(R.id.textView);
            /**
             * View of main actors
             */
            TextView actorView = alertView.findViewById(R.id.textView2);
            /**
             * View of length
             */
            TextView lengthView = alertView.findViewById(R.id.textView3);
            /**
             * View of description
             */
            TextView descView = alertView.findViewById(R.id.textView4);
            /**
             * View of rating
             */
            TextView ratingView = alertView.findViewById(R.id.textView5);
            /**
             * View of genre
             */
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
            /**
             * Builder for custom dialog
             */
            AlertDialog.Builder builder = new AlertDialog.Builder(MovieInfoMainActivity.this);
            /**
             * Inflater for dialog layout
             */
            LayoutInflater inflater = MovieInfoMainActivity.this.getLayoutInflater();
            /**
             * View displaying dialog layout
             */
            View alertView = inflater.inflate(R.layout.alert, null);
            /**
             * View of movie title
             */
            TextView titleView = alertView.findViewById(R.id.textView);
            /**
             * View of main actors
             */
            TextView actorView = alertView.findViewById(R.id.textView2);
            /**
             * View of length
             */
            TextView lengthView = alertView.findViewById(R.id.textView3);
            /**
             * View of description
             */
            TextView descView = alertView.findViewById(R.id.textView4);
            /**
             * View of rating
             */
            TextView ratingView = alertView.findViewById(R.id.textView5);
            /**
             * View of genre
             */
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
