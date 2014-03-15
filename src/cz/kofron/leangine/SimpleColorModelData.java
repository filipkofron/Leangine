package cz.kofron.leangine;

public class SimpleColorModelData extends SimpleModelData
{
	protected ColorBuffer colorBuffer;
	
	public SimpleColorModelData(VertexBuffer vb, NormalBuffer nb, ColorBuffer cb)
	{
		super(vb, nb);
		this.colorBuffer = cb;
	}
	
	@Override
	protected int getBuffersCount()
	{
		return super.getBuffersCount() + 1;
	}

	@Override
	public int getFullWidth()
	{
		return super.getFullWidth() + 4 * 4;
	}
	
	public final int getColorOffset()
	{
		return super.getFullWidth();
	}
	
	protected int fillDataBuffers(int bufferNum, AbstractDataBuffer [] adbs)
	{
		bufferNum = super.fillDataBuffers(bufferNum, adbs);
		adbs[bufferNum++] = colorBuffer;
		
		return bufferNum;
	}
}
