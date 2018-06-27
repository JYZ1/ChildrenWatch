package com.jyz.childrenwatch;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */

public class PositioningFragment extends Fragment {
    public LocationClient mLocationClient;
    private TextView mPositionText;
    private MapView mapView;
    private BaiduMap baiduMap;
    private boolean isFirstLocate = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SDKInitializer.initialize( getActivity().getApplicationContext() );
        View view = inflater.inflate( R.layout.activity_tab1, container, false );
        mLocationClient = new LocationClient(getContext().getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        mapView = (MapView) view.findViewById( R.id.map_view );
        baiduMap = mapView.getMap();
        baiduMap.setMyLocationEnabled( true );
        mPositionText = (TextView) view.findViewById( R.id.position_text_view );
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add( Manifest.permission.ACCESS_FINE_LOCATION );
        }
        if (ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.READ_PHONE_STATE ) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add( Manifest.permission.READ_PHONE_STATE );
        }
        if (ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add( Manifest.permission.WRITE_EXTERNAL_STORAGE );
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray( new String[permissionList.size()] );
            ActivityCompat.requestPermissions(this.getActivity(), permissions, 1 );
        } else {
            requestLocation();
        }

        return view;
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            StringBuilder currentPosition = new StringBuilder();
            currentPosition.append( "纬度：" ).append( location.getLatitude() ).append( "\n" );
            currentPosition.append( "经线：" ).append( location.getLongitude() ).append( "\n" );
            currentPosition.append( "国家：" ).append( location.getCountry() ).append( "\n" );
            currentPosition.append( "省：" ).append( location.getProvince() ).append( "\n" );
            currentPosition.append( "市：" ).append( location.getCity() ).append( "\n" );
            currentPosition.append( "区：" ).append( location.getDistrict() ).append( "\n" );
            currentPosition.append( "街道：" ).append( location.getStreet() ).append( "\n" );
            currentPosition.append( "定位方式：" );
            if (location.getLocType() == BDLocation.TypeGpsLocation) {
                currentPosition.append( "GPS" );
            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
                currentPosition.append( "网络" );
            }
            mPositionText.setText( currentPosition );
            if (location.getLocType() == BDLocation.TypeGpsLocation
                    || location.getLocType() == BDLocation.TypeNetWorkLocation) {
                navigateTo( location );
            }
        }
    }

    private void navigateTo(BDLocation location) {
        if (isFirstLocate) {
            Toast.makeText(this.getContext(), "nav to " + location.getAddrStr(), Toast.LENGTH_SHORT ).show();
            MapStatusUpdate update = MapStatusUpdateFactory.zoomTo( 12.0f );
            baiduMap.setMapStatus( update );
            LatLng ll = new LatLng( location.getLatitude(), location.getLongitude() );
            update = MapStatusUpdateFactory.newLatLng( ll );
            baiduMap.animateMapStatus( update );
            isFirstLocate = false;
        }

        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.latitude( location.getLatitude() );
        locationBuilder.longitude( location.getLongitude() );
        MyLocationData locationData = locationBuilder.build();
        baiduMap.setMyLocationData( locationData );
    }

    private void requestLocation() {
        initLocation();
        mLocationClient.start();
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan( 5000 );
        option.setIsNeedAddress( true );
        mLocationClient.setLocOption( option );
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled( false );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this.getContext(), R.string.must_agree_to_all_permissions_to_use_this_program, Toast.LENGTH_SHORT ).show();
                            return;
                        }
                    }
                    requestLocation();
                } else {
                    Toast.makeText( this.getContext(), R.string.An_unknown_error_has_occurred, Toast.LENGTH_SHORT ).show();
                }
                break;
            default:
        }
    }
}