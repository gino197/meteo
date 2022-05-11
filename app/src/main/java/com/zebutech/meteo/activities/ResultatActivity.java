package com.zebutech.meteo.activities;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.zebutech.meteo.R;
import android.widget.TextView;
import com.zebutech.meteo.model.VilleModel;
import com.zebutech.meteo.db.local_db;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.content.Intent;
import android.widget.ImageView;
import com.zebutech.meteo.network.ImageFromUrl;

public class ResultatActivity extends AppCompatActivity{

    private TextView txtMeteo, txtTemp, txtVitesse, txtLon, txtLat, txtHeure, txtDate,txtVille;
    private String id;
    private VilleModel ville;
    private Button btn_retour;
    private local_db db;
    private ImageView icon;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);
        initData();
        initView();
        setData();
    }
    private void initData(){
        db = new local_db(ResultatActivity.this);
        id = getIntent().getExtras().getString("id");     
        ville = db.getDataById(id);
    }
    private void initView(){
        txtMeteo = get(R.id.txtMeteo);
        txtTemp = get(R.id.txtTemp);
        txtVitesse = get(R.id.txtVitesse);
        txtLon = get(R.id.txtLon);
        txtLat = get(R.id.txtLat);
        txtDate = get(R.id.txtDate);
        txtHeure = get(R.id.txtHeure);
        txtVille = get(R.id.txtVille);
        btn_retour = findViewById(R.id.btn_retour);
        btn_retour.setOnClickListener(new OnClick());
        icon = findViewById(R.id.icon);
    }
    private void setData(){
        txtMeteo.setText(ville.getMeteo());
        txtTemp.setText(ville.getTemp());
        txtVitesse.setText("Vitesse du vent: "+ville.getVitesse());
        txtLon.setText("Longitude: "+ville.getLongitude());
        txtLat.setText("Latitude: "+ville.getLatitude());
        txtDate.setText(ville.getDate());
        txtHeure.setText(ville.getHeure());
        txtVille.setText(ville.getNom());
        new ImageFromUrl(getString(R.string.img_url)+ville.getIcon()+".png", icon).execute();
    }
    private TextView get(int res){
        return (TextView)findViewById(res);
    }
    class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View p1){
            final Animation anim = AnimationUtils.loadAnimation(ResultatActivity.this,R.anim.btn_click);
            p1.startAnimation(anim);
            BackToMain();
        } 
    }
    private void BackToMain(){
        Intent intent = new Intent(ResultatActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed(){
        BackToMain();
    }  
}
