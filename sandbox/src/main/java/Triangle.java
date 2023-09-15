public class Triangle {
    public static void main(String[] args) {
      /*  var Side_A = 3;
        var Side_B = 5;
        var Side_C = 10;
    */
        printTriangleArea(3.0, 5.0, 4.0);
        printTriangleArea(5.0, 11.0, 8.0);

        /*System.out.println("Длина стороны " + "a " + "= " + Side_A);
        System.out.println("Длина стороны " + "b " + "= " + Side_B);
        System.out.println("Длина стороны " + "c " + "= " + Side_C);
        System.out.println("Периметр треугольника " + "= " + (Side_A+Side_B+Side_C));

         */
    }

    private static void printTriangleArea(double a, double b, double c) {
        System.out.println("Периметр треугольника со сторонами " +  a + ", " + b + ", " + c + " = " + perimetrTriangele(a,b,c));
    }

    private static double perimetrTriangele(double a, double b, double c) {
        return a+b+c;
    }
}
