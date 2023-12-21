package test;

import java.util.Vector;

import com.google.gson.Gson;

import components.Data;
import components.Element;
import components.Relation;
import controller.QueryController;
import controller.QueryRequest;
import shared.Creator;
import shared.Function;

public class Main{
    public static void main(String[] args) throws Exception{
        QueryRequest.prompt();
        // Relation r = Data.loadRelation("null", "Etudiant");
        // Vector<Element> elem = r.getElements();
        // Gson g  = new Gson();
        // for(int i=0; i<elem.size(); i++){
        //     System.out.println(g.toJson(elem.get(i)));
        // }
    }
}