package math;

/**
 * An object class to represent a complex number.
 * 
 * Modifcations: Ali (3/28) clear up and javadoc comments for clarifaction. Ali (3/30) Editing &
 * clean up
 * 
 * @author Ava Momberger, Rhea Morris, Eric Hernandez
 * @version 3/24/2021
 */
public class ComplexNumber
{
  private static String iString = "i";
  private static String zero = "0";

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
    String str = string;
    String real = "";
    String img = "";

    int i = 0;
    String num = "";
    while (i < str.length())
    {
      if (Character.isDigit(str.charAt(i)))
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
          num = "-" + num;
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
      img = "1";
    }
    else if (img.isEmpty())
    {
      img = zero;
    }

    return new ComplexNumber(Double.parseDouble(real), Double.parseDouble(img));
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
   * @param base
   *          the base to be used for the exponential calculation.
   * @param power
   *          the power that the base will be raised to.
   * @return new complex number raised to the power.
   */
  public ComplexNumber exponent(final ComplexNumber base, final int power)
  {
    ComplexNumber result = base;
    int pow = power;
    if (power == 1)
    {
      return result;
    }
    else if (pow == 0)
    {
      return ComplexNumber.parse("1");
    }

    while (pow > 1)
    {
      result = result.multiply(base);
      pow--;
    }
    return result;
  }
  
  public ComplexNumber log() 
  {
    double r = Math.sqrt(Math.pow(real, 2) + Math.pow(img, 2));
    double theta = Math.atan(real/img); 
    return new ComplexNumber(Math.log(r), theta);
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
   * Change the Sign
   * 
   * @return complex number
   */
  public ComplexNumber changeSign()
  {
    return new ComplexNumber(getRealPart() * -1, getImaginaryPart());
    
  }
  
  /**
   * Calculate the inverse
   * 
   * @return the inverse of complex number
   */
  public ComplexNumber inverse()
  {
    if(this.getImaginaryPart() == 0) {
      return  new ComplexNumber(1 / getRealPart(), getImaginaryPart());
    }
    ComplexNumber one = new ComplexNumber(1 / getRealPart() , 1 / getImaginaryPart());
    ComplexNumber inv = one.multiply(one.conjugate());
    
    return inv;
  }

  /**
   * Modulus of this Complex number.
   * 
   * @return |z| where z is this Complex number.
   */
  public double mod()
  {
    if ( this.getRealPart() != 0 || this.getImaginaryPart() != 0)
    {
      return Math.sqrt(this.getRealPart() * this.getRealPart() + this.getImaginaryPart() * this.getImaginaryPart());
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
    if (this.getImaginaryPart() == 0)
    {
      return new ComplexNumber(Math.sqrt(this.getRealPart()), 0);
    }
    double r = Math.sqrt(this.mod());
    double theta = this.arg() / 2;
    return new ComplexNumber(r * Math.cos(theta), r * Math.sin(theta));
  }

  /**
   * The to string to be shown. If the solution is an integer, it will return it as so.
   *
   * @return the string for the result
   */
  public String toString()
  {
    String r = "";
    String i = "";
    if (getRealPart() != 0)
    {
      r += (getRealPart() % 1 == 0) ? (int) getRealPart() : getRealPart();
    }

    String tempI = "" + getImaginaryPart();
    if (getImaginaryPart() % 1 == 0)
    {
      tempI = "" + (int) getImaginaryPart();
    }

    if (getImaginaryPart() != 0)
    {

      i = "" + tempI + iString;

    }

    // change
    String result = "";
    if (real == 0 && getImaginaryPart() != 0)
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
