package com.afinal.group.best.androidfinal;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.afinal.group.best.androidfinal.OCTranspoMainActivity.APIKEY;
import static com.afinal.group.best.androidfinal.OCTranspoMainActivity.APPID;
import static com.afinal.group.best.androidfinal.OCTranspoMainActivity.nextTripsForStopUrl;

public class RouteInfoActivity extends Activity {

    private int routeNum, stopNum;
    private TextView routeTitle;

    TextView routeDest, routeLat, routeLon, routeGps, routeStart, routeAdjStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_info);

        Bundle routeInfo = getIntent().getExtras();

        routeNum = routeInfo.getInt("routenum");
        stopNum = routeInfo.getInt("stopnum");

        routeTitle = this.findViewById(R.id.route_title);
        routeTitle.setText("Route: " + routeNum);

        routeDest = this.findViewById(R.id.route_dest);
        routeLat = this.findViewById(R.id.route_lat);
        routeLon = this.findViewById(R.id.route_lon);
        routeGps = this.findViewById(R.id.route_gps);
        routeStart = this.findViewById(R.id.route_start);
        routeAdjStart = this.findViewById(R.id.route_adj_start);

        AsyncRouteInfo task = new AsyncRouteInfo();
        String stopUrl = nextTripsForStopUrl +
                "?appID=" +APPID +
                "&apiKey=" + APIKEY +
                "&stopNo=" + stopNum +
                "&routeNo=" + routeNum;

        task.execute(stopUrl);
    }

    public class AsyncRouteInfo extends AsyncTask<String, Integer, String> {

        private String routedest, routestart, routegps, routelat, routelon, routeadjstart;

        @Override
        protected String doInBackground(String... strings) {
            String siteUrl = strings[0];

            try {
                URL url = new URL(siteUrl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inStream = urlConnection.getInputStream();

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput( inStream  , "UTF-8");

                int eventType = xpp.getEventType();
                while(eventType != XmlPullParser.END_DOCUMENT) {
                    switch(eventType)
                    {
                        case XmlPullParser.START_TAG:
                            String tagName = xpp.getName();
                            if(tagName.equals("TripDestination"))
                            {
                                xpp.next();
                                routedest = xpp.getText();
                            }
                            if(tagName.equals("TripStartTime"))
                            {
                                xpp.next();
                                routestart = xpp.getText();
                            }
                            if(tagName.equals("GPSSpeed"))
                            {
                                xpp.next();
                                routegps = xpp.getText();
                            }
                            if(tagName.equals("Latitude"))
                            {
                                xpp.next();
                                routelat = xpp.getText();
                            }
                            if(tagName.equals("Longitude"))
                            {
                                xpp.next();
                                routelon = xpp.getText();
                            }
                            if(tagName.equals("AdjustedScheduleTime"))
                            {
                                xpp.next();
                                routeadjstart = xpp.getText();
                            }
                            break;
                    }
                    xpp.next();
                    eventType = xpp.getEventType();
                }



            } catch (MalformedURLException ex) {

            } catch (IOException ex) {

            } catch (XmlPullParserException ex) {

            }

            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            routeDest.setText(routedest);
            routeLat.setText(routelat);
            routeLon.setText(routelon);
            routeGps.setText(routegps);
            routeStart.setText(routestart);
            routeAdjStart.setText(routeadjstart);
        }
    }
}
