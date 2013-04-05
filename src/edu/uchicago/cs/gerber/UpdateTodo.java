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


public class UpdateTodo extends Activity implements OnClickListener {

	Button btnUpdate, btnDelete;
	EditText edtTitle, edtDetail;

	Todo tdo;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.update);
		edtTitle = (EditText) findViewById(R.id.edtTitle);
		edtDetail = (EditText) findViewById(R.id.edtDetail);

		btnUpdate = (Button) findViewById(R.id.btnUpdate);
		btnUpdate.setOnClickListener(this);

		btnDelete = (Button) findViewById(R.id.btnDelete);
		btnDelete.setOnClickListener(this);

		// get the intent that was passed into this Activity
		Intent itn = getIntent();
		// extract the Todo that was put into the bundle of that intent
		tdo = (Todo) itn.getSerializableExtra(Main.TODO_KEY);

		edtTitle.setText(tdo.getTitle());
		edtDetail.setText(tdo.getDetail());

	}

	// ################################################
	// used for ActionBar
	// ################################################
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.actbar_update, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.delete:
			delTodo();
			break;

		case R.id.update:
			updateTodo();
			break;

		}

		return super.onOptionsItemSelected(item);
	}

	// ################################################
	// used for buttons "delete" and "update"
	// ################################################
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btnDelete:
			delTodo();
			break;

		case R.id.btnUpdate:
			updateTodo();
			break;

		default:
			break;
		}

	}

	private void delTodo() {

		// pass the original Todo back with a request to delete it.
		Intent itnReturn = new Intent();
		itnReturn.putExtra(Main.TODO_KEY, tdo);
		setResult(Main.REQ_DELETE, itnReturn);
		finish();

	}

	private void updateTodo() {
		// pass the updated Todo back with a request to update it.
		Intent itnReturn = new Intent();
		// create/assign a new Todo based on any updated title or detail data
		tdo = new Todo(tdo.getId(), edtTitle.getText().toString(), edtDetail
				.getText().toString());
		itnReturn.putExtra(Main.TODO_KEY, tdo);
		setResult(Main.REQ_UPDATE, itnReturn);
		finish();

	}

}