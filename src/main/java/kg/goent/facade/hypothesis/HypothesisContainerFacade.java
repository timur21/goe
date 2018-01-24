package kg.goent.facade.hypothesis;

import kg.goent.dao.ObjectDao;
import kg.goent.models.hypothesis.HypothesisContainer;
import kg.goent.models.project.Project;

import java.util.List;

/**
 * Created by azire on 4/20/2017.
 */
public class HypothesisContainerFacade {
    private ObjectDao objectDao = new ObjectDao();

    public void create(HypothesisContainer hypothesisContainer) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().persist(hypothesisContainer);
        objectDao.commitAndCloseTransaction();
    }

    public void update(HypothesisContainer hypothesisContainer) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().merge(hypothesisContainer);
        objectDao.commitAndCloseTransaction();
    }

    public void delete(HypothesisContainer hypothesisContainer) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().remove(objectDao.getEntityManager().contains(hypothesisContainer) ? hypothesisContainer : objectDao.getEntityManager().merge(hypothesisContainer));
        objectDao.commitAndCloseTransaction();
    }

    public List<HypothesisContainer> findAll(){
        List<HypothesisContainer> objectList;
        try {
            objectDao.beginTransaction();
            objectList = objectDao.getEntityManager().createNamedQuery("HypothesisContainer.findAll",HypothesisContainer.class).getResultList();
        }catch (Exception ex){
            objectList = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return objectList;
    }

    public HypothesisContainer findById(Integer id) {
        HypothesisContainer hypothesisContainer;
        try {
            objectDao.beginTransaction();
            hypothesisContainer = objectDao.getEntityManager().find(HypothesisContainer.class, id);
        }catch (Exception ex){
            hypothesisContainer = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return hypothesisContainer;
    }

//    public HypothesisContainer findByStatus(String status){
//        HypothesisContainer ms;
//        try {
//            objectDao.beginTransaction();
//            ms = objectDao.getEntityManager().createNamedQuery("MemberStatus.findByMemberStatus",HypothesisContainer.class)
//                    .setParameter("status",status).getSingleResult();
//        }catch (Exception ex){
//            ms = null;
//        }finally {
//            objectDao.commitAndCloseTransaction();
//        }
//        return ms;
//    }

    public HypothesisContainer findByProject(Project project) {
        HypothesisContainer hypothesisContainer;
        try {
            objectDao.beginTransaction();
            hypothesisContainer = objectDao.getEntityManager().
                    createNamedQuery("HypothesisContainer.findByProject",HypothesisContainer.class)
                    .setParameter("project",project).getSingleResult();
        }catch (Exception ex){
            hypothesisContainer = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return hypothesisContainer;
    }
}
