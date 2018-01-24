package kg.goent.facade.bmc;

import kg.goent.dao.ObjectDao;
import kg.goent.models.bmc.Bmc;
import kg.goent.models.project.Project;

/**
 * Created by azire on 4/20/2017.
 */
public class BmcFacade {
    private ObjectDao objectDao = new ObjectDao();

    public void create(Bmc bmc) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().persist(bmc);
        objectDao.commitAndCloseTransaction();
    }

    public void update(Bmc bmc) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().merge(bmc);
        objectDao.commitAndCloseTransaction();
    }

    public void delete(Bmc bmc) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().remove(objectDao.getEntityManager().contains(bmc) ? bmc : objectDao.getEntityManager().merge(bmc));
        objectDao.commitAndCloseTransaction();
    }

//    public List<Bmc> findAll(){
//        List<Bmc> objectList;
//        try {
//            objectDao.beginTransaction();
//            objectList = objectDao.getEntityManager().createNamedQuery("Bmc.findAll",Bmc.class).getResultList();
//        }catch (Exception ex){
//            objectList = null;
//        }finally {
//            objectDao.commitAndCloseTransaction();
//        }
//        return objectList;
//    }

    public Bmc findById(int id) {
        Bmc bmc;
        try {
            objectDao.beginTransaction();
            bmc = objectDao.getEntityManager().find(Bmc.class, id);
        }catch (Exception ex){
            bmc = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return bmc;
    }

    public Bmc findByProject(Project project){
        Bmc bmc;
        try {
            objectDao.beginTransaction();
            bmc = objectDao.getEntityManager().createNamedQuery("Bmc.findByProject",Bmc.class)
                    .setParameter("project",project).getSingleResult();
        }catch (Exception ex){
            bmc = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return bmc;
    }

//    public Bmc findByStatus(String status){
//        Bmc ms;
//        try {
//            objectDao.beginTransaction();
//            ms = objectDao.getEntityManager().createNamedQuery("MemberStatus.findByMemberStatus",Bmc.class)
//                    .setParameter("status",status).getSingleResult();
//        }catch (Exception ex){
//            ms = null;
//        }finally {
//            objectDao.commitAndCloseTransaction();
//        }
//        return ms;
//    }


}
