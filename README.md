# matrix-exercise
Matrix zeroing of column/row

This is a devleopment exercise for Loveland Innovations. The instructions for this exercise were these:

> Given an arbitrary sized matrix (MxN), program a solution that zeros out the entire row and column of any element that is '0'.

I interpreted the instructions this way:

> Any zero that is visited causes the zeroing-out of the element's row/column, whether that zero is the result of the original placement in the matrix or whether it is the result of a previous zeroing-out of a row/column.

The algorithm that was chosen was a brute-force walking of the matrix and zero-ing out the row/column of any '0' element that is seen. This is in keeping with my interpretation of the instructions and can result in more zeroes than might be epected, because zeroed-out rows/columns can cause later row/columns to be zeroed-out.

Little thought was put into performance improvements. Almost always, it is better to create the code in a straight-forward manner and then address performance issues once those issues are found. There are also some `ToDo` comments in the code that would be addressed before the code is turned over to QA.

# How to run the exercise
* Clone this repository
* Build the executable jar witht the command `mvn clean package`.
* Run the application with this command line `java -jar target/matrix-exercise-1.0-SNAPSHOT.jar`.
* Invoking the application without the required arguments, as shown above, will cause the application to print `Usage` instructions.
* Accepted command-line arguments are
  * -dimensions (required) followed by the desired matrix rows and columns
  * -numZeroes (optional) followed by the number of zeroes that should be initially populated in the matrix. Note: the initial zeroes are randomly populated into the matrix.

# Examples
To run the application with a 7 x 8 matrix with 3 initial zeroes use, your command line should look something like this:
> java -jar target/matrix-exercise-1.0-SNAPSHOT.jar -dimensions 7 8 -numZeroes 3

When the application runs, it will print out the initially-generated matrix with the zeroes randomly placed in the matrix and then it will print the final matrix with the elements' rows/columns zeroed-out.

# Improvements
Along with possible performance improvements, the below improvements could be made:
* Validation. There is very little validation going on, outside of assuring that the command line parameters are integers.
* Testing and Testability. While there is a unit test class defined, it doesn't perform very stringent testing. In order to improve testing, the application would need some refactoring. Testability could be improved by decomposing the matrix class and separating supporting logic into other classes.

# Addendum

I've been notified that my original interpretition of the requirements was incorrect. Here are the clarified requirements that I have received:

> My intent in the requirement is to zero out the row and column of those indices (and only those indices) that are initially '0'.

With my better understanding of the requiremenst, I've refactored the code. While changing the application to only zero-out the rows/columns of the original '0' cells, I've refactored the method that performs that function such that it no longer walks the matrix, looking for zeroes. Instead, it iterates through the list of '0' cells and zeroes-out the corresponding rows and columns. This new algorithm is more performant than the original one since it only has to consider the list of requested '0' cells instead of each cell in the matrix.
