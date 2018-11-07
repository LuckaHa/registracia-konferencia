package sk.upjs.registracia_konferencia.persistent;

import java.util.List;

import sk.upjs.registracia_konferencia.entity.Workshop;

public interface WorkshopDAO {

	List<Workshop> getAll();
	
	Workshop save(Workshop workshop);
	
	void delete(long id);

}