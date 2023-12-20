/* All the operators utils for GamaSQL */


package shared;

import java.util.ArrayList;
import components.*;
import controller.*;

public class Function {

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
}