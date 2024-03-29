package com.vijaya.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.vijaya.sqlite.databinding.ActivityEmployerBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EmployerActivity extends AppCompatActivity {

    private static final String TAG = "EmployerActivity";
    private ActivityEmployerBinding binding;
    private String nameCurrent, descCurrent;
    private int currentRowId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employer);

        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToDB();
            }
        });
        binding.updateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                updateDB();
            }
        });
        binding.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteFromDB();
            }
        });

        binding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readFromDB();
            }
        });
    }

    private void updateDB() {

        SQLiteDatabase database = new SampleDBSQLiteHelper(this).getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(SampleDBContract.Employer.COLUMN_NAME, binding.nameEditText.getText().toString());
        values.put(SampleDBContract.Employer.COLUMN_DESCRIPTION, binding.descEditText.getText().toString());

        String whereClause = SampleDBContract.Employer.COLUMN_NAME + " like ? AND " + SampleDBContract.Employer.COLUMN_DESCRIPTION+ " like ?";
        String[] whereArgs = {"%" + nameCurrent + "%", "%" + descCurrent + "%"};

        database.update(SampleDBContract.Employer.TABLE_NAME,values,whereClause,whereArgs);
        Toast.makeText(this, "Updated " + nameCurrent, Toast.LENGTH_LONG).show();
        nameCurrent = "";
        descCurrent = "";
        readFromDB();

    }
    private void deleteFromDB() {

        Toast.makeText(this, "Deleted" , Toast.LENGTH_LONG).show();
        readFromDB();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SQLiteDatabase database = new SampleDBSQLiteHelper(this).getReadableDatabase();
        String name = binding.nameEditText.getText().toString();
        String desc = binding.descEditText.getText().toString();
        String whereClause = SampleDBContract.Employer.COLUMN_NAME + " like ? AND " + SampleDBContract.Employer.COLUMN_DESCRIPTION + " like ?";
        String[] whereArgs = {"%" + name + "%", "%" + desc + "%"};
        database.delete(SampleDBContract.Employer.TABLE_NAME, whereClause, whereArgs);
        binding.nameEditText.setText("");
        binding.descEditText.setText("");
        readFromDB();



    }

    private void saveToDB() {
        nameCurrent = binding.nameEditText.getText().toString();
        descCurrent = binding.descEditText.getText().toString();
        SQLiteDatabase database = new SampleDBSQLiteHelper(this).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SampleDBContract.Employer.COLUMN_NAME, binding.nameEditText.getText().toString());
        values.put(SampleDBContract.Employer.COLUMN_DESCRIPTION, binding.descEditText.getText().toString());

        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime((new SimpleDateFormat("dd/MM/yyyy")).parse(
                    binding.foundedEditText.getText().toString()));
            long date = calendar.getTimeInMillis();
            values.put(SampleDBContract.Employer.COLUMN_FOUNDED_DATE, date);
        } catch (Exception e) {
            Log.e(TAG, "Error", e);
            Toast.makeText(this, "Date is in the wrong format", Toast.LENGTH_LONG).show();
            return;
        }
        long newRowId = database.insert(SampleDBContract.Employer.TABLE_NAME, null, values);

        Toast.makeText(this, "The new Row Id is " + newRowId, Toast.LENGTH_LONG).show();
    }

    private void readFromDB() {
        String name = binding.nameEditText.getText().toString();
        String desc = binding.descEditText.getText().toString();
        long date = 0;

        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime((new SimpleDateFormat("dd/MM/yyyy")).parse(
                    binding.foundedEditText.getText().toString()));
            date = calendar.getTimeInMillis();
        } catch (Exception e) {
        }

        SQLiteDatabase database = new SampleDBSQLiteHelper(this).getReadableDatabase();

        String[] projection = {
                SampleDBContract.Employer._ID,
                SampleDBContract.Employer.COLUMN_NAME,
                SampleDBContract.Employer.COLUMN_DESCRIPTION,
                SampleDBContract.Employer.COLUMN_FOUNDED_DATE
        };

        String selection =
                SampleDBContract.Employer.COLUMN_NAME + " like ? and " +
                        SampleDBContract.Employer.COLUMN_FOUNDED_DATE + " > ? and " +
                        SampleDBContract.Employer.COLUMN_DESCRIPTION + " like ?";

        String[] selectionArgs = {"%" + name + "%", date + "", "%" + desc + "%"};

        Cursor cursor = database.query(
                SampleDBContract.Employer.TABLE_NAME,     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                             // don't group the rows
                null,                              // don't filter by row groups
                null                              // don't sort
        );

        binding.recycleView.setAdapter(new SampleRecyclerViewCursorAdapter(this, cursor));
        nameCurrent = name;
        descCurrent = desc;
    }
}