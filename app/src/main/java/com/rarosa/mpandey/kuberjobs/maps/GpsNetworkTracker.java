package com.rarosa.mpandey.kuberjobs.maps;

import java.util.Timer;
import java.util.TimerTask;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import android.os.Looper;

public class GpsNetworkTracker extends Thread {

    Timer timer1;
    LocationManager lm;
    LocationResult locationResult;

    private Context mContext;

    //flag for gps status
    boolean gps_enabled=false;
    //flag for network status
    boolean network_enabled=false;
    //flag for GPS and network status
    boolean canGetLocation = false;

    //Location net_loc=null, gps_loc=null;

    Location location = null;
    double latitude; // latitude
    double longitude; // longitude

    private int desiredAccuracy = 65; // 65 meter
    private int timeout = 60*1000; // 65 sec

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1; // 1 minute

    // The maximum delay to receive the last location in milliseconds
    private static final long MAX_DELAY_TIMER_FOR_LAST_DATA = 20000;

    public GpsNetworkTracker(Context context) {
        this.mContext = context;
    }

    public void run() {
        this.getLocation();
    }

    /**
     * Function to get the user's current location
     *
     * @return
     */
    public void getLocation()
    {
        //use LocationResult callback class to pass location value from MyLocation to user code.
        if(lm==null)
            lm = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);

        //exceptions will be thrown if provider is not permitted.
        try{
            gps_enabled=lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            network_enabled=lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        }catch(Exception e){
            e.printStackTrace();
        }

        //don't start listeners if no provider is enabled
        if (!gps_enabled && !network_enabled) {
            locationResult.gotLocation(null);
            return;
        }
        else {
            this.canGetLocation = true;
        }

        try {
            if (Looper.myLooper() == null) Looper.prepare();
            if(gps_enabled)
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListenerGps, Looper.myLooper());
            if(network_enabled)
                lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListenerNetwork, Looper.myLooper());
            timer1=new Timer();
            timer1.schedule(new GetLastLocation(Looper.myLooper()), MAX_DELAY_TIMER_FOR_LAST_DATA);
            Looper.loop();
        }catch (Exception e) {
            locationResult.gotLocation(null);
            if (timer1 != null) timer1.cancel();
            Looper.myLooper().quitSafely();
            e.printStackTrace();
            return;
        }
    }

    private void validateLocation(Location location) {
        if (location.getAccuracy() > desiredAccuracy || (System.currentTimeMillis()-location.getTime()) > this.timeout)
            return; // continue, accuracy or age is not valid
        else { // accuracy ok, stop
            timer1.cancel();
            locationResult.gotLocation(location);
            lm.removeUpdates(locationListenerGps);
            lm.removeUpdates(locationListenerNetwork);
            Looper.myLooper().quitSafely();
        }
    }

    /**
     * Function to show settings alert dialog On pressing Settings button will
     * lauch Settings Options
     * */
    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

        // Setting Dialog Title
        alertDialog.setTitle("GPS is settings");

        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

        // On pressing Settings button
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        mContext.startActivity(intent);
                    }
                });

        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        // Showing Alert Message
        alertDialog.show();
    }

    private LocationListener locationListenerGps = new LocationListener() {
        public void onLocationChanged(Location location) {
            validateLocation(location);
        }
        public void onProviderDisabled(String provider) {}
        public void onProviderEnabled(String provider) {}
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.i("myInfo", String.valueOf(status));
        }
    };

    private LocationListener locationListenerNetwork = new LocationListener() {
        public void onLocationChanged(Location location) {
            validateLocation(location);
        }
        public void onProviderDisabled(String provider) {}
        public void onProviderEnabled(String provider) {}
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.i("myInfo", String.valueOf(status));
        }
    };

    /**
     * Stop using GPS listener Calling this function will stop using GPS in your
     * app
     * */
    public void stopUsingGpsAndNetwork() {
        if (lm != null) {
            lm.removeUpdates(locationListenerGps);
            lm.removeUpdates(locationListenerNetwork);
        }
    }

    /**
     * Function to get latitude
     * */
    public double getLatitude() {
        if (location != null) {
            latitude = location.getLatitude();
        }

        // return latitude
        return latitude;
    }

    /**
     * Function to get longitude
     * */
    public double getLongitude() {
        if (location != null) {
            longitude = location.getLongitude();
        }

        // return longitude
        return longitude;
    }

    /**
     * Function to check GPS/wifi enabled
     *
     * @return boolean
     * */
    public boolean canGetLocation() {
        return this.canGetLocation;
    }

    public static abstract class LocationResult {
        public abstract void gotLocation(Location location);
    }

    class GetLastLocation extends TimerTask {
        private Looper parentLooper; // MyLocation

        GetLastLocation(Looper parentLooper) {
            this.parentLooper = parentLooper;
        }

        @Override
        public void run() {
            lm.removeUpdates(locationListenerGps);
            lm.removeUpdates(locationListenerNetwork);

            Location net_loc=null, gps_loc=null;
            location = null;
            if(gps_enabled)
                gps_loc=lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (network_enabled)
                net_loc=lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            }

            //if there are both values use the latest one
            if (gps_loc != null && net_loc != null) {
                if (gps_loc.getTime() > net_loc.getTime())
                    locationResult.gotLocation(gps_loc);
                else
                    locationResult.gotLocation(net_loc);
            } else {
                if (gps_loc != null)
                    locationResult.gotLocation(gps_loc);
                else if (net_loc != null)
                    locationResult.gotLocation(net_loc);
                else
                    locationResult.gotLocation(null);
            }

            parentLooper.quitSafely();
            timer1.cancel();
        }
    }

    private void gotLocation(Location newLocation){
        if(newLocation != null){
            //if(activity != null){
            //    activity.updateDirectionOnObjects(newLocation);
            //}

            location.setLatitude(newLocation.getLatitude());
            location.setLongitude(newLocation.getLongitude());
        }
    }


}