# Roman Numerals Converter
## Problem Statement
Roman numerals are symbols used  used in a system of numerical notation based on the ancient Roman system.
The symbols are I, V, X, L, C, D, and M, standing respectively for 1, 5, 10, 50, 100, 500, and 1,000 in the Hindu-Arabic numeral system.

Given a number described in roman numeral, we should be able to programmatically reformat its corresponding integer. 
### Example
   |Roman Numeral|Integer|
   |-------------|-------|
   |III          |3      |
   |IV           |4      |
   |VI           |6      |
   |XXXIX        |39     |
   |LXXX         |80     |
  
## Solution
Roman numerals can typically be calculated by summing up the value which the symbols represent.
We can also see that they usually will follow the pattern where the symbol representing the bigger value will precede the symbol representing the smaller value.
However in certain conditions, the smaller number will precede the bigger value, for example `IV` or `XXXIX`.

In this solution, we treat the roman numeral as a string input, and iterate through them using a `for` loop and do a summation of the values the symbols represent.

Within that loop we need to check for the special condition when the smaller value will precede the bigger value and do a subtraction instead.