import org.springframework.validation.Errors;  
import org.springframework.validation.ValidationUtils; 
import org.springframework.validation.Validator; 

public class RoleFormValidation implements Validator {
	
	@Override  
	public boolean supports(Class<?> paramClass) {
		return Role.class.equals(paramClass);
	}

	@Override
	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.roleName"); 
	}
}