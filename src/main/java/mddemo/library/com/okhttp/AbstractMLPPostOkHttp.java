package mddemo.library.com.okhttp;

import android.content.Context;
import android.os.Bundle;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年11月30日15:05:36
 * Description:
 */
public abstract class AbstractMLPPostOkHttp extends AbstractMLPBaseOkHttp {

    protected static final MediaType JSON = MediaType.parse(CONTENT_TYPE);
    protected String requestJson;

    public AbstractMLPPostOkHttp(Context context, Bundle bundle) {
        super(context, bundle);
    }

    @Override
    public Request getRequest() {
        return getRequestBuilder().url(getUrl()).post(getRequestBody()).build();
    }

    @Override
    public RequestBody getRequestBody() {
        requestJson = new Gson().toJson(bundle.getParcelable(BUNDLE_POST_OR_PUT_KEY));
        printLog.setRequestBody(requestJson);
        return RequestBody.create(JSON, requestJson);
    }
}
