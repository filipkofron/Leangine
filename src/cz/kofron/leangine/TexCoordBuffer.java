package cz.kofron.leangine;
import java.nio.*;

public class TexCoordBuffer extends AbstractDataBuffer
{
	private float [] texCoords;

	public TexCoordBuffer(float [] texCoords)
	{
		this.texCoords = texCoords;
	}

	@Override
	public int getElementSize()
	{
		return 2;
	}

	@Override
	public int getElementCount()
	{
		return texCoords.length / 2;
	}

	@Override
	public void fillElement(int which, FloatBuffer fb)
	{
		fb.put(texCoords[2 * which]);
		fb.put(texCoords[2 * which + 1]);
	}

}
