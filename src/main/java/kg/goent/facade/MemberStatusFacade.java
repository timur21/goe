package kg.goent.facade;

import kg.goent.dao.ObjectDao;
import kg.goent.models.project.MemberStatus;

import java.util.List;

/**
 * Created by azire on 4/20/2017.
 */
public class MemberStatusFacade {
    private ObjectDao objectDao = new ObjectDao();

    public MemberStatusFacade() {
        if(findAll().size() == 0){
            initialize();
        }
    }

    public void create(MemberStatus memberStatus) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().persist(memberStatus);
        objectDao.commitAndCloseTransaction();
    }

    public void update(MemberStatus memberStatus) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().merge(memberStatus);
        objectDao.commitAndCloseTransaction();
    }

    public void delete(MemberStatus memberStatus) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().remove(objectDao.getEntityManager().contains(memberStatus) ? memberStatus : objectDao.getEntityManager().merge(memberStatus));
        objectDao.commitAndCloseTransaction();
    }

    public List<MemberStatus> findAll(){
        List<MemberStatus> objectList;
        try {
            objectDao.beginTransaction();
            objectList = objectDao.getEntityManager().createNamedQuery("MemberStatus.findAll",MemberStatus.class).getResultList();
        }catch (Exception ex){
            objectList = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return objectList;
    }

    public MemberStatus findById(Integer id) {
        MemberStatus memberStatus;
        try {
            objectDao.beginTransaction();
            memberStatus = objectDao.getEntityManager().find(MemberStatus.class, id);
        }catch (Exception ex){
            memberStatus = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return memberStatus;
    }

    public MemberStatus findByStatus(String status){
        MemberStatus ms;
        try {
            objectDao.beginTransaction();
            ms = objectDao.getEntityManager().createNamedQuery("MemberStatus.findByMemberStatus",MemberStatus.class)
                    .setParameter("status",status).getSingleResult();
        }catch (Exception ex){
            ms = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return ms;
    }

    private void initialize(){
        MemberStatus ms = new MemberStatus("accepted");
        create(ms);
        ms = new MemberStatus("banned");
        create(ms);
        ms = new MemberStatus("pending");
        create(ms);
        ms = new MemberStatus("deleted/retired");
        create(ms);
    }
}
