package cz.kofron.leangine;

public abstract class Transform
{
	private boolean dirty = true;
	public float [] matrix = new float [16];
	
	public Transform()
	{
		update();
	}
	
	public final void onUpdate()
	{
		if(dirty)
		{
			dirty = false;
			update();
		}
	}
	
	protected abstract void update();
	
	public void makeDirty()
	{
		dirty = true;
	}
}
