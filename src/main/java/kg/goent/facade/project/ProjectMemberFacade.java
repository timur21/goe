package kg.goent.facade.project;

import kg.goent.dao.ObjectDao;
import kg.goent.models.project.Project;
import kg.goent.models.project.ProjectMember;
import kg.goent.models.User;

import java.util.List;

/**
 * Created by azire on 4/20/2017.
 */
public class ProjectMemberFacade {
    private ObjectDao objectDao = new ObjectDao();

    public void create(ProjectMember projectMember) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().persist(projectMember);
        objectDao.commitAndCloseTransaction();
    }

    public void update(ProjectMember projectMember) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().merge(projectMember);
        objectDao.commitAndCloseTransaction();
    }

    public void delete(ProjectMember projectMember) {
        objectDao.beginTransaction();
//        em.remove(em.contains(entity) ? entity : em.merge(entity));
        objectDao.getEntityManager().remove(objectDao.getEntityManager().contains(projectMember) ? projectMember : objectDao.getEntityManager().merge(projectMember));
        objectDao.commitAndCloseTransaction();
    }

    public List<ProjectMember> findAll(){
        List<ProjectMember> objectList;
        try {
            objectDao.beginTransaction();
            objectList = objectDao.getEntityManager().createNamedQuery("ProjectMember.findAll",ProjectMember.class).getResultList();
        }catch (Exception ex){
            objectList = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return objectList;
    }

    public ProjectMember findById(Integer id) {
        ProjectMember projectMember;
        try {
            objectDao.beginTransaction();
            projectMember = objectDao.getEntityManager().find(ProjectMember.class, id);
        }catch (Exception ex){
            projectMember = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return projectMember;
    }

    public List<ProjectMember> findByProject(Project project){
        List<ProjectMember> ms;
        try {
            objectDao.beginTransaction();
            ms = objectDao.getEntityManager().createNamedQuery("ProjectMember.findByProject",ProjectMember.class)
                    .setParameter("project",project).getResultList();
        }catch (Exception ex){
            ms = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return ms;
    }

    public List<ProjectMember> findByUser(User user){
        List<ProjectMember> ms;
        try {
            objectDao.beginTransaction();
            ms = objectDao.getEntityManager().createNamedQuery("ProjectMember.findByUser",ProjectMember.class)
                    .setParameter("user",user).getResultList();
        }catch (Exception ex){
            ms = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return ms;
    }

    public ProjectMember findByUserAndProject(User u,Project p){
        ProjectMember projectMember;
        try{
            objectDao.beginTransaction();
            projectMember = objectDao.getEntityManager().createNamedQuery("ProjectMemeber.findByUserAndProject",ProjectMember.class)
                    .setParameter("user",u).setParameter("project",p).getSingleResult();
        }catch (Exception ex){
            projectMember = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return projectMember;
    }

}
