package com.example.biblioparislocal.models;

import java.io.Serializable;

public class ApiFields implements Serializable {
    private String comment;
    private String adresse_ville;
    private String voie;
    private String cp;
    private String coordonnees_ban;
    private String libelle1;
    private double[] coordonnees_finales;

    public double[] getCoordonnees_finales() {
        return coordonnees_finales;
    }

    public void setCoordonnees_finales(double[] coordonnees_finales) {
        this.coordonnees_finales = coordonnees_finales;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAdresse_ville() {
        return adresse_ville;
    }

    public void setAdresse_ville(String adresse_ville) {
        this.adresse_ville = adresse_ville;
    }

    public String getVoie() {
        return voie;
    }

    public void setVoie(String voie) {
        this.voie = voie;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCoordonnees_ban() {
        return coordonnees_ban;
    }

    public void setCoordonnees_ban(String coordonnees_ban) {
        this.coordonnees_ban = coordonnees_ban;
    }

    public String getLibelle1() {
        return libelle1;
    }

    public void setLibelle1(String libelle1) {
        this.libelle1 = libelle1;
    }
}
