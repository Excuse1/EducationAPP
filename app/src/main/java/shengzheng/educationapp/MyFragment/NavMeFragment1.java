package shengzheng.educationapp.MyFragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shengzheng.educationapp.ContentAdapterData.NavMeFragmentContentProvider;
import shengzheng.educationapp.NavNearChild.ObtainMessage;
import shengzheng.educationapp.MyAdapter.NavMeFragmentItemAdapter;
import shengzheng.educationapp.R;
import shengzheng.educationapp.RoundIcon.CircleImg;
import shengzheng.educationapp.RoundIcon.FileUtil;
import shengzheng.educationapp.RoundIcon.NetUtil;
import shengzheng.educationapp.RoundIcon.SelectPicPopupWindow;
import shengzheng.educationapp.ViewPagerTranslate.Indent;

/**
 * Created by john on 2016/9/17.
 */
public class NavMeFragment1 extends Fragment implements View.OnClickListener {

    private Button zhifu_nav, sou_ke_nav, finish_nav, property_nav;  //
    private Context mContext;
    private CircleImg avatarImg;// 头像图片
    private SelectPicPopupWindow menuWindow; // 自定义的头像编辑弹出框
    // 上传服务器的路径【一般不硬编码到程序中】
    private String imgUrl = "baidu.com";
    private static final String IMAGE_FILE_NAME = "avatarImage.jpg";// 头像文件名称
    private String urlpath;            // 图片本地路径
    private String resultStr = "";    // 服务端返回结果集
    private static ProgressDialog pd;// 等待进度圈
    private static final int REQUESTCODE_PICK = 0;        // 相册选图标记
    private static final int REQUESTCODE_TAKE = 1;        // 相机拍照标记
    private static final int REQUESTCODE_CUTTING = 2;    // 图片裁切标记

