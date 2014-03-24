package cz.kofron.leangine;
import java.util.*;

public class ColorCircleData
{
	private ArrayList<Float> vs = new ArrayList<>();
	private int div;
	private final float CIRCLE = 2.0f * (float) Math.PI;
	
	public ColorCircleData(int div)
	{
		this.div = div;
		if(div < 3)
		{
			div = 3;
		}
		generate();
	}
	
	private void generate()
	{
		float [] prevX = new float[div + 1];
		float [] currX = new float[div + 1];
		
		float [] prevY = new float[div + 1];
		float [] currY = new float[div + 1];
		
		float [] prevZ = new float[div + 1];
		float [] currZ = new float[div + 1];
		
		float step = CIRCLE / div;
		float hStep = 2.0f / div;
		
		int hs;
		int ds = 0;
		
		for(float d = 0; d <= (CIRCLE + step / 3.0f); d += step)
		{
			hs = 0;
			for(float h = -1.0f; h < 1.0f + hStep / 3.0f; h += hStep)
			{
				float x = (float) Math.sin(d);
				float y = h;
				float z = (float) Math.cos(d);
				
				float len = (float) Math.sqrt(1.0f - y * y);
				
				currX[hs] = len * x;
				currY[hs] = y;
				currZ[hs] = len * z;
				hs++;
			}
			
			if(ds > 0)
			{
				for(int i = 0; i < div; i++)
				{
					int oi = i + 1;
					
					vs.add(prevX[i]);
					vs.add(prevY[i]);
					vs.add(prevZ[i]);
					
					vs.add(currX[i]);
					vs.add(currY[i]);
					vs.add(currZ[i]);
					
					vs.add(currX[oi]);
					vs.add(currY[oi]);
					vs.add(currZ[oi]);
					
					
					vs.add(prevX[oi]);
					vs.add(prevY[oi]);
					vs.add(prevZ[oi]);
					
					vs.add(prevX[i]);
					vs.add(prevY[i]);
					vs.add(prevZ[i]);
					
					vs.add(currX[oi]);
					vs.add(currY[oi]);
					vs.add(currZ[oi]);
				}
			}
			
			float [] t;
				
			t = prevX;
			prevX = currX;
			currX = t;
				
			t = prevY;
			prevY = currY;
			currY = t;
				
			t = prevZ;
			prevZ = currZ;
			currZ = t;

			ds++;
		}
	}
	
	public SimpleColorModelData makeColorData()
	{
		float [] res = new float[vs.size()];
		float [] n;
		float [] c;
		int i = 0;
		for(Float f: vs)
		{
			res[i++] = f / 6.0f;
		}
		i = 0;
		int sf = res.length / 3;
		n = new float[sf * 3];
		c = new float[sf * 4];
		while(i < sf)
		{
			float xx = res[i * 3];
			float yy = res[i * 3 + 1];
			float zz = res[i * 3 + 2];
			float len = (float) Math.sqrt(xx * xx + yy * yy + zz * zz);
			n[i * 3] = res[i * 3] / len;
			n[i * 3 + 1] = res[i * 3 + 1] / len;
			n[i * 3 + 2] = res[i * 3 + 2] / len;
			
			c[i * 4] = 0.5f;
			c[i * 4 + 1] = 0.5f;
			c[i * 4 + 2] = 0.5f;
			c[i * 4 + 3] = 1.0f;

			i++;
		}
		VertexBuffer vb = new VertexBuffer(res);
		NormalBuffer nb = new NormalBuffer(n);
		ColorBuffer cb = new ColorBuffer(c);
		SimpleColorModelData scmd = new SimpleColorModelData(vb, nb, cb);
		
		return scmd;
	}
}
