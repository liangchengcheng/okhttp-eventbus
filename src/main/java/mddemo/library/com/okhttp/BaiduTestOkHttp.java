package mddemo.library.com.okhttp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.UUID;

import de.greenrobot.event.EventBus;
import mddemo.library.com.model.Baidu;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年11月30日11:19:43
 * Description:
 */
public class BaiduTestOkHttp extends AbstractBaseOkHttp {

    public static final String TYPE_BAIDU="BaiduTestOkHttp";

    public BaiduTestOkHttp(@NonNull Context context, @NonNull Bundle bundle) {
        super(context, bundle);
    }

    @Override
    public AbstractBaseOkHttp getPostEvent() {
        return this;
    }

    @Override
    public String getUrl() {
        return "Http://www.baidu.com";
    }

    @Override
    public Request getRequest() {
        return getRequestBuilder().url(getUrl()).build();
    }

    @Override
    public RequestBody getRequestBody() {
        return null;
    }

    @Override
    public void onSuccess(@NonNull Response response) throws IOException {
        try{
            String strResponse=response.body().string();
            Baidu baidu=new Baidu();
            baidu.setId(UUID.randomUUID().toString());
            baidu.setResponse(strResponse);
            EventBus.getDefault().post(baidu);
            Logger.d(strResponse);
        }catch (Exception e){
            onFailed(e);
        }
    }
}
