import java.util.Stack;
public class CalculatePostfixSolve implements Requirement2_OutputGetter
{
	private String inputString;
	private double resultOfExpression;
	
	public CalculatePostfixSolve(){ 
		this.inputString = "";
		this.resultOfExpression = 0;
	}
	
	public CalculatePostfixSolve(String inputString){ 
		this.inputString = inputString;
		this.resultOfExpression = 0;
	}
	
	public void setInputString(String inputString){ 
		this.inputString = inputString;
	}	
	
	public double getResultOfExpression(){
		this.resultOfExpression = calculatePostfix();
		return this.resultOfExpression;
	}	
	
	private double calculatePostfix()
	{
		Stack <Double> stack = new Stack <Double> ();
		Double ans,n = 0.0;
		String [] tokens = stringTokenizer(this.inputString);
		for (int i=0; i<tokens.length; i++)
		{
			//Check num
			if (isNum(tokens[i]))
			{
				stack.push(Double.parseDouble(tokens[i]));
			}
			else 
			{
				n = stack.pop();
				// Exponent 
				if (tokens[i].equals("^"))
				{
					ans = Math.pow(stack.pop(),n);
					stack.push(ans);
				}
				// Multiplication
				if (tokens[i].equals("*"))
				{
					ans = stack.pop() * n;
					stack.push(ans);
				}
				// Division
				if (tokens[i].equals("/"))
				{
					if (n==0 && stack.peek()==0)
					{
						return 0;
					}
					ans = stack.pop() / n;
					stack.push(ans);
				}
				// Addition
				if (tokens[i].equals("+"))
				{
					ans = stack.pop() + n;
					stack.push(ans);
				}
				// Subtraction
				if (tokens[i].equals("-"))
				{
					ans = stack.pop() - n;
					stack.push(ans);
				}
			}
		}
		return stack.pop();
	}
	private String [] stringTokenizer(String str)
	{
		String [] tokens = str.split(" ");
		return tokens;
	}
	private boolean isNum(String c)
	{
    	return c.matches("-?\\d+(\\.\\d+)?");
    }

}
