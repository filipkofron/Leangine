package cz.kofron.leangine;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity
{
	public static MainActivity instance = null;
	private static GLView view = null;
	private LinearLayout layout = null;
	
	public static Leangine LEA = new Leangine();
	
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        
		view = new GLView(this);

		layout = new LinearLayout(this);
		layout.addView(view);

		instance = this;

		setContentView(layout);
    }
}
