package edu.uchicago.cs.gerber;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import edu.uchicago.cs.gerber.db.TodosDataSource;
import edu.uchicago.cs.gerber.model.Todo;

public class Main extends ListActivity {
	public static final String TODO_KEY = "result";

	private TodosDataSource tdsSource;

	EditText edtTitle, edtDetail;

	public static final int REQ_INSERT = 100;
	public static final int REQ_DELETE = 200;
	public static final int REQ_UPDATE = 300;

	public static final int CON_MENU_DEL = 1001;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);


	}

	

}