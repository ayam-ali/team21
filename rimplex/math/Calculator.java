package math;

import java.util.*;

public class Calculator
{
  private String recentResult;
  
  public Calculator() {
    recentResult = "0";
  }
  
  public ComplexNumber calculate(List<String> input) {
    // example input: {"7i", "+", "4-2i"}
    // this part handles running calculations
    if (isOperation(input.get(0))) {
      input.add(0, recentResult);
    }
    
    ComplexNumber total = parseComplex(input.get(0));
    for (int i = 2; i < input.size(); i += 2) {
      ComplexNumber num = parseComplex(input.get(i));
      total = performOperation(total, num, input.get(i-1));
    }
    
    recentResult = total.toString();
    return total;
  }
  
  private ComplexNumber parseComplex(String numString) {
    // examples: "3+5i", "4", "9i"
    ComplexNumber value = null;
    if (!numString.contains("+") && !numString.contains("-")) {
      // purely imaginary number
      if (numString.contains("i")) {
        numString = numString.replace('i', Character.MIN_VALUE);
        value = new ComplexNumber(0.0, Double.parseDouble(numString));
      } else {
        // purely real number
        value = new ComplexNumber(Double.parseDouble(numString), 0.0);
      }
    } else {
      // number with both real and imaginary parts
      int operation = (numString.contains("+")) ? numString.indexOf("+") : numString.indexOf("-");
      Double real = Double.parseDouble(numString.substring(0, operation));
      Double imaginary = Double.parseDouble(numString.substring(operation+1).replace('i', Character.MIN_VALUE));
      value = new ComplexNumber(real, imaginary);
    }
    return value;
  }
  
  private ComplexNumber performOperation(ComplexNumber first, ComplexNumber second, String operand) {
    ComplexNumber result = null;
    switch (operand) {
      case "+":
        result = first.add(second);
        break;
      case "-":
        result = first.subtract(second);
        break;
      case "*":
        result = first.multiply(second);
        break;
      case "/":
        result = first.divide(second);
        break;
      default:
        // do nothing
    }
    return result;
  }
  
  private boolean isOperation(String string) {
    return string == "+" || string == "-" || string == "*" || string == "/";
  }
  
}
