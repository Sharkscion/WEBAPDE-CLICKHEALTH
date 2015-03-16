package model.database;

import java.util.Iterator;

public interface DAOInterface {
	
	public Iterator getAllData();
	public Iterator insertData(Object obj);
	public Iterator updateData(Object obj);
	public Iterator getData(Object objID);

}
