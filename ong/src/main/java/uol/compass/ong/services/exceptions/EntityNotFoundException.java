package uol.compass.ong.services.exceptions;

public class EntityNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public EntityNotFoundException (Object id) {
		super ("Resource Not Found. Id " + id);
	}

}
