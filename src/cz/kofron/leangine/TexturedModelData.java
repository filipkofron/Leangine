package cz.kofron.leangine;
import android.graphics.*;

public class TexturedModelData extends SimpleModelData
{
	protected UVBuffer uvBuffer;
	protected int texture;

	public TexturedModelData(VertexBuffer vb, NormalBuffer nb, UVBuffer uvb, int texture)
	{
		super(vb, nb);
		this.uvBuffer = uvb;
		this.texture = texture;
	}

	@Override
	protected int getBuffersCount()
	{
		return super.getBuffersCount() + 1;
	}

	@Override
	public int getFullWidth()
	{
		return super.getFullWidth() + 2 * 4;
	}

	public final int getUVOffset()
	{
		return super.getFullWidth();
	}
	
	public int getTexture()
	{
		return texture;
	}

	protected int fillDataBuffers(int bufferNum, AbstractDataBuffer [] adbs)
	{
		bufferNum = super.fillDataBuffers(bufferNum, adbs);
		adbs[bufferNum++] = uvBuffer;

		return bufferNum;
	}
}
