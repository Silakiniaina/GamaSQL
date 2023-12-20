package controller;

public class QueryController {
    static String[] AVAILABLE_DOMAIN_TYPE = {"soratra","isa","daty"};

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
}
