import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;


@Entity
@Table(name = "person")
public class Person extends EntityParent{
	
	private long id;
	private Name name;
	private Gender gender;
	private Address address;
	private Date birthday;
	private float gwa;
	private Date dateHired;
	private boolean currentlyEmployed;
	private ContactInformation contactInformation;
	private Set<Role> roles;

	public Person() {}

	public Person(Name name, Gender gender, Address address, Date birthday, float gwa, 
					Date dateHired, boolean currentlyEmployed) {
		this.name = name;
		this.gender = gender;
		this.address = address;
		this.birthday = birthday;
		this.gwa = gwa;
		this.dateHired = dateHired;
		this.currentlyEmployed = currentlyEmployed;
	}

	public void setName(Name name) {
		this.name = name;
	}

	@Embedded
	public Name getName() {
		return name;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "gender", nullable = false)
	public Gender getGender() {
		return gender;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address")
	public Address getAddress() {
		return address;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "birthday", nullable = false)
	public Date getBirthday() {
		return birthday;
	}
	
	public void setGwa(float gwa) {
		this.gwa = gwa;
	}

	@Column(name = "gwa", nullable = false)
	public float getGwa() {
		return gwa;
	}

	
	public void setDateHired(Date dateHired) {
		this.dateHired = dateHired;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "date_hired", nullable = false)
	public Date getDateHired() {
		return dateHired;
	}

	public void setCurrentlyEmployed(boolean currentlyEmployed) {
		this.currentlyEmployed = currentlyEmployed;
	}

	@Type(type="yes_no")
	@Column(name = "currently_employed", nullable = false)
	public boolean getCurrentlyEmployed() {
		return currentlyEmployed;
	}

	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "contact_id", unique= true, nullable=true, insertable=true, updatable=true)
	public ContactInformation getContactInformation() {
		return contactInformation;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "person_role", joinColumns = { 
			@JoinColumn(name = "person_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "role_id", 
					nullable = false, updatable = false) })
	public Set<Role> getRoles() {
		return roles;
	}	

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} 
		if (!this.getClass().equals(obj.getClass())) {
			return false;	
		} 
		Person obj2 = (Person)obj;
		if((this.id == obj2.getId()) && (this.name.equals(obj2.getName()))) {
			 return true;
		}
		return false;
  	}
   
   	@Override
   	public int hashCode() {
		int tmp = 0;
		tmp = ( id + name.getFirstName() ).hashCode();
		return tmp;
   	}
}