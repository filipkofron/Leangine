package cz.kofron.leangine.shader;

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
