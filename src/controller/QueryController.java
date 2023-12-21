package controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Vector;

import components.Domaine;
import components.Relation;

public class QueryController {
    static String[] AVAILABLE_DOMAIN_TYPE = {"soratra","isa","daty"};

///UTILS
    /* escaping invalid character on the request : './\*^~`" */
    public static void escapeCharacter(String str)throws Exception{
        String invalid = "\'./\\*^~`\"";
        for(int i=0; i<str.length(); i++){
            for(int j=0; j<invalid.length(); j++){
                if(str.charAt(i) == invalid.charAt(j)){
                    Exception e = new Exception("Tsy azo asiana soratra voarara toa ny "+invalid.charAt(j)+" ny fangatahana");
                    throw e;
                }
            }
        }
    }

    /* Split the request for the analyst */
    public static String[] splitRequest(String request){
        return request.split(" ");
    }

///VERIFICATIONS

    /* Verification if the domaine is accepted by GamaSQL : soratra, isa, daty */
    public static boolean isAnExistantDomain(Domaine d){
        boolean result = false;
        String[] typeDomaineValable = QueryController.AVAILABLE_DOMAIN_TYPE;
        for(int i=0; i<typeDomaineValable.length; i++){
            if(d.getType().compareToIgnoreCase(typeDomaineValable[i]) == 0){
                result = true; 
                break;
            }
        }
        return result;
    }

    /* Verification if the relation contains the domain  */
    public static boolean isDomainOf(Relation table, String nomDomaine){
        boolean result = false;
        Vector<Domaine> allDomaine = table.getAttributs();
        for(int i=0; i<allDomaine.size(); i++){
            Domaine d = allDomaine.get(i);
            if(nomDomaine.compareToIgnoreCase(d.getNom()) == 0){
                result = true;
                break;
            }
        }
        return result;
    }

    /* Verification if the data from the request correspond on the Relation  */
    public static void isAllowedData(Relation r, String[] data)throws Exception{
        Vector<Domaine> lsDom = r.getAttributs();
        for(int i=0; i<lsDom.size(); i++){
            try{
                String dom = lsDom.get(i).getType();
                if(dom == "isa") Double.parseDouble(data[i]);
                else if(dom == "daty") LocalDate.parse(data[i]);
            }catch(NumberFormatException n){
                throw new Exception("Ny karazany domainina "+lsDom.get(i).getNom()+" dia tsy maintsy isa, hita :"+data[i]);
            }catch(DateTimeParseException e){
                throw new Exception("Ny karazany domainina "+lsDom.get(i).getNom()+" dia tsy maintsy daty yyyy-mm-dd, hita :"+data[i]);
            }
        }
    }

///GETTING REQUIREMENTS 

    /* getting the type of the request : insertion, selection, creation, .... */
    public static String getActionType(String request)throws Exception{
        String result = "huhu";
        String req = QueryController.splitRequest(request)[0]; 
        if(req.equalsIgnoreCase("mamorona")) result = "famoronana";
        else if(req.equalsIgnoreCase("manamboara")) result = "fanamboarana";
        else if(req.equalsIgnoreCase("alaivo"))result = "fangalana";
        else if(req.equalsIgnoreCase("ampidiro")) result = "fampidirana";
        else if(req.equalsIgnoreCase("ampifampitomboy")) result = "fampitomboana";
        else if(req.equalsIgnoreCase("ampifandraiso")) result = "fampifandraisana";
        else throw new Exception("Fangatahana tsy fantatra ny fangatahana *"+req+" izay tafiditra");
        return result;
    }

    /* getting the name of the relation or the database to create from the request */
    public static String getNomCreation(String request)throws Exception{
        String nom = QueryController.splitRequest(request)[2];
        if(nom == null || nom.trim() == "" ){
            throw new Exception("Tsy mahazo atao foana ny anarana fifandraisana na databazy iray ");
        }
        return nom;
    }

    /* getting data from request as a String */
    public static String getData(String request)throws Exception{
        String result = "bogosy";
        String type = QueryController.getActionType(request);
        if(type == "fanamboarana") result = QueryController.splitRequest(request)[3].trim();
        else if(type == "fampidirana" || type == "fangalana") result = QueryController.splitRequest(request)[1].trim();
        return result;
    }

    /* getting the name of the component target */
    public static String getNomCible(String request)throws Exception{
        String result = "huhu";
        String type = QueryController.getActionType(request);
        if(type == "fanamboarana" || type == "famoronana")result = QueryController.splitRequest(request)[2];
        else if(type == "fampidirana" || type == "fangalana")result = QueryController.splitRequest(request)[3];   
        else if(type == "fampifandaisana" || type == "fampitomboana")result = QueryController.splitRequest(request)[2]+"|"+QueryController.splitRequest(request)[4];
        return result;
    }

    /* getting the type of the target : databazy , fifandraisana, view?.... */
    public static String getTypeTarget(String request)throws Exception{
        String result = null;
        if(getActionType(request).equals("famoronana") && splitRequest(request)[1].equalsIgnoreCase("databazy"))result = "databazy";
        else if(getActionType(request).equals("fanamboarana") && splitRequest(request)[1].equalsIgnoreCase("fifandraisana"))result = "fifandraisana";
        else throw new Exception("Fangatahana tsy fantatra ny : +"+splitRequest(request)[1]);
        return result;
    }
}
