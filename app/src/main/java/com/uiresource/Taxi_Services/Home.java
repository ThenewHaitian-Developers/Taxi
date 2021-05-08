package com.uiresource.Taxi_Services;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class Home extends BaseActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private Button btn_request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitleHome(R.id.toolbar, R.id.iv_title, R.drawable.ic_burger, R.drawable.logo_actbar);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btn_request = (Button) findViewById(R.id.bt_request);
        btn_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this, SelectDriver.class));
            }
        });
    }
    public Bitmap getBitmapFromView(String title, int dotBg) {

        LinearLayout llmarker = (LinearLayout) findViewById(R.id.ll_marker);
        TextView markerImageView = (TextView) findViewById(R.id.tv_title);
        markerImageView.setText(title);
        View dot = (View) findViewById(R.id.dot_marker);
        dot.setBackgroundResource(dotBg);

        llmarker.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        Bitmap bitmap = Bitmap.createBitmap(llmarker.getMeasuredWidth(), llmarker.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        llmarker.layout(0, 0, llmarker.getMeasuredWidth(), llmarker.getMeasuredHeight());
        llmarker.draw(canvas);
        return bitmap;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        boolean success = googleMap.setMapStyle(new MapStyleOptions(getResources()
                .getString(R.string.style_json)));

        if (!success) {
            Log.e("Style", "Style parsing failed.");
        }
        LatLng p_a_p= new LatLng(18.539725,-72.321746);
        mMap.addMarker(new MarkerOptions().position(p_a_p).icon(BitmapDescriptorFactory.fromBitmap(getBitmapFromView("Set Pickup Location", R.drawable.dot_pickup))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p_a_p, 15f));


    }
}
