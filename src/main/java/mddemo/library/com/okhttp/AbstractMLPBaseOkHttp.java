package mddemo.library.com.okhttp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.squareup.okhttp.Response;
import java.io.IOException;
import mddemo.library.com.response.BaseServerResponse;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年11月30日13:19:21
 * Description:
 */
public abstract class AbstractMLPBaseOkHttp extends AbstractBaseOkHttp{

    public AbstractMLPBaseOkHttp(@NonNull Context context, @NonNull Bundle bundle) {
        super(context, bundle);
    }

    @Override
    public void onSuccess(@NonNull Response response) throws IOException {
        try{
            String responseString=response.body().string();
            BaseServerResponse baseServerResponse=new Gson().fromJson(responseString,BaseServerResponse.class);
            if (Boolean.valueOf(baseServerResponse.getSuccess())){
                //成功也打印日志
                printLogger(responseString);
                //处理成功消息
                onMlpSuccess(responseString);
                Logger.e("lcc",responseString);
            }
        }catch (Exception e){
            onFailed(e);
        }
    }

    public abstract void onMlpSuccess(String responseBody);

}
