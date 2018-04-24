import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping(value="/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;

	@RequestMapping(value="/list")
	public ModelAndView listRoles(ModelAndView modelAndView,
								@RequestParam(value="prompt", required=false) String prompt) {
		List<Role> roles = roleService.listRoles();
		modelAndView.addObject("title", "Role");
		modelAndView.addObject("prompt", prompt);
		modelAndView.addObject("roles", roles);
		modelAndView.setViewName("listRoles");
		return modelAndView;
	}

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addRole(ModelAndView modelAndView) {
		Role role = new Role();
		modelAndView.addObject("title", "Add Role");
		modelAndView.addObject("role", role);
		modelAndView.setViewName("roleForm");
		return modelAndView;
	}

	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ModelAndView saveRole(@ModelAttribute("role") Role role,
									BindingResult result) {
		RoleFormValidation formValidation = new RoleFormValidation();
		formValidation.validate(role, result);
		if (result.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("roleForm");
			modelAndView.addObject("role", role);
			if (role.getId() == 0) {
				modelAndView.addObject("title", "Add Role");
			}
			else {
				modelAndView.addObject("title", "Update Role");
			}
			return modelAndView;
		}
		ModelAndView modelAndView = new ModelAndView("redirect:/role/list");
		if (role.getId() == 0) {
			roleService.addRole(role);	
			modelAndView.addObject("prompt", "Successfully Added a Role!");
		}
		else {
			roleService.updateRole(role);
			modelAndView.addObject("prompt", "Successfully Updated a Role!");
		}
		return modelAndView;
	}

	@RequestMapping(value="/update", method=RequestMethod.GET)
	public ModelAndView updateRole(@RequestParam(value="roleId", required=true) long id) {
		Role role = roleService.getRoleById(id);		
		ModelAndView modelAndView = new ModelAndView("roleForm");
		modelAndView.addObject("role", role);
		modelAndView.addObject("title", "Update Role");
		return modelAndView;
	}
}