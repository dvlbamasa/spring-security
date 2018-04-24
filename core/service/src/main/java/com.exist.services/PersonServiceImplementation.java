import java.util.List;

public class PersonServiceImplementation implements PersonService{

	private Dao dao;

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	@Override
	public void addPerson(Person person) {
		dao.create(person);
	}

	@Override
	public void updatePerson(Person person) {
		dao.update(person);
	}

	@Override
	public List<Person> listPersons() {
		return dao.getList("Person");
	}

	@Override
	public Person getPersonById(long id) {
		return (Person) dao.getById(id, "Person");
	}

	@Override
	public void deletePerson(long id) {
		dao.delete(id, "Person");
	}

	@Override
	public List<Person> listPersonsOrderBy(String orderType) {
		return dao.getOrderedList("Person", orderType);
	}

	@Override
	public void deleteContact(long id) {
		dao.delete(id, "ContactInformation");
	}

	@Override
	public void addAddress(Address address) {
		dao.create(address);
	}
}