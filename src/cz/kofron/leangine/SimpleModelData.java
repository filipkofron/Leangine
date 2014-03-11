package cz.kofron.leangine;
import java.nio.*;

public class SimpleModelData
{
	public VertexBuffer vertexBuffer;
	public NormalBuffer normalBuffer;
	
	private int floatCount;
	private int stride;
	
	AbstractDataBuffer [] adbs;
	private int bufferCount;
	private int vertexCount;
	
	private boolean initialized = false;
	
	public SimpleModelData(VertexBuffer vertexBuffer, NormalBuffer normalBuffer)
	{
		this.vertexBuffer = vertexBuffer;
		this.normalBuffer = normalBuffer;
	}
	
	public int getFloatCount()
	{
		initialize();
		return floatCount;
	}
	
	public int getStride()
	{
		initialize();
		return stride;
	}
	
	public int getBufferCount()
	{
		initialize();
		return bufferCount;
	}
	
	public int getVertexCount()
	{
		initialize();
		return vertexCount;
	}
	
	private synchronized void initialize()
	{
		if(initialized)
		{
			return;
		}
		
		bufferCount = getBuffersCount();
		adbs = new AbstractDataBuffer[bufferCount];
		
		fillDataBuffers(0, adbs);
		
		for(int i = 0; i < bufferCount; i++)
		{
			if(i > 0)
			{
				if(vertexCount != adbs[i].getElementCount())
				{
					throw new RuntimeException("Given buffers differ in vertex count! ("
											   + vertexCount + " != " + adbs[i].getElementCount() + ")");
				}
			}
			else
			{
				vertexCount = adbs[i].getElementCount();
			}
			stride += adbs[i].getElementSize();
		}
		
		floatCount = stride * vertexCount;
		
		initialized = true;
	}
	
	public int getBuffersCount()
	{
		return 2;
	}
	
	protected void fillDataBuffers(int bufferNum, AbstractDataBuffer [] adbs)
	{
		adbs[bufferNum++] = vertexBuffer;
		adbs[bufferNum++] = normalBuffer;
	}

	public final FloatBuffer makeBuffer()
	{
		ByteBuffer bb = ByteBuffer.allocateDirect(floatCount * 4);
		bb.order(ByteOrder.nativeOrder());
		bb.position(0);
		
		FloatBuffer fb = bb.asFloatBuffer();
		fb.position(0);
		
		for(int i = 0; i < bufferCount; i++)
		{
			for(int j = 0; j < vertexCount; j++)
			{
				adbs[i].fillElement(j, fb);
			}
		}
		return fb;
	}
}
