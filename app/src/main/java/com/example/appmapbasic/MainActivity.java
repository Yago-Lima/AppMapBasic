package com.example.appmapbasic;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MainActivity extends AppCompatActivity {
    private MapView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Configuration.getInstance().load(MainActivity.this, PreferenceManager.getDefaultSharedPreferences(MainActivity.this));

        map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);

        Marker marcador1 = new Marker(map);
        GeoPoint point1 = new GeoPoint(-10.3292, -48.2913);
        marcador1.setIcon(getResources().getDrawable(R.drawable.iftologo));
        marcador1.setPosition(point1);
        marcador1.setTitle("Palmas");
        marcador1.setDraggable(true);
        map.getOverlays().add(marcador1);

        // Adicionar o marcador do Cristo Redentor
        Marker marcador2 = new Marker(map);
        GeoPoint pointCristo = new GeoPoint(-22.9519, -43.2105);
        marcador2.setPosition(pointCristo);
        marcador2.setTitle("Cristo Redentor");

        marcador1.setOnMarkerDragListener(new Marker.OnMarkerDragListener() {
            @Override
            public void onMarkerDrag(Marker marker) {
                GeoPoint newPosition =  marker.getPosition();
                Toast.makeText(MainActivity.this
                        , "Nova posição: "
                                + newPosition.getLatitude() + ", " + newPosition.getLongitude(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                GeoPoint newPosition = marker.getPosition();
                Toast.makeText(MainActivity.this
                        , newPosition.getLatitude() + ", " + newPosition.getLongitude(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMarkerDragStart(Marker marker) {
                Toast.makeText(MainActivity.this, "Vamos começar o passeio ", Toast.LENGTH_SHORT).show();
            }
        });

        map.getOverlays().add(marcador2);
        map.getController().setCenter(point1);
        map.getController().setZoom(10.0);

    }//onCreate

    @Override
    protected void onResume() {
        super.onResume();
        map.onPause();
    }//onResume

}//MainActivity