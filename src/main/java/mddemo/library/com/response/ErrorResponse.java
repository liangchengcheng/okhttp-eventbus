package mddemo.library.com.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:   2015年11月30日13:42:00
 * Description:
 */
public class ErrorResponse implements Parcelable{

    private String errorCode;
    private String fieldName;
    private String message;

    protected ErrorResponse(String errorCode,String fieldName,String message) {
        this.errorCode=errorCode;
        this.fieldName=fieldName;
        this.message=message;
    }

    protected ErrorResponse(Parcel in) {
        errorCode = in.readString();
        fieldName = in.readString();
        message = in.readString();
    }

    public static final Creator<ErrorResponse> CREATOR = new Creator<ErrorResponse>() {
        @Override
        public ErrorResponse createFromParcel(Parcel in) {
            return new ErrorResponse(in);
        }

        @Override
        public ErrorResponse[] newArray(int size) {
            return new ErrorResponse[size];
        }
    };

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(errorCode);
        dest.writeString(fieldName);
        dest.writeString(message);
    }

}
