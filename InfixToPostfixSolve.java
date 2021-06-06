import java.util.Stack;
public class InfixToPostfixSolve implements Requirement1_OutputGetter
{
	private String inputString;
	private String outputString;
	
	public InfixToPostfixSolve(){
		this.inputString = "";
		this.outputString = "";
	}
	
	public InfixToPostfixSolve(String inputString){ 
		this.inputString = inputString;
		this.outputString = "";
	}
		
	public void setInputString(String inputString){ 
		this.inputString = inputString;
	}
	
	public String getOutputString() { 
    	this.outputString = infixToPostfix();
		return outputString;
	}	
	private String infixToPostfix()
	{
		String postfix = "";
		Stack <String> stack = new Stack<String>();
		stack.push(" ");
		String [] tokens = stringTokenizer(this.inputString);
		for(int i=0; i<tokens.length; i++)
		{
			if(isNum(tokens[i]))
			{
				postfix = postfix + tokens[i] + " ";
			}
			else if(tokens[i].equals("("))
			{
				stack.push(tokens[i]);
			}
			else if(tokens[i].equals(")"))
			{
				while(!stack.peek().equals("("))
				{
					postfix = postfix + stack.pop() + " ";
				}
				stack.pop();
			}
			else if(priorityOfOperator(tokens[i]) > priorityOfOperator(stack.peek()))
			{
				stack.push(tokens[i]);
			}
			else if(priorityOfOperator(tokens[i]) <= priorityOfOperator(stack.peek()))
			{
				while(priorityOfOperator(tokens[i]) <= priorityOfOperator(stack.peek()))
				{
					postfix = postfix + stack.pop() + " ";
				}
				stack.push(tokens[i]);
			}
		} 
		while(!(stack.peek().equals(" ")))
		{
			postfix = postfix + stack.pop() + " ";
		}
		return postfix;
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
	private int priorityOfOperator(String op)
	{
		if(op.equals("+")||op.equals("-"))
		{
			return 1;
		}
		else if(op.equals("*")||op.equals("/"))
		{
			return 2;
		}
		else if(op.equals("^"))
		{
			return 3;
		}
		return -1;
	}
}
