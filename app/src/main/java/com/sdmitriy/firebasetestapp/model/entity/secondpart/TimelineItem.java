package com.sdmitriy.firebasetestapp.model.entity.secondpart;

import java.util.List;

public class TimelineItem {

    private boolean isGreen;
    private String eventTime;
    private String afternoon;
    private String eventTitle;
    private String eventPlace;

    private List<Integer> imagesDrawablesResources;

    public TimelineItem(boolean isGreen, String eventTime, String afternoon, String eventTitle, String eventPlace) {
        this.isGreen = isGreen;
        this.eventTime = eventTime;
        this.afternoon = afternoon;
        this.eventTitle = eventTitle;
        this.eventPlace = eventPlace;
    }

    public boolean isGreen() {
        return isGreen;
    }

    public String getEventTime() {
        return eventTime;
    }

    public String getAfternoon() {
        return afternoon;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public String getEventPlace() {
        return eventPlace;
    }

    public List<Integer> getImagesDrawablesResources() {
        return imagesDrawablesResources;
    }

    public void setImagesDrawablesResources(List<Integer> imagesDrawablesResources) {
        this.imagesDrawablesResources = imagesDrawablesResources;
    }
}
