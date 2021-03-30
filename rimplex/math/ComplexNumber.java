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
  private double real;
  private double img;

  public ComplexNumber(double real, double img)
  {
    this.real = real;
    this.img = img;
  }

  public double getRealPart()
  {
    // TODO Auto-generated method stub
    return real;
  }

  public double getImaginaryPart()
  {
    // TODO Auto-generated method stub
    return img;
  }

  public ComplexNumber add(ComplexNumber other)
  {
    double r = getRealPart() + other.getRealPart();

    double i = getImaginaryPart() + other.getImaginaryPart();

    // returning the output complex number
    return new ComplexNumber(r, i);
  }

  public static ComplexNumber parse(String string)
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
      real = "0";
    }

    if (img.isEmpty() && string.contains("i"))
    {
      img = "1";
    }
    else if (img.isEmpty())
    {
      img = "0";
    }

    return new ComplexNumber(Double.parseDouble(real), Double.parseDouble(img));
  }

  public ComplexNumber subtract(ComplexNumber other)
  {
    double r = getRealPart() - other.getRealPart();
    double i = getImaginaryPart() - other.getImaginaryPart();
    return new ComplexNumber(r, i);
  }

  public ComplexNumber multiply(ComplexNumber other)
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
  public ComplexNumber divide(ComplexNumber other)
  {
    ComplexNumber numerator = this.multiply(other.conjugate());
    ComplexNumber denominator = other.multiply(other.conjugate());

    double real = numerator.getRealPart() / denominator.getRealPart();
    double imaginary = numerator.getImaginaryPart() / denominator.getRealPart();
    return new ComplexNumber(real, imaginary);
  }

  public ComplexNumber conjugate()
  {
    return new ComplexNumber(getRealPart(), getImaginaryPart() * -1);
  }

  // private double realValue;
  // private double imaginaryValue;
  //
  // /**
  // * The constructor for complexNumber.
  // *
  // * @param realValue
  // * for the real value
  // * @param imaginaryValue
  // * for the imaginary part
  // */
  // public ComplexNumber(double realValue, double imaginaryValue)
  // {
  // this.realValue = realValue;
  // this.imaginaryValue = imaginaryValue;
  // }
  //
  // /**
  // * To add numbers together.
  // *
  // * @param other
  // * to be added
  // * @return the sum of the two numbers
  // */
  // public ComplexNumber add(ComplexNumber other)
  // {
  // double real = this.realValue + other.realValue;
  // double imaginary = this.imaginaryValue + other.imaginaryValue;
  // ComplexNumber num = new ComplexNumber(real, imaginary);
  // return num;
  // }
  //
  // /**
  // * To subtract numbers.
  // *
  // * @param other
  // * number to be subtracted
  // * @return the difference between the two numbers
  // */
  // public ComplexNumber subtract(ComplexNumber other)
  // {
  // double real = this.realValue - other.realValue;
  // double imaginary = this.imaginaryValue - other.imaginaryValue;
  // ComplexNumber num = new ComplexNumber(real, imaginary);
  // return num;
  // }
  //
  // /**
  // * To multiply two numbers.
  // *
  // * @param other
  // * number to be multiplied
  // * @return the product of the two numbers
  // */
  // public ComplexNumber multiply(ComplexNumber other)
  // {
  // double real = (realValue * other.getRealValue()) - (imaginaryValue *
  // other.getImaginaryValue());
  // double i = (realValue * other.getImaginaryValue()) + (imaginaryValue * other.getRealValue());
  // return new ComplexNumber(real, i);
  // }
  //
  // /**
  // * To divide two numbers.
  // *
  // * @param other
  // * number to be divided
  // * @return the quotient of the two numbers
  // */
  // public ComplexNumber divide(ComplexNumber other)
  // {
  // double real = ((realValue * other.getRealValue())
  // + (imaginaryValue * other.getImaginaryValue()))
  // / (Math.pow(imaginaryValue, 2) + Math.pow(other.getImaginaryValue(), 2));
  // double i = ((realValue * other.getRealValue()) - (imaginaryValue * other.getImaginaryValue()))
  // / (Math.pow(imaginaryValue, 2) + Math.pow(other.getImaginaryValue(), 2));
  // return new ComplexNumber(real, i);
  // }
  //
  // /**
  // * Gets the real value.
  // *
  // * @return real value
  // */
  // public double getRealValue()
  // {
  // return realValue;
  // }
  //
  // /**
  // * Gets the imaginary value.
  // *
  // * @return imaginary value
  // */
  // public double getImaginaryValue()
  // {
  // return imaginaryValue;
  // }
  //
  /**
   * The to string to be shown.
   *
   * @return the string for the result
   */
  public String toString()
  {
    String r = "";
    String i = "";
    if (getRealPart() != 0)
    {
      if (getRealPart() % 1 == 0)
      {
        r = r + (int) getRealPart();
      }
      else
      {
        r = "" + getRealPart();
      }
    }

    String tempI = "" + getImaginaryPart();
    if (getImaginaryPart() % 1 == 0)
    {
      tempI = "" + (int) getImaginaryPart();
    }

    if (getImaginaryPart() != 0)
    {
      if (getImaginaryPart() > 0)
      {
        i = "" + tempI + "i";
      }
      else
      {
        i = "" + tempI + "i";
      }
    }

    // String result = "";
    // if (real == 0 && getImaginaryPart() != 0)
    // {
    // result = "" + getImaginaryPart() + "i";
    // }
    // else if (getImaginaryPart() == 0)
    // {
    // result = "" + real;
    // }
    // else
    // {
    // result = "" + real + "+" + getImaginaryPart() + "i";
    // }
    String result = "";
    if (real == 0 && getImaginaryPart() != 0)
    {
      result = "" + i;
    }
    else if (getRealPart() == 0 && getImaginaryPart() == 0) {
      result = "0";
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
