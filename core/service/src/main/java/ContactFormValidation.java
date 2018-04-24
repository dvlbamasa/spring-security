import org.springframework.validation.Errors;  
import org.springframework.validation.ValidationUtils; 
import org.springframework.validation.Validator; 

public class ContactFormValidation implements Validator {
	
	@Override  
	public boolean supports(Class<?> paramClass) {
		return ContactInformation.class.equals(paramClass);
	}

	@Override
	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "landline", "required.landline"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobileNumber", "required.mobileNumber"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required.email"); 
	}
}