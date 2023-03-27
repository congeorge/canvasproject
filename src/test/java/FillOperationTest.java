import com.canvas.exception.CanvasException;
import com.canvas.exception.IncorrectParametersException;
import com.canvas.operations.FillOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FillOperationTest {
    @Test
    void FillOperationTest_CorrectNumberOfCommandParameters() throws CanvasException {
         FillOperation operation = new FillOperation(new String[]{"1","2","o"});
        Assertions.assertEquals(1,operation.getCoordinates()[0].getX());
        Assertions.assertEquals(2,operation.getCoordinates()[0].getY());
        Assertions.assertEquals('o',operation.getColor());
    }

    @ParameterizedTest
    @MethodSource("incorrectNoOfParameters")
    void FillOperationTest_IncorrectNumberOfParameters_IncorrectNoOfParameters(String[] args) {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new FillOperation(args));
        assertEquals("Fill operation needs 2 co-ordinates and fill color: B x y c ", exception.getMessage());
    }

    private static Stream<Arguments> incorrectNoOfParameters() {
        return Stream.of(
                Arguments.of((Object)new  String[]{"20","4"}),
                Arguments.of((Object)new String[]{"2"}),
                Arguments.of((Object)new String[]{})
                );
    }

    @ParameterizedTest
    @MethodSource("nonNumberInvalidValues")
    void FillOperationTest_InCorrectCommandParameters_WithInValidValues(String[] args) {
        Exception exception = assertThrows(IncorrectParametersException.class, () ->  new FillOperation(args));
        assertEquals("All Fill co-ordinates should be valid numbers", exception.getMessage());
    }

    private static Stream<Arguments> nonNumberInvalidValues() {
        return Stream.of(
                Arguments.of((Object)new String[]{"a", "b", "2"}),
                Arguments.of((Object)new String[]{"2","b","2"}),
                Arguments.of((Object)new String[]{"a","3","k"})

        );
    }

    @ParameterizedTest
    @MethodSource("negativeOrZeroValueParameters")
    void FillOperationTest_InCorrectParameters_WithNegativeOrZeroValueParameters(String[] args){
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new FillOperation(args));
        assertEquals("Fill coordinates cannot be less than 0", exception.getMessage());
    }

    private static Stream<Arguments> negativeOrZeroValueParameters() {
        return Stream.of(
                Arguments.of((Object)new String[]{"0", "2", "k"}),
                Arguments.of((Object)new String[]{"2","0","k"}),
                Arguments.of((Object)new String[]{"0","0","k"}),
                Arguments.of((Object)new String[]{"-1","-2","k"}),
                Arguments.of((Object)new String[]{"1","-2","b"})
            );
    }

}
