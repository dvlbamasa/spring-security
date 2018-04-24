import java.util.List;

public interface Dao {

	public <T> void create(T object);

  	public Object getById(long id, String object);

  	public <T> void update(T object);

  	public <T> void delete(long id, String object);

  	public List getList(String object);

  	public List getOrderedList(String object, String order);	
}