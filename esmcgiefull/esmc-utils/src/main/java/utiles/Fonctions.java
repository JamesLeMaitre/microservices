package utiles;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Fonctions {

    public Date covertStingToDate(String param) throws Exception {

        Date d = new SimpleDateFormat("yyyy-MM-dd").parse(param);

        return d;
    }

}
