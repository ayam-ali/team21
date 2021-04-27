package math;

/**
 * An object class to represent a complex number.
 * 
 * @author Ava Momberger, Rhea Morris, Eric Hernandez
 * @version 3/24/2021
 */
public class ComplexNumber
{
  private static String iString = "i";
  private static String zero = "0";
  private static String minus = "-";
  private static String sqrt = "\u221A";
  private static String inv = "Inv";
  private static String con = "Con";
  private static String log = "LOG";
  private static String re = "Re";
  private static String im = "Im";
  private static String one = "1";
  
  private double real;
  private double img;

  /**
   * The constructor for Complex number.
   * 
   * @param real
   *          number in the expression
   * @param img
   *          the imaginary number in the expression
   */
  public ComplexNumber(final double real, final double img)
  {
    this.real = real;
    this.img = img;
  }

  /**
   * Get the real part.
   * 
   * @return double for the real part
   */
  public double getRealPart()
  {
    return real;
  }

  /**
   * Gets the imaginary part.
   * 
   * @return double for the imaginary part
   */
  public double getImaginaryPart()
  {
    return img;
  }

  /**
   * Parses the string into a complex number.
   * 
   * @param string
   *          of the expression
   * @return a complex number
   */
  public static ComplexNumber parse(final String string)
  {

    ComplexNumber result;
    String str = "";
    String real = "";
    String img = "";

    String[] strs = findUnary(string);

    str = strs[0];
    boolean hasunary = (strs[1] != null);

    int i = 0;
    String num = "";
    while (i < str.length())
    {
      if (Character.isDigit(str.charAt(i)) || str.charAt(i) == '.' || str.charAt(i) == '-')
      {
        int j = i;
        while (j < str.length() && (Character.isDigit(str.charAt(j)) || str.charAt(j) == '.'))
        {
          num = num + str.charAt(j);
          i = j;
          j++;
        }
        if (i - num.length() >= 0 && str.charAt(i - num.length()) == '-')
        {
          num = minus + num;
        }
        if (num.equals(minus))
        {
          num = "-1";
        }
        if (i + 1 < str.length() && str.charAt(i + 1) == 'i')
        {
          i = i + 1;
          img = num;
        }
        else
        {
          real = num;
        }
      }
      num = "";
      i++;
    }

    if (real.isEmpty())
    {
      real = zero;
    }

    if (img.isEmpty() && string.contains(iString))
    {
      img = one;
    }
    else if (img.isEmpty())
    {
      img = zero;
    }

    result = new ComplexNumber(Double.parseDouble(real), Double.parseDouble(img));

    if (!hasunary)
    {
      return result;
    }
    return executeUnary(result, strs[1]);
  }

  /**
   * Parse helper method that is only called if a unary operator is present.
   * 
   * @param operand
   *          the operand to be operated on.
   * @param unary
   *          the unary function in string for or a string of a number if a exponential function
   *          needs to be performed.
   * @return the new ComplexNumber based on what unary operator was performed.
   */
  private static ComplexNumber executeUnary(final ComplexNumber operand, final String unary)
  {
    ComplexNumber returnVal = null;
    if (unary.equals(sqrt))
    {
      returnVal = operand.sqrt();
    }
    else if (unary.equals(inv))
    {
      returnVal = operand.inverse();
    }
    else if (unary.equals(con))
    {
      returnVal = operand.conjugate();
    }
    else if (unary.equals(log))
    {
      returnVal = operand.log();
    }
    else if (unary.equals(re)) // real and img operator
    {
      returnVal = new ComplexNumber(operand.getRealPart(), 0);
    }
    else if (unary.equals(im))
    {
      returnVal = new ComplexNumber(operand.getImaginaryPart(), 0);
    }
    else
    {
      return operand.exponent(Integer.parseInt(unary));
    }
    return returnVal;
  }

