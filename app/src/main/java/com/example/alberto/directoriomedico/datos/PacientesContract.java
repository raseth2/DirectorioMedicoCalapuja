package com.example.alberto.directoriomedico.datos;

import android.provider.BaseColumns;

/**
 * Created by AEPIS01 on 24/10/2017.
 */

public class PacientesContract  {
public static abstract class PacienteEntry implements BaseColumns {
    public static final String TABLE_NAME = "pacientes";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String TRATAMIENTO = "tratamiento";
    public static final String PHONE_NUMBER = "phoneNumber";
    public static final String AVATAR_URI = "avatarUri";
    public static final String BIO = "bio";
    }
}
