package mddemo.library.com.okhttp;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年11月30日10:00:33
 * Description:
 */
public interface IOkHttpEvent<M extends AbstractBaseOkHttp> {

    M getPostEvent();
}
