package edu.uchicago.cs.gerber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import edu.uchicago.cs.gerber.db.TodosDataSource;
import edu.uchicago.cs.gerber.model.Todo;

public class NewTodo extends Activity implements OnClickListener {

	Button btnAdd;
	EditText edtTitle, edtDetail;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insert);
		edtTitle = (EditText) findViewById(R.id.edtTitle);
		edtDetail = (EditText) findViewById(R.id.edtDetail);

		btnAdd = (Button) findViewById(R.id.btnUpdate);
		btnAdd.setOnClickListener(this);

	}

	// ################################################
	// used for ActionBar
	// ################################################
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.actbar_insert, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.add:
			addTodo();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	//################################################
	// used for the button "add"
	//################################################
	@Override
	public void onClick(View v) {
		addTodo();

	}

	private void addTodo() {

		Todo tdoAdd = null;
		//Todo has a 2-arg construtor which leaves id as 0
		tdoAdd = new Todo(edtTitle.getText().toString(), edtDetail.getText()
				.toString());
		Intent itnReturn = new Intent();
		//add a serializable object (a Todo) to the intent
		itnReturn.putExtra(Main.TODO_KEY, tdoAdd);
		//set result to return from startActivityForResult (the itnReturn contains the Todo inside its bundle)
		setResult(Main.REQ_INSERT, itnReturn);
		//destroy this activity
		finish();

	}

}