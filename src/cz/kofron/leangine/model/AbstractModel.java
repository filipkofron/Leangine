package cz.kofron.leangine.model;
import android.opengl.*;

import cz.kofron.leangine.shader.AbstractShader;
import cz.kofron.leangine.transform.Transformer;
import cz.kofron.leangine.util.Util;

public abstract class AbstractModel extends ModelNode
{
	protected AbstractShader shader;
	
	public AbstractModel(AbstractShader shader)
	{
		this.shader = shader;
	}

	public abstract boolean initializeGL();
	
	public abstract void destroyGL();
	
	@Override
	protected void onDraw(Transformer trans)
	{
		trans.updateNormal();
		
		GLES20.glUseProgram(shader.program);
		Util.checkGLError("GLES20.glUseProgram(shader.program);");
		
		trans.uploadToShader(shader);
		
		preDraw(trans);
		draw(trans);
		pastDraw(trans);
		
		GLES20.glUseProgram(0);
		Util.checkGLError("GLES20.glUseProgram(0);");
	}

	protected abstract void preDraw(Transformer trans);
	protected abstract void draw(Transformer trans);
	protected abstract void pastDraw(Transformer trans);
}
