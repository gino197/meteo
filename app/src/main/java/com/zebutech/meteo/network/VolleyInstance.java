package com.zebutech.meteo.network;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.widget.Toast;
import android.content.Context;
import android.util.Log;
import android.app.Activity;
import android.widget.TextView;
import com.zebutech.meteo.model.VilleModel;
import com.zebutech.meteo.utils.DateNow;
import com.zebutech.meteo.db.local_db;
import com.zebutech.meteo.utils.DialogAlert;
import android.content.Intent;
import com.zebutech.meteo.activities.ResultatActivity;
import android.app.ProgressDialog;
import com.zebutech.meteo.R;
/**

    RECUPERATION DES DONNEES DEPUIS UN URL ET
    INSERTION DANS UN BASE DE DONNEES LOCALE

 */
public class VolleyInstance
{
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private String url = "";
    private Context context;
    private String TAG = "VolleyRequest";
    private local_db db;
    private ProgressDialog dialog;
    
    public VolleyInstance(Context ctx){
        this.context = ctx;
        ///initialisation du DB
        this.db = new local_db(context);
    }
    
    public void sendAndRequestResponse(String query, ProgressDialog dlg){
        final String q = query;
        url = context.getString(R.string.api_url)+q+"&appid="+context.getString(R.string.appid)+"&units=metric";
        dialog = dlg;
        //Initialisation de RequestQueue
        mRequestQueue = Volley.newRequestQueue(context);
        //Initialisation String Request
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(response.indexOf("city not found") == -1){
                        VilleModel ville = new VilleModel();
                        ville.setNom(q);
                        ville.setMeteo(getData("description", response));
                        ville.setTemp(getData("temp", response)+"° C");
                        ville.setVitesse(getData("speed", response));
                        ville.setIcon(getData("icon",response));
                        ville.setLongitude(getData("lon",response));
                        ville.setLatitude(getData("lat",response));
                        ville.setDate(new DateNow().getDate());
                        ville.setHeure(new DateNow().getHeure());
                        ///insertion de toutes les données récuperées dans une base de données
                        long insert = db.insertData(ville);
                        if(insert == -1){
                            Toast.makeText(context, "Erreur d'insertion..", Toast.LENGTH_LONG).show();                    
                        }
                        else{
                            dialog.hide();
                            ///passe à l'ecran 2
                            Intent intent = new Intent(context, ResultatActivity.class);
                            intent.putExtra("id", db.getLastId());
                            context.startActivity(intent);
                            Activity activity = (Activity) context;
                            activity.finish();
                        }
                    }
                    else{
                        ///si la ville est introuvable
                        new DialogAlert(context, "Ville introuvable", "Vérifiez bien le nom de la ville!");                      
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    dialog.hide();
                    if(error.toString().indexOf("ServerError") != -1){
                        ///si la ville est introuvable
                        new DialogAlert(context, "Ville introuvable", "Vérifiez bien le nom de la ville!");      
                    }
                    else{
                        ///si le serveur est introuvable
                        new DialogAlert(context, "Connexion perdue", "Vérifiez votre connextion internet!");     
                    }          
                    Log.i(TAG,"Error :" + error.toString());
                }
            });

        mRequestQueue.add(mStringRequest);
    }
    public String getData(String cles, String data_get){
        ///Récuperation du donnée par cles dans le fichier JSON
        String data = "";
        int pos = data_get.indexOf("\""+cles+"\"");
        pos = pos+cles.length()+2;
        data = data_get.substring(pos, data_get.length()-1);
        ///Configuration du format de données
        data = data.substring(1, data.indexOf(","));
        data = data.replace("\"","");
        data = data.replace("}","");
        data = data.replace("]","");
        return data;
    }
}
