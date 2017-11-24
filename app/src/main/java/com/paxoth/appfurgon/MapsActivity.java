package com.paxoth.appfurgon;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MapsActivity  extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    List<LatLng> rutas=new ArrayList<>();
    private GoogleMap mMap;
    ArrayList<LatLng> MarkerPoints;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    public ArrayList<Integer> estados = new ArrayList<>();
    int rutaInicial = -1;
    int rutaFinal= 0;
    Button siguiente;
    LatLng chofer = new LatLng(-33.414985, -70.733097);
    LatLng colegio = new LatLng(-33.426552, -70.714080);
    public boolean stateChildren = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        rellenoInicialEstados();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MapsActivity.this,ChildrensActivity.class);
                in.putIntegerArrayListExtra("status",estados);
                startActivity(in);
            }
        });
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        MarkerPoints = new ArrayList<>();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);


        mapFragment.getMapAsync(this);
        rellenoRutas();
    }

    public void siguiente(View view){
        if(stateChildren){
            int saltos=1;
            int ruta_aux;
            if(estados.get(rutaFinal)==3){
                System.out.println("Estado["+rutaFinal+"] == 3");
                rutaInicial += 1;
                rutaFinal += 1;
                System.out.println("\nRUTA INICIAL: "+rutaInicial+"\nRUTA FINAL: "+rutaFinal+"\n");
                if(rutaFinal<rutas.size()) {
                    System.out.println(rutaFinal+"<="+rutas.size());
                    saltos = 0;
                /*Cambiamos el estado del estudiante a recogido*/
                    estados.set(rutaFinal-1, 1);
                    cambioDeEstados(0.9f);
                    rutaMapa(rutaInicial, rutaFinal);
                }else{
                    estados.set(estados.size()-1, 1);
                    Toast.makeText(this, "Felicidades, has llegado a tu destino", Toast.LENGTH_LONG).show();
                    stateChildren= false;
                }
            }else{
                ruta_aux =rutaFinal;
                while(estados.get(ruta_aux)==2){

                    if(ruta_aux+1<estados.size()){
                        ruta_aux +=1;
                        saltos+=1;
                    }else{
                        break;
                    }

                }
                rutaInicial += 1;
                rutaFinal += saltos;
                if(rutaFinal<rutas.size()) {
                    System.out.println(rutaFinal+"<="+rutas.size());
                    saltos = 0;
                /*Cambiamos el estado del estudiante a recogido*/
                    estados.set(rutaFinal-1, 1);
                    cambioDeEstados(0.9f);
                    rutaMapa(rutaInicial, rutaFinal);
                }else{
                    estados.set(estados.size()-1, 1);
                    Toast.makeText(this, "Felicidades, has llegado a tu destino", Toast.LENGTH_LONG).show();
                    stateChildren= false;
                }
            }
        }

    }

    public void rellenoRutas(){
        rutas.add(new LatLng(-33.420215, -70.735511));
        rutas.add(new LatLng(-33.422955, -70.738505));
        rutas.add(new LatLng(-33.425498, -70.727776));
        rutas.add(new LatLng(-33.425086, -70.721081));
        rutas.add(new LatLng(-33.422794, -70.718881));
        rutas.add(new LatLng(-33.419248, -70.716650));
        rutas.add(new LatLng(-33.419821, -70.714043));
        rutas.add(new LatLng(-33.421621, -70.712305));
        rutas.add(new LatLng(-33.419750, -70.712315));
        rutas.add(new LatLng(-33.413696, -70.710095));
        rutas.add(new LatLng(-33.414359, -70.704097));
        rutas.add(new LatLng(-33.418227, -70.707477));
        rutas.add(new LatLng(-33.419687, -70.708882));
        rutas.add(new LatLng(-33.421621, -70.712251));
        rutas.add(new LatLng(-33.423233, -70.713785));
        rutas.add(new LatLng(-33.424191, -70.707294));
        rutas.add(new LatLng(-33.425821, -70.705395));
        rutas.add(new LatLng(-33.427629, -70.706865));
        rutas.add(new LatLng(-33.428623, -70.703046));
        rutas.add(new LatLng(-33.425901, -70.700127));
        System.out.println("valor: "+ rutas.size());
    }
    public void rutaMapa(int posicionInicial, int posicionFinal){
        /*posicionInicial: referencia a donde parte un recorrido
        * posicionFinal: referencia a donde termina un recorrido*/
        MarkerPoints.clear();
        mMap.clear();

        // Adding new item to the ArrayList
        MarkerPoints.add(rutas.get(posicionInicial));
        MarkerPoints.add(rutas.get(posicionFinal));

        // Creating MarkerOptions
        MarkerOptions options = new MarkerOptions();
        MarkerOptions options2 = new MarkerOptions();

        // Setting the position of the marker
        options.position(rutas.get(posicionInicial));
        options2.position(rutas.get(posicionFinal));
        if(posicionInicial==0){
            options.title("Chofer ");
            options2.title("Niño "+String.valueOf(posicionFinal));
        }
        else if (posicionFinal<rutas.size()-1){
            options.title("Niño "+String.valueOf(posicionInicial));
            options2.title("Niño "+String.valueOf(posicionFinal));
        }
        else{
            options.title("Niño "+String.valueOf(posicionInicial));
            options2.title("Colegio");
        }

        /*
         * For the start location, the color of marker is GREEN and
         * for the end location, the color of marker is RED.
         */

        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        options2.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

        // Add new marker to the Google Map Android API V2
        mMap.addMarker(options);
        mMap.addMarker(options2);
        // Checks, whether start and end locations are captured
        LatLng origin = rutas.get(posicionInicial);
        LatLng dest = rutas.get(posicionFinal);

        // Getting URL to the Google Directions API
        String url = getUrl(origin, dest);
        Log.d("onMapClick", url);
        FetchUrl FetchUrl = new FetchUrl();

        // Start downloading json data from Google Directions API
        FetchUrl.execute(url);
        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(origin));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17));
    }
    /*rellenar el arreglo en un estado incial
    1-> Niño Recogido
    2-> Niño que no asistirá
    3-> Niño en espera
    */
    public void rellenoInicialEstados(){
        /*Rellena toda los estados con valores 3 que significa pendiente*/
        int random;
        int cont=0;
        for(int i= 0 ; i<20; i++ ){
            random = new Random().nextInt(50);
            if(random == 1){
                estados.add(2); /*1% de probabilidad de que cada niño no asista*/
            }
            else{
                estados.add(3);
                cont++;
            }
        }
        Toast.makeText(this, "¡Bienvenido! hoy debe pasar a buscar "+cont+" niños.", Toast.LENGTH_LONG).show();
    }
    public void cambioDeEstados(float probabilidad){
        /*Probabilidad del 0.5% de cambiar de pendiente a ausente*/
        int random;
        int range;
        if(probabilidad>100){
            probabilidad = 100;
            range = 1;
        }
        else if(probabilidad<0){
            probabilidad = 0;
            range = 0;
        }
        else{
            range = Math.round(100/probabilidad);
        }
        for(int i=0; i<20; i++){
            if(estados.get(i)==3){
                random = new Random().nextInt(range);
                if(random==1){
                    estados.set(i,2);
                    Toast.makeText(this, "Un apoderado ha indicado que no debe pasar a buscar a su hijo", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    /*
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        }
        else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

    }
    private String getUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Sensor enabled
        String sensor = "sensor=false";
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;
        return url;
    }

    /**
     * A method to download json data from url
     */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();
            // Connecting to url
            urlConnection.connect();
            // Reading data from url
            iStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            data = sb.toString();
            Log.d("downloadUrl", data);
            br.close();
        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    // Fetches data from url passed
    private class FetchUrl extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try {
                // Fetching the data from web service
                data = downloadUrl(url[0]);
                Log.d("Background Task data", data);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            ParserTask parserTask = new ParserTask();
            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);

        }
    }

    /**
     * A class to parse the Google Places in JSON format
     */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                Log.d("ParserTask",jsonData[0]);
                DataParser parser = new DataParser();
                Log.d("ParserTask", parser.toString());

                // Starts parsing data
                routes = parser.parse(jObject);
                Log.d("ParserTask","Executing routes");
                Log.d("ParserTask",routes.toString());

            } catch (Exception e) {
                Log.d("ParserTask",e.toString());
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points;
            PolylineOptions lineOptions = null;

            // Traversing through all the routes
            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList<>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(10);
                lineOptions.color(Color.RED);

                Log.d("onPostExecute","onPostExecute lineoptions decoded");

            }

            // Drawing polyline in the Google Map for the i-th route
            if(lineOptions != null) {
                mMap.addPolyline(lineOptions);
            }
            else {
                Log.d("onPostExecute","without Polylines drawn");
            }
        }
    }
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Marcando los puntos de inicio y término
        LatLng latLng = chofer;
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Casa chofer");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        LatLng latLng2 = colegio;
        MarkerOptions markerOptions2 = new MarkerOptions();
        markerOptions2.position(latLng2);
        markerOptions2.title("Colegio");
        markerOptions2.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
        mCurrLocationMarker = mMap.addMarker(markerOptions2);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
        }
    }
}
