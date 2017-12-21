package com.example.kishore.spotfinder;

import android.os.Bundle;

/**
 * Created by Kishore on 06/25/2016.
 */
public class SpotInformation { //implements java.io.Serializable {
    double dist1;
    double dist2;
    double dist3;
    double dist4;
    double dist5;
    double dist6;
    String status;
    String alias;
    int Afloor;
    String reserver;

    public SpotInformation() {
    }

    public SpotInformation(double dist1, double dist2, double dist3, double dist4, double dist5, double dist6, String status, int Afloor, String alias, String reserver) {
        this.dist1 = dist1;
        this.dist2 = dist2;
        this.dist3 = dist3;
        this.dist4 = dist4;
        this.dist5 = dist5;
        this.dist6 = dist6;
        this.status = status;
        this.alias = alias;
        this.Afloor = Afloor;
        this.reserver = reserver;
    }

    public double getDist1() {
        return dist1;
    }

    public double getDist2() {
        return dist2;
    }

    public double getDist3() {
        return dist3;
    }

    public double getDist4() {
        return dist4;
    }

    public double getDist5() {
        return dist5;
    }

    public double getDist6() {
        return dist6;
    }

    public String getStatus() {
        return status;
    }

    public String getAlias() {
        return alias;
    }

    public int getFloor() {
        return Afloor;
    }

    public String getReserver() {
        return reserver;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
