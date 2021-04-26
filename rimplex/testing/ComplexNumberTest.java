package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import math.ComplexNumber;

class ComplexNumberTest
{

  @Test
  void testConstructorAndGetters()
  {
    ComplexNumber cm = ComplexNumber.parse("3^2");
    assertEquals(9, cm.getRealPart());
  }

}
