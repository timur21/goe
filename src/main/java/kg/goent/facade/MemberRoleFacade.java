package kg.goent.facade;

import kg.goent.dao.ObjectDao;
import kg.goent.models.MemberRole;

import java.util.List;

/**
 * Created by azire on 4/20/2017.
 */
public class MemberRoleFacade {
    private ObjectDao objectDao = new ObjectDao();

    public MemberRoleFacade() {
        if(findAll().size() == 0){

        }
    }

    public void create(MemberRole memberRole) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().persist(memberRole);
        objectDao.commitAndCloseTransaction();
    }

    public void update(MemberRole memberRole) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().merge(memberRole);
        objectDao.commitAndCloseTransaction();
    }

    public void delete(MemberRole memberRole) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().remove(memberRole);
        objectDao.commitAndCloseTransaction();
    }

    public List<MemberRole> findAll(){
        List<MemberRole> objectList;
        try {
            objectDao.beginTransaction();
            objectList = objectDao.getEntityManager().createNamedQuery("MemberRole.findAll",MemberRole.class).getResultList();
        }catch (Exception ex){
            objectList = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return objectList;
    }

    public MemberRole findById(Integer id) {
        MemberRole memberRole;
        try {
            objectDao.beginTransaction();
            memberRole = objectDao.getEntityManager().find(MemberRole.class, id);
        }catch (Exception ex){
            memberRole = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return memberRole;
    }

    public MemberRole findByStatus(String status){
        MemberRole ms;
        try {
            objectDao.beginTransaction();
            ms = objectDao.getEntityManager().createNamedQuery("MemberStatus.findByMemberStatus",MemberRole.class)
                    .setParameter("status",status).getSingleResult();
        }catch (Exception ex){
            ms = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return ms;
    }

    private void initialize(){
        MemberRole mr = new MemberRole("administrator");
        create(mr);
        mr = new MemberRole("");
        create(mr);
    }

}
