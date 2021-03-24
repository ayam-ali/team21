package math;

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
    return null;
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
      result = "" + imaginaryValue;
    }
    else if (realValue != 0 && imaginaryValue == 0)
    {
      result = "" + realValue;
    }
    else
    {
      result = "" + realValue + imaginaryValue;
    }
    return result;
  }
}
