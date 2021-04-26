package math;

import java.util.*;

/**
 * Calculator class.
 * 
 * @author Eric Hernandez, Rhea Morris, Ava Momberger
 * @version 3/24/2021
 */
public class Calculator
{
  private final String plus = "+";
  private final String minus = "-";
  private final String multiply = "\u00D7";
  private final String divide = "\u00F7";
  private final String sqrt = "\u221A";
  private final String inv = "Inv";
  private final String log = "LOG";
  private final String con = "Con";
  private final String exp = "Exp";
  private final String re = "Re"; // real and img operator 
  private final String im = "Im";

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
    if (input.size() == 0)
      return ComplexNumber.parse(recentResult);
    ComplexNumber total;
    // this part handles running calculations
    if (isOperation(input.get(0)))
    {
      input.add(0, recentResult);
    }
    if (input.size() == 1)
    {
      total = ComplexNumber.parse(input.get(0));
    }
    else if (input.size() == 2)
    {
      total = performOperation(ComplexNumber.parse(input.get(0)), null, input.get(1));
    } 
    else
    {
      total = ComplexNumber.parse(input.get(0));
      for (int i = 2; i < input.size(); i += 2)
      {
        ComplexNumber num = ComplexNumber.parse(input.get(i));
        total = performOperation(total, num, input.get(i - 1));
      }
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
    return string == plus || string == minus || string == multiply || string == divide
        || string == inv || string == sqrt || string == log || string == con
        || string == exp || string == re || string == im; // real and img operator 
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
      case plus:
        result = first.add(second);
        break;
      case minus:
        result = first.subtract(second);
        break;
      case multiply:
        result = first.multiply(second);
        break;
      case divide:
        result = first.divide(second);
        break;
      case sqrt:
        result = first.sqrt();
        break;
      case inv:
        result = first.inverse();
        break;
      case log:
        result = first.log();
        break;
      case con:
        result = first.conjugate();
        break;
      case exp:
        result = first.exponent((int) second.getRealPart());
        break;
      case re:
        result = new ComplexNumber(first.getRealPart(), 0);  /// real and img operator
        break;
      case im:
        result = new ComplexNumber(first.getImaginaryPart(), 0);
        break;
      default:
        // do nothing
    }
    return result;
  }

}
