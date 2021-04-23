package testing;

import static org.junit.jupiter.api.Assertions.*;
import math.*;
import java.util.*;
import org.junit.jupiter.api.Test;

class CalculatorTest
{

  // types of input to test
  // regular calculations (+-*/)
  // running calculations (+-*/)
  // single operand (sqrt, log, etc)
  // running calculations with single operand
  // double operand (exp)
  // running calculations with double operand
  // re and im
  // running calculations with re and im?
  
  private String plus = "+";
  private String minus = "-";
  private String multiply = "\u00D7";
  private String divide = "\u00F7";
  private String sqrt = "\u221A";
  private String inv = "Inv";
  private String log = "LOG";
  private String con = "Con";
  private String exp = "Exp";
  private String re = "Re"; // real and img operator 
  private String im = "Im";
  
  @Test
  void testConstructor()
  {
    Calculator calc = new Calculator();
  }
  
  @Test
  void testBasic()
  {
    Calculator calc = new Calculator();
    List<String> input = new ArrayList<String>();
    
    // purely real operands
    input.add("10");
    input.add(plus);
    input.add("2");
    assertEquals("12", calc.calculate(input).toString());
    input.remove(1);
    input.add(1, minus);
    assertEquals("8", calc.calculate(input).toString());
    input.remove(1);
    input.add(1, multiply);
    assertEquals("20", calc.calculate(input).toString());
    input.remove(1);
    input.add(1, divide);
    assertEquals("5", calc.calculate(input).toString());
    input.clear();
    
    // purely imaginary operands
    input.add("i");
    input.add(plus);
    input.add("i");
    assertEquals("2i", calc.calculate(input).toString());
    input.remove(1);
    input.add(1, minus);
    assertEquals("0", calc.calculate(input).toString());
    input.remove(1);
    input.add(1, multiply);
    assertEquals("-1", calc.calculate(input).toString());
    input.remove(1);
    input.add(1, divide);
    assertEquals("1", calc.calculate(input).toString());
    input.clear();
    
    // complex operands
    input.add("10+i");
    input.add(plus);
    input.add("2-i");
    assertEquals("12", calc.calculate(input).toString());
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
    input.add("10");
    input.add(plus);
    input.add("2");
    assertEquals("12", calc.calculate(input).toString());
    input.clear();
    
    input.add(plus);
    input.add("2");
    assertEquals("14", calc.calculate(input).toString());
    input.clear();
    input.add(minus);
    input.add("4");
    assertEquals("10", calc.calculate(input).toString());
    input.clear();
    input.add(multiply);
    input.add("5");
    assertEquals("50", calc.calculate(input).toString());
    input.clear();
    input.add(divide);
    input.add("2");
    assertEquals("25", calc.calculate(input).toString());
    input.clear();
    
    // purely imaginary operands
    input.add("10i");
    input.add(plus);
    input.add("2i");
    assertEquals("12i", calc.calculate(input).toString());
    input.clear();
    
    input.add(plus);
    input.add("2i");
    assertEquals("14i", calc.calculate(input).toString());
    input.clear();
    input.add(minus);
    input.add("4i");
    assertEquals("10i", calc.calculate(input).toString());
    input.clear();
    input.add(multiply);
    input.add("5i");
    assertEquals("-50", calc.calculate(input).toString());
    input.clear();
    input.add(divide);
    input.add("2i");
    assertEquals("100i", calc.calculate(input).toString());
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
    input.add("3+4i");
    assertEquals("17+5i", calc.calculate(input).toString());
    input.clear();
    input.add(multiply);
    input.add("2+8i");
    assertEquals("-6+146i", calc.calculate(input).toString());
    input.clear();
    input.add(divide);
    input.add("4+3i");
    assertEquals("462/25+602/25i", calc.calculate(input).toString(true));
    input.clear();
  }
  
  @Test
  void testSingleOperand()
  {
    Calculator calc = new Calculator();
    List<String> input = new ArrayList<String>();
    
    // purely real operands
    input.add("1");
    input.add(log);
    assertEquals("0", calc.calculate(input).toString());
    input.clear();
    input.add("25");
    input.add(sqrt);
    assertEquals("5", calc.calculate(input).toString());
    input.clear();
    input.add("2");
    input.add(inv);
    assertEquals("0.5", calc.calculate(input).toString());
    input.clear();
    
    // purely imaginary operands
    input.add("i");
    input.add(log);
    assertEquals(""+Math.PI/2+"i", calc.calculate(input).toString());
    input.clear();
    input.add("-1");
    input.add(sqrt);
    assertEquals("i", calc.calculate(input).toString());
    input.clear();
    input.add("2i");
    input.add(inv);
    assertEquals("-0.5i", calc.calculate(input).toString());
    input.clear();
    input.add("18i");
    input.add(con);
    assertEquals("-18i", calc.calculate(input).toString());
    input.clear();
    
    // complex operands
    input.add("3+2i");
    input.add(log);
    assertTrue(Math.abs(1.28247 - calc.calculate(input).getRealPart()) < 0.001);
    assertTrue(Math.abs(0.588 - calc.calculate(input).getImaginaryPart()) < 0.001);
    input.clear();
    input.add("2-6i");
    input.add(sqrt);
    assertTrue(Math.abs(2.04016 - calc.calculate(input).getRealPart()) < 0.001);
    assertTrue(Math.abs(-1.47046 - calc.calculate(input).getImaginaryPart()) < 0.001);
    input.clear();
    input.add("3+4i");
    input.add(inv);
    assertEquals("0.12-0.16i", calc.calculate(input).toString());
    input.clear();
    input.add("18+22i");
    input.add(con);
    assertEquals("18-22i", calc.calculate(input).toString());
  }

  @Test
  void testDoubleOperand()
  {
    Calculator calc = new Calculator();
    List<String> input = new ArrayList<String>();
    
    // purely real operands
    input.add("2");
    input.add(exp);
    input.add("3");
    assertEquals("8", calc.calculate(input).toString());
    input.clear();
    
    // purely imaginary operands
    input.add("2i");
    input.add(exp);
    input.add("3i");
    assertEquals("2", calc.calculate(input).toString());
    input.clear();
    
    // complex operands
    input.add("2+4i");
    input.add(exp);
    input.add("3-2i");
    assertTrue(Math.abs(775.793 - calc.calculate(input).getRealPart()) < 0.001);
    assertTrue(Math.abs(262.0185 - calc.calculate(input).getImaginaryPart()) < 0.001);
    input.clear();
  }
}