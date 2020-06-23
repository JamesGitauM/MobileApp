package com.example.labpro2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_CLLENT_REQUEST=1;
    private ClientViewModel clientViewModel;
    private DrawerLayout drawer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //navigation drawer
        drawer=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this, drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        ///navigation drawer end
        FloatingActionButton btnAddClient= findViewById(R.id.button_add_client);
        btnAddClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AddClientActivity.class);
                startActivityForResult(intent,ADD_CLLENT_REQUEST);
            }
        });

        RecyclerView recyclerView=findViewById(R.id.recycler_viewc);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        ClientAdapter adapter=new ClientAdapter();
        recyclerView.setAdapter(adapter);

        clientViewModel= ViewModelProviders.of(this).get(ClientViewModel.class);
        clientViewModel.getAllClients().observe(this, new Observer<List<Client>>() {
            @Override
            public void onChanged(List<Client> clients) {
                //to update recycler view
               adapter.setClients(clients);
                //Toast.makeText(getApplicationContext(),"samurai on changed",Toast.LENGTH_SHORT).show();
            }
        });
    }
// navigation button action**********************************
    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){

            drawer.closeDrawer(GravityCompat.START);

        }else{

        super.onBackPressed();
        }
    }
//***********************************************************
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==ADD_CLLENT_REQUEST && resultCode==RESULT_OK){
            String clientname=data.getStringExtra(AddClientActivity.EXTRA_CLNAME);
            String clientcode=data.getStringExtra(AddClientActivity.EXTRA_CLCODE);
            String clientemailaddress=data.getStringExtra(AddClientActivity.EXTRA_CLEMAIL);
            String clientPhysicaladress=data.getStringExtra(AddClientActivity.EXTRA_CLPHYSICALADRESS);
            Client client =new Client(clientname,clientcode,clientemailaddress,clientPhysicaladress);
            clientViewModel.insert(client);
            Toast.makeText(this,"Client Added Successfully",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Client not saved!",Toast.LENGTH_LONG).show();
        }
    }
}
