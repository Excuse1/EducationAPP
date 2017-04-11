package shengzheng.educationapp.AsyncTaskDemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Timer;
import java.util.TimerTask;

import shengzheng.educationapp.MianPackage.MainActivity;
import shengzheng.educationapp.R;

/**
 * Created by join on 2016/9/28.
 */
public class AsyncTaskMain extends Activity {
    private ImageView async_task_im;
    private ProgressBar progress_bar_async;
    private static String url;
    private MyAsyncTask myAsyncTask;
    @Override
      protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.async_task);
        url ="http://img1.imgtn.bdimg.com/it/u=3498572852,2834502375&fm=21&gp=0.jpg";
        async_task_im = (ImageView) findViewById(R.id.async_task_im);
        progress_bar_async = (ProgressBar) findViewById(R.id.progress_bar_async);
        myAsyncTask= new MyAsyncTask();
        myAsyncTask.execute(url);
        Log.i("aaa",""+progress_bar_async.getProgress());


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (myAsyncTask!=null&&myAsyncTask.getStatus()==AsyncTask.Status.RUNNING){
            //cancel 只是标记为 cancel状态
                 myAsyncTask.cancel(true);
        }
    }

    //异步任务
    class MyAsyncTask extends AsyncTask<String, Integer, Bitmap>{

        //Params 启动任务输入参数类型   Progress后台任务执行中返回进度值的类型   Result后台任务执行完成后返回结果的类型



        //执行后台耗时操作之前调用,完成一些初始化操作
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress_bar_async.setVisibility(View.VISIBLE);
        }



        //异步任务执行后台线程将要完成的任务
        @Override
        protected Bitmap doInBackground(String... params) {

            for (int i=0;i<10;i++){
                //如果是cancel 状态就跳出
                if (isCancelled()){
                    break;
                }
                //更新任务进度
                publishProgress(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            String url = params[0];
            Bitmap bitmap = null;
            URLConnection connection;
            InputStream is;

            try {
                connection =  new URL(url).openConnection();
                is = connection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                bitmap = BitmapFactory.decodeStream(bis);
                is.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return bitmap;
        }


        //当doInBackground()完成之后调用  同时将doInBackground方法的返回值传给它.
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            //隐藏
            progress_bar_async.setVisibility(View.GONE);
            //设置图片
            async_task_im.setImageBitmap(bitmap);
        }


        //在doInBackground方法中调用publicProgress()方法 跟新任务的执行进度
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (isCancelled()){
                return;
            }
            //设置进度值
            progress_bar_async.setProgress(values[0]);
            if (progress_bar_async.getProgress()==9) {
                //1.创建Timer对象
                Timer timer = new Timer();
                //3.创建TimerTask对象
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(AsyncTaskMain.this, MainActivity.class);
                        startActivity(intent);

                        //finish();
                    }
                };
                //2.使用timer.schedule（）方法调用timerTask，定时3秒后执行run
                Log.i("====", "----------------------");
                timer.schedule(timerTask, 2000);
            }
            Log.i("aaa",""+progress_bar_async.getProgress());
        }
    }
}
