package cz.kofron.leangine;
import android.content.*;
import java.io.*;

public class Util
{
	public static InputStream getFile(Context context, String path) throws IOException {
		return context.getAssets().open(path);
	}

	public static String loadFile(InputStream is) {
		String res = "";
		try
		{
			InputStreamReader isr = new InputStreamReader(is);

			int c;
			while((c = isr.read()) != -1)
			{
				res += (char) c;
			}

			isr.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			is.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return res;
	}
}
