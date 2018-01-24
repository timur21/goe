package kg.goent.facade.project;

import kg.goent.dao.ObjectDao;
import kg.goent.models.project.ProjectStatus;

import java.util.List;

/**
 * Created by azire on 4/20/2017.
 */
public class ProjectStatusFacade {
    private ObjectDao objectDao = new ObjectDao();

    public ProjectStatusFacade() {
        if(findAll().size() == 0){
            initialize();
        }
    }

    public void create(ProjectStatus projectStatus) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().persist(projectStatus);
        objectDao.commitAndCloseTransaction();
    }

    public void update(ProjectStatus projectStatus) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().merge(projectStatus);
        objectDao.commitAndCloseTransaction();
    }

    public void delete(ProjectStatus projectStatus) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().remove(objectDao.getEntityManager().contains(projectStatus) ? projectStatus : objectDao.getEntityManager().merge(projectStatus));
        objectDao.commitAndCloseTransaction();
    }

    public List<ProjectStatus> findAll(){
        List<ProjectStatus> objectList;
        try {
            objectDao.beginTransaction();
            objectList = objectDao.getEntityManager().createNamedQuery("ProjectStatus.findAll",ProjectStatus.class).getResultList();
        }catch (Exception ex){
            objectList = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return objectList;
    }

    public ProjectStatus findById(Integer id) {
        ProjectStatus projectStatus;
        try {
            objectDao.beginTransaction();
            projectStatus = objectDao.getEntityManager().find(ProjectStatus.class, id);
        }catch (Exception ex){
            projectStatus = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return projectStatus;
    }

    public ProjectStatus findByStatus(String status){
        ProjectStatus ms;
        try {
            objectDao.beginTransaction();
            ms = objectDao.getEntityManager().createNamedQuery("ProjectStatus.findByStatus",ProjectStatus.class)
                    .setParameter("status",status).getSingleResult();
        }catch (Exception ex){
            ms = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return ms;
    }

    private void initialize(){
        ProjectStatus ps = new ProjectStatus("active");
        create(ps);
        ps = new ProjectStatus("frozen");
        create(ps);
        ps = new ProjectStatus("finished");
        create(ps);
    }
}
