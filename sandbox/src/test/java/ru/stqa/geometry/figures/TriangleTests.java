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
       /* double result = Triangle.perimeter(5, 7, 10);*/
        Assertions.assertEquals(22.0, new Triangle(5,7,10).perimeter());
    }

    @Test
    void cannotCreateTriangleWithNegativeSide() {
        try {
            new Triangle(-5.0, 3.0,7);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //OK
        }
    }

    @Test
    void cannotCreateTriangleInequalityViolated() {
        try {
            new Triangle(3.0, 3.0,19);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //OK
        }
    }
    @Test
    void testEquality() {
        var t1 = new Triangle (5.0, 5.0,5.0);
        var t2 = new Triangle (5.0, 5.0, 5.0);
        Assertions.assertEquals(t1,t2);
    }
    @Test
    void testEquality2() {
        var t1 = new Triangle (5.0, 4.0,3.0);
        var t2 = new Triangle (4.0, 3.0, 5.0);
        Assertions.assertEquals(t1,t2);
    }

}
