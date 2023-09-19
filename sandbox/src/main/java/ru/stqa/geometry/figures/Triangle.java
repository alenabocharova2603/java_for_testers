package ru.stqa.geometry.figures;


import static java.lang.Math.sqrt;


public class Triangle {


    private double sideA;
    private double sideB;
    private double sideC;
    public Triangle(double a, double b, double c) {
        this.sideA = a;
        this.sideB = b;
        this.sideC = c;
    }




    public static void main(String[] args) {
        printTrianglePerimeter(5,7,10);
        printTriangleArea(4,6,6);
    }


    public static void printTriangleArea(double a, double b, double c) {
        String text = String.format("Площадь треугольника со сторонами %.2f , %.2f , %.2f = %.2f", a, b, c, area(a, b, c));
        System.out.println(text);
    }


    public static double area(double a, double b, double c) {
        return sqrt(((a+b+c)/2)*(((a+b+c)/2)-a)*(((a+b+c)/2)-b)*(((a+b+c)/2)-c));
    }


    public static void printTrianglePerimeter(double a, double b, double c) {
        String text = String.format("Периметр треугольника со сторонами %.2f , %.2f , %.2f = %.2f", a, b, c, perimetr(a, b, c));
        System.out.println(text);
    }


    public static double perimetr(double a, double b, double c) {
        return a+b+c;
    }


    public double area() {
        return sqrt(((this.sideA+this.sideB+this.sideC)/2)*(((this.sideA+this.sideB+this.sideC)/2)-this.sideA)*(((this.sideA+this.sideB+this.sideC)/2)-this.sideB)*(((this.sideA+this.sideB+this.sideC)/2)-this.sideC));
    }


    public double perimetr() {
        return this.sideA+this.sideB+this.sideC;
    }
}
