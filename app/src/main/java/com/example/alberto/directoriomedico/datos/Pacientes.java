package com.example.alberto.directoriomedico.datos;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.UUID;

/**
 * Created by AEPIS01 on 24/10/2017.
 */

public class Pacientes {
    private String id;
    private String name;
    private String tratamiento;
    private String phoneNumber;
    private String bio;
    private String avatarUri;
    public Pacientes( String name
            , String tratamiento, String phoneNumber
            , String bio, String avatarUri) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.tratamiento = tratamiento;
        this.phoneNumber = phoneNumber;
        this.bio = bio;
        this.avatarUri = avatarUri;
    }

    public Pacientes(Cursor cursor){
        id = cursor.getString(cursor.getColumnIndex
                (PacientesContract.PacienteEntry.ID));
        name = cursor.getString(cursor.getColumnIndex
                (PacientesContract.PacienteEntry.NAME));
        tratamiento = cursor.getString(cursor.getColumnIndex
                (PacientesContract.PacienteEntry.TRATAMIENTO));
        phoneNumber = cursor.getString(cursor.getColumnIndex
                (PacientesContract.PacienteEntry.PHONE_NUMBER));
        bio = cursor.getString(cursor.getColumnIndex
                (PacientesContract.PacienteEntry.BIO));
        avatarUri = cursor.getString(cursor.getColumnIndex
                (PacientesContract.PacienteEntry.AVATAR_URI));
    }

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();
        values.put(PacientesContract.PacienteEntry.ID,id);
        values.put(PacientesContract.PacienteEntry.NAME,name);
        values.put(PacientesContract.PacienteEntry.TRATAMIENTO
                ,tratamiento);
        values.put(PacientesContract.PacienteEntry.PHONE_NUMBER
                ,phoneNumber);
        values.put(PacientesContract.PacienteEntry.BIO,bio);
        values.put(PacientesContract.PacienteEntry.AVATAR_URI
                ,avatarUri);
        return values;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBio() {
        return bio;
    }

    public String getAvatarUri() {
        return avatarUri;
    }
}
