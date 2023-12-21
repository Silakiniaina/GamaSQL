package test;

import java.util.Vector;

import com.google.gson.Gson;

import components.Data;
import components.Domaine;
import components.Element;
import components.Relation;
import controller.QueryController;
import controller.QueryRequest;
import shared.Creator;
import shared.Function;

public class Main{
    public static void main(String[] args)throws Exception{
        QueryRequest.prompt();
        // Relation r = Data.loadRelation("null", "Etudiant");
        // Vector<Element> elem = r.getElements();
        // Vector<Domaine> dom = r.getAttributs();
        // Vector<String> e = r.getAllElement(dom.get(0));
        // Gson g  = new Gson();
        // for(int i=0; i<elem.size(); i++){
        //     System.out.println(e.get(i));
        // }
        // r.printTable(dom);
    }
}