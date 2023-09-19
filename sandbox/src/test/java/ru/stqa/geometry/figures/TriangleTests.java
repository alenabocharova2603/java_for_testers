package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    @Test
    void canCalculateTriangleArea() {
        var t = new Triangle(4,6,6);
        double result = t.area();
        Assertions.assertEquals(11.313708498984761, result);
    }
    @Test
    void canCalculateTrianglePerimeter(){
       /* double result = Triangle.perimetr(5, 7, 10);*/
        Assertions.assertEquals(22.0, new Triangle(5,7,10).perimetr());
    }
}
