package cz.kofron.leangine;

import android.opengl.*;
import java.util.*;

public abstract class ModelNode
{
	public ModelNode()
	{
		
	}
	
	protected abstract void onDraw(Transformer trans);
}
