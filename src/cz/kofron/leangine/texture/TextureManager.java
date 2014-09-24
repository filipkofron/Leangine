package cz.kofron.leangine.texture;
import java.util.*;

public class TextureManager
{
	private static HashMap<Integer, Integer> texturesId = new HashMap<>();
	private static HashMap<String, Integer> texturesPath = new HashMap<>();
	
	public static int getTextureFromResource(int resId)
	{
		if(texturesId.containsKey(resId))
		{
			return texturesId.get(resId);
		}

		int handle = TextureHelper.loadTexture(resId);
		texturesId.put(resId, handle);
		
		return handle;
	}
	
	public static int getTextureFromAssets(String path)
	{
		if(texturesPath.containsKey(path))
		{
			return texturesPath.get(path);
		}

		int handle = TextureHelper.loadTexture(path);
		texturesPath.put(path, handle);

		return handle;
	}
	
}
