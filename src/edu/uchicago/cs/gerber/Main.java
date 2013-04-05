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
		edtTitle = (EditText) findViewById(R.id.edtTitle);
		edtDetail = (EditText) findViewById(R.id.edtDetail);
		tdsSource = new TodosDataSource(this);
		registerForContextMenu(getListView());

	}

	private void refreshData() {

		List<Todo> values = tdsSource.getAllTodos();
		//will call the toString() method of Todo
		ArrayAdapter<Todo> adapter = new ArrayAdapter<Todo>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);

	}

	// ################################################
	// used for ActionBar
	// ################################################
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.actbar_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		//this will send us to the new NewTodo activity. Notice the call
		//to startActivityForResult with request key REQ_INSERT
		case R.id.insert:
			Intent intent = new Intent(Main.this, NewTodo.class);
			startActivityForResult(intent, REQ_INSERT);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	// ################################################
	// used for context menu
	// if you long-click an item you can delete it.
	// ################################################
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, CON_MENU_DEL, 0, "Delete Todo");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case CON_MENU_DEL:
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
					.getMenuInfo();
			Todo tdo = null;
			tdo = (Todo) getListAdapter().getItem((int) info.id);

			tdsSource.deleteTodo(tdo);
			refreshData();
			break;

		}
		return super.onContextItemSelected(item);

	}

	//################################################
	// on activty result. Once we get back data from the called activity
	//################################################
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		Todo tdoReturn = null;
		switch (resultCode) {
		case REQ_INSERT:

			tdoReturn = (Todo) data.getSerializableExtra(TODO_KEY);
			tdsSource.createTodo(tdoReturn.getTitle(), tdoReturn.getDetail());
			refreshData();
			break;

		case REQ_DELETE:

			tdoReturn = (Todo) data.getSerializableExtra(TODO_KEY);
			tdsSource.deleteTodo(tdoReturn);
			refreshData();
			break;

		case REQ_UPDATE:
			tdoReturn = (Todo) data.getSerializableExtra(TODO_KEY);
			tdsSource.updateTodo(tdoReturn);
			refreshData();
			Toast.makeText(Main.this, "update successful", Toast.LENGTH_SHORT)
					.show();
			break;
		default:
			//if there was an error then just refresh whatever data is in the db
			refreshData();
			break;
		}

	}

	//################################################
	// this responds to the click of ListActivity
	//################################################
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		//call the UpdateTodo activity
		Intent intent = new Intent(Main.this, UpdateTodo.class);
		Bundle bnd = new Bundle();
		Todo tdoPass = null;
		tdoPass = (Todo) getListAdapter().getItem(position);
		bnd.putSerializable(TODO_KEY, tdoPass);
		intent.putExtras(bnd);
		startActivityForResult(intent, REQ_UPDATE);
	}

	//################################################
	// manage the tdsSource resource with callbacks
	//################################################
	@Override
	protected void onResume() {
		super.onResume();
		tdsSource.open();
		refreshData();
		
	}

	@Override
	protected void onDestroy() {
		tdsSource.close();
		super.onDestroy();
	}

}