package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import math.ComplexNumber;

class ComplexNumberTest
{
  String one = "1";

  @Test
  void testConstructorAndGetters()
  {
    ComplexNumber cm = ComplexNumber.parse("3^2");
    assertEquals(9, cm.getRealPart());
  }

  @Test
  void testParse()
  {
    // just real
    ComplexNumber num = ComplexNumber.parse("5");
    assertEquals(5, num.getRealPart());
    assertEquals(0, num.getImaginaryPart());
    num = ComplexNumber.parse("-17");
    assertEquals(-17, num.getRealPart());
    assertEquals(0, num.getImaginaryPart());
    num = ComplexNumber.parse("4.8");
    assertEquals(4.8, num.getRealPart());
    assertEquals(0, num.getImaginaryPart());
    num = ComplexNumber.parse(".8");
    assertEquals(.8, num.getRealPart());
    assertEquals(0, num.getImaginaryPart());

    // just imaginary
    num = ComplexNumber.parse("55i");
    assertEquals(0, num.getRealPart());
    assertEquals(55, num.getImaginaryPart());
    num = ComplexNumber.parse("-2i");
    assertEquals(0, num.getRealPart());
    assertEquals(-2, num.getImaginaryPart());

    // complex
    num = ComplexNumber.parse("3+8i");
    assertEquals(3, num.getRealPart());
    assertEquals(8, num.getImaginaryPart());
    num = ComplexNumber.parse("-16+7i");
    assertEquals(-16, num.getRealPart());
    assertEquals(7, num.getImaginaryPart());
    num = ComplexNumber.parse("9-4i");
    assertEquals(9, num.getRealPart());
    assertEquals(-4, num.getImaginaryPart());
    num = ComplexNumber.parse("-11-8i");
    assertEquals(-11, num.getRealPart());
    assertEquals(-8, num.getImaginaryPart());
  }

  @Test
  void testToString()
  {
    ComplexNumber num = ComplexNumber.parse("0.2");
    assertEquals("1/5", num.toString(true));
    num = ComplexNumber.parse("-1.5");
    assertEquals("-3/2", num.toString(true));
    num = ComplexNumber.parse(one);
    assertEquals(one, num.toString(true));
  }
}
