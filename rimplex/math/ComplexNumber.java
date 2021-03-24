package math;

public class ComplexNumber
{
  private double realValue;
  private double imaginaryValue;
  
  public ComplexNumber(double realValue, double imaginaryValue) 
  {
    
  }
  
  public ComplexNumber add(ComplexNumber other)
  {
    return null;
  }
  
  public ComplexNumber subtract(ComplexNumber other)
  {
    return null;
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
    return "";
  }
}
