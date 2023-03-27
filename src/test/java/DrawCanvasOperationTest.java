import com.canvas.exception.IncorrectParametersException;
import com.canvas.operations.DrawCanvasOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DrawCanvasOperationTest {
    @Test
    void DrawCanvasOperationTest_CorrectNumberOfCommandParameters() throws Exception {
        DrawCanvasOperation operation = new DrawCanvasOperation(new String[]{"20", "4"});
         Assertions.assertEquals(20,operation.getCoordinates()[0].getX());
        Assertions.assertEquals(4,operation.getCoordinates()[0].getY());
    }

    @ParameterizedTest
    @MethodSource("incorrectNoOfParameters")
    void DrawCanvasOperationTest_IncorrectNumberOfCommandParameters(String[] args) {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new DrawCanvasOperation(new String[]{"20"}));
        assertEquals("Canvas needs height and width to be specified: C W H", exception.getMessage());
    }

    private static Stream<Arguments> incorrectNoOfParameters() {
        return Stream.of(
                Arguments.of((Object)new  String[]{"20"}),
                Arguments.of((Object)new String[]{})
        );
    }

    @ParameterizedTest
    @MethodSource("negativeOrZeroValueParameters")
    void DrawCanvasOperationTest_InCorrectParameters_NegativeOrZeroValueParameters(String[] args)  {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new DrawCanvasOperation(args));
        assertEquals("Canvas needs height and width to be greater than 0", exception.getMessage());
    }
    private static Stream<Arguments> negativeOrZeroValueParameters() {
        return Stream.of(
                Arguments.of((Object)new String[]{"0","0"}),
                Arguments.of((Object)new String[]{"0","8"}),
                Arguments.of((Object)new String[]{"9","0"}),
                Arguments.of((Object)new String[]{"-20","-5"}),
                Arguments.of((Object)new String[]{"-20","5"}),
                Arguments.of((Object)new String[]{"-20","5"})

        );
    }
    @Test
    void DrawCanvasOperationTest_InCorrectCommandParameters_WithNonValidValues()  {
        Exception exception = assertThrows(IncorrectParametersException.class, () ->  new DrawCanvasOperation(new String[]{"a","b"}));
        assertEquals("Canvas width and height should be valid numbers", exception.getMessage());
    }

}
