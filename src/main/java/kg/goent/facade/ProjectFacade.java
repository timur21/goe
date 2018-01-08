package kg.goent.facade;

import kg.goent.dao.ObjectDao;
import kg.goent.models.Project;

import java.util.List;

/**
 * Created by azire on 4/20/2017.
 */
public class ProjectFacade {
    private ObjectDao objectDao = new ObjectDao();

    public void create(Project project) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().persist(project);
        objectDao.commitAndCloseTransaction();
    }

    public void update(Project project) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().merge(project);
        objectDao.commitAndCloseTransaction();
    }

    public void delete(Project project) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().remove(project);
        objectDao.commitAndCloseTransaction();
    }

    public Project findById(Integer id) {
        objectDao.beginTransaction();
        Project project = objectDao.getEntityManager().find(Project.class, id);
        objectDao.commitAndCloseTransaction();
        return project;
    }

    public Project findByTitle(String title){
        objectDao.beginTransaction();
        Project project;
        try {
            project = objectDao.getEntityManager().createNamedQuery("Project.findByTitle", Project.class)
                    .setParameter("title", title).getSingleResult();
        }catch (Exception ex){
            project = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return project;
    }

}
