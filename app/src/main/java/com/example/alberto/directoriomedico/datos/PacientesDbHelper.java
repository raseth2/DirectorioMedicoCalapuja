package com.example.alberto.directoriomedico.datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by AEPIS01 on 24/10/2017.
 */

public class PacientesDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION
            = 1;
    public static final String DATABASE_NAME
            = "Pacientes.db";
    public PacientesDbHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE "
                + PacientesContract.PacienteEntry.TABLE_NAME + "("
                + PacientesContract.PacienteEntry._ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + PacientesContract.PacienteEntry.ID
                + " TEXT NOT NULL,"
                + PacientesContract.PacienteEntry.NAME
                + " TEXT NOT NULL,"
                + PacientesContract.PacienteEntry.TRATAMIENTO
                + " TEXT NOT NULL,"
                + PacientesContract.PacienteEntry.PHONE_NUMBER
                + " TEXT NOT NULL,"
                + PacientesContract.PacienteEntry.BIO
                + " TEXT NOT NULL,"
                + PacientesContract.PacienteEntry.AVATAR_URI
                + " TEXT,"
                + "UNIQUE (" + PacientesContract.PacienteEntry.ID + "))"
        );
        mockData(sqLiteDatabase);
    }

    private void mockData(SQLiteDatabase sqLiteDatabase){
        mockPacientes(sqLiteDatabase,new Pacientes("Carlos Sanchez"
                ,"operdado de los ojos","300 200 1111",
                "Gran profesional con experiencia de 5 años " +
                        "en Servicio de Emergencia.","carlos_sanchez.jpg"));
        mockPacientes(sqLiteDatabase,new Pacientes("Gregory House"
                ,"Pierna Enyesada","300 200 2222",
                "Gran profesional con experiencia de 15 años " +
                        "en Servicio de Hospitalización.","gregory_house.jpg"));
        mockPacientes(sqLiteDatabase,new Pacientes("Marina Acosta"
                ,"Médico Internista","300 200 3333",
                "Gran profesional con experiencia de 25 años " +
                        "en Servicio de Hospitalización.","marina_acosta.jpg"));
        mockPacientes(sqLiteDatabase,new Pacientes("Daniel Samper"
                ,"Médico Ginecólogo","300 200 4444",
                "Gran profesional con experiencia de 25 años " +
                        "en Servicio de Ginecología.","daniel_samper.jpg"));
        mockPacientes(sqLiteDatabase,new Pacientes("Lucia Aristizabal"
                ,"Médico Internista","300 200 5555",
                "Gran profesional con experiencia de 25 años " +
                        "en Servicio de Hospitalización.","lucia_aristizabal.jpg"));
        mockPacientes(sqLiteDatabase,new Pacientes("Olga Ortiz"
                ,"Médico Internista","300 200 6666",
                "Gran profesional con experiencia de 25 años " +
                        "en Servicio de Hospitalización.","olga_ortiz.jpg"));
        mockPacientes(sqLiteDatabase,new Pacientes("Pamela Briger"
                ,"Médico Internista","300 200 7777",
                "Gran profesional con experiencia de 25 años " +
                        "en Servicio de Hospitalización.","pamela_briger.jpg"));
        mockPacientes(sqLiteDatabase,new Pacientes("Rodrigo Benavidez"
                ,"Médico Internista","300 200 8888",
                "Gran profesional con experiencia de 25 años " +
                        "en Servicio de Hospitalización.","rodrigo_benavidez.jpg"));
        mockPacientes(sqLiteDatabase,new Pacientes("Tom Bonz"
                ,"Médico Internista","300 200 9999",
                "Gran profesional con experiencia de 25 años " +
                        "en Servicio de Hospitalización.","tom_bonz.jpg"));

    }

    public long mockPacientes(SQLiteDatabase db
            , Pacientes pacientes){
        return db.insert(PacientesContract.PacienteEntry.TABLE_NAME
                ,null
                ,pacientes.toContentValues());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public Cursor getAllPacientes(){
        return getReadableDatabase()
                .query(PacientesContract.PacienteEntry.TABLE_NAME
                        ,null // columnas
                        ,null // WHERE
                        ,null // valores WHERE
                        ,null // GROUP BY
                        ,null // HAVING
                        ,null); // OREDER BY
    }

    public Cursor getPacienteById(String pacienteId){
        return getReadableDatabase()
                .query(PacientesContract.PacienteEntry.TABLE_NAME
                        ,null
                        , PacientesContract.PacienteEntry.ID + " LIKE ?"
                        ,new String[] {pacienteId}
                        ,null
                        ,null
                        ,null);
    }

    public long savePaciente(Medicos pacientes){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                PacientesContract.PacienteEntry.TABLE_NAME
                ,null
                ,pacientes.toContentValues());
    }

    public int updatePaciente(Pacientes pacientes
            ,String pacienteId){
        return getWritableDatabase().update(
                PacientesContract.PacienteEntry.TABLE_NAME
                ,pacientes.toContentValues()
                , PacientesContract.PacienteEntry.ID+" LIKE ?"
                ,new String[]{pacienteId}
        );
    }

    public int deletePaciente(String pacienteId){
        return getWritableDatabase().delete(
                PacientesContract.PacienteEntry.TABLE_NAME
                , PacientesContract.PacienteEntry.ID+" LIKE ?"
                ,new String[]{pacienteId}
        );
    }
}
