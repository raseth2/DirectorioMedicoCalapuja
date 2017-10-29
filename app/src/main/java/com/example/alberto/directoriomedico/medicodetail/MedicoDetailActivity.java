package com.example.alberto.directoriomedico.medicodetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.example.alberto.directoriomedico.MainActivity;
import com.example.alberto.directoriomedico.MedicosFragment;
import com.example.alberto.directoriomedico.Paciente.PacienteActivity;
import com.example.alberto.directoriomedico.Paciente.PacienteFragment;
import com.example.alberto.directoriomedico.R;

public class MedicoDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medico_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String id = getIntent().getStringExtra(MainActivity.EXTRA_MEDICO_ID);

        MedicoDetailFragment fragment = (MedicoDetailFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.medico_detail_container);

        if(fragment == null){
            fragment = MedicoDetailFragment.newInstance(id);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.medico_detail_container,fragment)
                    .commit();
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),PacienteActivity.class));

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_medico_detail,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}
