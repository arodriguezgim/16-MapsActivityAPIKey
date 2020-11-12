package org.iesch.alberto.mapsactivityapikey;

import androidx.fragment.app.FragmentActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        //Le decimos que los estilos de mapa que queremos que use estan en el recurso json que nos hemos generado.
        try {
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style_json));

            if (!success) {
                Log.e("TAG", "Existe el recurso pero está mal");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("TAG", "El objeto Json no existe en la ubicacion que le indici. Error: ", e);
        }



        mMap = googleMap;
        //El tipo de mapa que queremos que cargue es Normal. (Puede ser Satelite, hibrido..., etc)
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Nos creamos el objeto LatLng que posiciona TERUEL
        LatLng teruel = new LatLng(40.336192, -1.107440);

        // Nos creamos el objeto LatLng que posiciona las tres oficinas que tenemos
        LatLng oficina1 = new LatLng(40.336139, -1.104079);
        LatLng oficina2 = new LatLng(40.341565, -1.107254);
        LatLng oficina3 = new LatLng(40.332114, -1.108394);

        //Situamos los marcadores de las tres oficinas en el mapa. Ademas los personalizamos.
        mMap.addMarker(
                new MarkerOptions()
                        .position(oficina1)
                        .title("Oficina 1")
                        .snippet("Tfno: 987 654 321")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.alfiler))

        );
        mMap.addMarker(
                new MarkerOptions()
                        .position(oficina2)
                        .title("Oficina 2")
                        .snippet("Tfno: 874 234 765")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.alfiler))

        );
        mMap.addMarker(
                new MarkerOptions()
                        .position(oficina3)
                        .title("Oficina 3")
                        .snippet("Tfno: 978 237 432")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.alfiler))

        );
        mMap.moveCamera(CameraUpdateFactory.newLatLng(teruel));
    }
}