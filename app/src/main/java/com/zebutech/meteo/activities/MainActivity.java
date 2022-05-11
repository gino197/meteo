package com.zebutech.meteo.activities;

import android.app.Activity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.zebutech.meteo.R;
import android.widget.Toast;
import com.zebutech.meteo.network.VolleyInstance;
import android.widget.TextView;
import com.zebutech.meteo.utils.DateNow;
import android.widget.ListView;
import com.zebutech.meteo.adapter.VilleListAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.view.View;
import com.zebutech.meteo.db.local_db;
import android.database.Cursor;
import android.widget.EditText;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.text.TextUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.app.ProgressDialog;
import android.content.Intent;
import android.app.AlertDialog;
import com.zebutech.meteo.utils.DialogAlert;
import android.content.Context;
import com.zebutech.meteo.network.ImageFromUrl;



public class MainActivity extends AppCompatActivity {

    private VolleyInstance volleyInstance;
    private ListView resultat;
    private EditText editText_query;
    private ImageView btn_search, logo;
    private String[] listVille;
    private String[] id_ville, heure_date;
    private local_db db;
    ImageView image;
    ProgressDialog dialog;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  
        ////initialisation des objets View (XML) ///
        initView();
        ////Ajouter les données dans un ListView///
        setData();
      }
    
     
    
    private void initView(){
        ///ListView pour l'histotique/////
        resultat = findViewById(R.id.resultat);
        ///EditText Recherche...//////
        editText_query = findViewById(R.id.editText_query);
        ///Button rechecher avec icon//////
        btn_search = findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new OnClick());
        ///ImageView Logo///
        logo = findViewById(R.id.img_logo);
        image = findViewById(R.id.img_logo);
    }
    
   
    private void setData(){  
        ///Initialisation du base de données Local///
        db = new local_db(MainActivity.this);
        ///Recuperation de données (Historique Ville)///
        Cursor c = db.readData();
        ///Initialisation du données par nom///
        listVille = new String[c.getCount()];
        ///Initialisation du données par id///
        id_ville = new String[c.getCount()];
        ///Initialisation du données par date et heure///
        heure_date = new String[c.getCount()];
        while(c.moveToNext()){
            ///Recuperation des données par nom
            listVille[c.getPosition()] = c.getString(1);
            ///Recuperation des données par id
            id_ville[c.getPosition()] = c.getString(0);
            ///Recuperation des données par date et heure
            heure_date[c.getPosition()] = c.getString(9)+" "+new DateNow().setFormat(c.getString(8));
        }
        ///Ajout de toutes les données recuperées dans un listView
        VilleListAdapter adapter = new VilleListAdapter(MainActivity.this, listVille, heure_date); 
        resultat.setAdapter(adapter); 
        resultat.setOnItemClickListener(new OnClickItemAdapter()); 
    }   
    
    class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View p1){
            
            final Animation anim = AnimationUtils.loadAnimation(MainActivity.this,R.anim.btn_click);
            p1.startAnimation(anim);
            
            String query = editText_query.getText().toString();
            if(TextUtils.isEmpty(query)){
                editText_query.setError("Vide");
            }
            else{
                if(query.indexOf("/") != -1 || query.indexOf("&") != -1){
                    editText_query.setError("Ville invalide");
                }
                else{
                dialog = ProgressDialog.show(MainActivity.this, "", "Recherche...", true);
                volleyInstance = new VolleyInstance(MainActivity.this);
                volleyInstance.sendAndRequestResponse(query, dialog);     
                }
            }
        }     
      }
      
    class OnClickItemAdapter implements AdapterView.OnItemClickListener{
        
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            Intent intent = new Intent(MainActivity.this, ResultatActivity.class);
            intent.putExtra("id", id_ville[position]);
            startActivity(intent);
            finish();
        }         
      }
      
    }
