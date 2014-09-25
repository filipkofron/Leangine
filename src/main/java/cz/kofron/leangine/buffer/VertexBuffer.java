package cz.kofron.leangine.buffer;
import java.nio.*;

public class VertexBuffer extends AbstractDataBuffer
{
	private float [] vertices;
	
	public VertexBuffer(float [] vertices)
	{
		this.vertices = vertices;
	}
	
	@Override
	public int getElementSize()
	{
		return 3;
	}

	@Override
	public int getElementCount()
	{
		return vertices.length / 3;
	}

	@Override
	public void fillElement(int which, FloatBuffer fb)
	{
		fb.put(vertices[3 * which]);
		fb.put(vertices[3 * which + 1]);
		fb.put(vertices[3 * which + 2]);
	}

}