  /**
   * Parse Helper Method.
   * 
   * @param str
   *          String to check for unary operators.
   * @return A String[] containing the operand and the unary operator in that second index or null
   *         if there was no unary operator.
   */
  private static String[] findUnary(final String str)
  {
    String[] temp = new String[2];
    String string = str;
    if (string.contains(sqrt))
    {
      // squareRoot
      temp[0] = string.substring(0, string.indexOf(sqrt));
      temp[1] = sqrt;
    }
    else if (string.contains(inv))
    {
      // inverse
      temp[0] = str.substring(0, str.indexOf(inv));
      temp[1] = inv;
    }
    else if (string.contains(con))
    {
      temp[0] = string.substring(0, string.indexOf(con));
      temp[1] = con;
    }
    else if (string.contains(log))
    {
      temp[0] = string.substring(0, string.indexOf(log));
      temp[1] = log;
    }
    else if (string.contains("^"))
    {
      temp[0] = string.substring(0, string.indexOf('^'));
      temp[1] = string.substring(string.indexOf('^') + 1);
    }
    else if (string.contains(re)) // real operator
    {
      temp[0] = string.substring(0, string.indexOf(re));
      temp[1] = re;
    }
    else if (string.contains(im)) // real operator
    {
      temp[0] = string.substring(0, string.indexOf(im));
      temp[1] = im;
    }
    else
    {
      temp[0] = string;
      temp[1] = null;
    }
    return temp;
  }

  /**
   * Adds two complex numbers.
   * 
   * @param other
   *          number to add to
   * @return complex number with the solution
   */
  public ComplexNumber add(final ComplexNumber other)
  {
    double r = getRealPart() + other.getRealPart();

    double i = getImaginaryPart() + other.getImaginaryPart();

    // returning the output complex number
    return new ComplexNumber(r, i);
  }

  /**
   * Finds the new complex number when raised to a power.
   * 
   * @param power
   *          the power that the base will be raised to.
   * @return new complex number raised to the power.
   */
  public ComplexNumber exponent(final int power)
  {
    ComplexNumber result = this;
    int pow = power;
    if (power == 1)
    {
      return result;
    }
    else if (pow == 0)
    {
      return ComplexNumber.parse(one);
    }

    while (pow > 1)
    {
      result = result.multiply(this);
      pow--;
    }
    return result;
  }

  /**
   * The log calculations.
   * 
   * @return complex number for the solution
   */
  public ComplexNumber log()
  {
    return new ComplexNumber(Math.log(mod()), arg());
  }

  /**
   * To subtract numbers.
   *
   * @param other
   *          number to be subtracted
   * @return the difference between the two numbers
   */
  public ComplexNumber subtract(final ComplexNumber other)
  {
    double r = getRealPart() - other.getRealPart();
    double i = getImaginaryPart() - other.getImaginaryPart();
    return new ComplexNumber(r, i);
  }

  /**
   * To multiply two numbers.
   *
   * @param other
   *          number to be multiplied
   * @return the product of the two numbers
   */
  public ComplexNumber multiply(final ComplexNumber other)
  {
    double r = (getRealPart() * other.getRealPart())
        + (getImaginaryPart() * other.getImaginaryPart() * -1);
    double i = (other.getImaginaryPart() * getRealPart())
        + (getImaginaryPart() * other.getRealPart());
    return new ComplexNumber(r, i);
  }

  /**
   * To divide two numbers.
   *
   * @param other
   *          number to be divided
   * @return the quotient of the two numbers
   */
  public ComplexNumber divide(final ComplexNumber other)
  {
    ComplexNumber numerator = this.multiply(other.conjugate());
    ComplexNumber denominator = other.multiply(other.conjugate());

    double realNum = numerator.getRealPart() / denominator.getRealPart();
    double imaginary = numerator.getImaginaryPart() / denominator.getRealPart();
    return new ComplexNumber(realNum, imaginary);
  }

  /**
   * Helper method to assist divide, gets the conjugate.
   * 
   * @return complex number
   */
  public ComplexNumber conjugate()
  {
    return new ComplexNumber(getRealPart(), getImaginaryPart() * -1);
  }

  /**
   * Calculate the inverse.
   * 
   * @return the inverse of complex number
   */
  public ComplexNumber inverse()
  {
    double length = real * real + img * img;
    return new ComplexNumber(real / length, -img / length);
  }

