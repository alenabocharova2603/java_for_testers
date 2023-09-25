package ru.stqa.geometry.figures;


import static java.lang.Math.sqrt;

public record Triangle(double a, double b, double c) {

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

    public double perimeter() {
         return this.a+this.b+this.c;
    }
}


