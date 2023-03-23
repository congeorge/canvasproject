import com.canvas.exception.CanvasException;
import com.canvas.exception.IncorrectParametersException;
import com.canvas.operations.LineOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LineOperationTest {
    @Test
    void LineOperationTest_CorrectNumberOfCommandParameters() throws CanvasException {
        LineOperation operation = new LineOperation(new String[]{"1", "2","6", "2"});
        Assertions.assertEquals(1,operation.getCoordinates()[0].getX());
        Assertions.assertEquals(2,operation.getCoordinates()[0].getY());
        Assertions.assertEquals(6,operation.getCoordinates()[1].getX());
        Assertions.assertEquals(2,operation.getCoordinates()[1].getY());
    }

    @Test
    void LineOperationTest_CorrectNumberOfCommandParameters_SortOrderOfArguments() throws CanvasException {
        LineOperation operation = new LineOperation(new String[]{"6", "2","1", "2"});
        Assertions.assertEquals(1,operation.getCoordinates()[0].getX());
        Assertions.assertEquals(2,operation.getCoordinates()[0].getY());
        Assertions.assertEquals(6,operation.getCoordinates()[1].getX());
        Assertions.assertEquals(2,operation.getCoordinates()[1].getY());
    }


    @Test
    void getDrawCanvasOperationTest_IncorrectNumberOfParameters_OneParamOnly() {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new LineOperation(new String[]{"20"}));
        assertEquals("Line needs 4 co-ordinates : L x1 y1 x2 y2", exception.getMessage());
    }
    @Test
    void getDrawCanvasOperationTest_IncorrectNumberOfParameters_TwoParamOnly() {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new LineOperation(new String[]{"20","4"}));
        assertEquals("Line needs 4 co-ordinates : L x1 y1 x2 y2", exception.getMessage());
    }

    @Test
    void RectangleOperationTest_IncorrectNumberOfParameters_ThreeParamOnly() {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new LineOperation(new String[]{"20","4","22"}));
        assertEquals("Line needs 4 co-ordinates : L x1 y1 x2 y2", exception.getMessage());
    }

    @Test
    void getDrawCanvasOperationTest_InCorrectCommandParameters_WithInValidValues() {
        Exception exception = assertThrows(IncorrectParametersException.class, () ->  new LineOperation(new String[]{"a","b","2","4"}));
        assertEquals("Line needs valid 4 co-ordinates : L x1 y1 x2 y2", exception.getMessage());
    }


    @Test
    void getDrawCanvasOperationTest_InCorrectParameters_WithDiagonalLineParams(){
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new LineOperation(new String[]{"1","2","6","3"}));
        assertEquals("Diagonal line is not supported", exception.getMessage());
    }
    @Test
    void getDrawCanvasOperationTest_InCorrectParameters_WithNegativeValues()  {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new LineOperation(new String[]{"-1","-2","-6","-2"}));
        assertEquals("Line coordinates cannot be less than 0", exception.getMessage());
    }
}
