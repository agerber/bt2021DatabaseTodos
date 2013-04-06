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

public class NewTodo extends Activity  {

	Button btnAdd;
	EditText edtTitle, edtDetail;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insert);


	}



}