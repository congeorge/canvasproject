import com.canvas.exception.CanvasException;
import com.canvas.exception.IncorrectParametersException;
import com.canvas.operations.FillOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FillOperationTest {
    @Test
    void FillOperationTest_CorrectNumberOfCommandParameters() throws CanvasException {
         FillOperation operation = new FillOperation(new String[]{"1","2","o"});
        Assertions.assertEquals(1,operation.getCoordinates()[0].getX());
        Assertions.assertEquals(2,operation.getCoordinates()[0].getY());
        Assertions.assertEquals('o',operation.getColor());
    }

    @Test
    void FillOperationTest_IncorrectNumberOfParameters_OneParamOnly() {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new FillOperation(new String[]{"20"}));
        assertEquals("Fill operation needs 2 co-ordinates and fill color: B x y c ", exception.getMessage());
    }
    @Test
    void FillOperationTest_IncorrectNumberOfParameters_TwoParamOnly() {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new FillOperation(new String[]{"20","4"}));
        assertEquals("Fill operation needs 2 co-ordinates and fill color: B x y c ", exception.getMessage());
    }

    @Test
    void FillOperationTest_InCorrectCommandParameters_WithInValidValues() {
        Exception exception = assertThrows(IncorrectParametersException.class, () ->  new FillOperation(new String[]{"a","b","2"}));
        assertEquals("All Fill co-ordinates should be valid numbers", exception.getMessage());
    }


    @Test
    void FillOperationTest_InCorrectParameters_WithZeroValue(){
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new FillOperation(new String[]{"0","2","k"}));
        assertEquals("Fill coordinates cannot be less than 0", exception.getMessage());
    }
    @Test
    void FillOperationTest_InCorrectParameters_WithNegativeValues()  {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new FillOperation(new String[]{"-1","-2","-6","-2"}));
        assertEquals("Fill coordinates cannot be less than 0", exception.getMessage());
    }
}
