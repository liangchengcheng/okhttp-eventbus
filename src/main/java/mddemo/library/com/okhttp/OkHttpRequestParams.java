package mddemo.library.com.okhttp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年11月30日14:28:39
 * Description:
 */
public class OkHttpRequestParams implements Parcelable {

    protected ConcurrentHashMap<String, String> urlParams = new ConcurrentHashMap<String, String>();

    /**
     * 添加
     * @param key 键
     * @param value 值
     */
    public void put(String key, String value) {
        if (key != null && value != null) {
            urlParams.put(key, value);
        }
    }

    /**
     * 添加
     * @param key 键
     * @param value 值
     */
    public void put(String key, int value) {
        if (key != null) {
            urlParams.put(key, String.valueOf(value));
        }
    }

    /**
     * 添加
     * @param key 键
     * @param value 值
     */
    public void put(String key, long value) {
        if (key != null) {
            urlParams.put(key, String.valueOf(value));
        }
    }

    /**
     * 移除
     * @param key 键
     */
    public void remove(String key) {
        urlParams.remove(key);
    }

    /**
     * 是否存在
     * @param key 键
     * @return 是否存在
     */
    public boolean has(String key) {
        return urlParams.get(key) != null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (ConcurrentHashMap.Entry<String, String> entry : urlParams.entrySet()) {
            if (result.length() > 0)
                result.append("&");

            result.append(entry.getKey());
            result.append("=");
            result.append(entry.getValue());
        }
        return result.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.urlParams);
    }

    public OkHttpRequestParams() {
    }

    protected OkHttpRequestParams(Parcel in) {
        this.urlParams = (ConcurrentHashMap<String, String>) in.readSerializable();
    }

    public static final Creator<OkHttpRequestParams> CREATOR = new Creator<OkHttpRequestParams>() {
        public OkHttpRequestParams createFromParcel(Parcel source) {
            return new OkHttpRequestParams(source);
        }

        public OkHttpRequestParams[] newArray(int size) {
            return new OkHttpRequestParams[size];
        }
    };
}
