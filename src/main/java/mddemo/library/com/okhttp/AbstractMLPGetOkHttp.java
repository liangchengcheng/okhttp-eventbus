package mddemo.library.com.okhttp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年11月30日14:23:51
 * Description:
 */
public abstract class AbstractMLPGetOkHttp extends AbstractMLPBaseOkHttp {

    public AbstractMLPGetOkHttp(@NonNull Context context, @NonNull Bundle bundle) {
        super(context, bundle);
    }

    @Override
    public Request getRequest() {
        return getRequestBuilder().url(getUrlByParams()).build();
    }

    @Override
    public RequestBody getRequestBody() {
        return null;
    }

    private String getUrlByParams() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(getUrl());
        if (bundle.containsKey(BUNDLE_GET_KEY)){
            OkHttpRequestParams okHttpRequestParams=bundle.getParcelable(BUNDLE_GET_KEY);
            if (okHttpRequestParams!=null){
                stringBuilder.append("?").append(okHttpRequestParams.toString());
            }
        }
        return stringBuilder.toString();
    }
}
