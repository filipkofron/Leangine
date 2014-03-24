package cz.kofron.leangine;
import android.opengl.*;

public class TexturedModel extends SimpleModel
{
	protected int uvOffset;
	private int tex;

	public TexturedModel(TexturedShader shader, TexturedModelData data)
	{
		super(shader, data);
	}

	public TexturedModel(TexturedModelData data)
	{
		super((TexturedShader) ShaderCollection.texturedShader, data);
	}
	
	@Override
	protected void postUploadData(SimpleModelData data)
	{
		super.postUploadData(data);
		uvOffset = ((TexturedModelData) data).getUVOffset();
		tex = ((TexturedModelData) data).getTexture();
	}

	@Override
	protected void preDraw(Transformer trans)
	{
		super.preDraw(trans);
		GLES20.glVertexAttribPointer(getShader().a_uv, 2, GLES20.GL_FLOAT, false, stride, uvOffset);
		Util.checkGLError("GLES20.glVertexAttribPointer(getShader().a_uv, 2, GLES20.GL_FLOAT, false, stride, uvOffset);");
		GLES20.glEnableVertexAttribArray(getShader().a_uv);
		Util.checkGLError("GLES20.glEnableVertexAttribArray(getShader().a_uv);");
		
		GLES20.glEnable(GLES20.GL_TEXTURE_2D);
		GLES20.glUniform1i(getShader().u_tex, 0);
		GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, tex);
	}

	@Override
	protected void draw(Transformer trans)
	{
		super.draw(trans);
	}

	@Override
	protected void pastDraw(Transformer trans)
	{
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
		GLES20.glDisable(GLES20.GL_TEXTURE_2D);
		GLES20.glDisableVertexAttribArray(getShader().a_uv);
		Util.checkGLError("GLES20.glDisableVertexAttribArray(getShader().a_uv);");
		super.pastDraw(trans);
	}

	private final TexturedShader getShader()
	{
		return (TexturedShader) shader;
	}
}
