package com.marvel.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Thumbnail implements Parcelable {

    @SerializedName("path")
    private String path;

    @SerializedName("extension")
    private String extension;

    public String getPath() {
        return path;
    }

    public String getExtension() {
        return extension;
    }

    protected Thumbnail(Parcel in) {
        path = in.readString();
        extension = in.readString();
    }

    public static final Creator<Thumbnail> CREATOR = new Creator<Thumbnail>() {
        @Override
        public Thumbnail createFromParcel(Parcel in) {
            return new Thumbnail(in);
        }

        @Override
        public Thumbnail[] newArray(int size) {
            return new Thumbnail[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(path);
        dest.writeString(extension);
    }
}
