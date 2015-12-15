package um.vi8e.com.stocktakescanner.utils;

/**
 * Created by Fixer on 11/27/2015.
 */
public class Const {

public class Status {

	public static final String PENDING   = "Pending";
	public static final String COMPLETED = "Completed";
	public static final String DELETED   = "Deleted";
}

public static String getApiUrl(String barcode){
return  "http://staging.uobapi.vi9e.com/product/CS/121/5156441/"+barcode+"/7b04dbce9373f29617eb53d1bb38463e";
}

public static final String NO_CONTINUE ="noContinue";

}
