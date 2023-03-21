package com.canvas.model;

import com.canvas.exception.IncorrectCoordinatesException;

public interface Coordinate {
    int getX()throws IncorrectCoordinatesException;
    int getY() throws IncorrectCoordinatesException;
    int getZ() throws IncorrectCoordinatesException;
}
