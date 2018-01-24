package kg.goent.facade.bmc;

import kg.goent.dao.ObjectDao;
import kg.goent.models.bmc.Segment;
import kg.goent.models.bmc.SegmentType;
import kg.goent.models.hypothesis.Hypothesis;

import java.util.List;

/**
 * Created by azire on 4/20/2017.
 */
public class SegmentFacade {
    private ObjectDao objectDao = new ObjectDao();

    public void create(Segment segment) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().persist(segment);
        objectDao.commitAndCloseTransaction();

    }

    public void update(Segment segment) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().merge(segment);
        objectDao.commitAndCloseTransaction();
    }

    public void delete(Segment segment) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().remove(objectDao.getEntityManager().contains(segment) ? segment : objectDao.getEntityManager().merge(segment));
        objectDao.commitAndCloseTransaction();
    }

    public List<Segment> findAll(){
        List<Segment> objectList;
        try {
            objectDao.beginTransaction();
            objectList = objectDao.getEntityManager().createNamedQuery("Segment.findAll",Segment.class).getResultList();
        }catch (Exception ex){
            objectList = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return objectList;
    }

    public Segment findById(Integer id) {
        Segment segment;
        try {
            objectDao.beginTransaction();
            segment = objectDao.getEntityManager().find(Segment.class, id);
        }catch (Exception ex){
            segment = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return segment;
    }

    public List<Segment> findBySegmentType(SegmentType st){
        List<Segment> ms;
        try {
            objectDao.beginTransaction();
            ms = objectDao.getEntityManager().createNamedQuery("Segment.findBySegmentType",Segment.class)
                    .setParameter("segmentType",st).getResultList();
        }catch (Exception ex){
            ms = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return ms;
    }


}
