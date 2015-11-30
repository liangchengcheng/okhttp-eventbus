package mddemo.library.com.manager;

import android.app.IntentService;
import android.content.Intent;
import de.greenrobot.event.EventBus;
import mddemo.library.com.utils.NetWorkUtils;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年11月30日15:31:45
 * Description:
 */
public class ApiService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public ApiService() {
        super("ApiService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (!NetWorkUtils.isNetworkConnected(getApplicationContext())) {
            //在baseActivity里注册即可
            EventBus.getDefault().post(new NetStatusEvent(NetStatusEvent.Please_Connect_To_The_Network_And_Try_Again));
        } else {
            OkHttpFactory.createHttp(this, intent.getType(), intent.getExtras());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
