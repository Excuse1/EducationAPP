package shengzheng.educationapp.BaiDuDome;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class DemoApplication extends Application {
//PullToRefreshLayout   上拉刷新下拉下载   gatt
    @Override
    public void onCreate() {
        super.onCreate();
        //QQ登录
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .build();//构建 ImageLoader
        ImageLoader.getInstance().init(config);//全局初始化

        //百度地图   在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        SDKInitializer.initialize(this);
    }

}