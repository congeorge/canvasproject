import com.canvas.exception.*;
import com.canvas.operations.*;
import com.canvas.others.CanvasOperationExecutor;
import com.canvas.others.CanvasOperationFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CanvasOperationExecutorTest {
    private CanvasOperationExecutor executor;

    @BeforeEach
    void setUp() throws Exception {
        executor =new CanvasOperationExecutor();


    }


    @Test
    void CanvasOperationExecutorTest_LineOperationBeforeCanvasOperation() throws Exception {

        Exception e =Assertions.assertThrows(NoCanvasException.class,() -> executor.executeDrawOperation(new LineOperation(new String[]{"1", "2","6", "2"})));;

    }
    @Test
    void CanvasOperationExecutorTest_FilleOperationBeforeCanvasOperation() throws Exception {

        Exception e =Assertions.assertThrows(NoCanvasException.class,() -> executor.executeDrawOperation(new FillOperation(new String[]{"1","2","o"})));

    }
    @Test
    void CanvasOperationExecutorTest_RectangleOperationBeforeCanvasOperation() throws Exception {

        Exception e =Assertions.assertThrows(NoCanvasException.class,() -> executor.executeDrawOperation(new RectangleOperation(new String[]{"14","1","18","13"})));

    }
    @Test
    void CanvasOperationExecutorTest_CheckDrawCanvasOperation() throws Exception {
        executor.executeDrawOperation(new DrawCanvasOperation(new String[]{"20", "4"}));
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
        executor.executeDrawOperation(new DrawCanvasOperation(new String[]{"20", "4"}));
        executor.executeDrawOperation(  new LineOperation(new String[]{"1", "2","6", "2"}));;
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
    void CanvasOperationExecutorTest_DrawRectangleWithCoordinatesOutsideOfCanvasTest() throws Exception {
        //When
        executor.executeDrawOperation(new DrawCanvasOperation(new String[]{"20", "4"}));
        Exception exception = assertThrows(CoordinatesNotWithinCanvasException.class, () ->   executor.executeDrawOperation(new RectangleOperation(new String[]{"5", "5","10", "25"})));
        assertEquals("Co-ordinates are outside of the Canvas", exception.getMessage());
        Assertions.assertEquals(1,executor.getCanvasOperationList().size());


    }

    @Test
    void CanvasOperationExecutorTest_FillOperationWithCoordinatesOutsideOfCanvasTest() throws Exception {
        //When
        executor.executeDrawOperation(new DrawCanvasOperation(new String[]{"20", "4"}));
        Exception exception = assertThrows(CoordinatesNotWithinCanvasException.class, () ->   executor.executeDrawOperation(new FillOperation(new String[]{"10", "25","c"})));
        assertEquals("Co-ordinates are outside of the Canvas", exception.getMessage());
        Assertions.assertEquals(1,executor.getCanvasOperationList().size());


    }



    @Test
    void CanvasOperationExecutorTest_DrawLineWithCoordinatesOutsideOfCanvasTest() throws Exception {
        //When
        executor.executeDrawOperation(new DrawCanvasOperation(new String[]{"20", "4"}));
        Exception exception = assertThrows(CoordinatesNotWithinCanvasException.class, () ->   executor.executeDrawOperation(new LineOperation(new String[]{"5", "10","5", "25"})));
        assertEquals("Co-ordinates are outside of the Canvas", exception.getMessage());
        Assertions.assertEquals(1,executor.getCanvasOperationList().size());


    }



    @Test
    void CanvasOperationExecutorTest_DrawRectangleOnCanvasAndThenUndoTest() throws Exception {

        //When
        executor.executeDrawOperation(new DrawCanvasOperation(new String[]{"20", "4"}));
        executor.executeDrawOperation(  new RectangleOperation(new String[]{"14", "1","18", "3"}));;
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
        executor.undo();
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
        executor.undo();
        Assertions.assertEquals(0,executor.getCanvasOperationList().size());
    }

    @Test
    void CanvasOperationExecutorTest_DrawLineAndRectangleAndDoFillOnCanvasAndThenUndoTest() throws Exception {

        //When
        executor.executeDrawOperation(new DrawCanvasOperation(new String[]{"20", "4"}));
        executor.executeDrawOperation(  new LineOperation(new String[]{"1", "2","6", "2"}));;
        executor.executeDrawOperation(  new RectangleOperation(new String[]{"14", "1","18", "3"}));;
        executor.executeDrawOperation(  new FillOperation(new String[]{"10", "3","o"}));;
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
        executor.undo();
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

    @Test
    void CanvasOperationExecutorTest_IsNonDrawOperationTest() throws Exception {
        assertTrue(executor.isDrawOperation(new String[]{"U"}));
        assertTrue(executor.isDrawOperation(new String[]{"Q"}));
        assertTrue(executor.isDrawOperation(new String[]{"H"}));
    }
    @Test
    void CanvasOperationExecutorTest_QuitApplicationTest() throws Exception {
        assertTrue(executor.isDrawOperation(new String[]{"Q"}));
        Exception exception = assertThrows(QuitCanvasException.class, () -> executor.executeNonDrawOperation(new String[]{"Q"}));
        assertEquals("Quitting the Application", exception.getMessage());

    }


    @Test
    void CanvasOperationExecutorTest_HelpTest() throws Exception {
        assertTrue(executor.isDrawOperation(new String[]{"Q"}));
        Exception exception = assertThrows(HelpCanvasException.class, () -> executor.executeNonDrawOperation(new String[]{"H"}));
        assertEquals("Help Details", exception.getMessage());

    }


    @Test
    void CanvasOperationExecutorTest_UndoNonDrawOperationTest() throws Exception {
        executor.executeDrawOperation(new DrawCanvasOperation(new String[]{"20", "4"}));
        executor.executeDrawOperation(  new LineOperation(new String[]{"1", "2","6", "2"}));;
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
        assertTrue(executor.isDrawOperation(new String[]{"U"}));
        executor.executeNonDrawOperation(new String[]{"U"});
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

}
