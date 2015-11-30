package mddemo.library.com.manager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import mddemo.library.com.okhttp.BaiduTestOkHttp;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:
 * Description:
 */
public class StartServiceManager {

    public static void startBaiduOkHttp(Context context) {
        startIntent(context, BaiduTestOkHttp.TYPE_BAIDU, null);
    }

    private static void startIntent(@NonNull Context context, @NonNull String intentType, @Nullable Bundle bundle) {
        Intent intent = new Intent(context, ApiService.class);
        intent.setType(intentType);
        if (bundle != null)
            intent.putExtras(bundle);
        context.startService(intent);
    }
}
