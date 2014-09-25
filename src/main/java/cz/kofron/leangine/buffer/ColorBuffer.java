package cz.kofron.leangine.buffer;
import java.nio.*;

import cz.kofron.leangine.buffer.AbstractDataBuffer;

public class ColorBuffer extends AbstractDataBuffer
{
	float [] colors;
	
	public ColorBuffer(float [] colors)
	{
		this.colors = colors;
	}

	@Override
	public int getElementSize()
	{
		return 4;
	}

	@Override
	public int getElementCount()
	{
		return colors.length / 4;
	}

	@Override
	public void fillElement(int which, FloatBuffer fb)
	{
		fb.put(colors[4 * which]);
		fb.put(colors[4 * which + 1]);
		fb.put(colors[4 * which + 2]);
		fb.put(colors[4 * which + 3]);
	}
}
