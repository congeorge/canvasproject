import com.canvas.exception.CanvasException;
import com.canvas.exception.IncorrectParametersException;
import com.canvas.operations.RectangleOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RectangleOperationTest {
    @Test
    void RectangleOperationTest_CorrectNumberOfCommandParameters() throws CanvasException {
         RectangleOperation operation = new RectangleOperation(new String[]{"14","1","18","13"});
        Assertions.assertEquals(14,operation.getCoordinates()[0].getX());
        Assertions.assertEquals(1,operation.getCoordinates()[0].getY());
        Assertions.assertEquals(18,operation.getCoordinates()[1].getX());
        Assertions.assertEquals(13,operation.getCoordinates()[1].getY());
    }
    @Test
    void RectangleOperationTest_CorrectNumberOfCommandParameters_SortOrderOfArguments() throws CanvasException {
        RectangleOperation operation = new RectangleOperation(new String[]{"18", "13","14", "1"});
        Assertions.assertEquals(14,operation.getCoordinates()[0].getX());
        Assertions.assertEquals(1,operation.getCoordinates()[0].getY());
        Assertions.assertEquals(18,operation.getCoordinates()[1].getX());
        Assertions.assertEquals(13,operation.getCoordinates()[1].getY());
    }

    @ParameterizedTest
    @MethodSource("incorrectNoOfParameters")
    void RectangleOperationTest_IncorrectNumberOfParameters_IncorrectNoOfParameters(String[] args) {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new RectangleOperation(args));
        assertEquals("Rectangle needs 4 co-ordinates", exception.getMessage());
    }


    private static Stream<Arguments> incorrectNoOfParameters() {
        return Stream.of(
                Arguments.of((Object)new  String[]{"20","4"}),
                Arguments.of((Object)new  String[]{"20","4","20"}),
                Arguments.of((Object)new String[]{"2"}),
                Arguments.of((Object)new String[]{})
        );
    }

    @ParameterizedTest
    @MethodSource("nonNumberInvalidValues")
    void RectangleOperationTest_InCorrectCommandParameters_NonNumberInvalidValues(String[] args) {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new RectangleOperation(args));
        assertEquals("All Rectangle co-ordinates should be valid numbers", exception.getMessage());
    }

    private static Stream<Arguments> nonNumberInvalidValues() {
        return Stream.of(
                Arguments.of((Object)new String[]{"a","2","2","4"}),
                Arguments.of((Object)new String[]{"2","a","2","4"}),
                Arguments.of((Object)new String[]{"2","2","2","a"}),
                Arguments.of((Object)new String[]{"a","b","c","d"}),
                Arguments.of((Object)new String[]{"2","2","c","d"})

        );
    }

    @ParameterizedTest
    @MethodSource("negativeOrZeroValueParameters")
    void RectangleOperationTest_InCorrectParameters_NegativeOrZeroValueParameters(String[] args){
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new RectangleOperation(new String[]{"0","2","5","5"}));
        assertEquals("Rectangle coordinates cannot be less than 0", exception.getMessage());
    }
    private static Stream<Arguments> negativeOrZeroValueParameters() {
        return Stream.of(
                Arguments.of((Object)new String[]{"0","2","6","2"}),
                Arguments.of((Object)new String[]{"2","0","6","0"}),
                Arguments.of((Object)new String[]{"2","2","0","2"}),
                Arguments.of((Object)new String[]{"2","6","2","0"}),
                Arguments.of((Object)new String[]{"-1","2","-1","2"}),
                Arguments.of((Object)new String[]{"1","-2","1","-2"})

        );
    }
}
