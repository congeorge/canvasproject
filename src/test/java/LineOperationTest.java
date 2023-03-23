import com.canvas.exception.IncorrectParametersException;
import com.canvas.operations.DrawCanvasOperation;
import com.canvas.operations.LineOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LineOperationTest {
    @Test
    void LineOperationTest_CorrectNumberOfCommandParameters() throws Exception {
        LineOperation operation = new LineOperation(new String[]{"1", "2","6", "2"});
        Assertions.assertTrue((operation instanceof LineOperation));
        Assertions.assertEquals(operation.getCoordinates()[0].getX(), 1);
        Assertions.assertEquals(operation.getCoordinates()[0].getY(), 2);
        Assertions.assertEquals(operation.getCoordinates()[0].getX(), 6);
        Assertions.assertEquals(operation.getCoordinates()[0].getY(), 2);
    }

    @Test
    void getDrawCanvasOperationTest_IncorrectNumberOfParameters() throws Exception {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new LineOperation(new String[]{"20"}));
        assertEquals("Line needs 4 co-ordinates : L x1 y1 x2 y2", exception.getMessage());
    }
    @Test
    void getDrawCanvasOperationTest_IncorrectNumberOfParameters2() throws Exception {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new LineOperation(new String[]{"20","4"}));
        assertEquals("Line needs 4 co-ordinates : L x1 y1 x2 y2", exception.getMessage());
    }

    @Test
    void getDrawCanvasOperationTest_InCorrectCommandParameters_WithInValidValues() throws Exception {
        Exception exception = assertThrows(IncorrectParametersException.class, () ->  new LineOperation(new String[]{"a","b","2","4"}));
        assertEquals("Line needs valid 4 co-ordinates : L x1 y1 x2 y2", exception.getMessage());
    }


    @Test
    void getDrawCanvasOperationTest_InCorrectParameters_WithZeroWidth() throws Exception {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new LineOperation(new String[]{"1","2","6","3"}));
        assertEquals("Diagonal line is not supported", exception.getMessage());
    }
    @Test
    void getDrawCanvasOperationTest_InCorrectParameters_WithNegativeValues() throws Exception {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new LineOperation(new String[]{"-1","-2","-6","-2"}));
        assertEquals("Line coordinates cannot be less than 0", exception.getMessage());
    }
}
