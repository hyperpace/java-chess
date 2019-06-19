package chess.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static chess.domain.Direction.valuesOf;
import static org.assertj.core.api.Assertions.assertThat;

public class DirectionTest {

    @ParameterizedTest
    @CsvSource({"UP,3,2,3,1",
            "DOWN,3,2,3,3",
            "LEFT,3,2,2,2",
            "RIGHT,3,2,4,2",
            "DOWN_LEFT,3,2,2,3",
            "DOWN_RIGHT,3,2,4,3",
            "UP_LEFT,3,2,2,1",
            "UP_RIGHT,3,2,4,1",
            "DOUBLE_UP_LEFT,3,2,2,0",
            "DOUBLE_LEFT_UP,3,2,1,1",
            "DOUBLE_LEFT_DOWN,3,2,1,3",
            "DOUBLE_DOWN_LEFT,3,2,2,4",
            "DOUBLE_DOWN_RIGHT,3,2,4,4",
            "DOUBLE_RIGHT_DOWN,3,2,5,3",
            "DOUBLE_RIGHT_UP,3,2,5,1",
            "DOUBLE_UP_RIGHT,3,2,4,0"})
    void check_direction_true_test(Direction direction, int sourceX, int sourceY, int targetX, int targetY) {
        assertThat(direction.check(new Square(sourceX, sourceY), new Square(targetX,targetY))).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"UP,3,2,3,1",
            "DOWN,3,2,3,3",
            "LEFT,3,2,2,2",
            "RIGHT,3,2,4,2",
            "DOWN_LEFT,3,2,2,3",
            "DOWN_RIGHT,3,2,4,3",
            "UP_LEFT,3,2,2,1",
            "UP_RIGHT,3,2,4,1",
            "DOUBLE_UP_LEFT,3,2,2,0",
            "DOUBLE_LEFT_UP,3,2,1,1",
            "DOUBLE_LEFT_DOWN,3,2,1,3",
            "DOUBLE_DOWN_LEFT,3,2,2,4",
            "DOUBLE_DOWN_RIGHT,3,2,4,4",
            "DOUBLE_RIGHT_DOWN,3,2,5,3",
            "DOUBLE_RIGHT_UP,3,2,5,1",
            "DOUBLE_UP_RIGHT,3,2,4,0"})
    void 현재(Direction direction, int sourceX, int sourceY, int targetX, int targetY) {
        assertThat(valuesOf(new Square(sourceX,sourceY), new Square(targetX,targetY))).isEqualTo(direction);
    }
}
