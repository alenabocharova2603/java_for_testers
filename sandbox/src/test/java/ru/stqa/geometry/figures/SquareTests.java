package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {
    @Test
    void canCalculateArea() {
        double result = Square.area(5);
        Assertions.assertEquals(25.0, result);
    }
    @Test
    void canCalculatePerimeter(){
        Assertions.assertEquals(20, Square.perimeter(5.0));

    }
}
