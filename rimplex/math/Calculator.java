package math;

import java.util.*;

/**
 * Calculator class.
 * 
 * @author The Team
 * @version 3/24/2021
 */
public class Calculator
{
  private String plus = "+";
  private String minus = "-";
  private String multiply = "\u00D7";
  private String divide = "\u00F7";

  private String recentResult;

  /**
   * The default constructor for calculator.
   */
  public Calculator()
  {
    recentResult = "0";
  }

  /**
   * Calculates what is inside the input.
   * 
   * @param input
   *          given to be calculated
   * @return complex number of the solution
   */
  public ComplexNumber calculate(final List<String> input)
  {
    // example input: {"7i", "+", "4-2i"}
    // this part handles running calculations
    if (isOperation(input.get(0)))
    {
      input.add(0, recentResult);
    }

    ComplexNumber total = ComplexNumber.parse(input.get(0));
    for (int i = 2; i < input.size(); i += 2)
    {
      ComplexNumber num = ComplexNumber.parse(input.get(i));
      total = performOperation(total, num, input.get(i - 1));
    }

    recentResult = total.toString();
    return total;
  }

  /**
   * Checks whether the string is any of the operators.
   *
   * @param string
   *          to see which operand
   * @return the operand
   */
  public boolean isOperation(final String string)
  {
    return string == plus || string == minus || string == multiply || string == divide;
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
  private ComplexNumber performOperation(final ComplexNumber first, final ComplexNumber second,
      final String operand)
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
      case "\u00D7":
        result = first.multiply(second);
        break;
      case "\u00F7":
        result = first.divide(second);
        break;
      default:
        // do nothing
    }
    return result;
  }

}
