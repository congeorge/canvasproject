package com.canvas.model;

import com.canvas.exception.IncorrectCoordinatesException;

public interface Coordinate<D> {
    D getX();
    D getY();
    D getZ() throws IncorrectCoordinatesException;
}
