package ru.geekbrains.cities;

import java.io.Serializable;

public final class City implements Serializable {

    private final int imageIndex;
    private final String cityName;

    public City(int imageIndex, String cityName) {
        this.imageIndex = imageIndex;
        this.cityName = cityName;
    }

    public int getImageIndex() {
        return imageIndex;
    }

    public String getCityName() {
        return cityName;
    }
}
