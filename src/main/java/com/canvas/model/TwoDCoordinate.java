package com.canvas.model;
import com.canvas.exception.IncorrectCoordinatesException;
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


    public int getY() {
        return y;
    }

    @Override
    public int getZ() throws IncorrectCoordinatesException {
        throw new IncorrectCoordinatesException("Not Supported");
    }

    public void setY(int y) {
        this.y = y;
    }




}
