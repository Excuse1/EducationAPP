package shengzheng.educationapp.MyFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

import shengzheng.educationapp.NavNearChild.Choice;
import shengzheng.educationapp.NavNearChild.ObtainMessage;
import shengzheng.educationapp.R;


/**
 * 附近
 * Created by join on 2016/9/17.
 */
public class NavNearbyFragment extends Fragment implements View.OnClickListener {
    private MapView mMapView = null;  //百度
    private BaiduMap mBaiduMap;
    //定位相关
    private LocationClient mLocationClient;
    private MyLocationListener myLocationListener;

    private boolean isFirstIn = true;
    private ImageView iv_icon_nearby;  //消息
    private View view;
    private ImageView iv_temp_nearby;  //选择

    private class MyLocationListener implements BDLocationListener{

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            MyLocationData mLocationData = new MyLocationData.Builder()//
            .accuracy(bdLocation.getRadius())//
            .latitude(bdLocation.getLatitude())//
            .longitude(bdLocation.getLongitude())//
            .build();

            mBaiduMap.setMyLocationData(mLocationData);
            //MyLocationConfiguration configuration = new MyLocationConfiguration(LocationClientOption.LocationMode)
            if (isFirstIn){
                LatLng latlng = new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());
                MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latlng);
                mBaiduMap.animateMapStatus(msu);
                isFirstIn = false;
            }
        }
    }
    @Nullable
   @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        view = inflater.inflate(R.layout.nav_nearby_fragment, null);
        initView();
        //初始化定位
        initLocation();
        return view;
    }

    private void initLocation() {
        mLocationClient = new LocationClient(getActivity());
        myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);

        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// 打开gps
        option.setIsNeedAddress(true);
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setAddrType("detail"); // 设置了仍然获取不到详细地址
        option.setScanSpan(1000);
        //mLocClient.setLocOption(option);
    }

    private void initView() {
        //获取地图控件引用
        mMapView = (MapView) view.findViewById(R.id.b_mapView);
        mBaiduMap = mMapView.getMap();
        //设置进入的地图比例 500米
        MapStatusUpdate factoy = MapStatusUpdateFactory.zoomTo(15.0f);
        mBaiduMap.setMapStatus(factoy);
        /*
         *地图标注
         */
       //定义Maker坐标点
        LatLng point = new LatLng(39.963175, 116.400244);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.huaji);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);


        iv_icon_nearby = (ImageView) view.findViewById(R.id.iv_icon_nearby);
        iv_icon_nearby.setOnClickListener(this);
        iv_temp_nearby = (ImageView) view.findViewById(R.id.iv_temp_nearby);
        iv_temp_nearby.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_icon_nearby:
                Intent intent = new Intent(getActivity(), ObtainMessage.class);
                startActivity(intent);
                break;
            case R.id.iv_temp_nearby:
                Intent intent2 = new Intent(getActivity(), Choice.class);
                startActivity(intent2);
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mBaiduMap.setMyLocationEnabled(true);
        if (!mLocationClient.isStarted()){  //判断是否启动
            mLocationClient.start();

        }
    }

    @Override
    public void onStop() {
        super.onStop();
        mBaiduMap.setMyLocationEnabled(false);
        mLocationClient.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }


}
