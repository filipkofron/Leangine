package cz.kofron.leangine;
import java.util.*;

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
		trans.uploadToShader(shader);

		draw(trans);
	}
	
	protected abstract void preDraw(Transformer trans);
	protected abstract void draw(Transformer trans);
	protected abstract void pastDraw(Transformer trans);
}
