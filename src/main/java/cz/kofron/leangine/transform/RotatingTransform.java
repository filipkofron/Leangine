package cz.kofron.leangine.transform;

public class RotatingTransform extends SimpleTransform
{
	private float rotSpeedX = 0;
	private float rotSpeedY = 0;
	private float rotSpeedZ = 0;
	
	private long lastTime;

	public RotatingTransform(float rotSpeedX, float rotSpeedY, float rotSpeedZ)
	{
		this.rotSpeedX = rotSpeedX;
		this.rotSpeedY = rotSpeedY;
		this.rotSpeedZ = rotSpeedZ;
		lastTime = System.currentTimeMillis();
	}

	@Override
	protected void update()
	{
		float diff = (System.currentTimeMillis() - lastTime) / 1000.0f;
		rotX += rotSpeedX * diff;
		rotY += rotSpeedY * diff;
		rotZ += rotSpeedZ * diff;
		lastTime = System.currentTimeMillis();
		super.update();
		makeDirty();
	}
}
