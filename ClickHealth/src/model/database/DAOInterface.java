package model.database;

import java.util.Iterator;

public interface DAOInterface {
	
	public Iterator getAllData();
	public void insertData(Object obj);
	public void updateData(Object obj);
	public Object getData(String keyID);

}
