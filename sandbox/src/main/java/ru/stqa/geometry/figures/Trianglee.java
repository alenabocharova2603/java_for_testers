package ru.stqa.geometry.figures;

import static java.lang.Math.sqrt;

public class Trianglee {
    public static void main(String[] args) {
        printPerimetrTriangle(5,7,10);
        printSquereTriangle(4,6,6);
    }

    public static void printSquereTriangle(double a, double b, double c) {
        String text = String.format("Площадь треугольника со сторонами %f , %f , %f = %f", a, b, c, squereTriangelee(a, b, c));
        System.out.println(text);
    }

    public static double squereTriangelee(double a, double b, double c) {
        return sqrt(((a+b+c)/2)*(((a+b+c)/2)-a)*(((a+b+c)/2)-b)*(((a+b+c)/2)-c));
    }

    public static void printPerimetrTriangle(double a, double b, double c) {
        String text = String.format("Периметр треугольника со сторонами  %f , %f , %f = %f", a, b, c, perimetrTriangelee(a, b, c));
        System.out.println(text);
    }

    public static double perimetrTriangelee(double a, double b, double c) {
        return a+b+c;
    }
}
