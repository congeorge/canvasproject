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
    void CanvasOperationExecutorTest_DrawLineOnCanvasAndThenUndoTest() throws Exception {
        //When
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
    /*
    @Test
    void CanvasTest_AddRectangle() throws Exception {
        //When
        canvas.drawRectangle(new TwoDCoordinate<>(14, 1),new TwoDCoordinate<>(18, 3));
        String expected=""+
                "----------------------\n"+
                "|             XXXXX  |\n" +
                "|             X   X  |\n" +
                "|             XXXXX  |\n" +
                "|                    |\n"+
                "----------------------\n";

        //then
        System.out.println(canvas.showCanvas());
      Assertions.assertEquals(expected,canvas.showCanvas());
    }

     @Test
     void CanvasTest_AddFillOnRectangle() throws Exception {
         //When
         canvas.drawRectangle(new TwoDCoordinate<>(14, 1),new TwoDCoordinate<>(18, 3));
         canvas.drawFill(new TwoDCoordinate<>(14, 1),'o');
         String expected=""+
                 "----------------------\n"+
                 "|             ooooo  |\n" +
                 "|             o   o  |\n" +
                 "|             ooooo  |\n" +
                 "|                    |\n"+
                 "----------------------\n";

         //then
          Assertions.assertEquals(expected,canvas.showCanvas());
     }
     @Test
     void CanvasTest_AddFillAroundRectangle() throws Exception {
         //When
         canvas.drawRectangle(new TwoDCoordinate<>(14, 1),new TwoDCoordinate<>(18, 3));
         canvas.drawFill(new TwoDCoordinate<>(14, 4),'o');
         String expected=""+
                 "----------------------\n"+
                 "|oooooooooooooXXXXXoo|\n" +
                 "|oooooooooooooX   Xoo|\n" +
                 "|oooooooooooooXXXXXoo|\n" +
                 "|oooooooooooooooooooo|\n"+
                 "----------------------\n";

         //then
         Assertions.assertEquals(expected,canvas.showCanvas());
     }

     @Test
     void CanvasTest_AddFillOnLine() throws Exception {
         //When
         canvas.drawLine(new TwoDCoordinate<>(1, 2),new TwoDCoordinate<>(6, 2));
         String expected1=""+
                 "----------------------\n"+
                 "|                    |\n"+
                 "|XXXXXX              |\n"+
                 "|                    |\n"+
                 "|                    |\n"+
                 "----------------------\n";

         //then
         Assertions.assertEquals(expected1,canvas.showCanvas());
         canvas.drawFill(new TwoDCoordinate<>(1, 2),'o');
         String expected2=""+
                 "----------------------\n"+
                 "|                    |\n"+
                 "|oooooo              |\n"+
                 "|                    |\n"+
                 "|                    |\n"+
                 "----------------------\n";

         //then
         System.out.println(canvas.showCanvas());
         Assertions.assertEquals(expected2,canvas.showCanvas());
     }
     @Test
     void CanvasTest_AddFillAroundLine() throws Exception {
         //When
         canvas.drawLine(new TwoDCoordinate<>(1, 2),new TwoDCoordinate<>(6, 2));
         canvas.drawFill(new TwoDCoordinate<>(1, 3),'o');
         String expected=""+
                 "----------------------\n"+
                 "|oooooooooooooooooooo|\n" +
                 "|XXXXXXoooooooooooooo|\n" +
                 "|oooooooooooooooooooo|\n" +
                 "|oooooooooooooooooooo|\n"+
                 "----------------------\n";

         //then
         Assertions.assertEquals(expected,canvas.showCanvas());
     }

     @Test
     void CanvasTest_AddFillAroundLineAndRectangle() throws Exception {
         //When
         canvas.drawLine(new TwoDCoordinate<>(1, 2),new TwoDCoordinate<>(6, 2));
         canvas.drawRectangle(new TwoDCoordinate<>(14, 1),new TwoDCoordinate<>(18, 3));
         canvas.drawFill(new TwoDCoordinate<>(1, 3),'o');
         String expected=""+
                 "----------------------\n"+
                 "|oooooooooooooXXXXXoo|\n" +
                 "|XXXXXXoooooooX   Xoo|\n" +
                 "|oooooooooooooXXXXXoo|\n" +
                 "|oooooooooooooooooooo|\n"+
                 "----------------------\n";

         //then
         Assertions.assertEquals(expected,canvas.showCanvas());
     }
     @Test
     void CanvasTest_CheckWithinCanvasCorrectValues() throws Exception {
         //When
         canvas.isWithinCanvas(new TwoDCoordinate<>(1, 3));
         Assertions.assertTrue(canvas.isWithinCanvas(new TwoDCoordinate<>(1, 3)));
     }

     @Test
     void CanvasTest_CheckWithinCanvasInCorrectValues() throws Exception {
         //When
         canvas.isWithinCanvas(new TwoDCoordinate<>(1, 3));
         Exception exception =assertThrows(CoordinatesNotWithinCanvasException.class,() ->canvas.isWithinCanvas(new TwoDCoordinate<>(5, 10)));


     }*/
}
