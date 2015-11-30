package mddemo.library.com.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Baidu implements Parcelable {

    private String id;
    private String response;

    public Baidu() {
    }

    public Baidu(String id) {
        this.id = id;
    }

    public Baidu(String id, String response) {
        this.id = id;
        this.response = response;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.response);
    }

    protected Baidu(Parcel in) {
        this.id = in.readString();
        this.response = in.readString();
    }

    public static final Creator<Baidu> CREATOR = new Creator<Baidu>() {
        public Baidu createFromParcel(Parcel source) {
            return new Baidu(source);
        }

        public Baidu[] newArray(int size) {
            return new Baidu[size];
        }
    };

}
