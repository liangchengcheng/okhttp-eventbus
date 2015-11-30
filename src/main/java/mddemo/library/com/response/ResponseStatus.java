package mddemo.library.com.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Author:  梁铖城
 * Email:   1038127753@qq.com
 * Date:    2015年11月30日13:40:40
 * Description:
 */
public class ResponseStatus implements Parcelable{

    private String errorCode;
    private String message;
    private List<ErrorResponse> errors;

    public ResponseStatus(String errorCode, String message, List<ErrorResponse> errors) {
        this.errorCode = errorCode;
        this.message = message;
        this.errors = errors;
    }

    protected ResponseStatus(Parcel in) {
        this.errorCode = in.readString();
        this.message = in.readString();
        this.errors = in.createTypedArrayList(ErrorResponse.CREATOR);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ErrorResponse> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorResponse> errors) {
        this.errors = errors;
    }


    public static final Creator<ResponseStatus> CREATOR = new Creator<ResponseStatus>() {
        @Override
        public ResponseStatus createFromParcel(Parcel in) {
            return new ResponseStatus(in);
        }

        @Override
        public ResponseStatus[] newArray(int size) {
            return new ResponseStatus[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.errorCode);
        dest.writeString(this.message);
        dest.writeTypedList(errors);
    }
}
