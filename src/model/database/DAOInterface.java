package model.database;

import java.util.Iterator;

public interface DAOInterface {
	
	public Iterator getAllData();
	public boolean insertData(Object obj);
	public boolean updateData(Object obj);
	public Object getData(Object keyID);

}
