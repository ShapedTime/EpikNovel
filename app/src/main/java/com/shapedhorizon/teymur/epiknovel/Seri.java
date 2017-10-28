package com.shapedhorizon.teymur.epiknovel;

import java.util.ArrayList;

/**
 * Created by Teymur on 13.10.2017.
 */

public class Seri {
    private String title;
    private String link;

    public Seri(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }
    public static ArrayList<Seri> addSeri(String basliq, String address){
        Seri seri = new Seri(basliq, address);
        ArrayList<Seri> seris = new ArrayList<Seri>();
        seris.add(seri);
        return seris;
    }
}
