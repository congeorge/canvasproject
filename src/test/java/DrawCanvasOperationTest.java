import com.canvas.exception.IncorrectParametersException;
import com.canvas.operations.CanvasOperation;
import com.canvas.operations.DrawCanvasOperation;
import com.canvas.others.CanvasOperationFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DrawCanvasOperationTest {
    @Test
    void DrawCanvasOperationTest_CorrectNumberOfCommandParameters() throws Exception {
        DrawCanvasOperation operation = new DrawCanvasOperation(new String[]{"20", "4"});
        Assertions.assertTrue((operation instanceof DrawCanvasOperation));
        Assertions.assertEquals(operation.getCoordinates()[0].getX(), 20);
        Assertions.assertEquals(operation.getCoordinates()[0].getY(), 4);
    }

    @Test
    void DrawCanvasOperationTest_IncorrectNumberOfCommandParameters() throws Exception {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new DrawCanvasOperation(new String[]{"20"}));
        assertEquals("Canvas needs height and width to be specified: C W H", exception.getMessage());
    }
    @Test
    void DrawCanvasOperationTest_InCorrectParameters_WithZeroWidth() throws Exception {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new DrawCanvasOperation(new String[]{"0","4"}));
        assertEquals("Canvas needs height and width to be greater than 0", exception.getMessage());
    }
    @Test
    void DrawCanvasOperationTest_InCorrectParameters_WithNegativeValues() throws Exception {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new DrawCanvasOperation(new String[]{"-20","-5"}));
        assertEquals("Canvas needs height and width to be greater than 0", exception.getMessage());
    }
    @Test
    void DrawCanvasOperationTest_InCorrectCommandParameters_WithNonValidValues() throws Exception {
        Exception exception = assertThrows(IncorrectParametersException.class, () ->  new DrawCanvasOperation(new String[]{"a","b"}));
        assertEquals("Canvas width and height should be valid numbers", exception.getMessage());
    }

}
