package com.marvel.beans;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Events implements Parcelable {
    @SerializedName("available")
    private int available;
    @SerializedName("collectionURI")
    private String collectionURI;
    @SerializedName("items")
    private List<ItemsXXX> items;
    @SerializedName("returned")
    private int returned;

    protected Events(Parcel in) {
        available = in.readInt();
        collectionURI = in.readString();
        returned = in.readInt();
    }

    public static final Creator<Events> CREATOR = new Creator<Events>() {
        @Override
        public Events createFromParcel(Parcel in) {
            return new Events(in);
        }

        @Override
        public Events[] newArray(int size) {
            return new Events[size];
        }
    };

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public List<ItemsXXX> getItems() {
        return items;
    }

    public void setItems(List<ItemsXXX> items) {
        this.items = items;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(available);
        parcel.writeString(collectionURI);
        parcel.writeInt(returned);
    }
}
