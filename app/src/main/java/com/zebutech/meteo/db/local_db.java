package com.zebutech.meteo.db;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import com.zebutech.meteo.model.VilleModel;
import android.database.Cursor;

public class local_db extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "meteo.db";
    public static final String TABLE_NAME = "liste_ville";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOM = "nom_ville";
    public static final String COLUMN_METEO = "meteo";
    public static final String COLUMN_TEMPERATURE = "temperature";
    public static final String COLUMN_VITESSE = "vitesse_du_vent";
    public static final String COLUMN_ICON = "icon";
    public static final String COLUMN_DATE = "date_a_jour";
    public static final String COLUMN_HEURE = "heure_a_jour";
    public static final String COLUMN_LON = "longitude";
    public static final String COLUMN_LAT = "latitude";

    Context context;
    
    public local_db(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ////CREATION DE LA TABLE///
        String SQL_CREATE = "CREATE TABLE " + TABLE_NAME +
            " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NOM + " TEXT, " +
            COLUMN_METEO + " TEXT, " +
            COLUMN_TEMPERATURE + " TEXT, " +
            COLUMN_VITESSE + " TEXT, " +
            COLUMN_ICON + " TEXT, " +
            COLUMN_LON + " TEXT, " +
            COLUMN_LAT + " TEXT, " +
            COLUMN_DATE + " DATE, " +
            COLUMN_HEURE + " TIME );";

        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DELETE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(SQL_DELETE);
        onCreate(db);
    }
    ////Ajout des données par le Model VilleModel
    public long insertData(VilleModel ville) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOM, ville.getNom());
        values.put(COLUMN_METEO, ville.getMeteo());
        values.put(COLUMN_TEMPERATURE, ville.getTemp());
        values.put(COLUMN_VITESSE, ville.getVitesse());
        values.put(COLUMN_ICON, ville.getIcon());
        values.put(COLUMN_LON, ville.getLongitude());
        values.put(COLUMN_LAT, ville.getLatitude());
        values.put(COLUMN_DATE, ville.getDate());
        values.put(COLUMN_HEURE, ville.getHeure());
        return db.insert(TABLE_NAME, null, values);
    }
    ///Récuperation des données par id
    public VilleModel getDataById(String id){
        VilleModel v_model = new VilleModel();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=\""+id+"\"";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        v_model.setNom(c.getString(1));
        v_model.setMeteo(c.getString(2));
        v_model.setTemp(c.getString(3));
        v_model.setVitesse(c.getString(4));
        v_model.setIcon(c.getString(5));
        v_model.setLongitude(c.getString(6));
        v_model.setLatitude(c.getString(7));
        v_model.setDate(c.getString(8));
        v_model.setHeure(c.getString(9));
        return v_model;
    }
    ///Récuperation de la dernière id
    public String getLastId(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY "+COLUMN_ID+" DESC LIMIT 1";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }
    /////Supprimer par ID
    public long deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }
    ///Récupation de données avec le limite 10
    public Cursor readData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME+ " ORDER BY "+COLUMN_ID+" DESC LIMIT 10";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
    ///Tous supprimer
    public void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}

