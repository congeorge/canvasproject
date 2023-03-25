package com.canvas.model;
import com.canvas.exception.IncorrectCoordinatesException;

public class TwoDCoordinate<D extends Number> implements Coordinate<Number> {
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
            throw new IncorrectCoordinatesException("Only two dimensional Coordinates are Supported");
        } catch (IncorrectCoordinatesException e) {
          e.printStackTrace();
        }
        return null;
    }

    public void setY(D y) {
        this.y = y;
    }




}
