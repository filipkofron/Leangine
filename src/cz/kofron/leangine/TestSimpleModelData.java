package cz.kofron.leangine;

public class TestSimpleModelData extends SimpleModelData
{
	public static float [] quadNormals = {
		// face a
		0.0f, 0.0f, 1.0f,
		0.0f, 0.0f, 1.0f,
		0.0f, 0.0f, 1.0f,

		// face b
		0.0f, 0.0f, 1.0f,
		0.0f, 0.0f, 1.0f,
		0.0f, 0.0f, 1.0f
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
	
	public TestSimpleModelData()
	{
		super(new VertexBuffer(quadVertices), new NormalBuffer(quadNormals));
	}
}
