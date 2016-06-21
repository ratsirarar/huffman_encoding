/**
 * Custom Exception to handle Invalid postfix expression
 * 
 * @author Andy Ratsirarson
 * @version 1.0 
 */

package utils;

public class InvalidInputException extends Exception
{
  public InvalidInputException(String message)
  {
    super(message);
  }
}
