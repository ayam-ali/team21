package math;

import java.util.*;

/**
 * Calculator class.
 * 
 * @author Ava Momberger && Rhea Morris
 * @version 3/24/2021
 */
public class Calculator
{
  private String plus = "+";
  private String minus = "-";
  private String multiply = "×";
  private String divide = "÷";

  private String recentResult;

  /**
   * The default constructor for calculator.
   */
  public Calculator()
  {
    recentResult = "0";
  }

  /**
   * Handles the running calculations using the helper methods.
   * 
   * @param input
   *          for the list of numbers and operands
   * @return a complex number for the result
   */
  public ComplexNumber calculate(List<String> input)
  {
    // example input: {"7i", "+", "4-2i"}
    // this part handles running calculations
    if (isOperation(input.get(0)))
    {
      input.add(0, recentResult);                             
    }

    ComplexNumber total = parseComplex(input.get(0));
    for (int i = 2; i < input.size(); i += 2)
    {
      ComplexNumber num = parseComplex(input.get(i));
      total = performOperation(total, num, input.get(i - 1));
    }

    recentResult = total.toString();
    return total;
  }

  /**
   * Parses the string to know if it is real, imaginary or both.
   * 
   * @param numString
   *          the given string
   * @return a complex number for the value
   */
  private ComplexNumber parseComplex(String numString)
  {
    // examples: "3+5i", "4", "9i"
    ComplexNumber value = null;
    if ((!numString.contains(plus) && !numString.contains(minus)) || (!numString.contains(plus) && numString.indexOf(minus) == 0))
    {
      // purely imaginary number
      if (numString.contains("i"))
      {
        numString = numString.replace('i', Character.MIN_VALUE);
        value = new ComplexNumber(0.0, Double.parseDouble(numString));
      }
      else
      {
        // purely real number
        value = new ComplexNumber(Double.parseDouble(numString), 0.0);
      }
    }
    else
    {
      // number with both real and imaginary parts
      int operation;
      Double imaginary;
      if (numString.contains(minus) && numString.indexOf(minus) != 0) {
        operation = numString.indexOf(minus); 
        imaginary = Double
            .parseDouble(numString.substring(operation).replace('i', Character.MIN_VALUE));
      } else {
        operation = numString.indexOf(plus);
        imaginary = Double
            .parseDouble(numString.substring(operation + 1).replace('i', Character.MIN_VALUE));
      }
      Double real = Double.parseDouble(numString.substring(0, operation));
      value = new ComplexNumber(real, imaginary);
    }
    return value;
  }

  /**
   * Helper method to handle the operations.
   * 
   * @param first
   *          complex number to perform operation on
   * @param second
   *          complex number to be perform operation on
   * @param operand
   *          to know which operation to be performed
   * @return a complex number for the result
   */
  private ComplexNumber performOperation(ComplexNumber first, ComplexNumber second, String operand)
  {
    ComplexNumber result = null;
    switch (operand)
    {
      case "+":
        result = first.add(second);
        break;
      case "-":
        result = first.subtract(second);
        break;
      case "×":
        result = first.multiply(second);
        break;
      case "÷":
        result = first.divide(second);
        break;
      default:
        // do nothing
    }
    return result;
  }

  /**
   * Returns if the string is either of the operators.
   * 
   * @param string
   *          to see which operand
   * @return the operand
   */
  private boolean isOperation(String string)
  {
    return string == plus || string == minus || string == multiply || string == divide;
  }

}
