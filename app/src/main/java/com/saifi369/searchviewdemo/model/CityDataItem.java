package com.saifi369.searchviewdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

public class CityDataItem implements Parcelable {

    private String cityId;
    private String cityName;
    private int rank;
    private String province;
    private long population;
    private String description;
    private String image;


    public CityDataItem() {
    }

    public CityDataItem(String cityId, String cityName,
                        int rank, String province,
                        long population, String description, String image) {

        if (cityId == null) {
            cityId=UUID.randomUUID().toString();
        }

        this.cityId = cityId;
        this.cityName = cityName;
        this.rank = rank;
        this.province = province;
        this.population = population;
        this.description = description;
        this.image = image;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "CityDataItem{" +
                "cityId='" + cityId + '\'' +
                ", cityName='" + cityName + '\'' +
                ", rank=" + rank +
                ", province='" + province + '\'' +
                ", population=" + population +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cityId);
        dest.writeString(this.cityName);
        dest.writeInt(this.rank);
        dest.writeString(this.province);
        dest.writeLong(this.population);
        dest.writeString(this.description);
        dest.writeString(this.image);
    }

    protected CityDataItem(Parcel in) {
        this.cityId = in.readString();
        this.cityName = in.readString();
        this.rank = in.readInt();
        this.province = in.readString();
        this.population = in.readLong();
        this.description = in.readString();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<CityDataItem> CREATOR = new Parcelable.Creator<CityDataItem>() {
        @Override
        public CityDataItem createFromParcel(Parcel source) {
            return new CityDataItem(source);
        }

        @Override
        public CityDataItem[] newArray(int size) {
            return new CityDataItem[size];
        }
    };
}