    private ListView lv_content_item;
    private ListView lv_content_item1;
    private ImageButton imageButtonMessage;
    private View view;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.nav_me_fragment, null);
        mContext = getActivity();
        initViews();
        IncrementAdapterData(getActivity());
        IncrementAdapterData1(getActivity());
        return view;
    }

    /**
     * 初始化页面控件
     */
    private void initViews() {
        avatarImg = (CircleImg) view.findViewById(R.id.avatarImg);
        avatarImg.setOnClickListener(NavMeFragment1.this);
        lv_content_item = (ListView) view.findViewById(R.id.lv_content_item);
        lv_content_item1 = (ListView) view.findViewById(R.id.lv_content_item1);
        imageButtonMessage = (ImageButton) view.findViewById(R.id.message_have_nav_me);
        imageButtonMessage.setOnClickListener(this);
        zhifu_nav = (Button) view.findViewById(R.id.zhifu_nav);
        sou_ke_nav = (Button) view.findViewById(R.id.sou_ke_nav);
        finish_nav = (Button) view.findViewById(R.id.finish_nav);
        property_nav = (Button) view.findViewById(R.id.property_nav);

        zhifu_nav.setOnClickListener(this);
        sou_ke_nav.setOnClickListener(this);
        finish_nav.setOnClickListener(this);
        property_nav.setOnClickListener(this);

    }

    //第二行的数据
    private void IncrementAdapterData1(Context context) {
        List<NavMeFragmentContentProvider> list = new ArrayList<>();
        NavMeFragmentContentProvider content = new NavMeFragmentContentProvider(R.mipmap.myguanzhu, "我的关注", R.mipmap.go_xiao);
        NavMeFragmentContentProvider content1 = new NavMeFragmentContentProvider(R.mipmap.mysetting, "系统设置", R.mipmap.go_xiao);
        list.add(content);
        list.add(content1);
        NavMeFragmentItemAdapter adapter = new NavMeFragmentItemAdapter(list, context);
        lv_content_item1.setAdapter(adapter);
    }

    //第一行的数据
    private void IncrementAdapterData(Context context) {
        List<NavMeFragmentContentProvider> list = new ArrayList<>();
        NavMeFragmentContentProvider content = new NavMeFragmentContentProvider(R.mipmap.myorder, "我的预约", R.mipmap.go_xiao);
        NavMeFragmentContentProvider content1 = new NavMeFragmentContentProvider(R.mipmap.mywallet, "我的钱包", R.mipmap.go_xiao);
        NavMeFragmentContentProvider content2 = new NavMeFragmentContentProvider(R.mipmap.myvoucher, "我的代金劵", R.mipmap.go_xiao);
        NavMeFragmentContentProvider content3 = new NavMeFragmentContentProvider(R.mipmap.myagency, "我的机构", R.mipmap.go_xiao);
        list.add(content);
        list.add(content1);
        list.add(content2);
        list.add(content3);
        NavMeFragmentItemAdapter adapter = new NavMeFragmentItemAdapter(list, context);
        lv_content_item.setAdapter(adapter);
    }


    /**
     * 点击事件，使用 Bundle传值
     * 更换头像点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 更换头像点击事件
            case R.id.avatarImg:
                menuWindow = new SelectPicPopupWindow(mContext, itemsOnClick);
                menuWindow.showAtLocation(view.findViewById(R.id.fl_upper),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.message_have_nav_me:
                Intent intent = new Intent(getActivity(), ObtainMessage.class);
                startActivity(intent);
                break;
            case R.id.zhifu_nav:
                Intent intent1 = new Intent(getActivity(), Indent.class);
                Bundle bundle = new Bundle();
                bundle.putString("tag", "intent1");
                intent1.putExtras(bundle);
                startActivity(intent1);
                break;
            case R.id.sou_ke_nav:
                Intent intent2 = new Intent(getActivity(), Indent.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("tag", "intent2");
                intent2.putExtras(bundle1);
                startActivity(intent2);
                break;
            case R.id.finish_nav:
                Intent intent3 = new Intent(getActivity(), Indent.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("tag", "intent3");
                intent3.putExtras(bundle2);
                startActivity(intent3);
                break;
            case R.id.property_nav:
                Intent intent4 = new Intent(getActivity(), Indent.class);
                Bundle bundle3 = new Bundle();
                bundle3.putString("tag", "intent4");
                intent4.putExtras(bundle3);
                startActivity(intent4);
                break;

        }
    }

    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            menuWindow.dismiss();
            switch (v.getId()) {
                // 拍照
                case R.id.takePhotoBtn:
                    Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    //下面这句指定调用相机拍照后的照片存储的路径
                    takeIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME)));
                    startActivityForResult(takeIntent, REQUESTCODE_TAKE);
                    break;
                // 相册选择图片
                case R.id.pickPhotoBtn:
                    Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
                    // 如果朋友们要限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
                    pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    startActivityForResult(pickIntent, REQUESTCODE_PICK);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            case REQUESTCODE_PICK:// 直接从相册获取
                try {
                    startPhotoZoom(data.getData());
                } catch (NullPointerException e) {
                    e.printStackTrace();// 用户点击取消操作
                }
                break;
            case REQUESTCODE_TAKE:// 调用相机拍照
                File temp = new File(Environment.getExternalStorageDirectory() + "/" + IMAGE_FILE_NAME);
                startPhotoZoom(Uri.fromFile(temp));
                break;
            case REQUESTCODE_CUTTING:// 取得裁剪后的图片
                if (data != null) {
                    setPicToView(data);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, REQUESTCODE_CUTTING);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param picdata
     */
    private void setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        if (extras != null) {
            // 取得SDCard图片路径做显示
            Bitmap photo = extras.getParcelable("data");
            Drawable drawable = new BitmapDrawable(null, photo);
            urlpath = FileUtil.saveFile(mContext, "temphead.jpg", photo);
            avatarImg.setImageDrawable(drawable);

            // 新线程后台上传服务端
            pd = ProgressDialog.show(mContext, null, "正在上传图片，请稍候...");
            new Thread(uploadImageRunnable).start();
        }
    }

    /**
     * 使用HttpUrlConnection模拟post表单进行文件
     * 上传平时很少使用，比较麻烦
     * 原理是： 分析文件上传的数据格式，然后根据格式构造相应的发送给服务器的字符串。
     */
    Runnable uploadImageRunnable = new Runnable() {
        @Override
        public void run() {

            if (TextUtils.isEmpty(imgUrl)) {
                Toast.makeText(mContext, "还没有设置上传服务器的路径！", Toast.LENGTH_SHORT).show();
                return;
            }

            Map<String, String> textParams = new HashMap<String, String>();
            Map<String, File> fileparams = new HashMap<String, File>();

            try {
                // 创建一个URL对象
                URL url = new URL(imgUrl);
                textParams = new HashMap<String, String>();
                fileparams = new HashMap<String, File>();
                // 要上传的图片文件
                File file = new File(urlpath);
                fileparams.put("image", file);
                // 利用HttpURLConnection对象从网络中获取网页数据
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                // 设置连接超时（记得设置连接超时,如果网络不好,Android系统在超过默认时间会收回资源中断操作）
                conn.setConnectTimeout(5000);
                // 设置允许输出（发送POST请求必须设置允许输出）
                conn.setDoOutput(true);
                // 设置使用POST的方式发送
                conn.setRequestMethod("POST");
                // 设置不使用缓存（容易出现问题）
                conn.setUseCaches(false);
                conn.setRequestProperty("Charset", "UTF-8");//设置编码
                // 在开始用HttpURLConnection对象的setRequestProperty()设置,就是生成HTML文件头
                conn.setRequestProperty("ser-Agent", "Fiddler");
                // 设置contentType
                conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + NetUtil.BOUNDARY);
                OutputStream os = conn.getOutputStream();
                DataOutputStream ds = new DataOutputStream(os);
                NetUtil.writeStringParams(textParams, ds);
                NetUtil.writeFileParams(fileparams, ds);
                NetUtil.paramsEnd(ds);
                // 对文件流操作完,要记得及时关闭
                os.close();
                // 服务器返回的响应吗
                int code = conn.getResponseCode(); // 从Internet获取网页,发送请求,将网页以流的形式读回来
                // 对响应码进行判断
                if (code == 200) {// 返回的响应码200,是成功
                    // 得到网络返回的输入流
                    InputStream is = conn.getInputStream();
                    resultStr = NetUtil.readString(is);
                } else {
                    Toast.makeText(mContext, "请求URL失败！", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            handler.sendEmptyMessage(0);// 执行耗时的方法之后发送消给handler
        }
    };

    Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    pd.dismiss();

                    try {
                        // 返回数据示例，根据需求和后台数据灵活处理
                        // {"status":"1","statusMessage":"上传成功","imageUrl":"http://120.24.219.49/726287_temphead.jpg"}
                        JSONObject jsonObject = new JSONObject(resultStr);

                        // 服务端以字符串“1”作为操作成功标记
                        if (jsonObject.optString("status").equals("1")) {
                            BitmapFactory.Options option = new BitmapFactory.Options();
                            // 压缩图片:表示缩略图大小为原始图片大小的几分之一，1为原图，3为三分之一
                            option.inSampleSize = 1;

                            // 服务端返回的JsonObject对象中提取到图片的网络URL路径
                            String imageUrl = jsonObject.optString("imageUrl");
                            Toast.makeText(mContext, imageUrl, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(mContext, jsonObject.optString("statusMessage"), Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    break;

                default:
                    break;
            }
            return false;
        }
    });
}

