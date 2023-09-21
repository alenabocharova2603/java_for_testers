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
        printTrianglePerimeter(new Triangle(5,7,10));
        printTriangleArea(new Triangle(4,6,6));
    }


    public static void printTriangleArea(Triangle triangle) {
        String text = String.format("Площадь треугольника со сторонами %.2f , %.2f , %.2f = %.2f", triangle.sideA, triangle.sideB, triangle.sideC, triangle.area());
        System.out.println(text);
    }



    public static void printTrianglePerimeter(Triangle triangle) {
        String text = String.format("Периметр треугольника со сторонами %.2f , %.2f , %.2f = %.2f", triangle.sideA, triangle.sideB, triangle.sideC, triangle.perimeter());
        System.out.println(text);
    }


    public double area() {
        return sqrt(((this.sideA+this.sideB+this.sideC)/2)*(((this.sideA+this.sideB+this.sideC)/2)-this.sideA)*(((this.sideA+this.sideB+this.sideC)/2)-this.sideB)*(((this.sideA+this.sideB+this.sideC)/2)-this.sideC));
    }


    public double perimeter() {
        return this.sideA+this.sideB+this.sideC;
    }
}
