package cz.kofron.leangine;
import android.opengl.*;
import java.nio.*;

public class SimpleModel extends AbstractModel
{
	protected int facesCount;
	protected int stride;
	
	protected SimpleModelData data;
	
	@Override
	public void destroyGL()
	{
		GLES20.glDeleteBuffers(GLES20.GL_ARRAY_BUFFER, vbo, 1);
	}

	public final void uploadData()
	{
		FloatBuffer fb = data.makeBuffer();
		fb.position(0);
		facesCount = data.getVertexCount() / 4;

		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[0]);
		GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, fb.capacity() * 4, fb, GLES20.GL_STATIC_DRAW);

		fb = null;

		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);

		stride = data.getStride();
	}
	
	@Override
	public boolean initializeGL()
	{
		GLES20.glGenBuffers(1, vbo, 0);
		return vbo[0] != GLES20.GL_INVALID_VALUE;
	}

	private int [] vbo;
	
	public SimpleModel(SimpleModelData data)
	{
		super(ShaderCollection.simpleShader);
		this.data = data;
	}
	
	public SimpleModel(SimpleShader shader, SimpleModelData data)
	{
		super(shader);
		this.data = data;
	}
	
	@Override
	public void preDraw(Transformer trans)
	{
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[0]);
		GLES20.glVertexAttribPointer(getShader().a_pos, 3, GLES20.GL_FLOAT, false, stride, 0);
		GLES20.glEnableVertexAttribArray(getShader().a_pos);

		GLES20.glVertexAttribPointer(getShader().a_norm, 3, GLES20.GL_FLOAT, false, stride, 3 * 4);
		GLES20.glEnableVertexAttribArray(getShader().a_norm);
	}
	
	@Override
	protected void draw(Transformer trans)
	{
		GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, facesCount * 3); 
	}

	@Override
	public void pastDraw(Transformer trans)
	{
	}
	
	private SimpleShader getShader()
	{
		return (SimpleShader) shader;
	}
}
