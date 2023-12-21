package components;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;

import controller.QueryController;

public class Element implements Serializable{
    HashMap<String,String> listeValeur;

    /* Constructors */
    public Element(Relation table, String[] val)throws Exception{
        this.listeValeur = new HashMap<String,String>();
        this.setListeValeur(table,val);
    }

    /* Getters */

    public HashMap<String,String> getListeValeur(){
        return listeValeur;
    }
    public String[] getValues(Relation r){
        String[] result = new String[this.getListeValeur().size()];
        HashMap<String,String> ls = getListeValeur();
        Vector<Domaine> lsDom = r.getAttributs();
        for(int i=0; i<ls.size(); i++){
            result[i] = (ls.get(lsDom.get(i).getNom()));
        }
        return result;
    }

    public String getValue(String d){
        return this.getListeValeur().get(d);
    }

    public void setListeValeur(Relation r,String[] ls)throws Exception{
        if(r.getAttributs().size() != ls.length){
            throw  new Exception("Tsy maintsy mitovy ny isan'ny attribut anaty fifandraisan "+r.getNomRelation()+" mba hahafahana mampiditra element iray \n Hita "+ls.length+ " raha toa ka tokony hoe "+r.getAttributs().size());
        }else{
            QueryController.isAllowedData(r, ls);
            Vector<Domaine> lsDom = r.getAttributs();
            for(int i=0; i<lsDom.size(); i++){
                listeValeur.put(lsDom.get(i).getNom(), ls[i]);
            }
        }
    }
}
