package components;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializer;
import com.google.gson.stream.JsonReader;

public class Data {
    public static void createBase(String baseName){
        File dir = new File("./.data/"+baseName);
        if(!dir.exists()){
            if(dir.mkdir()){
                System.out.println("Voaforona ny directory ana databazy "+baseName);
            }
        }
    }
    public static void saveRelation(Relation r)throws Exception{
        Gson g = new Gson();
        Data.createBase(r.getBaseName());
        File f = new File("./.data/"+r.getBaseName()+"/"+r.getNomRelation()+".tab");
        ObjectOutputStream io = new ObjectOutputStream(new FileOutputStream(f));
        io.writeObject(r);
        io.close();
        System.out.println("Saved Succesfully !");
    }

    public static Relation loadRelation(String baseName,String relName)throws Exception{
        Relation res = null;
        File f = new File("./.data/"+baseName+"/"+relName+".tab");
        System.out.println("./.data/"+baseName+"/"+relName+".tab");
        if(f.exists()){
            ObjectInputStream o = new ObjectInputStream(new FileInputStream(f));
            res = (Relation)o.readObject();
        }else throw new Exception("Tsy misy ny fifandraisana mitondra ny anarana : "+relName+" ao anatin'ny databazy "+baseName);
        return res;
    }
}