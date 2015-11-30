package mddemo.library.com.manager;

import android.content.Context;
import android.os.Bundle;
import mddemo.library.com.okhttp.AbstractBaseOkHttp;
import mddemo.library.com.okhttp.BaiduTestOkHttp;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年11月30日15:32:20
 * Description:
 */
public class OkHttpFactory {

    private static AbstractBaseOkHttp abstractBaseOkHttp;

    public static AbstractBaseOkHttp createHttp(Context context, String intentType, Bundle bundle) {
        switch (intentType) {
            case BaiduTestOkHttp.TYPE_BAIDU:
                abstractBaseOkHttp = new BaiduTestOkHttp(context, bundle);
                break;
        }
        return abstractBaseOkHttp;
    }
}
