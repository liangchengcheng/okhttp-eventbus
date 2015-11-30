package mddemo.library.com.okhttp;

import android.support.annotation.NonNull;
import com.squareup.okhttp.Response;
import java.io.IOException;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年11月30日10:11:24
 * Description:
 */
public interface IOkHttpResponse {

    /*请求失败*/
    void onFailed(@NonNull Exception exception);

    /*请求成功*/
    void onSuccess(@NonNull Response response)throws IOException;
}
