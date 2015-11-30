package mddemo.library.com.exception;


/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年11月30日10:44:40
 * Description:
 */
public class HandleOkHttpException {

    public static String handleMessage(Exception e) {
        String s = e.toString();
        if (s.contains("SocketTimeoutException")) {
            return "网络连接超时，请检查网络";
        } else if (s.contains("ConnectException")) {
            return "请求地址无效";
        } else if (s.contains("ServerResponseException")) {
            return e.getMessage();
        }
        return s;
    }

}
