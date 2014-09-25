package cz.kofron.leangine.scene;

import cz.kofron.leangine.model.ModelGroup;
import cz.kofron.leangine.transform.Transformer;

public class SceneRoot extends ModelGroup
{
	public void drawScene(Transformer transformer)
	{
		onDraw(transformer);
	}
}
