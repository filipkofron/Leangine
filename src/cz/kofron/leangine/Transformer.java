package cz.kofron.leangine;
import java.util.*;
import android.opengl.*;

public class Transformer
{
	private ArrayList<float []> parents = new ArrayList<float []>();
	private int cursor = 0;
	
	public float [] model;
	public float [] view = new float[16];
	public float [] projection = new float[16];
	public float [] normal = new float[16];
	private float [] temp = new float[16];
	private float [] temp2 = new float[16];
	
	public Transformer()
	{
		grow();
		model = parents.get(0);
		Matrix.setIdentityM(model, 0);
	}
	
	public void grow()
	{
		parents.add(new float [16]);
	}
	
	public void descend(Transform childTransform)
	{
		cursor++;
		if(cursor == parents.size())
		{
			grow();
		}
		Matrix.multiplyMM(parents.get(cursor), 0, parents.get(cursor - 1), 0, childTransform.matrix, 0);
		model = parents.get(cursor);
	}
	
	public void ascend()
	{
		cursor--;
		model = parents.get(cursor);
	}

	public void printAll()
	{
		print(model, "model");
		int cur = 0;
		for(float [] mat : parents)
		{
			print(mat, "cursor: " + cur);
			cur++;
		}
	}
	
	public void print(float [] mat, String name)
	{
		System.out.println("== Matrix: " + name + " ==");
		System.out.println(mat[0] + ", " + mat[1] + ", " + mat[2] + ", " + mat[3]);
		System.out.println(mat[4] + ", " + mat[5] + ", " + mat[6] + ", " + mat[7]);
		System.out.println(mat[8] + ", " + mat[9] + ", " + mat[10] + ", " + mat[11]);
		System.out.println(mat[12] + ", " + mat[13] + ", " + mat[14] + ", " + mat[15]);
	}
	
	public void updateNormal()
	{
		Matrix.multiplyMM(temp, 0, view, 0, model, 0);
		Matrix.invertM(temp2, 0, temp, 0);
		Matrix.transposeM(normal, 0, temp2, 0);
	}
	
	public void uploadToShader(AbstractShader shader)
	{
		GLES20.glUniformMatrix4fv(shader.u_model, 1, false, model, 0);
		Util.checkGLError("GLES20.glUniformMatrix4fv(shader.u_model, 1, false, model, 0);");
		GLES20.glUniformMatrix4fv(shader.u_view, 1, false, view, 0);
		Util.checkGLError("GLES20.glUniformMatrix4fv(shader.u_view, 1, false, view, 0);");
		GLES20.glUniformMatrix4fv(shader.u_proj, 1, false, projection, 0);
		Util.checkGLError("GLES20.glUniformMatrix4fv(shader.u_proj, 1, false, projection, 0);");
		GLES20.glUniformMatrix4fv(shader.u_norm, 1, false, normal, 0);
		Util.checkGLError("GLES20.glUniformMatrix4fv(shader.u_norm, 1, false, normal, 0);");
	}
}
