package com.canvas.model;
import com.canvas.exception.IncorrectCoordinatesException;

public class TwoDCoordinate<D> implements Coordinate<D>{
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
    public D getZ()  throws IncorrectCoordinatesException {
                    throw new IncorrectCoordinatesException("Only two dimensional Coordinates are Supported");
         }





}
