package com.example.labpro2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddClientActivity extends AppCompatActivity {
    public static final String  EXTRA_CLNAME=
            "com.example.labpro2.EXTRA_CLNAME";
    public static final String  EXTRA_CLCODE=
            "com.example.labpro2.EXTRA_CLCODE";
    public static final String  EXTRA_CLEMAIL=
            "com.example.labpro2.EXTRA_CLEMAIL";
    public static final String  EXTRA_CLPHYSICALADRESS=
            "com.example.labpro2.EXTRA_CLPHYSICALADRESS";

    private EditText editTextCname;
    private EditText editTextCcode;
    private EditText editTextEmail;
    private EditText editTextPhysicalAdress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        editTextCname = findViewById(R.id.editetxtcname);
        editTextCcode = findViewById(R.id.editetxtccode);
        editTextEmail = findViewById(R.id.editetxtemailaddress);
        editTextPhysicalAdress = findViewById(R.id.editetxtpysicaladress);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
        setTitle("Add Client");
    }
    private void saveClient(){
        String clientname=editTextCname.getText().toString();
        String clientcode=editTextCcode.getText().toString();
        String clientemailaddress=editTextEmail.getText().toString();
        String clientPhysicaladress=editTextPhysicalAdress.getText().toString();
        if (clientname.trim().isEmpty()||clientcode.trim().isEmpty()||clientemailaddress.trim().isEmpty()){
            Toast.makeText(this,"Please Provide some input",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data=new Intent();
        data.putExtra(EXTRA_CLNAME,clientname);
        data.putExtra(EXTRA_CLCODE,clientcode);
        data.putExtra(EXTRA_CLEMAIL,clientemailaddress);
        data.putExtra(EXTRA_CLPHYSICALADRESS,clientPhysicaladress);
        // if saving was succesful
        setResult(RESULT_OK,data);
        finish();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_client_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_client:
                saveClient();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }


    }
}
