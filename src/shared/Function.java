/* All the operators utils for GamaSQL */


package shared;

import java.util.Vector;

import components.*;

public class  Function {

    /* Extract data from the request */
    public static String[] getListData(String data, int type)throws Exception{
        if(type < -1 || type > 1) throw new Exception("Tsy fantatra ny type angatahana");
        String str = data.substring(data.indexOf("[")+1,data.lastIndexOf("]"));
        String[] ls = str.split(",");
        if(type == -1){
            return ls;
        }
        String[] result = new String[ls.length];
        for(int i=0; i<ls.length; i++){
            result[i] = ls[i].split(":")[type];
        }
        return result;
    }

    /* Generate a domains of type string un with a array of name of domain */
    public static Vector<Domaine> generateDomains(String[] domNames) throws Exception{
        if(domNames == null || domNames.length < 1) throw new Exception("Tsy maintsy farafahakeliny iray ny anarana domainina iray ");
        Vector<Domaine> result = new Vector<Domaine>();
        for(int i=0; i<domNames.length; i++){
            Domaine d = new Domaine(domNames[i], "soratra");
            result.add(d);
        }
        return result;
    }

    /* verifying if the two arrays contains the same data*/
    public static boolean isTheSame(Object[] o1, Object[] o2)throws Exception{
        if(o1.length != o2.length) throw new Exception("Two array to compare must have the same length");
        boolean result = true;
        for(int i=0; i<o1.length; i++){
            if(o1[i] != o2[i]){
                result =  false;
                break;
            }
        }
         return result;
    }
}