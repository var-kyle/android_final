package com.afinal.group.best.androidfinal;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
//DoctorForm.java
//• Author: Keith Morris
//• Course: CST2335 – Android
//• Assignment: Final
//• Date: 2018-04-18
//• Professor: Torunski
//• Purpose: Display patient intake fields for the purpose of saving to a database(which is not fully implemented).
**/


public class DoctorForm extends Activity {


    private EditText e1, e2, e3, e4, e5, e6;
    /**
     * Widget that activates the Toast message.
     */
    private Button submit;
    private ListView listV;
    private SQLiteDatabase db;
    private SQLiteOpenHelper dbHelper;
    private ContentValues newData;
    private Cursor results;
    private ArrayList<String> arrList = new ArrayList<>();


    /**
     * onCreate method houses the code that enables the submit button to display a Toast message when selected.
     * The database implentatation is not fully functional, unfortunately.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_form);

        submit = (Button) findViewById(R.id.submitButtonDoctor);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "No Connection, Submission Failed.", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }

        });
//        e1 = (EditText) findViewById(R.id.fullName);
//        e2 = (EditText) findViewById(R.id.address);
//        e3 = (EditText) findViewById(R.id.birthday);
//        e4 = (EditText) findViewById(R.id.phone);
//        e5 = (EditText) findViewById(R.id.healthCard);
//        e6 = (EditText) findViewById(R.id.info);
//
//
//        listV = (ListView) findViewById(R.id.patientList);
//
//        adapter = new PatientAdapter(this);
//
//        listV.setAdapter(adapter);
//
//        dbHelper = new DatabaseHelper(DoctorForm.this);
//        db = dbHelper.getWritableDatabase();
//
//        results = db.query(DatabaseHelper.TABLE_NAME, new String[]{ DatabaseHelper.KEY_ID_COLUMN,
//        DatabaseHelper.FULL_NAME, DatabaseHelper.ADDRESS, DatabaseHelper.BIRTHDAY, DatabaseHelper.PHONE, DatabaseHelper.HEALTH_CARD, DatabaseHelper.INFO}, null, null, null, null, null);
//
//        results.moveToFirst();
//
//
//        while (!results.isAfterLast()) {
//            Log.i("ChatWindow", "SQL MESSAGE:" + results.getString(results.getColumnIndex(DatabaseHelper.FULL_NAME)));
//            Log.i("ChatWindow", "Cursor’s  column count =" + results.getColumnCount());
//            arrList.add(results.getString(results.getColumnIndex(DatabaseHelper.FULL_NAME)));
//            arrList.add(results.getString(results.getColumnIndex(DatabaseHelper.ADDRESS)));
//            arrList.add(results.getString(results.getColumnIndex(DatabaseHelper.BIRTHDAY)));
//            arrList.add(results.getString(results.getColumnIndex(DatabaseHelper.PHONE)));
//            arrList.add(results.getString(results.getColumnIndex(DatabaseHelper.HEALTH_CARD)));
//            arrList.add(results.getString(results.getColumnIndex(DatabaseHelper.INFO)));
//
//            results.moveToNext();
//        }
//        for(int i = 0; i<results.getColumnCount();i++) {
//            Log.i("Activity Message", "Columns returned are " + results.getColumnName(i));
//        }
//
//       submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                newData = new ContentValues();
//                String fName = e1.getText().toString();
//                String addy = e2.getText().toString();
//                String bday = e3.getText().toString();
//                String phoneNumber = e4.getText().toString();
//                String hCard = e5.getText().toString();
//                String priorInfo = e6.getText().toString();
//
//                newData.put(DatabaseHelper.FULL_NAME, fName);
//                newData.put(DatabaseHelper.ADDRESS, addy);
//                newData.put(DatabaseHelper.BIRTHDAY, bday);
//                newData.put(DatabaseHelper.PHONE, phoneNumber);
//                newData.put(DatabaseHelper.HEALTH_CARD, hCard);
//                newData.put(DatabaseHelper.INFO, priorInfo);
//                db.insert(DatabaseHelper.TABLE_NAME, null, newData);
//
//                arrList.addAll(Arrays.asList(fName, addy, bday, phoneNumber, hCard, priorInfo));
//
//                adapter.notifyDataSetChanged();
//
//
//
//                adapter.notifyDataSetChanged();
//
        //EditText.setText("");

//            }
//        });

    }
//             private class PatientAdapter extends ArrayAdapter<String> {
//
//                public PatientAdapter(Context ctx){
//                    super(ctx, 0);
//                }
//                public int getCount(){
//                    return arrList.size();
//                }
//                public String getItem(int position){
//                    return arrList.get(position);
//                }
//                public View getView(int position, View convertView, ViewGroup parent){
//                    LayoutInflater inflater = DoctorForm.this.getLayoutInflater();
//                    View result = null;
//                    //if(position%2 == 0) {
//                        result = inflater.inflate(R.layout.activity_patient_intake_form_main, null);
//
//
//                    TextView message = (TextView) result.findViewById(R.id.patient_text);
//                    message.setText(getItem(position));
//                    return result;
//                }
//                public long getID(int position) {
//                    return position;
//                }
//                }
//                @Override
//                protected void onDestroy() {
//                    super.onDestroy();
//                    db.close();
//                    // Log.i("Start Activity", "In onDestroy()");
//                }
}



