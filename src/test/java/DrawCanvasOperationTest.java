import com.canvas.exception.IncorrectParametersException;
import com.canvas.others.CanvasOperationFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DrawCanvasOperationTest {
    @Test
    void getDrawCanvasOperationTest_IncorrectNumberOfCommandParameters() throws Exception {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> CanvasOperationFactory.getOperation(new String[]{"C","20"}));
        assertEquals("Canvas needs height and width to be specified: C W H", exception.getMessage());
    }
    @Test
    void getDrawCanvasOperationTest_InCorrectCommandParameters_WithZeroWidth() throws Exception {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> CanvasOperationFactory.getOperation(new String[]{"C","0","4"}));
        assertEquals("Canvas needs height and width to be greater than 0", exception.getMessage());
    }
    @Test
    void getDrawCanvasOperationTest_InCorrectCommandParameters_WithNegativeValues() throws Exception {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> CanvasOperationFactory.getOperation(new String[]{"C","-20","-5"}));
        assertEquals("Canvas needs height and width to be greater than 0", exception.getMessage());
    }
    @Test
    void getDrawCanvasOperationTest_InCorrectCommandParameters_WithNonValidValues() throws Exception {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> CanvasOperationFactory.getOperation(new String[]{"C","a","b"}));
        assertEquals("Canvas width and height should be valid numbers", exception.getMessage());
    }

}
