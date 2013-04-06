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


public class UpdateTodo extends Activity  {

	Button btnUpdate, btnDelete;
	EditText edtTitle, edtDetail;

	Todo tdo;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);



	}



}