package mddemo.library.com.constant;

import java.io.File;

import mddemo.library.com.utils.ExternalStorageUtil;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:   2015年11月29日22:27:51
 * Description:通常程序的配置文件就是此
 */
public class Constants {

    /**
     * 程序隐藏根目录
     */
    public static final StringBuilder APP_BASE_PATH = ExternalStorageUtil.getExternalStoragePath();
    /**
     * 程序异常崩溃日志
     */
    public static final StringBuilder APP_CRASH_BASE_PATH = new StringBuilder().append(APP_BASE_PATH).append("crash").append(File.separator);
    /**
     * 照片存储路径
     */
    public static final StringBuilder APP_IMG_FOLDER_PATH = new StringBuilder().append(APP_BASE_PATH).append("photos").append(File.separator);
    /**
     * 服务地址配置路径
     */
    public static final StringBuilder APP_URL_FOLDER_PATH = new StringBuilder().append(APP_BASE_PATH).append("URL/URL.txt");
    /**
     * 照片存储路径文件夹名
     */
    public static final StringBuilder APP_IMG_FOLDER_NAME = new StringBuilder().append("photos").append(File.separator);
    /**
     * 打开系统相机返回resultCode
     */
    public static final int CAMERA_TAKE = 11;

}
