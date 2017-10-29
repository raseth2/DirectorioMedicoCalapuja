package com.example.alberto.directoriomedico.Paciente;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alberto.directoriomedico.R;
import com.example.alberto.directoriomedico.addeditmedico.AddEditMedicoActivity;
import com.example.alberto.directoriomedico.datos.MedicosContract;
import com.example.alberto.directoriomedico.datos.PacientesContract;
import com.example.alberto.directoriomedico.datos.PacientesCursorAdapter;
import com.example.alberto.directoriomedico.datos.PacientesDbHelper;
import com.example.alberto.directoriomedico.medicodetail.MedicoDetailActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class PacienteFragment extends Fragment {
    ListView mMedicosList;
    FloatingActionButton mAddButton;
    PacientesCursorAdapter mMedicosAdapter;
    PacientesDbHelper mMedicosDBHelper;

    public static final int REQUEST_UPDATE_DELETE_PACIENTE = 2;




    public PacienteFragment() {
        // Required empty public constructor
    }

    public static PacienteFragment newInstances(){return new PacienteFragment();}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_paciente, container, false);


        mMedicosList = (ListView)root.findViewById(R.id.pacientes_list);
        mMedicosAdapter = new PacientesCursorAdapter(getActivity(),null,0);
        mAddButton = (FloatingActionButton)getActivity().findViewById(R.id.fab);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddScreen();
            }
        });

        mMedicosList.setAdapter(mMedicosAdapter);

        mMedicosDBHelper = new PacientesDbHelper(getActivity());


        mMedicosList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor currentItem = (Cursor)mMedicosAdapter.getItem(position);
                String currentMedicoId = currentItem.getString(currentItem.getColumnIndex(PacientesContract.PacienteEntry.ID));
                showDetailScreen(currentMedicoId);
            }
        });



        loadPacientes();

        return root;
    }
    private void showAddScreen(){
        Intent intent = new Intent(getActivity(),AddEditMedicoActivity.class);
        startActivityForResult(intent,AddEditMedicoActivity.REQUEST_ADD_MEDICO);
    }

    private void showDetailScreen(String medicoId){
        Intent intent = new Intent(getActivity(), MedicoDetailActivity.class);
        intent.putExtra(PacienteActivity.EXTRA_MEDICO_I,medicoId);
        startActivityForResult(intent,REQUEST_UPDATE_DELETE_PACIENTE);
    }

    private void loadPacientes(){
        new PacientesLoadTask().execute();
    }

    private class PacientesLoadTask extends AsyncTask<Void,Void,Cursor> {


        @Override
        protected Cursor doInBackground(Void... params) {
            return mMedicosDBHelper.getAllPacientes();
        }

        @Override
        protected void onPostExecute(Cursor c){
            if(c!=null && c.getCount()>0){
                mMedicosAdapter.swapCursor(c);
            }else{

            }
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(Activity.RESULT_OK == resultCode){
            switch(requestCode){
                case AddEditMedicoActivity.REQUEST_ADD_MEDICO:
                    showSuccessfullSavedMessage();
                    loadPacientes();
                    break;
                case REQUEST_UPDATE_DELETE_PACIENTE:
                    loadPacientes();
                    break;

            }
        }
    }

    private void showSuccessfullSavedMessage(){
        Toast.makeText(getActivity(),"Médico guardado correctamente",Toast.LENGTH_SHORT).show();
    }

}
