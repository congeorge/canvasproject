import com.canvas.exception.IncorrectParametersException;
import com.canvas.exception.NoSuchOperationException;
import com.canvas.operations.*;
import com.canvas.others.CanvasOperationFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OperationFactoryTest {

    @Test
    void OperationFactoryTest_getValidDrawCanvasOperation() throws Exception {
        DrawCanvasOperation drawCanvasOperation = new DrawCanvasOperation(new String[]{"20", "4"});
        CanvasOperation operation = CanvasOperationFactory.getOperation(new String[]{"C", "20", "4"});
        Assertions.assertTrue((operation instanceof DrawCanvasOperation));
        Assertions.assertEquals(operation.getCoordinates()[0].getY(), 4);
        Assertions.assertEquals(operation.getCoordinates()[0].getX(), 20);

    }

    @Test
    void OperationFactoryTest_getValidDrawLineOperation() throws Exception {
        CanvasOperation operation = CanvasOperationFactory.getOperation(new String[]{"L","1", "2","6","2"});
        Assertions.assertTrue((operation instanceof LineOperation));
        Assertions.assertEquals(operation.getCoordinates()[0].getX(), 1);
        Assertions.assertEquals(operation.getCoordinates()[0].getY(), 2);
        Assertions.assertEquals(operation.getCoordinates()[1].getX(), 6);
        Assertions.assertEquals(operation.getCoordinates()[1].getY(), 2);


    }
    @Test
    void OperationFactoryTest_ValidFillOperation() throws Exception {
        CanvasOperation operation = CanvasOperationFactory.getOperation(new String[]{"B","1", "2","o"});
        Assertions.assertTrue((operation instanceof FillOperation));
        Assertions.assertEquals(operation.getCoordinates()[0].getX(), 1);
        Assertions.assertEquals(operation.getCoordinates()[0].getY(), 2);
        FillOperation fillOperation =((FillOperation)operation);
        Assertions.assertEquals(fillOperation.getColor(), 'o');

    }
    @Test
    void OperationFactoryTest_ValidQuitOperation() throws Exception {
        CanvasOperation operation = CanvasOperationFactory.getOperation(new String[]{"Q"});
        Assertions.assertTrue((operation instanceof QuitOperation));
      }
    @Test
    void OperationFactoryTest_ValidHelpOperation() throws Exception {
        CanvasOperation operation = CanvasOperationFactory.getOperation(new String[]{"H"});
        Assertions.assertTrue((operation instanceof HelpOperation));
    }

    @Test
    void OperationFactoryTest_ValidUndoOperation() throws Exception {
        CanvasOperation operation = CanvasOperationFactory.getOperation(new String[]{"U"});
        Assertions.assertTrue((operation instanceof UndoCanvasOperation));
    }

    @Test
    void OperationFactoryTest_InvalidOperation() throws Exception {
        Exception exception = assertThrows(NoSuchOperationException.class, () -> CanvasOperationFactory.getOperation(new String[]{"T","0","4"}));
        assertEquals("Not a supported operation", exception.getMessage());

    }
}



