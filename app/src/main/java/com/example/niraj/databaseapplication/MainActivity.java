package com.example.niraj.databaseapplication;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editTextname,editTextsurname,editTextmarks,editTextid;
    Button buttonadd;
    Button buttonshow;
    Button buttonupdate;
    Button buttondelete;
    Button buttonclear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb=new DatabaseHelper(this);

        editTextname=(EditText) findViewById(R.id.editText_name);
        editTextsurname=(EditText) findViewById(R.id.editText_surname);
        editTextmarks=(EditText) findViewById(R.id.editText_marks);
        editTextid=(EditText) findViewById(R.id.editText_id);
        buttonadd=(Button)findViewById(R.id.btn_Add);
        buttonshow=(Button) findViewById(R.id.btn_Show);
        buttonupdate=(Button) findViewById(R.id.btn_Update);
        buttondelete=(Button) findViewById(R.id.btn_delete);
        buttonclear=(Button) findViewById(R.id.btn_clear);
        addData();
        showData();
        updateData();
        deleteData();
        clearData();
    }

    public void addData(){
        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               boolean isInserted= myDb.insertData(editTextname.getText().toString(),editTextsurname.getText().toString(),
                        editTextmarks.getText().toString());
                if(isInserted=true){
                    Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                }else
                    Toast.makeText(MainActivity.this,"Data Not Inserted",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void showData(){
        buttonshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Cursor cursor= myDb.getAllData();
                if(cursor.getCount()== 0){
                    showMessage("Error", "Nothing Found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (cursor.moveToNext()){
                    buffer.append("id :"+cursor.getString(0)+"\n");
                    buffer.append("name :"+cursor.getString(1)+"\n");
                    buffer.append("surname :"+cursor.getString(2)+"\n");
                    buffer.append("marks :"+cursor.getString(3)+"\n\n");
                }
                showMessage("Data",buffer.toString());
            }
        });
    }

    public void showMessage(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void updateData(){
        buttonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdate=myDb.updateData(editTextid.getText().toString(),editTextname.getText()
                .toString(),editTextsurname.getText().toString(),editTextmarks.getText().toString());
                if (isUpdate==true){
                    Toast.makeText(MainActivity.this,"Data updated",Toast.LENGTH_LONG).show();
                }else
                    Toast.makeText(MainActivity.this,"Data not updated",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void deleteData(){
        buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isDelete=myDb.deleteData(editTextid.getText().toString(),editTextname.getText().toString(),
                        editTextsurname.getText().toString(),editTextmarks.getText().toString());
                if (isDelete == true){
                    Toast.makeText(MainActivity.this,"Data deleted",Toast.LENGTH_LONG).show();
                }else
                    Toast.makeText(MainActivity.this,"Data deleted",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void clearData(){
        buttonclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextid.setText("");
                editTextname.setText("");
                editTextsurname.setText("");
                editTextmarks.setText("");
            }
        });
    }
}
