package kg.goent.facade;

import kg.goent.dao.ObjectDao;
import kg.goent.models.Project;
import kg.goent.models.ProjectMember;
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
        objectDao.getEntityManager().remove(projectMember);
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

    public List<ProjectMember> findByStatus(Project project){
        List<ProjectMember> ms;
        try {
            objectDao.beginTransaction();
            ms = objectDao.getEntityManager().createNamedQuery("MemberStatus.findByProject",ProjectMember.class)
                    .setParameter("project",project).getResultList();
        }catch (Exception ex){
            ms = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return ms;
    }

    public List<ProjectMember> findByStatus(User project){
        List<ProjectMember> ms;
        try {
            objectDao.beginTransaction();
            ms = objectDao.getEntityManager().createNamedQuery("MemberStatus.findByUser",ProjectMember.class)
                    .setParameter("project",project).getResultList();
        }catch (Exception ex){
            ms = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return ms;
    }
}
