package testing;

import static org.junit.jupiter.api.Assertions.*;
import math.*;
import java.util.*;
import org.junit.jupiter.api.Test;

class CalculatorTest
{
  private String plus = "+";
  private String minus = "-";
  private String multiply = "\u00D7";
  private String divide = "\u00F7";
  private String sqrt = "\u221A";
  private String inv = "Inv";
  private String log = "LOG";
  private String con = "Con";
  private String exp = "^";
  private String re = "Re"; // real and img operator
  private String im = "Im";
  private String i = "i";

  private String negOne = "-1";
  private String zero = "0";
  private String one = "1";
  private String two = "2";
  private String three = "3";
  private String four = "4";
  private String five = "5";
  private String eight = "8";
  private String ten = "10";
  private String twelve = "12";
  private String twenty = "20";
  private String twentyOne = "21";
  private String tFive = "25";
  private String threeFiveThree = "353";

  private String twoI = "2i";
  private String fiveI = "5i";
  private String tenI = "10i";

  private String threePlusFour = "3+4i";
  private String negTwoPlusFive = "-2+5i";
  private String negEightMinusI = "-8-i";

  @Test
  void testConstructor()
  {
    Calculator calc = new Calculator();
    List<String> input = new ArrayList<String>();
    assertEquals(zero, calc.calculate(input).toString());
  }

  @Test
  void testBasic()
  {
    Calculator calc = new Calculator();
    List<String> input = new ArrayList<String>();

    // purely real operands
    input.add(ten);
    input.add(plus);
    input.add(two);
    assertEquals(twelve, calc.calculate(input).toString());
    input.remove(1);
    input.add(1, minus);
    assertEquals(eight, calc.calculate(input).toString());
    input.remove(1);
    input.add(1, multiply);
    assertEquals(twenty, calc.calculate(input).toString());
    input.remove(1);
    input.add(1, divide);
    assertEquals(five, calc.calculate(input).toString());
    input.clear();

    // purely imaginary operands
    input.add(i);
    input.add(plus);
    input.add(i);
    assertEquals(twoI, calc.calculate(input).toString());
    input.remove(1);
    input.add(1, minus);
    assertEquals(zero, calc.calculate(input).toString());
    input.remove(1);
    input.add(1, multiply);
    assertEquals(negOne, calc.calculate(input).toString());
    input.remove(1);
    input.add(1, divide);
    assertEquals(one, calc.calculate(input).toString());
    input.clear();

    // complex operands
    input.add("10+i");
    input.add(plus);
    input.add("2-i");
    assertEquals(twelve, calc.calculate(input).toString());
    input.remove(1);
    input.add(1, minus);
    assertEquals("8+2i", calc.calculate(input).toString());
    input.remove(1);
    input.add(1, multiply);
    assertEquals("21-8i", calc.calculate(input).toString());
    input.remove(1);
    input.add(1, divide);
    assertEquals("3.8+2.4i", (calc.calculate(input).toString()));
    input.clear();
  }

  @Test
  void testRunningBasic()
  {
    Calculator calc = new Calculator();
    List<String> input = new ArrayList<String>();

    // purely real operands
    input.add(ten);
    input.add(plus);
    input.add(two);
    assertEquals(twelve, calc.calculate(input).toString());
    input.clear();

    input.add(plus);
    input.add(two);
    assertEquals("14", calc.calculate(input).toString());
    input.clear();
    input.add(minus);
    input.add(four);
    assertEquals(ten, calc.calculate(input).toString());
    input.clear();
    input.add(multiply);
    input.add(five);
    assertEquals("50", calc.calculate(input).toString());
    input.clear();
    input.add(divide);
    input.add(two);
    assertEquals(tFive, calc.calculate(input).toString());
    input.clear();

    // purely imaginary operands
    input.add(tenI);
    input.add(plus);
    input.add(twoI);
    assertEquals("12i", calc.calculate(input).toString());
    input.clear();

    input.add(plus);
    input.add(twoI);
    assertEquals("14i", calc.calculate(input).toString());
    input.clear();
    input.add(minus);
    input.add("4i");
    assertEquals(tenI, calc.calculate(input).toString());
    input.clear();
    input.add(multiply);
    input.add(fiveI);
    assertEquals("-50", calc.calculate(input).toString());
    input.clear();
    input.add(divide);
    input.add(twoI);
    assertEquals("25i", calc.calculate(input).toString());
    input.clear();

    // complex operands
    input.add("10+5i");
    input.add(plus);
    input.add("6+2i");
    assertEquals("16+7i", calc.calculate(input).toString());
    input.clear();

    input.add(plus);
    input.add("4+2i");
    assertEquals("20+9i", calc.calculate(input).toString());
    input.clear();
    input.add(minus);
    input.add(threePlusFour);
    assertEquals("17+5i", calc.calculate(input).toString());
    input.clear();
    input.add(multiply);
    input.add("2+8i");
    assertEquals("-6+146i", calc.calculate(input).toString());
    input.clear();
    input.add(divide);
    input.add("4+3i");
    assertEquals("414/25+602/25i", calc.calculate(input).toString(true));
    input.clear();
  }

