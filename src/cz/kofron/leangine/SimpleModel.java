package cz.kofron.leangine;
import android.opengl.*;
import java.nio.*;
import android.util.*;

public class SimpleModel extends AbstractModel
{
	protected int facesCount;
	protected int stride;
	protected int vertexOffset;
	protected int normalOffset;
	
	protected SimpleModelData data;
	
	@Override
	public void destroyGL()
	{
		GLES20.glDeleteBuffers(GLES20.GL_ARRAY_BUFFER, vbo, 1);
	}

	public final void uploadData()
	{
		Log.i("MyApp", "uploadData()");

		FloatBuffer fb = data.makeBuffer();
		fb.position(0);
		facesCount = data.getVertexCount() / 3;

		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[0]);
		Util.checkGLError("GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[0]); (upload)");
		GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, fb.capacity() * 4, fb, GLES20.GL_STATIC_DRAW);
		Util.checkGLError("GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, fb.capacity() * 4, fb, GLES20.GL_STATIC_DRAW);");
		
		System.out.println("fb.capacity(): " + fb.capacity());
		
		fb = null;

		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
		Util.checkGLError("GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);");
		
		stride = data.getStride();
		
		postUploadData(data);
	}
	
	protected void postUploadData(SimpleModelData data)
	{
		vertexOffset = data.getVertexOffset();
		normalOffset = data.getNormalOffset();
	}
	
	@Override
	public boolean initializeGL()
	{
		Log.i("MyApp", "initializeGL()");
		GLES20.glGenBuffers(1, vbo, 0);
		Util.checkGLError("GLES20.glGenBuffers(1, vbo, 0);");
		return vbo[0] != GLES20.GL_INVALID_VALUE;
	}

	protected int [] vbo = new int[1];
	
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
	protected void preDraw(Transformer trans)
	{
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[0]);
		Util.checkGLError("GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vbo[0]); (draw)");
		
		GLES20.glVertexAttribPointer(getShader().a_pos, 3, GLES20.GL_FLOAT, false, stride, vertexOffset);
		Util.checkGLError("GLES20.glVertexAttribPointer(getShader().a_pos, 3, GLES20.GL_FLOAT, false, stride, vertexOffset);");
		GLES20.glEnableVertexAttribArray(getShader().a_pos);
		Util.checkGLError("GLES20.glEnableVertexAttribArray(getShader().a_pos);");

		GLES20.glVertexAttribPointer(getShader().a_norm, 3, GLES20.GL_FLOAT, false, stride, normalOffset);
		Util.checkGLError("GLES20.glVertexAttribPointer(getShader().a_norm, 3, GLES20.GL_FLOAT, false, stride, normalOffset);");
		GLES20.glEnableVertexAttribArray(getShader().a_norm);
		Util.checkGLError("GLES20.glEnableVertexAttribArray(getShader().a_norm);");
	}
	
	@Override
	protected void draw(Transformer trans)
	{
		GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, facesCount * 3);
		Util.checkGLError("GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, facesCount * 3);");
	}

	@Override
	protected void pastDraw(Transformer trans)
	{
		GLES20.glDisableVertexAttribArray(getShader().a_norm);
		GLES20.glDisableVertexAttribArray(getShader().a_pos);
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
	}

	private final SimpleShader getShader()
	{
		return (SimpleShader) shader;
	}
}
