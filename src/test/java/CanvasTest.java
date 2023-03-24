import com.canvas.exception.CoordinatesNotWithinCanvasException;
import com.canvas.exception.IncorrectParametersException;
import com.canvas.model.TwoDCanvas;
import com.canvas.model.TwoDCoordinate;
import com.canvas.operations.FillOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CanvasTest {
    private TwoDCanvas canvas;

    @BeforeEach
    void setUp() throws Exception {
        canvas = new TwoDCanvas(new TwoDCoordinate<>(20, 4));
        canvas.createCanvas();
    }

    @Test
    void CanvasTest_Borders() throws Exception {
         String Expected=""+
                 "--------------------\n"+
                 "|                  |\n"+
                 "|                  |\n"+
                 "|                  |\n"+
                 "|                  |\n"+
                 "--------------------\n";

        Assertions.assertEquals(Expected,canvas.showCanvas());


    }
@Test
     void CanvasTest_drawLine() throws Exception {
        //When
        canvas.drawLine(new TwoDCoordinate<>(1, 2),new TwoDCoordinate<>(6, 2));
        String Expected=""+
                "----------------------\n"+
                "|                    |\n"+
                "|XXXXXX              |\n"+
                "|                    |\n"+
                "|                    |\n"+
                "----------------------\n";

        //then
        Assertions.assertEquals(Expected,canvas.showCanvas());


    }
    @Test
    public void CanvasTest_AddRectangle() throws Exception {
        //When
        canvas.drawRectangle(new TwoDCoordinate<>(14, 1),new TwoDCoordinate<>(18, 3));
        String Expected=""+
                "----------------------\n"+
                "|             XXXXX  |\n" +
                "|             X   X  |\n" +
                "|             XXXXX  |\n" +
                "|                    |\n"+
                "----------------------\n";

        //then
        System.out.println(canvas.showCanvas());
      Assertions.assertEquals(Expected,canvas.showCanvas());
    }

     @Test
     public void CanvasTest_AddFillOnRectangle() throws Exception {
         //When
         canvas.drawRectangle(new TwoDCoordinate<>(14, 1),new TwoDCoordinate<>(18, 3));
         canvas.drawFill(new TwoDCoordinate<>(14, 1),'o');
         String Expected=""+
                 "----------------------\n"+
                 "|             ooooo  |\n" +
                 "|             o   o  |\n" +
                 "|             ooooo  |\n" +
                 "|                    |\n"+
                 "----------------------\n";

         //then
          Assertions.assertEquals(Expected,canvas.showCanvas());
     }
     @Test
     public void CanvasTest_AddFillAroundRectangle() throws Exception {
         //When
         canvas.drawRectangle(new TwoDCoordinate<>(14, 1),new TwoDCoordinate<>(18, 3));
         canvas.drawFill(new TwoDCoordinate<>(14, 4),'o');
         String Expected=""+
                 "----------------------\n"+
                 "|oooooooooooooXXXXXoo|\n" +
                 "|oooooooooooooX   Xoo|\n" +
                 "|oooooooooooooXXXXXoo|\n" +
                 "|oooooooooooooooooooo|\n"+
                 "----------------------\n";

         //then
         Assertions.assertEquals(Expected,canvas.showCanvas());
     }

     @Test
     public void CanvasTest_AddFillOnLine() throws Exception {
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
     public void CanvasTest_AddFillAroundLine() throws Exception {
         //When
         canvas.drawLine(new TwoDCoordinate<>(1, 2),new TwoDCoordinate<>(6, 2));
         canvas.drawFill(new TwoDCoordinate<>(1, 3),'o');
         String Expected=""+
                 "----------------------\n"+
                 "|oooooooooooooooooooo|\n" +
                 "|XXXXXXoooooooooooooo|\n" +
                 "|oooooooooooooooooooo|\n" +
                 "|oooooooooooooooooooo|\n"+
                 "----------------------\n";

         //then
         Assertions.assertEquals(Expected,canvas.showCanvas());
     }

     @Test
     public void CanvasTest_AddFillAroundLineAndRectangle() throws Exception {
         //When
         canvas.drawLine(new TwoDCoordinate<>(1, 2),new TwoDCoordinate<>(6, 2));
         canvas.drawRectangle(new TwoDCoordinate<>(14, 1),new TwoDCoordinate<>(18, 3));
         canvas.drawFill(new TwoDCoordinate<>(1, 3),'o');
         String Expected=""+
                 "----------------------\n"+
                 "|oooooooooooooXXXXXoo|\n" +
                 "|XXXXXXoooooooX   Xoo|\n" +
                 "|oooooooooooooXXXXXoo|\n" +
                 "|oooooooooooooooooooo|\n"+
                 "----------------------\n";

         //then
         Assertions.assertEquals(Expected,canvas.showCanvas());
     }
     @Test
     public void CanvasTest_CheckWithinCanvasCorrectValues() throws Exception {
         //When
         canvas.isWithinCanvas(new TwoDCoordinate<>(1, 3));
         Assertions.assertTrue(canvas.isWithinCanvas(new TwoDCoordinate<>(1, 3)));
     }

     @Test
     public void CanvasTest_CheckWithinCanvasInCorrectValues() throws Exception {
         //When
         canvas.isWithinCanvas(new TwoDCoordinate<>(1, 3));
         Exception exception =assertThrows(CoordinatesNotWithinCanvasException.class,() ->canvas.isWithinCanvas(new TwoDCoordinate<>(5, 10)));


     }
}
