package edu.uchicago.cs.gerber.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import edu.uchicago.cs.gerber.model.Todo;

public class TodosDataSource {

	private static String TAG = "Todos";
	// Database fields
	private SQLiteDatabase sqdDatabase;
	private TodosSQLiteHelper myhHelper;
	private String[] strColumns = { TodosSQLiteHelper.COLUMN_ID,
			TodosSQLiteHelper.COLUMN_COMMENT, TodosSQLiteHelper.COLUMN_DETAIL };

	public TodosDataSource(Context context) {
		myhHelper = new TodosSQLiteHelper(context);
	}

	public void open() throws SQLException {
		sqdDatabase = myhHelper.getWritableDatabase();
	}

	public void close() {
		myhHelper.close();
	}
	
	public boolean isOpen(){
		return (sqdDatabase != null);
	}

	// basic CRUD operations
	// CREATE
	public Todo createTodo(String strTitle, String detail) {
		ContentValues values = new ContentValues();
		values.put(TodosSQLiteHelper.COLUMN_COMMENT, strTitle);
		values.put(TodosSQLiteHelper.COLUMN_DETAIL, detail);
		long insertId = sqdDatabase.insert(TodosSQLiteHelper.TABLE_COMMENTS, null,
				values);
		Cursor cursor = sqdDatabase.query(TodosSQLiteHelper.TABLE_COMMENTS,
				strColumns, TodosSQLiteHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		Todo newTodo = cursorToTodo(cursor);
		cursor.close();
		return newTodo;
	}

	// READ
	public List<Todo> getAllTodos() {
		List<Todo> Todos = new ArrayList<Todo>();

		Cursor cursor = sqdDatabase.query(TodosSQLiteHelper.TABLE_COMMENTS,
				strColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Todo Todo = cursorToTodo(cursor);
			Todos.add(Todo);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return Todos;
	}

	// get one Todo
	// UPDATE
	public int updateTodo(Todo com) {

		ContentValues cv = new ContentValues();

		cv.put(TodosSQLiteHelper.COLUMN_COMMENT, com.getTitle());
		cv.put(TodosSQLiteHelper.COLUMN_DETAIL, com.getDetail());

		Log.i(TAG, "Todo updated with id: " + com.getId());

		return sqdDatabase.update(TodosSQLiteHelper.TABLE_COMMENTS, cv,
				TodosSQLiteHelper.COLUMN_ID + " =?",
				new String[] { String.valueOf(com.getId()) });

	}

	// DELETE
	public void deleteTodo(Todo Todo) {
		long id = Todo.getId();

		sqdDatabase.delete(TodosSQLiteHelper.TABLE_COMMENTS,
				TodosSQLiteHelper.COLUMN_ID + " = " + id, null);
		Log.i(TAG, "Todo deleted with id: " + id);
	}
	
	//overriden to tak
	public void deleteTodo(long lId) {
		

		sqdDatabase.delete(TodosSQLiteHelper.TABLE_COMMENTS,
				TodosSQLiteHelper.COLUMN_ID + " = " + lId, null);
		Log.i(TAG, "Todo deleted with id: " + lId);
	}

	private Todo cursorToTodo(Cursor cursor) {
		Todo Todo = new Todo();
		Todo.setId(cursor.getLong(0));
		Todo.setTitle(cursor.getString(1));
		Todo.setDetail(cursor.getString(2));
		return Todo;
	}
}