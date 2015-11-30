package mddemo.library.com.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年11月30日13:49:10
 * Description:
 */
public class BaseServerResponse implements Parcelable{
    private String success;
    private ResponseStatus responseStatus;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public BaseServerResponse() {
    }

    protected BaseServerResponse(Parcel in) {
        this.success=in.readString();
        this.responseStatus=in.readParcelable(ResponseStatus.class.getClassLoader());
    }

    public static final Creator<BaseServerResponse> CREATOR = new Creator<BaseServerResponse>() {
        @Override
        public BaseServerResponse createFromParcel(Parcel in) {
            return new BaseServerResponse(in);
        }

        @Override
        public BaseServerResponse[] newArray(int size) {
            return new BaseServerResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.success);
        dest.writeParcelable(this.responseStatus,0);
    }
}
