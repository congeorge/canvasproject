import com.canvas.model.TwoDCanvas;
import com.canvas.model.TwoDCoordinate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
     void CanvasTest_AddLine() throws Exception {
        //When
        canvas.addLine(new TwoDCoordinate<>(1, 2),new TwoDCoordinate<>(6, 2));
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
}
