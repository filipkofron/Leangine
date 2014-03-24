package cz.kofron.leangine;
import java.nio.*;

public class UVBuffer extends AbstractDataBuffer
{
	private float [] uvs;

	public UVBuffer(float [] uvs)
	{
		this.uvs = uvs;
	}

	@Override
	public int getElementSize()
	{
		return 2;
	}

	@Override
	public int getElementCount()
	{
		return uvs.length / 2;
	}

	@Override
	public void fillElement(int which, FloatBuffer fb)
	{
		fb.put(uvs[2 * which]);
		fb.put(uvs[2 * which + 1]);
	}
}
