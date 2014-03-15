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
	
	public final int getFloatCount()
	{
		initialize();
		return floatCount;
	}
	
	public final int getStride()
	{
		initialize();
		return stride;
	}
	
	public final int getBufferCount()
	{
		initialize();
		return bufferCount;
	}
	
	public final int getVertexCount()
	{
		initialize();
		return vertexCount;
	}
	
	public final int getVertexOffset()
	{
		return 0;
	}
	
	public final int getNormalOffset()
	{
		return 3 * 4;
	}
	
	protected int getBuffersCount()
	{
		return 2;
	}
	
	public int getFullWidth()
	{
		return (3 + 3) * 4;
	}
	
	protected int fillDataBuffers(int bufferNum, AbstractDataBuffer [] adbs)
	{
		adbs[bufferNum++] = vertexBuffer;
		adbs[bufferNum++] = normalBuffer;

		return bufferNum;
	}
	
	private final synchronized void initialize()
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
			stride += adbs[i].getElementSize() * 4;
		}
		
		floatCount = (stride * vertexCount) / 4;
		
		initialized = true;
	}

	public final FloatBuffer makeBuffer()
	{
		initialize();
		
		ByteBuffer bb = ByteBuffer.allocateDirect(floatCount * 4);
		bb.order(ByteOrder.nativeOrder());
		bb.position(0);
		
		FloatBuffer fb = bb.asFloatBuffer();
		fb.position(0);
		
		for(int j = 0; j < vertexCount; j++)
		{
			for(int i = 0; i < bufferCount; i++)
			{
				adbs[i].fillElement(j, fb);
			}
		}
		fb.position(0);
		return fb;
	}
}
