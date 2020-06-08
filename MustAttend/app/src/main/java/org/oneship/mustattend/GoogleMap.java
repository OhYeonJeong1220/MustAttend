package org.oneship.mustattend;

import android.app.FragmentManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMap extends AppCompatActivity implements OnMapReadyCallback {

    private FragmentManager fragmentManager;
    private MapFragment mapFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);

        fragmentManager = getFragmentManager();
        mapFragment = (MapFragment)fragmentManager.findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(com.google.android.gms.maps.GoogleMap googleMap) {
        // 지도 만들어지면 마커 생성
        LatLng location = new LatLng(36.666163, 127.494578); // 라마다호텔 위치
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title("라마다호텔");
        markerOptions.snippet("호텔");
        markerOptions.position(location); // 위도 경도 가져옴
        googleMap.addMarker(markerOptions);

        //googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16)); // 16정도로 zoom 가까이보고싶으면 숫자 올리기
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 16));
    }
}























