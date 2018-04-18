package com.afinal.group.best.androidfinal;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class OCTranspoMainActivity extends Activity {

    public static final String APPID = "223eb5c3";
    public static final String APIKEY = "ab27db5b435b8c8819ffb8095328e775";
    public static String routeSummaryForStopUrl = "https://api.octranspo1.com/v1.2/GetRouteSummaryForStop";
    public static String nextTripsForStopUrl = "https://api.octranspo1.com/v1.2/GetNextTripsForStop";

    private Button btn;
    private EditText editTxt;
    private ListView stopInfo;
    private ArrayList<String> busInfo;

    /**
     * creates the list view of search results as well as the bottom search bar.
     * the list view is populated from oc transpo's api
     * search for stop numbers
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_octranspo_main);

        btn = this.findViewById(R.id.btn_search);
        editTxt = this.findViewById(R.id.stop_num);
        stopInfo = this.findViewById(R.id.stop_info);
        busInfo = new ArrayList<>();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //https://api.octranspo1.com/v1.2/GetRouteSummaryForStop?appID=223eb5c3&&apiKey=ab27db5b435b8c8819ffb8095328e775&stopNo=3050
                String stopNum = editTxt.getText().toString();
                String stopUrl = routeSummaryForStopUrl +
                        "?appID=" +APPID +
                        "&apiKey=" + APIKEY +
                        "&stopNo=" + stopNum;

                busInfo.clear();

                AsyncBusInfo task = new AsyncBusInfo();
                task.execute(stopUrl);
            }
        });
    }

    public class AsyncBusInfo extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... strings) {
            String siteUrl = strings[0];
            String busNum = "";
            String busDesc = "";
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
                            if(tagName.equals("RouteNo"))
                            {
                                xpp.next();
                                String txt = xpp.getText();
                                busNum = txt;
                            }
                            if(tagName.equals("RouteHeading"))
                            {
                                xpp.next();
                                String txt = xpp.getText();
                                busDesc = txt;
                                String result = busNum + " - " + busDesc;
                                busInfo.add(result);
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
            //super.onPostExecute(s);

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    OCTranspoMainActivity.this,
                    android.R.layout.simple_list_item_1,
                    busInfo );
            stopInfo.setAdapter(arrayAdapter);

            if (busInfo == null || busInfo.size() == 0) {
                Toast.makeText(getApplicationContext(), "This stop does not exist!", Toast.LENGTH_SHORT).show();
            }

            stopInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Bundle routeInfo = new Bundle();
                    String[] s = busInfo.get(position).split("-");
                    routeInfo.putInt("routenum", Integer.parseInt(s[0].trim()));
                    routeInfo.putInt("stopnum", Integer.parseInt(editTxt.getText().toString()));
                    Intent intent = new Intent(OCTranspoMainActivity.this, RouteInfoActivity.class);
                    intent.putExtras(routeInfo);
                    startActivity(intent);
                }
            });
        }
    }
}
