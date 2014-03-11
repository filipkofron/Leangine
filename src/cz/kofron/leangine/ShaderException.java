package cz.kofron.leangine;

public class ShaderException extends Exception
{
	public ShaderException(Throwable t)
	{
		super(t);
	}
	
	public ShaderException(String s)
	{
		super(s);
	}
}
