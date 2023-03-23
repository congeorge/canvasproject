package com.canvas.model;
import com.canvas.exception.IncorrectCoordinatesException;

public class TwoDCoordinate<D> implements Coordinate {
    D x;
    D y;

    public TwoDCoordinate(D x, D y) {
        this.x = x;
        this.y = y;
    }

    public D getX() {
        return x;
    }


    public D getY() {
        return y;
    }

    @Override
    public D getZ()  {
        try {
            throw new IncorrectCoordinatesException("Not Supported");
        } catch (IncorrectCoordinatesException e) {
            System.out.println("Only two dimensional Coordinates are Supported");
        }
        return null;
    }

    public void setY(D y) {
        this.y = y;
    }




}
