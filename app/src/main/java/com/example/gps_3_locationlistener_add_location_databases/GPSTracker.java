package com.example.gps_3_locationlistener_add_location_databases;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GPSTracker implements LocationListener {


    Context context;
    public GPSTracker(Context c) {
        context=c;
    }


    public Location getlocation()
    {

        if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(context,"permission not granted",Toast.LENGTH_LONG).show();
            return null;

        }
        LocationManager locationManager= (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnable=locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if(isGPSEnable)
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,6000,10,this);

            Location l=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            String url="http://10.0.2.2/Project/3Android_Php_tackingapp/PHPtrackingApp.php?logi="+String.valueOf(l.getLatitude())
                    +"&latit="+ String.valueOf(l.getLongitude() +"&device_id=555");


            new MyAsyncTaskgetNews().execute(url);


        }else
            {
                Toast.makeText(context,"pleace Enable GPS",Toast.LENGTH_SHORT).show();
            }
        return null;

    }

    @Override
    public void onLocationChanged(Location location)
    {

    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }


    @Override
    public void onProviderEnabled(String provider) {

    }


    @Override
    public void onProviderDisabled(String provider) {

    }


    // get news from server
    public class MyAsyncTaskgetNews extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            //before works
        }
        @Override
        protected String  doInBackground(String... params) {
            // TODO Auto-generated method stub
            try {
                String NewsData;
                //define the url we have to connect with
                URL url = new URL(params[0]);
                //make connect with url and send request
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                //waiting for 7000ms for response
                urlConnection.setConnectTimeout(7000);//set timeout to 5 seconds

                try {
                    //getting the response data
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    //convert the stream to string
                    NewsData = ConvertInputToStringNoChange(in);
                    //send to display data
                    publishProgress(NewsData);
                } finally {
                    //end connection
                    urlConnection.disconnect();
                }

            }catch (Exception ex){}
            return null;
        }
        protected void onProgressUpdate(String... progress) {

            try {

                //JSONObject jsonObject= new JSONObject(progress[0]);
                //display response data
                Toast.makeText(context,progress[0], Toast.LENGTH_LONG).show();

            } catch (Exception ex) {
            }


        }

        protected void onPostExecute(String  result2){


        }




    }

    // this method convert any stream to string
    public static String ConvertInputToStringNoChange(InputStream inputStream) {

        BufferedReader bureader=new BufferedReader( new InputStreamReader(inputStream));
        String line ;
        String linereultcal="";

        try{
            while((line=bureader.readLine())!=null) {

                linereultcal+=line;

            }
            inputStream.close();


        }catch (Exception ex){}

        return linereultcal;
    }

}
