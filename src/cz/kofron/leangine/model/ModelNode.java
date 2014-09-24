package cz.kofron.leangine.model;

import cz.kofron.leangine.transform.Transformer;

public abstract class ModelNode
{
	public ModelNode()
	{
		
	}
	
	protected abstract void onDraw(Transformer trans);
}
