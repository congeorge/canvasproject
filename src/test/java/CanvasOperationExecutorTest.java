import com.canvas.exception.CoordinatesNotWithinCanvasException;
import com.canvas.exception.NoCanvasException;
import com.canvas.model.TwoDCanvas;
import com.canvas.model.TwoDCoordinate;
import com.canvas.operations.*;
import com.canvas.others.CanvasOperationExecutor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CanvasOperationExecutorTest {
    private CanvasOperationExecutor executor;

    @BeforeEach
    void setUp() throws Exception {
        executor =new CanvasOperationExecutor();


    }


    @Test
    void CanvasOperationExecutorTest_LineOperationBeforeCanvasOperation() throws Exception {

        Exception e =Assertions.assertThrows(NoCanvasException.class,() -> executor.executeOperation(new LineOperation(new String[]{"1", "2","6", "2"})));;

    }
    @Test
    void CanvasOperationExecutorTest_FilleOperationBeforeCanvasOperation() throws Exception {

        Exception e =Assertions.assertThrows(NoCanvasException.class,() -> executor.executeOperation(new FillOperation(new String[]{"1","2","o"})));

    }
    @Test
    void CanvasOperationExecutorTest_RectangleOperationBeforeCanvasOperation() throws Exception {

        Exception e =Assertions.assertThrows(NoCanvasException.class,() -> executor.executeOperation(new RectangleOperation(new String[]{"14","1","18","13"})));

    }
    @Test
    void CanvasOperationExecutorTest_CheckDrawCanvasOperation() throws Exception {
        executor.executeOperation(new DrawCanvasOperation(new String[]{"20", "4"}));
        String expected=""+
                "----------------------\n"+
                "|                    |\n"+
                "|                    |\n"+
                "|                    |\n"+
                "|                    |\n"+
                "----------------------\n";

        Assertions.assertEquals(expected,executor.getCanvas().showCanvas());
        Assertions.assertEquals(1,executor.getCanvasOperationList().size());

    }


    @Test
     void CanvasOperationExecutorTest_DrawLineOnCanvasTest() throws Exception {
        //When
        executor.executeOperation(new DrawCanvasOperation(new String[]{"20", "4"}));
        executor.executeOperation(  new LineOperation(new String[]{"1", "2","6", "2"}));;
         String expected=""+
                "----------------------\n"+
                "|                    |\n"+
                "|XXXXXX              |\n"+
                "|                    |\n"+
                "|                    |\n"+
                "----------------------\n";

        //then
        Assertions.assertEquals(expected,executor.getCanvas().showCanvas());
    Assertions.assertEquals(2,executor.getCanvasOperationList().size());


    }


    @Test
    void CanvasOperationExecutorTest_DrawRectangleOnCanvasAndThenUndoTest() throws Exception {

        //When
        executor.executeOperation(new DrawCanvasOperation(new String[]{"20", "4"}));
        executor.executeOperation(  new RectangleOperation(new String[]{"14", "1","18", "3"}));;
        String expected=""+
                "----------------------\n"+
                "|             XXXXX  |\n" +
                "|             X   X  |\n" +
                "|             XXXXX  |\n" +
                "|                    |\n"+
                "----------------------\n";

        //then
        Assertions.assertEquals(expected,executor.getCanvas().showCanvas());
        Assertions.assertEquals(2,executor.getCanvasOperationList().size());
        executor.executeOperation(  new UndoCanvasOperation(new String[]{}));
        String expected2=""+
                "----------------------\n"+
                "|                    |\n"+
                "|                    |\n"+
                "|                    |\n"+
                "|                    |\n"+
                "----------------------\n";

        //then
        Assertions.assertEquals(expected2,executor.getCanvas().showCanvas());
        Assertions.assertEquals(1,executor.getCanvasOperationList().size());

    }

    @Test
    void CanvasOperationExecutorTest_DrawLineAndRectangleAndDoFillOnCanvasAndThenUndoTest() throws Exception {

        //When
        executor.executeOperation(new DrawCanvasOperation(new String[]{"20", "4"}));
        executor.executeOperation(  new LineOperation(new String[]{"1", "2","6", "2"}));;
        executor.executeOperation(  new RectangleOperation(new String[]{"14", "1","18", "3"}));;
        executor.executeOperation(  new FillOperation(new String[]{"10", "3","o"}));;
        String expected=""+
                "----------------------\n"+
                "|oooooooooooooXXXXXoo|\n" +
                "|XXXXXXoooooooX   Xoo|\n" +
                "|oooooooooooooXXXXXoo|\n" +
                "|oooooooooooooooooooo|\n"+
                "----------------------\n";

        //then
        Assertions.assertEquals(expected,executor.getCanvas().showCanvas());
        Assertions.assertEquals(4,executor.getCanvasOperationList().size());
        executor.executeOperation(  new UndoCanvasOperation(new String[]{}));
        String expected2=""+
                "----------------------\n"+
                "|             XXXXX  |\n"+
                "|XXXXXX       X   X  |\n"+
                "|             XXXXX  |\n"+
                "|                    |\n"+
                "----------------------\n";

        //then
        Assertions.assertEquals(expected2,executor.getCanvas().showCanvas());
        Assertions.assertEquals(3,executor.getCanvasOperationList().size());

    }

}
