package mddemo.library.com.exception;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.Properties;
import mddemo.library.com.constant.Constants;
import mddemo.library.com.utils.ExternalStorageUtil;
import mddemo.library.com.utils.FileUtils;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年11月29日21:48:38
 * Description:
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    //一个debug的标签
    public static final String TAG="CrashHandler";

    //crashhandler的实例
    private static CrashHandler INSTANCE;

    //程序的上下文的对象
    private Context context;

    //系统默认的UncaughtExceptionHandler的处理的类
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    //使用Properties来保存设备的信息的错误堆的信息
    private Properties mDeviceCrashInfo = new Properties();
    private static final String VERSION_NAME = "versionName";
    private static final String VERSION_CODE = "versionCode";
    private static final String STACK_TRACE = "STACK_TRACE";

    //错误报告文件的拓展的名字
    private static final String CRASH_REPORTER_EXTENSION=".txt";
    private CrashBack crashBack;

    public interface CrashBack {
        public void crashBack(String crashMsg);
    }

    /*保证只有一个CrashHandler实例*/
    private CrashHandler(){

    }

    private StringBuilder GEN= ExternalStorageUtil.getExternalStoragePath();

    /*通过单例的模式获取实例*/
    public static CrashHandler getInstance(){
        if (INSTANCE==null){
            INSTANCE=new CrashHandler();
        }
        return INSTANCE;
    }

    /*初始化一些对象*/
    public void init(Context context){
        this.context=context;
        mDefaultHandler=Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        GEN= Constants.APP_CRASH_BASE_PATH;
    }

    /**
     * 注册一个带有程序崩溃回调的观察者，当程序崩溃的时候会调用里面的方法，用户可以自己去处理
     * @param ctx 上下文对象
     * @param crashBack CrashBack
     */
    public void init(Context ctx, CrashBack crashBack) {
        this.context=context;
        mDefaultHandler=Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.crashBack=crashBack;
    }

    /**
     * 获取crash日志外部存储目录
     * @return 外部存储目录
     */
    public String getExceptionPath() {
        return GEN.toString();
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex)&&mDeviceCrashInfo!=null){
            //要是用户自己不处理的话就让程序去处理
            mDefaultHandler.uncaughtException(thread,ex);
        }else{
            try{
                Thread.sleep(2000);
            }catch (Exception e){
                Log.e(TAG,"error:..",e);
            }
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(10);
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成. 开发者可以根据自己的情况来自定义异常处理逻辑
     * @param ex 错误
     * @return true:如果处理了该异常信息;否则返回false
     */
    private boolean handleException(Throwable ex) {
        if(ex==null){
            return true;
        }
        final String msg=ex.getLocalizedMessage();
        //使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                // Toast 显示需要出现在一个线程的消息队列中
                Looper.prepare();
                Toast.makeText(context, "Application error!---->\n" + msg, Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();
        //收集设备的信息
        collectCrashDeviceInfo(context);
        //保存错误信息
        String crashFielName=saveCrashInfoToFile(ex);
        Log.e("error",crashFielName);
        return true;
    }

    /**
     * 通过反射获取手机的信息
     * @param ctx 上下文
     */
    public void collectCrashDeviceInfo(Context ctx){
        try{

        }catch (Exception e){
            Log.e(TAG, "Error while collect package info", e);
        }
        //通过反射机制回去设备的信息
        Field[] fields= Build.class.getDeclaredFields();
        for (Field field: fields){
            try{
                //设置为私有对象也能访问的模式
                field.setAccessible(true);
                mDeviceCrashInfo.put(field.getName(),field.get(null));
            }catch (Exception e){
                Log.e(TAG, "Error while collect crash info", e);
            }
        }
    }

    /**
     * 将错误信息保存到文本中去
     * @param ex 错误
     * @return filename
     */
    private String saveCrashInfoToFile(Throwable ex){
        Writer info=new StringWriter();
        PrintWriter printWriter=new PrintWriter(info);
        ex.printStackTrace(printWriter);
        //getCause返回这个throwable的case不存在就返回null
        Throwable cause=ex.getCause();
        while (cause!=null){
            cause.printStackTrace(printWriter);
            cause=cause.getCause();
        }
        //toString字符串的形式返回会存取当前的值
        String result=info.toString();
        printWriter.close();
        mDeviceCrashInfo.put(STACK_TRACE,result);
        //用户自己去处理
        if (crashBack!=null){
            crashBack.crashBack(mDeviceCrashInfo.toString());
            return null;
        }
        try{
            String fileName=GEN.append("crash-").append(System.currentTimeMillis()).append(CRASH_REPORTER_EXTENSION).toString();
            FileUtils.writeFile(fileName,mDeviceCrashInfo.toString());
            return fileName;
        }catch (Exception e){
            Log.e(TAG, "an error occured while writing report file...", e);
        }
        return null;
    }
}
