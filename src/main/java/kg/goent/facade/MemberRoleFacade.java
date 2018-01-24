package kg.goent.facade;

import kg.goent.dao.ObjectDao;
import kg.goent.models.project.MemberRole;

import java.util.List;

/**
 * Created by azire on 4/20/2017.
 */
public class MemberRoleFacade {
    private ObjectDao objectDao = new ObjectDao();

    public MemberRoleFacade() {
        if(findAll().size() == 0){
            initialize();
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
        objectDao.getEntityManager().remove(objectDao.getEntityManager().contains(memberRole) ? memberRole : objectDao.getEntityManager().merge(memberRole));
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

    public MemberRole findByRole(String status){
        MemberRole ms;
        try {
            objectDao.beginTransaction();
            ms = objectDao.getEntityManager().createNamedQuery("MemberRole.findByMemberRole",MemberRole.class)
                    .setParameter("status",status).getSingleResult();
        }catch (Exception ex){
            ms = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return ms;
    }

    private void initialize(){
        MemberRole mr = new MemberRole("team leader");
        create(mr);
        mr = new MemberRole("team member");
        create(mr);
        mr = new MemberRole("observer");
        create(mr);
    }

    public List<MemberRole> findAllSimpleUsers(){
        List<MemberRole> memberRoleList;
        try {
            objectDao.beginTransaction();
            memberRoleList = objectDao.getEntityManager().createNamedQuery("MemberRole.findAllSimpleUsers",MemberRole.class).getResultList();
        }catch (Exception ex){
            memberRoleList = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return memberRoleList;
    }

}
