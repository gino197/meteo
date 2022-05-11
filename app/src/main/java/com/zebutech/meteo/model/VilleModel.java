package com.zebutech.meteo.model;

public class VilleModel
{
    private String nom = ""; 
    private String meteo = "";
    private String temperature = "";
    private String vitesse_vent = "";
    private String icon = "";
    private String date = "";
    private String heure = "";
    private String lon = "";
    private String lat = "";

    public String getNom(){
        return nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }
    public String getMeteo(){
        return meteo;
    }
    public void setMeteo(String new_meteo){
        this.meteo = new_meteo;
    }
    public String getTemp(){
        return temperature;
    }
    public void setTemp(String new_temp){
        this.temperature = new_temp;
    }
    public String getVitesse(){
        return vitesse_vent;
    }
    public void setVitesse(String vitesse){
        this.vitesse_vent = vitesse;
    }
    public String getIcon(){
        return icon;
    }
    public void setIcon(String new_icon){
        this.icon = new_icon;
    }
    public String getDate(){
        return date;
    }
    public void setDate(String new_date){
        this.date = new_date;
    }
    public String getHeure(){
        return heure;
    }
    public void setHeure(String new_heure){
        this.heure = new_heure;
    }
    public String getLongitude(){
        return lon;
    }
    public void setLongitude(String new_lon){
        this.lon = new_lon;
    }
    public String getLatitude(){
        return lat;
    }
    public void setLatitude(String new_lat){
        this.lat = new_lat;
    }
}
