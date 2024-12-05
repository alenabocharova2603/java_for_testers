### Collections

sandbox/src/main/java/ru/stqa/geometry
Functions for calculating the area of various geometric shapes

sandbox/src/main/test/ru/stqa/geometry/figures
This would be the directory for the test cases related to the geometric shapes.

REMARKS (@Test)

1) Assertions.assertEquals(3, array.length);

The assertEquals method from the Assertions library is used here to check that the length of the array is equal to 3.
If the length of the array is not 3, the test will fail.

2) try {} catch {exception}
try {
    new Square(-5.0);
    Assertions.fail();
} catch (IllegalArgumentException exception) {

In the "catch" block, the exception type IllegalArgumentException is used, which is part of the standard Java library – a convenient way to signal that the function/constructor parameters are invalid.

If no exception is thrown, meaning we proceed to the next line after calling the constructor, it indicates that the behavior is not as expected (an exception was expected because it is not possible to calculate the area from a negative number). In this case, the test should fail, and therefore Assertions.fail() is called.
