package cz.kofron.leangine;
import java.util.*;

public class ModelGroup extends ModelNode
{
	private static class NodeHolder
	{
		public ModelNode node;
		public Transform transform;
		
		public NodeHolder(ModelNode node, Transform transform)
		{
			this.node = node;
			this.transform = transform;
		}
	}
	
	private List<NodeHolder> nodes = new ArrayList<NodeHolder>();
	
	public void updateTransforms()
	{
		for(NodeHolder nodeHolder : nodes)
		{
			if(nodeHolder.transform != null)
			{
				nodeHolder.transform.onUpdate();
			}
			
			if(nodeHolder.node instanceof ModelGroup)
			{
				((ModelGroup) nodeHolder.node).updateTransforms();
			}
		}
	}
	
	@Override
	protected void onDraw(Transformer trans)
	{
		for(NodeHolder nodeHolder : nodes)
		{
			trans.descend(nodeHolder.transform);
			nodeHolder.node.onDraw(trans);
			trans.ascend();
		}
	}
}
