package mddemo.library.com.okhttp;

import android.support.annotation.NonNull;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年11月30日10:02:24
 * Description:
 */
public interface IOkHttpRequest {

    /**
     * 获取 OkHttpClient
     * @return OkHttpClient
     */
    OkHttpClient getOkHttpClient();

    /**
     * 获取配置地址
     * @return 配置地址
     */
    String getUrl();

    /**
     * 配置请求
     * @return 请求
     */
    Request getRequest();

    /**
     * 配置请求
     * @return 配置请求
     */
    Request.Builder getRequestBuilder();


    /**
     * 处理返回的消息
     * @return 返回的信息
     */
    Callback getResponseCallBack();

    /**
     * 获取请求的主体
     * @return 主体
     */
    RequestBody getRequestBody();

    void execute(@NonNull OkHttpClient okHttpClient,@NonNull Request request,@NonNull Callback callback);

}
