package com.sdmitriy.firebasetestapp.model.entity.secondpart;

public class CarouselItem {

    private int day;
    private String month;
    private String eventsCount;

    public CarouselItem(int day, String month, String eventsCount) {
        this.day = day;
        this.month = month;
        this.eventsCount = eventsCount;
    }

    public int getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getEventsCount() {
        return eventsCount;
    }
}
