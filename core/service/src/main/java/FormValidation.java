import org.springframework.validation.Errors;  
import org.springframework.validation.ValidationUtils; 
import org.springframework.validation.Validator; 

public class FormValidation implements Validator {
	
	@Override  
	public boolean supports(Class<?> paramClass) {
		return Person.class.equals(paramClass);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Person person = (Person) object;  

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name.firstName", "required.firstName"); 

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name.middleName", "required.MiddleName"); 

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name.lastName", "required.lastName");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthday", "required.birthday");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateHired", "required.dateHired"); 

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.barangay", "required.barangay"); 

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.municipality", "required.municipality"); 

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contactInformation.landline", "required.landline"); 

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contactInformation.mobileNumber", "required.mobileNumber"); 

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contactInformation.email", "required.email"); 

		if (person.getGwa() > 3.0 || person.getGwa() < 1.0 ) {
			errors.rejectValue("gwa", "gwa.range");
		}

		if (person.getAddress().getStreetNo() < 0) {
			errors.rejectValue("address.streetNo", "streetNo.negative");
		}

		if (person.getAddress().getZipCode() < 0) {
			errors.rejectValue("address.zipCode", "zipCode.negative");
		}
	}
}