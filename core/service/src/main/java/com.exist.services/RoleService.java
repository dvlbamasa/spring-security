import java.util.List;

public interface RoleService {
	
	public void addRole(Role role);
	public void updateRole(Role role);
	public Role getRoleById(long id);
	public List<Role> listRoles();
}