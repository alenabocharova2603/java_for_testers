package ru.stqa.geometry.figures;


import java.util.Objects;

import static java.lang.Math.sqrt;

public record Triangle(double a, double b, double c) {

    public Triangle{
        if (a < 0 || b < 0  || c < 0) {
            throw new IllegalArgumentException("Triangle side should be non-negative");
        }
        if ( a > (b+c) || b > (a+c) || c > (a+b)) {
            throw new IllegalArgumentException("Triangle side  not should be greater, than the sum of the other two sides");
        }
    }


    public static void main(String[] args) {
        printTrianglePerimeter(new Triangle(5,7,10));
        printTriangleArea(new Triangle(4,6,6));
    }

     public static void printTriangleArea(Triangle t){
         String text = String.format("Площадь треугольника со сторонами %.2f , %.2f , %.2f = %.2f", t.a, t.b, t.c,t.area());
         System.out.println(text);
     }

    public double area() {
        return sqrt(perimeter()/2*(perimeter()/2-this.a)*(perimeter()/2-this.b)*(perimeter()/2-this.c));
    }

    public static void printTrianglePerimeter(Triangle t) {
        String text = String.format("Периметр треугольника со сторонами %.2f , %.2f , %.2f = %.2f", t.a, t.b, t.c, t.perimeter());
        System.out.println(text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Double.compare(triangle.a, this.a) == 0 && Double.compare(triangle.b, this.b) == 0 && Double.compare(triangle.c, this.c) == 0
               || Double.compare(triangle.a, this.a) == 0 && Double.compare(triangle.b, this.c) == 0 && Double.compare(triangle.c, this.b) == 0
                || Double.compare(triangle.a, this.b) == 0 && Double.compare(triangle.b, this.a) == 0 && Double.compare(triangle.c, this.c) == 0
                || Double.compare(triangle.a, this.b) == 0 && Double.compare(triangle.b, this.c) == 0 && Double.compare(triangle.c, this.a) == 0
                || Double.compare(triangle.a, this.c) == 0 && Double.compare(triangle.b, this.a) == 0 && Double.compare(triangle.c, this.b) == 0
                || Double.compare(triangle.a, this.a) == 0 && Double.compare(triangle.b, this.b) == 0 && Double.compare(triangle.c, this.a) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }

    public double perimeter() {
         return this.a+this.b+this.c;
    }
}


