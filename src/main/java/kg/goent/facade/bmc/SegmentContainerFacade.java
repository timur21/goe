package kg.goent.facade.bmc;

import kg.goent.dao.ObjectDao;
import kg.goent.models.bmc.Bmc;
import kg.goent.models.bmc.SegmentContainer;

import java.util.List;

/**
 * Created by azire on 4/20/2017.
 */
public class SegmentContainerFacade {
    private ObjectDao objectDao = new ObjectDao();

    public void create(SegmentContainer segmentContainer) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().persist(segmentContainer);
        objectDao.commitAndCloseTransaction();
    }

    public void update(SegmentContainer segmentContainer) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().merge(segmentContainer);
        objectDao.commitAndCloseTransaction();
    }

    public void delete(SegmentContainer segmentContainer) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().remove(objectDao.getEntityManager().contains(segmentContainer) ? segmentContainer : objectDao.getEntityManager().merge(segmentContainer));
        objectDao.commitAndCloseTransaction();
    }

    public List<SegmentContainer> findAll(){
        List<SegmentContainer> objectList;
        try {
            objectDao.beginTransaction();
            objectList = objectDao.getEntityManager().createNamedQuery("SegmentContainer.findAll",SegmentContainer.class).getResultList();
        }catch (Exception ex){
            objectList = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return objectList;
    }
    public List<SegmentContainer> findByBmc(Bmc bmc){
        List<SegmentContainer> objectList;
        try {
            objectDao.beginTransaction();
            objectList = objectDao.getEntityManager().createNamedQuery("SegmentContainer.findByBmc",SegmentContainer.class)
                    .setParameter("bmc",bmc).getResultList();
        }catch (Exception ex){
            objectList = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return objectList;
    }

    public SegmentContainer findById(Integer id) {
        SegmentContainer segmentContainer;
        try {
            objectDao.beginTransaction();
            segmentContainer = objectDao.getEntityManager().find(SegmentContainer.class, id);
        }catch (Exception ex){
            segmentContainer = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return segmentContainer;
    }

//    public SegmentContainer findByStatus(String status){
//        SegmentContainer ms;
//        try {
//            objectDao.beginTransaction();
//            ms = objectDao.getEntityManager().createNamedQuery("MemberStatus.findByMemberStatus",SegmentContainer.class)
//                    .setParameter("status",status).getSingleResult();
//        }catch (Exception ex){
//            ms = null;
//        }finally {
//            objectDao.commitAndCloseTransaction();
//        }
//        return ms;
//    }


}