  /**
   * Modulus of this Complex number.
   * 
   * @return |z| where z is this Complex number.
   */
  public double mod()
  {
    if (this.getRealPart() != 0 || this.getImaginaryPart() != 0)
    {
      return Math.sqrt(this.getRealPart() * this.getRealPart()
          + this.getImaginaryPart() * this.getImaginaryPart());
    }
    else
    {
      return 0d;
    }
  }

  /**
   * Argument of this Complex number.
   * 
   * @return arg(z) where z is this Complex number.
   */
  public double arg()
  {
    return Math.atan2(this.getImaginaryPart(), this.getRealPart());
  }

  /**
   * Calculates the square root of a complex number.
   * 
   * @return arg(z) where z is this Complex number.
   */
  public ComplexNumber sqrt()
  {
    if (this.getImaginaryPart() == 0 && this.getRealPart() > 0)
    {
      return new ComplexNumber(Math.sqrt(this.getRealPart()), 0);
    }
    if (this.getImaginaryPart() == 0 && this.getRealPart() < 0)
    {
      return new ComplexNumber(0, Math.sqrt(Math.abs(getRealPart())));
    }
    double r = Math.sqrt(this.mod());
    double theta = this.arg() / 2;
    return new ComplexNumber(r * Math.cos(theta), r * Math.sin(theta));
  }

  /**
   * Turns the decimal into a fraction.
   * 
   * @param num
   *          the decimal
   * @return the fraction form F
   */
  public String makeFraction(final double num)
  {
    double number = num;
    double n = 1;
    double d = 1;
    double error = 0.0001;
    boolean isNegative = number < 0;

    if (number % 1 == 0)
      return "" + (int) number;
    if (isNegative)
      number = Math.abs(number);

    while (Math.abs((n / d) - number) >= error)
    {
      if ((n / d) > number)
        d++;
      if ((n / d) < number)
        n++;
    } 

    if (isNegative)
      n = n * -1;
    return (n == d) ? one : (int) n + "/" + (int) d;
  }

  /**
   * The greatest common divisor.
   * 
   * @param a
   *          the first input
   * @param b
   *          the second input
   * @return the divisor
   */
  public int gcd(final int a, final int b)
  {
    int dividend;
    int divisor;
    int remainder;

    if (a > b)
    {
      dividend = a;
      divisor = b;
    }
    else
    {
      dividend = b;
      divisor = a;
    }
    remainder = dividend % divisor;

    while (remainder != 0)
    {
      dividend = divisor;
      divisor = remainder;
      remainder = dividend % divisor;
    }

    return divisor;
  }

  /**
   * The string to be shown.
   * 
   * @return The String of a complex number.
   */
  public String toString()
  {
    return this.toString(false);
  }

  /**
   * The toString with a boolean, correct output for fraction.
   *
   * @param isFraction
   *          If true, the string returned will be in fraction form. If false, the string returned
   *          will be in decimal form.
   * @return the string for the result
   */
  public String toString(final boolean isFraction)
  {
    String r = "";
    String i = "";
    if (getRealPart() != 0)
    {
      if (!isFraction)
      {
        r = (getRealPart() % 1 == 0) ? "" + (int) getRealPart() : "" + getRealPart();
      }
      else
      {
        r += makeFraction(getRealPart());
      }
    }

    String tempI = (isFraction) ? makeFraction(getImaginaryPart()) : "" + getImaginaryPart();
    if (getImaginaryPart() % 1 == 0)
    {
      tempI = "" + (int) getImaginaryPart();
    }
    i = tempI + iString;
    if (i.equals("1i"))
      i = iString;
    if (i.equals("-1i"))
      i = minus + iString;

    // change
    String result = "";
    if (real == 0 && img != 0)
    {
      result = "" + i;
    }
    else if (getRealPart() == 0 && getImaginaryPart() == 0)
    {
      result = zero;
    }
    else if (getImaginaryPart() == 0)
    {
      result = "" + r;
    }
    else if (getImaginaryPart() < 0)
    {
      result = "" + r + i;
    }
    else
    {
      result = "" + r + "+" + i;
    }

    return result;

  }
}
