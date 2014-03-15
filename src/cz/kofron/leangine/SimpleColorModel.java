package cz.kofron.leangine;
import android.opengl.*;

public class SimpleColorModel extends SimpleModel
{
	protected int colorOffset;
	
	public SimpleColorModel(SimpleColorShader shader, SimpleColorModelData data)
	{
		super(shader, data);
	}
	
	public SimpleColorModel(SimpleColorModelData data)
	{
		super((SimpleColorShader) ShaderCollection.simpleColorShader, data);
	}
	
	@Override
	protected void postUploadData(SimpleModelData data)
	{
		super.postUploadData(data);
		colorOffset = ((SimpleColorModelData) data).getColorOffset();
	}

	@Override
	protected void preDraw(Transformer trans)
	{
		super.preDraw(trans);
		GLES20.glVertexAttribPointer(getShader().a_color, 4, GLES20.GL_FLOAT, false, stride, colorOffset);
		Util.checkGLError("GLES20.glVertexAttribPointer(getShader().a_color, 4, GLES20.GL_FLOAT, false, stride, colorOffset);");
		GLES20.glEnableVertexAttribArray(getShader().a_color);
		Util.checkGLError("GLES20.glEnableVertexAttribArray(getShader().a_color);");
	}

	@Override
	protected void draw(Transformer trans)
	{
		super.draw(trans);
	}

	@Override
	protected void pastDraw(Transformer trans)
	{
		GLES20.glDisableVertexAttribArray(getShader().a_color);
		Util.checkGLError("GLES20.glDisableVertexAttribArray(getShader().a_color);");
		super.pastDraw(trans);
	}
	
	private final SimpleColorShader getShader()
	{
		return (SimpleColorShader) shader;
	}
}
