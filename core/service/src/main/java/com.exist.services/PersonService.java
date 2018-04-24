import java.util.List;

public interface PersonService {
	
	public void addPerson(Person person);
	public void updatePerson(Person person);
	public Person getPersonById(long id);
	public List<Person> listPersons();
	public void deletePerson(long id);
	public List<Person> listPersonsOrderBy(String orderType);
	public void deleteContact(long id);
}