package dao;

import java.util.List;

import entities.Ville;
import jakarta.ejb.Remote;

@Remote
public interface IDaoRemote <T> {
	
	public T create(T o);
	public boolean delete(T o);
	public T update(T o);
	public T findById(int id);
	public List<T> findAll();
	

}
