package ru.stqa.geometry.figures;

import static java.lang.Math.sqrt;

public class Trianglee {
    public static void main(String[] args) {
        printPerimetrTriangle(5,7,10);
        printSquereTriangle(4,6,6);
    }

    private static void printSquereTriangle(double a, double b, double c) {
        System.out.println("Площадь треугольника со сторонами " + a + " , " + b + " , " + c + " = " +  squereTriangelee(a,b,c));
    }

    private static double squereTriangelee(double a, double b, double c) {
        return sqrt(((a+b+c)/2)*(((a+b+c)/2)-a)*(((a+b+c)/2)-b)*(((a+b+c)/2)-c));
    }

    static void printPerimetrTriangle(double a, double b, double c) {
     System.out.println("Периметр треугольника со сторонами " + a + " , " + b + " , " + c + " = " +  perimetrTriangelee(a,b,c));
    }

    private static double perimetrTriangelee(double a, double b, double c) {
        return a+b+c;
    }
}
