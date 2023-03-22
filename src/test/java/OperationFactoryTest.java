import com.canvas.exception.IncorrectParametersException;
import com.canvas.operations.CanvasOperation;
import com.canvas.operations.DrawCanvasOperation;
import com.canvas.others.CanvasOperationFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OperationFactoryTest {

    @Test
    void getDrawCanvasOperationTest_correctCommand() throws Exception {
        DrawCanvasOperation drawCanvasOperation = new DrawCanvasOperation(new String[]{"20", "4"});
        CanvasOperation operation = CanvasOperationFactory.getOperation(new String[]{"C", "20", "4"});
        Assertions.assertTrue((operation instanceof DrawCanvasOperation));
        Assertions.assertEquals(operation.getCoordinates()[0].getY(), 4);
        Assertions.assertEquals(operation.getCoordinates()[0].getX(), 20);

    }
}



