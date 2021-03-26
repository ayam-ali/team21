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

  public ComplexNumber(double realValue, double imaginaryValue)
  {
    this.realValue = realValue;
    this.imaginaryValue = imaginaryValue;
  }

  public ComplexNumber add(ComplexNumber other)
  {
    double real = this.realValue + other.realValue;
    double imaginary = this.imaginaryValue + other.imaginaryValue;
    ComplexNumber num = new ComplexNumber(real, imaginary);
    return num;
  }

  public ComplexNumber subtract(ComplexNumber other)
  {
    double real = this.realValue - other.realValue;
    double imaginary = this.imaginaryValue - other.imaginaryValue;
    ComplexNumber num = new ComplexNumber(real, imaginary);
    return num;
  }

  public ComplexNumber multiply(ComplexNumber other)
  {
    double real = (realValue * other.getRealValue()) - (imaginaryValue * other.getImaginaryValue());
    double i = (realValue * other.getImaginaryValue()) + (imaginaryValue * other.getRealValue());
    return new ComplexNumber(real, i);
  }

  public ComplexNumber divide(ComplexNumber other)
  {
    double real = ((realValue * other.getRealValue()) + (imaginaryValue * other.getImaginaryValue()))
        / (Math.pow(imaginaryValue, 2) + Math.pow(other.getImaginaryValue(), 2));
    double i = ((realValue * other.getRealValue()) - (imaginaryValue * other.getImaginaryValue()))
        / (Math.pow(imaginaryValue, 2) + Math.pow(other.getImaginaryValue(), 2));
    return new ComplexNumber(real, i);
  }

  public double getRealValue()
  {
    return realValue;
  }

  public double getImaginaryValue()
  { 
    return imaginaryValue;
  }

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
      result = "" + realValue + imaginaryValue + "i";
    }
    return result;
  }
}