  @Test
  void testSingleOperand()
  {
    Calculator calc = new Calculator();
    List<String> input = new ArrayList<String>();

    // purely real operands
    input.add(one + log);
    assertEquals(zero, calc.calculate(input).toString());
    input.clear();
    input.add("-25" + sqrt);
    assertEquals(fiveI, calc.calculate(input).toString());
    input.clear();
    input.add(two + inv);
    assertEquals("0.5", calc.calculate(input).toString());
    input.clear();
    input.add(zero + log);
    assertEquals("-Infinity", calc.calculate(input).toString());
    input.clear();

    // purely imaginary operands
    input.add(i + log);
    assertEquals("" + Math.PI / 2 + i, calc.calculate(input).toString());
    input.clear();
    input.add(negOne + sqrt);
    assertEquals(i, calc.calculate(input).toString());
    input.clear();
    input.add(twoI + inv);
    assertEquals("-0.5i", calc.calculate(input).toString());
    input.clear();
    input.add("18i" + con);
    assertEquals("-18i", calc.calculate(input).toString());
    input.clear();

    // complex operands
    input.add("3+2i" + log);
    assertTrue(Math.abs(1.28247 - calc.calculate(input).getRealPart()) < 0.001);
    assertTrue(Math.abs(0.588 - calc.calculate(input).getImaginaryPart()) < 0.001);
    input.clear();
    input.add("2-6i" + sqrt);
    calc.calculate(input);
    assertTrue(Math.abs(2.04016 - calc.calculate(input).getRealPart()) < 0.001);
    assertTrue(Math.abs(-1.47046 - calc.calculate(input).getImaginaryPart()) < 0.001);
    input.clear();
    input.add(threePlusFour + inv);
    assertEquals("0.12-0.16i", calc.calculate(input).toString());
    input.clear();
    input.add("18+22i" + con);
    assertEquals("18-22i", calc.calculate(input).toString());
  }

  @Test
  void testRunningSingleOperand()
  {
    Calculator calc = new Calculator();
    List<String> input = new ArrayList<String>();

    // purely real operands
    input.add(twenty);
    input.add(plus);
    input.add(five);
    assertEquals(tFive, calc.calculate(input).toString());
    input.clear();

    input.add(sqrt);
    assertEquals(five, calc.calculate(input).toString());
    input.clear();
    input.add(inv);
    assertEquals("0.2", calc.calculate(input).toString());
    input.clear();
    input.add(log);
    assertTrue(Math.abs(-1.6094 - calc.calculate(input).getRealPart()) < 0.001);
    assertEquals(0, calc.calculate(input).getImaginaryPart());
    input.clear();

    // purely imaginary operands
    input.add(fiveI);
    assertEquals(fiveI, calc.calculate(input).toString());
    input.clear();

    input.add(sqrt);
    assertTrue(Math.abs(1.5811 - calc.calculate(input).getRealPart()) < 0.001);
    assertTrue(Math.abs(1.5811 - calc.calculate(input).getImaginaryPart()) < 0.001);
    input.clear();
    input.add(inv);
    assertTrue(Math.abs(0.3162 - calc.calculate(input).getRealPart()) < 0.001);
    assertTrue(Math.abs(-0.3162 - calc.calculate(input).getImaginaryPart()) < 0.001);
    input.clear();
    input.add(log);
    assertTrue(Math.abs(-0.8047 - calc.calculate(input).getRealPart()) < 0.001);
    assertTrue(Math.abs(-0.7853 - calc.calculate(input).getImaginaryPart()) < 0.001);
    input.clear();
    input.add(con);
    assertTrue(Math.abs(-0.8047 - calc.calculate(input).getRealPart()) < 0.001);
    assertTrue(Math.abs(0.7853 - calc.calculate(input).getImaginaryPart()) < 0.001);
    input.clear();

    // complex operands
    input.add(negTwoPlusFive);
    assertEquals(negTwoPlusFive, calc.calculate(input).toString());
    input.clear();

    input.add(sqrt);
    assertTrue(Math.abs(1.3009 - calc.calculate(input).getRealPart()) < 0.001);
    assertTrue(Math.abs(1.9216 - calc.calculate(input).getImaginaryPart()) < 0.001);
    input.clear();
    input.add(inv);
    assertTrue(Math.abs(0.2415 - calc.calculate(input).getRealPart()) < 0.001);
    assertTrue(Math.abs(-0.3568 - calc.calculate(input).getImaginaryPart()) < 0.001);
    input.clear();
    input.add(log);
    assertTrue(Math.abs(-0.8418 - calc.calculate(input).getRealPart()) < 0.001);
    assertTrue(Math.abs(-0.9756 - calc.calculate(input).getImaginaryPart()) < 0.001);
    input.clear();
    input.add(con);
    assertTrue(Math.abs(-0.8418 - calc.calculate(input).getRealPart()) < 0.001);
    assertTrue(Math.abs(0.9756 - calc.calculate(input).getImaginaryPart()) < 0.001);
    input.clear();
  }

