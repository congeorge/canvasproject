import com.canvas.exception.CanvasException;
import com.canvas.exception.IncorrectParametersException;
import com.canvas.operations.RectangleOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RectangleOperationTest {
    @Test
    void RectangleOperationTest_CorrectNumberOfCommandParameters() throws CanvasException {
         RectangleOperation operation = new RectangleOperation(new String[]{"14","1","18","13"});
        Assertions.assertEquals(14,operation.getCoordinates()[0].getX());
        Assertions.assertEquals(1,operation.getCoordinates()[0].getY());
        Assertions.assertEquals(18,operation.getCoordinates()[1].getX());
        Assertions.assertEquals(13,operation.getCoordinates()[1].getY());
    }
    @Test
    void RectangleOperationTest_CorrectNumberOfCommandParameters_SortOrderOfArguments() throws CanvasException {
        RectangleOperation operation = new RectangleOperation(new String[]{"18", "13","14", "1"});
        Assertions.assertEquals(14,operation.getCoordinates()[0].getX());
        Assertions.assertEquals(1,operation.getCoordinates()[0].getY());
        Assertions.assertEquals(18,operation.getCoordinates()[1].getX());
        Assertions.assertEquals(13,operation.getCoordinates()[1].getY());
    }

    @Test
    void RectangleOperationTest_IncorrectNumberOfParameters_OneParamOnly() {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new RectangleOperation(new String[]{"20"}));
        assertEquals("Rectangle needs 4 co-ordinates", exception.getMessage());
    }
    @Test
    void RectangleOperationTest_IncorrectNumberOfParameters_TwoParamOnly() {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new RectangleOperation(new String[]{"20","4"}));
        assertEquals("Rectangle needs 4 co-ordinates", exception.getMessage());
    }


    @Test
    void RectangleOperationTest_IncorrectNumberOfParameters_ThreeParamOnly() {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new RectangleOperation(new String[]{"13","14", "1"}));
        assertEquals("Rectangle needs 4 co-ordinates", exception.getMessage());
    }

    @Test
    void RectangleOperationTest_InCorrectCommandParameters_WithInValidValues() {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new RectangleOperation(new String[]{"12", "13","14", "c"}));
        assertEquals("All Rectangle co-ordinates should be valid numbers", exception.getMessage());
    }

    @Test
    void RectangleOperationTest_InCorrectCommandParameters_WithInValidValues2() {
        Exception exception = assertThrows(IncorrectParametersException.class, () ->  new RectangleOperation(new String[]{"a","b","2","4"}));
        assertEquals("All Rectangle co-ordinates should be valid numbers", exception.getMessage());
    }


    @Test
    void RectangleOperationTest_InCorrectParameters_WithZeroValue(){
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new RectangleOperation(new String[]{"0","2","5","5"}));
        assertEquals("Rectangle coordinates cannot be less than 0", exception.getMessage());
    }
    @Test
    void RectangleOperationTest_InCorrectParameters_WithZeroValue2(){
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new RectangleOperation(new String[]{"2","2","0","5"}));
        assertEquals("Rectangle coordinates cannot be less than 0", exception.getMessage());
    }
    @Test
    void RectangleOperationTest_InCorrectParameters_WithNegativeValues()  {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new RectangleOperation(new String[]{"-1","-2","-6","-2"}));
        assertEquals("Rectangle coordinates cannot be less than 0", exception.getMessage());
    }
}
