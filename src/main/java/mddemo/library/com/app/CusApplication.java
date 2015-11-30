package mddemo.library.com.app;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Settings;

import butterknife.ButterKnife;
import mddemo.library.com.exception.CrashHandler;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年11月30日15:13:04
 * Description:
 */
public class CusApplication extends Application {

    //debug模式
    public static boolean isDebug=true;

    //long tag
    public static final String LOGGER_TAG="zzy";

    private CrashHandler crashHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        if (isDebug){
            ButterKnife.setDebug(isDebug);
        }else{
            initCrashHandler();
        }
        initLogger();
    }

    /**
     * 初始化crashHandler观察者日志
     */
    public void initCrashHandler() {
        crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
    }

    /**
     * 初始化配置Logger
     */
    private void initLogger() {
        Settings settings = Logger.init(LOGGER_TAG)               // default PRETTYLOGGER or use just init()
                .setMethodCount(2).hideThreadInfo();            // default 2
        if (isDebug)
            settings.setLogLevel(LogLevel.NONE);
        else
            settings.setLogLevel(LogLevel.FULL);
    }
}
