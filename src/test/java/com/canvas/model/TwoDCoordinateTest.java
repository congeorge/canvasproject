package com.canvas.model;

import com.canvas.exception.CanvasException;
import com.canvas.exception.IncorrectCoordinatesException;
import com.canvas.exception.NoCanvasException;
import com.canvas.operations.LineOperation;
import com.canvas.others.CanvasOperationExecutor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwoDCoordinateTest {

    TwoDCoordinate<Integer> coordinate;
    @BeforeEach
    void setUp() throws Exception {
        coordinate =new TwoDCoordinate<>(20,5);
    }
    @Test
    void TwoDCoordinateTest_GetX() {

        Assertions.assertEquals(20,coordinate.getX());

    }
    @Test
    void TwoDCoordinateTest_GetY() {

        Assertions.assertEquals(5,coordinate.getY());

    }
    @Test
    void TwoDCoordinateTest_GetZ() {
         Exception e =assertThrows(IncorrectCoordinatesException.class,()-> coordinate.getZ());

    }

}