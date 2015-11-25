###MATH 2605 Project###


By Erik Maday, Christopher Gordon Carson, and Quinton Johnson

Implementations of multiple Linear Algebra algorithms for MATH 2605.

**To begin:**

Use the command cd followed by the directory path that contains
the "mathproject" folder

Execute the following line:

```
javac -sourcepath mathproject -d mathproject mathproject/**/*.java
```
Then set your current working directory to mathproject

```
cd mathproject
```

Next execute the driver for the part you wish to check using the commands found
below.


**Part One: The Symmetric Pascal Matrix**

```
java PartOne.Part1Driver
```

**Part Two: Convergence of the Iterative Methods**

```
java PartTwo.Part2Driver
```


**Part Three: Convergence of the Power Method**

```
java PartThree.Part3Driver
```

**Base**

This package contains methods used for basic linear algebra functions.

The methods used are from the JAMA library found here: 

http://math.nist.gov/javanumerics/jama/