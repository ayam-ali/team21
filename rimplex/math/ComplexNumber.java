package math;

/**
 * An object class to represent a complex number.
 * 
 * Modifcations: Ali (3/28) clearing up and added javadoc comments for clarifaction.
 * 
 * @author Ava Momberger && Rhea Morris
 * @version 3/24/2021
 */
public class ComplexNumber
{
  private double realValue;
  private double imaginaryValue;

  /**
   * The constructor for complexNumber.
   * 
   * @param realValue
   *          for the real value
   * @param imaginaryValue
   *          for the imaginary part
   */
  public ComplexNumber(double realValue, double imaginaryValue)
  {
    this.realValue = realValue;
    this.imaginaryValue = imaginaryValue;
  }

  /**
   * To add numbers together.
   * 
   * @param other
   *          to be added
   * @return the sum of the two numbers
   */
  public ComplexNumber add(ComplexNumber other)
  {
    double real = this.realValue + other.realValue;
    double imaginary = this.imaginaryValue + other.imaginaryValue;
    ComplexNumber num = new ComplexNumber(real, imaginary);
    return num;
  }

  /**
   * To subtract numbers.
   * 
   * @param other
   *          number to be subtracted
   * @return the difference between the two numbers
   */
  public ComplexNumber subtract(ComplexNumber other)
  {
    double real = this.realValue - other.realValue;
    double imaginary = this.imaginaryValue - other.imaginaryValue;
    ComplexNumber num = new ComplexNumber(real, imaginary);
    return num;
  }

  /**
   * To multiply two numbers.
   * 
   * @param other
   *          number to be multiplied
   * @return the product of the two numbers
   */
  public ComplexNumber multiply(ComplexNumber other)
  {
    double real = (realValue * other.getRealValue()) - (imaginaryValue * other.getImaginaryValue());
    double i = (realValue * other.getImaginaryValue()) + (imaginaryValue * other.getRealValue());
    return new ComplexNumber(real, i);
  }

  /**
   * To divide two numbers.
   * 
   * @param other
   *          number to be divided
   * @return the quotient of the two numbers
   */
  public ComplexNumber divide(ComplexNumber other)
  {
    ComplexNumber numerator;
    double denominator;
    
    if (other.getImaginaryValue() != 0) { 
      ComplexNumber conjugate = new ComplexNumber(other.getRealValue(), other.getImaginaryValue() * -1);
      numerator = this.multiply(conjugate);
      denominator = Math.pow(other.getRealValue(), 2) + Math.pow(other.getImaginaryValue(), 2);
    } else {
      numerator = this;
      denominator = other.getRealValue();
    }

    if (denominator == 0) return null;
    
    return new ComplexNumber(numerator.getRealValue()/denominator, numerator.getImaginaryValue()/denominator);
  }

  /**
   * Gets the real value.
   * 
   * @return real value
   */
  public double getRealValue()
  {
    return realValue;
  }

  /**
   * Gets the imaginary value.
   * 
   * @return imaginary value
   */
  public double getImaginaryValue()
  {
    return imaginaryValue;
  }

  /**
   * The to string to be shown.
   * 
   * @return the string for the result
   */
  public String toString()
  {
    String result = "";
    if (realValue == 0 && imaginaryValue != 0)
    {
      result = String.format("%.3fi", imaginaryValue);
    }
    else if (imaginaryValue == 0)
    {
      result = String.format("%.3f", realValue);
    }
    else
    {
      result = (imaginaryValue < 0) ? "" + String.format("%.3f", realValue) + String.format("%.3f", imaginaryValue) + "i" 
          : "" + String.format("%.3f", realValue) + "+" + String.format("%.3f", imaginaryValue) + "i";
    }
    return result;
  }
}