import com.canvas.exception.NoSuchOperationException;
import com.canvas.operations.*;
import com.canvas.others.CanvasOperationFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OperationFactoryTest {

    @Test
    void OperationFactoryTest_getValidDrawCanvasOperation() throws Exception {
        DrawCanvasOperation expectedCanvas = new DrawCanvasOperation(new String[]{"20", "4"});
        CanvasOperation operation = CanvasOperationFactory.getOperation(new String[]{"C", "20", "4"});
        Assertions.assertTrue((operation instanceof DrawCanvasOperation));
        Assertions.assertEquals(expectedCanvas.getCoordinates()[0].getY(), operation.getCoordinates()[0].getY());
        Assertions.assertEquals(expectedCanvas.getCoordinates()[0].getX(), operation.getCoordinates()[0].getX());

    }

    @Test
    void OperationFactoryTest_getValidDrawLineOperation() throws Exception {
        LineOperation expectedLine = new LineOperation(new String[]{"1", "2","6","2"});
        CanvasOperation operation = CanvasOperationFactory.getOperation(new String[]{"L","1", "2","6","2"});
        Assertions.assertTrue((operation instanceof LineOperation));
        Assertions.assertEquals(expectedLine.getCoordinates()[0].getX(),operation.getCoordinates()[0].getX());
        Assertions.assertEquals(expectedLine.getCoordinates()[0].getY(),operation.getCoordinates()[0].getY());
        Assertions.assertEquals(expectedLine.getCoordinates()[1].getX(),operation.getCoordinates()[1].getX());
        Assertions.assertEquals(expectedLine.getCoordinates()[1].getY(),operation.getCoordinates()[1].getY());


    }
    @Test
    void OperationFactoryTest_ValidFillOperation() throws Exception {
        FillOperation expectedFill = new FillOperation(new String[]{"1","2","o"});
        CanvasOperation operation = CanvasOperationFactory.getOperation(new String[]{"B","1", "2","o"});
        Assertions.assertTrue((operation instanceof FillOperation));
        Assertions.assertEquals(expectedFill.getCoordinates()[0].getX(),operation.getCoordinates()[0].getX());
        Assertions.assertEquals(expectedFill.getCoordinates()[0].getY(),operation.getCoordinates()[0].getY());
        FillOperation fillOperation =((FillOperation)operation);
        Assertions.assertEquals('o',fillOperation.getColor());

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



