package com.canvas.model;
import com.canvas.model.Coordinate;

public class TwoDCoordinate implements Coordinate {
    int x;
    int y;

    public TwoDCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }




}
