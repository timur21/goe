package kg.goent.facade.hypothesis;

import kg.goent.dao.ObjectDao;
import kg.goent.models.bmc.Segment;
import kg.goent.models.hypothesis.Hypothesis;
import kg.goent.models.hypothesis.HypothesisContainer;

import java.util.List;

/**
 * Created by azire on 4/20/2017.
 */
public class HypothesisFacade {
    private ObjectDao objectDao = new ObjectDao();

    public void create(Hypothesis hypothesis) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().persist(hypothesis);
        objectDao.commitAndCloseTransaction();
    }

    public void update(Hypothesis hypothesis) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().merge(hypothesis);
        objectDao.commitAndCloseTransaction();
    }

    public void delete(Hypothesis hypothesis) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().remove(objectDao.getEntityManager().contains(hypothesis) ? hypothesis : objectDao.getEntityManager().merge(hypothesis));
        objectDao.commitAndCloseTransaction();
    }

    public List<Hypothesis> findAll(){
        List<Hypothesis> objectList;
        try {
            objectDao.beginTransaction();
            objectList = objectDao.getEntityManager().createNamedQuery("Hypothesis.findAll",Hypothesis.class).getResultList();
        }catch (Exception ex){
            objectList = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return objectList;
    }

    public Hypothesis findById(Integer id) {
        Hypothesis hypothesis;
        try {
            objectDao.beginTransaction();
            hypothesis = objectDao.getEntityManager().find(Hypothesis.class, id);
        }catch (Exception ex){
            hypothesis = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return hypothesis;
    }
    public List<Hypothesis> findByContainer(HypothesisContainer hypothesisContainer){
        List<Hypothesis> objectList;
        try {
            objectDao.beginTransaction();
            objectList = objectDao.getEntityManager().createNamedQuery("Hypothesis.findByContainer",Hypothesis.class).
                    setParameter("container",hypothesisContainer).getResultList();
        }catch (Exception ex){
            objectList = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return objectList;
    }
    public Hypothesis findBySegment(Segment segment){
        Hypothesis hypothesis;
        try {
            objectDao.beginTransaction();
            hypothesis = objectDao.getEntityManager().createNamedQuery("Hypothesis.findBySegment",Hypothesis.class).
                    setParameter("segment",segment).getSingleResult();
        }catch (Exception ex){
            hypothesis = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return hypothesis;
    }
//    public Hypothesis findByStatus(String status){
//        Hypothesis ms;
//        try {
//            objectDao.beginTransaction();
//            ms = objectDao.getEntityManager().createNamedQuery("MemberStatus.findByMemberStatus",Hypothesis.class)
//                    .setParameter("status",status).getSingleResult();
//        }catch (Exception ex){
//            ms = null;
//        }finally {
//            objectDao.commitAndCloseTransaction();
//        }
//        return ms;
//    }


}
