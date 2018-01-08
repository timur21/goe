package kg.goent.facade;


import kg.goent.dao.UserDao;
import kg.goent.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserFacade {

    private UserDao userDao = new UserDao();

    public void createUser(User user) {
        userDao.beginTransaction();
        userDao.getEntityManager().persist(user);
        userDao.commitAndCloseTransaction();
    }

    public void updateUser(User user) {
        userDao.beginTransaction();
        userDao.getEntityManager().merge(user);
        userDao.commitAndCloseTransaction();
    }

    public void deleteUser(User user) {
        userDao.beginTransaction();
        userDao.getEntityManager().remove(user);
        userDao.commitAndCloseTransaction();
    }

    public User findById(Integer id) {
        userDao.beginTransaction();
        User user = userDao.getEntityManager().find(User.class, id);
        userDao.commitAndCloseTransaction();
        return user;
    }

    public List<User> findAll(){
        List<User> userList;
        try {
            userDao.beginTransaction();
            userList = userDao.getEntityManager().createNamedQuery("User.findAll",User.class).getResultList();
        }catch (Exception ex){
            userList = new ArrayList<User>();
        }finally {
            userDao.commitAndCloseTransaction();
        }
        return userList;
    }

    public User findByEmail(String email){
        User user;
        try {
            userDao.beginTransaction();
            user = userDao.getEntityManager().createNamedQuery("User.findByEmail", kg.goent.models.User.class)
                    .setParameter("email", email).getSingleResult();
        }catch (Exception ex){
            user = null;
        }finally {
            userDao.commitAndCloseTransaction();
        }
        return user;
    }
    public User findByEmailPass(String email,String pass){
        User user;
        try {
            userDao.beginTransaction();
            user =userDao.getEntityManager().createNamedQuery("User.findByEmailPass", User.class)
                    .setParameter("email", email).setParameter("password", pass).getSingleResult();
        }catch (Exception ex){
            user = null;
        }finally {
            userDao.commitAndCloseTransaction();
        }
        return user;

    }
}
