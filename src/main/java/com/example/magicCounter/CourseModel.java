package com.example.magicCounter;

import android.os.Parcel;
import android.os.Parcelable;

public class CourseModel implements Parcelable {
    private int color;
    private String player_name;
    private String pv;
    private String change;
    private String type_count;

    // Constructor
    public CourseModel(String pv, String player_name, String change, String type_count, int color) {
        this.player_name = player_name;
        this.pv = pv;
        this.change = change;
        this.type_count = type_count;
        this.color = color;
    }

    protected CourseModel(Parcel in) {
        player_name = in.readString();
        pv = in.readString();
        change = in.readString();
        type_count = in.readString();
        color = in.readInt();
    }

    public static final Creator<CourseModel> CREATOR = new Creator<CourseModel>() {
        @Override
        public CourseModel createFromParcel(Parcel in) {
            return new CourseModel(in);
        }

        @Override
        public CourseModel[] newArray(int size) {
            return new CourseModel[size];
        }
    };

    // Getter and Setter
    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public String getPv() {
        return pv;
    }

    public void setPv(String pv) {
        this.pv = pv;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getType_count() {
        return type_count;
    }

    public void setType_count(String type_count) {
        this.type_count = type_count;
    }

    public int getColor(){return color;}

    public void setColor(int color){this.color = color;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(player_name);
        parcel.writeString(pv);
        parcel.writeString(change);
        parcel.writeString(type_count);
        parcel.writeInt(color);
    }

    public void applyLife(){
        this.pv = Integer.toString(Integer.parseInt(this.pv) + Integer.parseInt(this.change));
        this.change = "0";
    }
}
