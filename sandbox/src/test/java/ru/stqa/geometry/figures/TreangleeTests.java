package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TreangleeTests {
    @Test
    void canCalculateSquareTreanglee() {
        double result = Trianglee.squereTriangelee(4, 6, 6);
        Assertions.assertEquals(11.313708498984761, result);
    }
    @Test
    void canCanculatePerimeterTreanglee(){
        double result = Trianglee.perimetrTriangelee(5, 7, 10);
        Assertions.assertEquals(22.0, result);
    }
}
