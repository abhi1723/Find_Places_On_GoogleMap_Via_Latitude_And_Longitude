package com.recstudentportal.www.findplacesongooglemapvialatitudeandlongitude;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    double entered_latitude_in_double,entered_longitude_in_double;
    GoogleMap map;
    boolean MAP_DRAW=false;
    EditText latitude_edit_text,longitude_edit_text;
    Button show_map_button;
    TextView error_message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        latitude_edit_text=(EditText)findViewById(R.id.latitude);
        longitude_edit_text=(EditText)findViewById(R.id.longitude);
        show_map_button=(Button)findViewById(R.id.show_map_button);
        error_message=(TextView)findViewById(R.id.error_message);
        show_map_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String entered_latitude=latitude_edit_text.getText().toString();
                String entered_longitude=longitude_edit_text.getText().toString();
                if(entered_latitude.length()!=0 && entered_longitude.length()!=0) {
                    entered_latitude_in_double = Double.parseDouble(entered_latitude);
                    entered_longitude_in_double = Double.parseDouble(entered_longitude);
                    if (entered_latitude_in_double < -90 || entered_latitude_in_double > 90) {
                        error_message.setText("Latitude can vary from -90 to +90");
                    } else if (entered_longitude_in_double < -180 || entered_longitude_in_double > 180) {
                        error_message.setText("Longitude can vary from -180 to +180");
                    } else {

                        MapFragment mapFragment=(MapFragment)getFragmentManager().findFragmentById(R.id.maps);
                        mapFragment.getMapAsync(MainActivity.this);

                        error_message.setText("Enjoy The Map");
                    }
                }
                else
                {
                    error_message.setText("Longitude & Longitude should be valid");
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MAP_DRAW=true;
        LatLng place=new LatLng(entered_latitude_in_double,entered_longitude_in_double);
        CameraPosition cameraPosition=CameraPosition.builder().target(place).zoom(17).bearing(112.5f).build();
        map=googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }
}
