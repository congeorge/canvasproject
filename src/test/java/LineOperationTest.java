import com.canvas.exception.CanvasException;
import com.canvas.exception.IncorrectParametersException;
import com.canvas.operations.LineOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

 class LineOperationTest {
    @Test
    void LineOperationTest_CorrectNumberOfCommandParameters() throws CanvasException {
        LineOperation operation = new LineOperation(new String[]{"1", "2","6", "2"});
        Assertions.assertEquals(1,operation.getCoordinates()[0].getX());
        Assertions.assertEquals(2,operation.getCoordinates()[0].getY());
        Assertions.assertEquals(6,operation.getCoordinates()[1].getX());
        Assertions.assertEquals(2,operation.getCoordinates()[1].getY());
    }

    @Test
    void LineOperationTest_CorrectNumberOfCommandParameters_SortOrderOfArguments() throws CanvasException {
        LineOperation operation = new LineOperation(new String[]{"6", "2","1", "2"});
        Assertions.assertEquals(1,operation.getCoordinates()[0].getX());
        Assertions.assertEquals(2,operation.getCoordinates()[0].getY());
        Assertions.assertEquals(6,operation.getCoordinates()[1].getX());
        Assertions.assertEquals(2,operation.getCoordinates()[1].getY());
    }


     @ParameterizedTest
     @MethodSource("incorrectNoOfParameters")
    void getDrawCanvasOperationTest_IncorrectNumberOfParameters_IncorrectNoOfParameters() {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new LineOperation(new String[]{"20","4"}));
        assertEquals("Line needs 4 co-ordinates : L x1 y1 x2 y2", exception.getMessage());
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
    void getDrawCanvasOperationTest_InCorrectCommandParameters_NonNumberInvalidValues(String[] args) {
        Exception exception = assertThrows(IncorrectParametersException.class, () ->  new LineOperation(new String[]{"a","b","2","4"}));
        assertEquals("Line needs valid 4 co-ordinates : L x1 y1 x2 y2", exception.getMessage());
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
    void getDrawCanvasOperationTest_InCorrectParameters_NegativeOrZeroValueParameters(String[] args)  {
        Exception exception = assertThrows(IncorrectParametersException.class, () -> new LineOperation(args));
        assertEquals("Line coordinates cannot be less than 0", exception.getMessage());
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

     @ParameterizedTest
     @MethodSource("diagonalLineParameters")
     void getDrawCanvasOperationTest_InCorrectParameters_WithDiagonalLineParams(String[] args){
         Exception exception = assertThrows(IncorrectParametersException.class, () -> new LineOperation(new String[]{"1","2","6","3"}));
         assertEquals("Diagonal line is not supported", exception.getMessage());
     }

     private static Stream<Arguments> diagonalLineParameters() {
         return Stream.of(
                 Arguments.of((Object)new String[]{"1","2","6","3"}),
                 Arguments.of((Object)new String[]{"2","4","6","3"}),
                 Arguments.of((Object)new String[]{"5","2","6","3"})



         );
     }
 }
