package com.inftga.gamematch.core.event;


import java.util.Date;

/**
 * For tracking
 */
public abstract class Event {

    private Date date;

    public Event(){
        this.date = new Date();
    }

    public abstract String toString();
}
