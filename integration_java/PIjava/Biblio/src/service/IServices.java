package service;

import java.sql.SQLException;
import java.util.List;

public interface IServices<T> {
	
	void ajouterCour(T t) throws SQLException;
	boolean updateCour(T t) throws SQLException;
	boolean deleteCour(T t) throws SQLException;
	List<T> ListeCours() throws SQLException;

}
