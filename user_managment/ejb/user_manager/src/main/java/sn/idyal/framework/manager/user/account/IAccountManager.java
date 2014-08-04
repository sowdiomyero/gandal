package sn.idyal.framework.manager.user.account;


import sn.idyal.framework.users.Role;
import sn.idyal.framework.users.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sowdiomyero
 * Date: 27/05/14
 * Time: 15:29
 * To change this template use File | Settings | File Templates.
 */
public interface IAccountManager {

    public  void add(Object entity);
    public  void delete(Object entity);
    public  void update(Object entity);
    public  void merge(Object entity);

    public List<User> getAllUser();
    public User getUserByLogin(String mLogin);
/*    public void updateUser(User userToEdit);
    public void delete(User userToDelete);
    public  void addUser(User newUser);
    */

    public List<Role> getUserRoles(Role role);
    public List<User> getRolesUser(User user);

    public List<Role> getAllRole();
    public List<Role> getDefaultRole();

    public List getAll(Class clazz);


}
