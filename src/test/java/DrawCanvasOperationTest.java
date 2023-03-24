import com.canvas.exception.IncorrectParametersException;
import com.canvas.operations.DrawCanvasOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DrawCanvasOperationTest {
    @Test
    void DrawCanvasOperationTest_CorrectNumberOfCommandParameters() throws Exception {
        DrawCanvasOperation operation = new DrawCanvasOperation(new String[]{"20", "4"});
         Assertions.assertEquals(20,operation.getCoordinates()[0].getX());
        Assertions.assertEquals(4,operation.getCoordinates()[0].getY());
    }

    @Test
    void DrawCanvasOperationTest_IncorrectNumberOfCommandParameters() {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new DrawCanvasOperation(new String[]{"20"}));
        assertEquals("Canvas needs height and width to be specified: C W H", exception.getMessage());
    }
    @Test
    void DrawCanvasOperationTest_InCorrectParameters_WithZeroWidth() {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new DrawCanvasOperation(new String[]{"0","4"}));
        assertEquals("Canvas needs height and width to be greater than 0", exception.getMessage());
    }
    @Test
    void DrawCanvasOperationTest_InCorrectParameters_WithNegativeValues()  {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new DrawCanvasOperation(new String[]{"-20","-5"}));
        assertEquals("Canvas needs height and width to be greater than 0", exception.getMessage());
    }
    @Test
    void DrawCanvasOperationTest_InCorrectCommandParameters_WithNonValidValues()  {
        Exception exception = assertThrows(IncorrectParametersException.class, () ->  new DrawCanvasOperation(new String[]{"a","b"}));
        assertEquals("Canvas width and height should be valid numbers", exception.getMessage());
    }

}
