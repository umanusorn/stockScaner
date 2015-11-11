package um.vi8e.com.stocktakescanner.Model;

/**
 * Created by Fixer on 11/6/2015.
 */
public class ModelType {

public static final String STOCK_RESULT = "list";
public static final String SUB_TASK     = "subTask";
public static final String TASK         = "task";
public static final String STOCK_TAKE   = "comment";

public String type;

public ModelType(String type) {
	this.type = type;
}

}
