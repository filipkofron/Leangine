package cz.kofron.leangine;
import java.nio.*;

public abstract class AbstractDataBuffer
{
	public abstract int getElementSize();
	public abstract int getElementCount();
	public abstract void fillElement(int which, FloatBuffer fb);
}
