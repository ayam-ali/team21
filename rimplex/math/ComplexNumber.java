package math;

/**
 * An object class to represent a complex number.
 * 
 * @author Ava Momberger && Rhea Morris
 * @version 3/24/2021
 */
public class ComplexNumber
{
  private double realValue;
  private double imaginaryValue;

  /*
   * Constructs a complex number
   */
  public ComplexNumber(double realValue, double imaginaryValue)
  {
    this.realValue = realValue;
    this.imaginaryValue = imaginaryValue;
  }

  /*
   * Adds two complex numbers
   * 
   * @param other the complex number to add with
   * 
   * @result the result of adding the two complex numbers together
   */
  public ComplexNumber add(ComplexNumber other)
  {
    double real = this.realValue + other.realValue;
    double imaginary = this.imaginaryValue + other.imaginaryValue;
    ComplexNumber num = new ComplexNumber(real, imaginary);
    return num;
  }

  /*
   * Subtracts two complex numbers
   * 
   * @param other the complex number to subtract
   * 
   * @result the result of subtracting the two complex numbers
   */
  public ComplexNumber subtract(ComplexNumber other)
  {
    double real = this.realValue - other.realValue;
    double imaginary = this.imaginaryValue - other.imaginaryValue;
    ComplexNumber num = new ComplexNumber(real, imaginary);
    return num;
  }

  /*
   * Multiply two complex numbers
   * 
   * @param other the complex number to multiply by
   * 
   * @result the result of multiplying the two complex numbers
   */
  public ComplexNumber multiply(ComplexNumber other)
  {
    double real = (realValue * other.getRealValue()) - (imaginaryValue * other.getImaginaryValue());
    double i = (realValue * other.getImaginaryValue()) + (imaginaryValue * other.getRealValue());
    return new ComplexNumber(real, i);
  }

  /*
   * Multiply two complex numbers
   * 
   * @param other the complex number to multiply by
   * 
   * @result the result of multiplying the two complex numbers
   */
  public ComplexNumber divide(ComplexNumber other)
  {
    double real = ((realValue * other.getRealValue())
        + (imaginaryValue * other.getImaginaryValue()))
        / (Math.pow(imaginaryValue, 2) + Math.pow(other.getImaginaryValue(), 2));
    double i = ((realValue * other.getRealValue()) - (imaginaryValue * other.getImaginaryValue()))
        / (Math.pow(imaginaryValue, 2) + Math.pow(other.getImaginaryValue(), 2));
    return new ComplexNumber(real, i);
  }

  /*
   * Gets the real number part of a complex number
   * 
   */
  public double getRealValue()
  {
    return realValue;
  }

  /*
   * Gets the imaginary number part of a complex number
   * 
   */
  public double getImaginaryValue()
  {
    return imaginaryValue;
  }

  /*
   * returns a string format of the complex number
   * 
   */
  public String toString()
  {
    String result = "";
    if (realValue == 0 && imaginaryValue != 0)
    {
      result = "" + imaginaryValue + "i";
    }
    else if (imaginaryValue == 0)
    {
      result = "" + realValue;
    }
    else
    {
      result = "" + realValue + "+" + imaginaryValue + "i";
    }
    return result;
  }
}
