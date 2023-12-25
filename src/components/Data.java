package components;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

public class Data {
    public static void saveRequest(String request)throws Exception{
        File f = new File("./docs/_requestHistory.log");
        if(f.exists()){
            FileOutputStream fo = new FileOutputStream(f,true);
            fo.write(request.getBytes());
            String line = "\n";
            fo.write(line.getBytes());
            fo.close();
        }
    }
    public static void createBase(String baseName){
        File dir = new File("./.data/"+baseName);
        if(!dir.exists()){
            if(dir.mkdir()){
                System.out.println("Voaforona ny directory ana databazy "+baseName);
            }
        }
    }
    public static void saveRelation(Relation r)throws Exception{
        Data.createBase(r.getBaseName());
        File f = new File("./.data/"+r.getBaseName()+"/"+r.getNomRelation()+".tab");
        ObjectOutputStream io = new ObjectOutputStream(new FileOutputStream(f));
        io.writeObject(r);
        io.close();
    }

    public static Relation loadRelation(String baseName,String relName)throws Exception{
        Relation res = null;
        File f = new File("./.data/"+baseName+"/"+relName+".tab");
        if(f.exists()){
            ObjectInputStream o = new ObjectInputStream(new FileInputStream(f));
            res = (Relation)o.readObject();
        }else throw new Exception("Tsy misy ny fifandraisana mitondra ny anarana : "+relName+" ao anatin'ny databazy "+baseName);
        return res;
    }

    public static void save(Relation r) throws IOException{
        Gson g = new Gson();
        FileOutputStream fos = new FileOutputStream(new File("./.data/huhu.tab"));
        fos.write(g.toJson(r).getBytes());
    }

}