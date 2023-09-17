package ru.stqa.geometry.figures;

public class Square {
    public static void printSquareArea(double side) {
        String format = String.format("Площадь квадрата со стороной %f = %f", side, area(side));
        System.out.println(format);
    }

    public static double area(double a) {
        return a * a;
    }

    public static double perimeter(double a) {
        return 4*a;
    }
}