  @Test
  void testDoubleOperand()
  {
    Calculator calc = new Calculator();
    List<String> input = new ArrayList<String>();

    // purely real operands
    input.add(two + exp + three);
    assertEquals(eight, calc.calculate(input).toString());
    input.clear();
    input.add(five + exp + one);
    assertEquals(five, calc.calculate(input).toString());
    input.clear();
    input.add("18" + exp + zero);
    assertEquals(one, calc.calculate(input).toString());
    input.clear();

    // purely imaginary operands
    input.add(twoI + exp + four);
    assertEquals("16", calc.calculate(input).toString());
    input.clear();

    // complex operands
    input.add("2+4i" + exp + two);
    assertEquals("-12+16i", calc.calculate(input).toString());
    input.clear();
  }

  @Test
  void testRunningDoubleOperand()
  {
    Calculator calc = new Calculator();
    List<String> input = new ArrayList<String>();

    // purely real operands
    input.add(three);
    assertEquals(three, calc.calculate(input).toString());
    input.clear();

    input.add(exp + four);
    // input.add(four);
    assertEquals("81", calc.calculate(input).toString());
    input.clear();

    // purely imaginary operands
    input.add(twoI);
    assertEquals(twoI, calc.calculate(input).toString());
    input.clear();

    input.add(exp);
    input.add(three);
    assertEquals("-8i", calc.calculate(input).toString());
    input.clear();

    // complex operands
    input.add(negEightMinusI);
    assertEquals(negEightMinusI, calc.calculate(input).toString());
    input.clear();

    input.add(exp);
    input.add(five);
    assertEquals("-27688-19841i", calc.calculate(input).toString());
    input.clear();
  }

  @Test
  void testReAndIm()
  {
    Calculator calc = new Calculator();
    List<String> input = new ArrayList<String>();

    // purely real operands
    input.add(threeFiveThree);
    input.add(re);
    assertEquals(threeFiveThree, calc.calculate(input).toString());
    input.clear();
    input.add("6");
    input.add(im);
    assertEquals(zero, calc.calculate(input).toString());
    input.clear();

    // purely imaginary operands
    input.add("57i");
    input.add(re);
    assertEquals(zero, calc.calculate(input).toString());
    input.clear();
    input.add("28i");
    input.add(im);
    assertEquals("28", calc.calculate(input).toString());
    input.clear();

    // complex operands
    input.add("23+9i");
    input.add(re);
    assertEquals("23", calc.calculate(input).toString());
    input.clear();
    input.add("6-16i");
    input.add(im);
    assertEquals("-16", calc.calculate(input).toString());
    input.clear();
  }

  @Test
  void testRunningReAndIm()
  {
    Calculator calc = new Calculator();
    List<String> input = new ArrayList<String>();

    // purely real operands
    input.add(three);
    input.add(multiply);
    input.add("7");
    assertEquals(twentyOne, calc.calculate(input).toString());
    input.clear();

    input.add(re);
    assertEquals(twentyOne, calc.calculate(input).toString());
    input.clear();
    input.add(im);
    assertEquals(zero, calc.calculate(input).toString());

    // purely imaginary operands
  }
}
