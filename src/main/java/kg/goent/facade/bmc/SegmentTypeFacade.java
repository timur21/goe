package kg.goent.facade.bmc;

import kg.goent.dao.ObjectDao;
import kg.goent.models.bmc.SegmentType;

import java.util.List;

/**
 * Created by azire on 4/20/2017.
 */
public class SegmentTypeFacade {
    private ObjectDao objectDao = new ObjectDao();

    public SegmentTypeFacade() {
        if(findAll().size()==0){
            initialize();
        }
    }

    public void create(SegmentType segmentType) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().persist(segmentType);
        objectDao.commitAndCloseTransaction();
    }

    public void update(SegmentType segmentType) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().merge(segmentType);
        objectDao.commitAndCloseTransaction();
    }

    public void delete(SegmentType segmentType) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().remove(objectDao.getEntityManager().contains(segmentType) ? segmentType : objectDao.getEntityManager().merge(segmentType));
        objectDao.commitAndCloseTransaction();
    }

    public List<SegmentType> findAll(){
        List<SegmentType> objectList;
        try {
            objectDao.beginTransaction();
            objectList = objectDao.getEntityManager().createNamedQuery("SegmentType.findAll",SegmentType.class).getResultList();
        }catch (Exception ex){
            objectList = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return objectList;
    }

    public SegmentType findById(Integer id) {
        SegmentType segmentType;
        try {
            objectDao.beginTransaction();
            segmentType = objectDao.getEntityManager().find(SegmentType.class, id);
        }catch (Exception ex){
            segmentType = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return segmentType;
    }

    public List<SegmentType> findAllOrdered(){
        List<SegmentType> objectList;
        try {
            objectDao.beginTransaction();
            objectList = objectDao.getEntityManager().createNamedQuery("SegmentType.findAllOrdered",SegmentType.class).getResultList();
        }catch (Exception ex){
            objectList = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return objectList;
    }

//    public SegmentType findByStatus(String status){
//        SegmentType ms;
//        try {
//            objectDao.beginTransaction();
//            ms = objectDao.getEntityManager().createNamedQuery("MemberStatus.findByMemberStatus",SegmentType.class)
//                    .setParameter("status",status).getSingleResult();
//        }catch (Exception ex){
//            ms = null;
//        }finally {
//            objectDao.commitAndCloseTransaction();
//        }
//        return ms;
//    }

    private void initialize(){
        SegmentType st = new SegmentType("Customer Segment",1);
        create(st);
        st = new SegmentType("Value Proposition",2);
        create(st);
        st = new SegmentType("Distribution Channel",3);
        create(st);
        st = new SegmentType("Customer Relationships",4);
        create(st);
        st = new SegmentType("Revenue Streams",5);
        create(st);
        st = new SegmentType("Key Resources",6);
        create(st);
        st = new SegmentType("Key Activities",7);
        create(st);
        st = new SegmentType("Key Partners",8);
        create(st);
        st = new SegmentType("Cost Structure",9);
        create(st);
    }

}
