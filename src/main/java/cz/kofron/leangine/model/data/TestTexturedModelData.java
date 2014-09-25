package cz.kofron.leangine.model.data;

import cz.kofron.leangine.texture.TextureManager;
import cz.kofron.leangine.buffer.NormalBuffer;
import cz.kofron.leangine.buffer.UVBuffer;
import cz.kofron.leangine.buffer.VertexBuffer;

public class TestTexturedModelData extends TexturedModelData
{
	public static float [] quadNormals = {
		// face a
		0.0f, 0.0f, 1.0f,
		0.0f, 0.0f, 1.0f,
		0.0f, 0.0f, 1.0f,

		// face b
		0.0f, 0.0f, 1.0f,
		0.0f, 0.0f, 1.0f,
		0.0f, 0.0f, 1.0f,
	};

	public static float [] quadVertices = {
		// face a
		-0.3f, -0.3f, 0.0f,
		0.3f, -0.3f, 0.0f,
		-0.3f, 0.3f, 0.0f,

		// face b
		0.3f, -0.3f, 0.0f,
		0.3f, 0.3f, 0.0f,
		-0.3f, 0.3f, 0.0f,
	};

	public static float [] uvCoords = {
		// face a
		0.0f, 0.0f,
		1.0f, 0.0f,
		0.0f, 1.0f,

		// face b
		1.0f, 0.0f,
		1.0f, 1.0f,
		0.0f, 1.0f,
	};
	
	public static int loadTex()
	{
		return TextureManager.getTextureFromAssets("texture.jpg");
	}

	public TestTexturedModelData()
	{
		super(new VertexBuffer(quadVertices),
			  new NormalBuffer(quadNormals),
			  new UVBuffer(uvCoords), loadTex());
	}
}
