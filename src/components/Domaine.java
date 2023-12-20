package components;

import controller.QueryController;

public class Domaine{
    String nom;
    String type;

    /* Constructors */
    public Domaine(String nom, String type)throws Exception{
        this.setNom(nom);
        this.setType(type);
    }
    
    /* Getters*/
    public String getNom(){
        return this.nom;
    }
    public String getType(){
        return this.type;
    }

    /* Setters */
    public void setNom(String str) throws Exception{
        if(str == null || str.trim() == ""){
            Exception e = new Exception("Tsy azo atao foana na tsy misy ny type ana domaine iray azafady");
            throw e;
        }else{
            QueryController.escapeCharacter(str);
            this.nom = str;
        }
    }
    public void setType(String str) throws Exception{
        if(str == null || str.trim() == ""){
            Exception e = new Exception("Tsy azo atao foana na tsy misy ny anarana domaine iray azafady");
            throw e;
        }else{
            QueryController.escapeCharacter(str);
            this.type = str;
        }
    }
}
