package mddemo.library.com.okhttp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import com.orhanobut.logger.Logger;
import de.greenrobot.event.EventBus;
import mddemo.library.com.exception.HandleOkHttpException;
import mddemo.library.com.exception.PrintLog;
import mddemo.library.com.utils.TimeUtils;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年11月30日10:19:35
 * Description:
 */
public abstract class AbstractBaseOkHttp implements IOkHttpEvent,IOkHttpRequest,IOkHttpResponse{

    private static final String CONTENT_TYPE_KEY = "Content-Type";
    protected static final String CONTENT_TYPE = "application/json; charset=utf-8";
    private static final String ACCEPT_KEY = "Accept";
    private static final String ACCEPT = "application/json";
    private static final String UTF8 = "utf8";

    private static OkHttpClient okHttpClient;

    //get请求参数bundle key
    public static final String BUNDLE_GET_KEY="BUNDLE_GET_KEY";

    //post请求参数的bundle key
    public  static final  String BUNDLE_POST_OR_PUT_KEY="BUNDLE_POST_OR_PUT_KEY";

    protected Context context;
    protected Bundle bundle;
    protected Request request;

    //日志
    protected PrintLog printLog;

    //网络请求的异常信息
    protected String errorMessage;


    public AbstractBaseOkHttp(@NonNull Context context,@NonNull Bundle bundle){
        this.context=context;
        this.bundle=bundle;
        printLog=new PrintLog();
        printLog.setRequestTime(TimeUtils.getCurrentTimeInString(new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒")));
        request=getRequest();
        printLog.setUrl(request.urlString());
        printLog.setHeaders(new StringBuilder().append(CONTENT_TYPE_KEY).append("=").append(request.header(CONTENT_TYPE_KEY)).toString());
        execute(getOkHttpClient(),getRequest(),getResponseCallBack());
    }

    @Override
    public OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        } else {
            okHttpClient = okHttpClient.clone();
        }
        okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
        return okHttpClient;
    }

    @Override
    public Request.Builder getRequestBuilder() {
        return new Request.Builder()
                .addHeader(CONTENT_TYPE_KEY, CONTENT_TYPE)
                .addHeader(ACCEPT_KEY, ACCEPT);
    }

    @Override
    public Callback getResponseCallBack() {
      return new Callback() {
          @Override
          public void onFailure(Request request, IOException e) {
              onFailed(e);
          }

          @Override
          public void onResponse(Response response) throws IOException {
            if (response.isSuccessful()){
                onSuccess(response);
            }else{
                throw  new IOException();
            }
          }
      };
    }

    @Override
    public RequestBody getRequestBody() {
        return null;
    }

    @Override
    public void execute(@NonNull OkHttpClient okHttpClient, @NonNull Request request, @NonNull Callback callback) {
        okHttpClient.newCall(request).enqueue(callback);
    }

    @Override
    public void onFailed(@NonNull Exception exception) {
        errorMessage= HandleOkHttpException.handleMessage(exception);
        printLogger(errorMessage);
        //EventBus回调
        EventBus.getDefault().post(getPostEvent());
        Logger.e(errorMessage);
    }

    /**
     * 打印日志并且写入sd卡提交腾讯服务
     * @param body
     */
    protected void printLogger(String body){
        printLog.setResponseTime(TimeUtils.getCurrentTimeInString(new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒")));
        printLog.setRequestBody(body);
        printLog.printLogToSdCard(context);
    }

    /*获取错误信息*/
    public  String getErrorMessge(){
        return errorMessage;
    }

    /*设置错误信息*/
    public void setErrorMessage(){
        this.errorMessage=errorMessage;
    }
}
