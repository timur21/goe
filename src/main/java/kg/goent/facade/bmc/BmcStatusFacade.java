package kg.goent.facade.bmc;

import kg.goent.dao.ObjectDao;
import kg.goent.models.bmc.BmcStatus;

import java.util.List;

/**
 * Created by azire on 4/20/2017.
 */
public class BmcStatusFacade {
    private ObjectDao objectDao = new ObjectDao();

    public BmcStatusFacade() {
        if(findAll().size() == 0){
            initialize();
        }
    }

    public void create(BmcStatus bmcStatus) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().persist(bmcStatus);
        objectDao.commitAndCloseTransaction();
    }

    public void update(BmcStatus bmcStatus) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().merge(bmcStatus);
        objectDao.commitAndCloseTransaction();
    }

    public void delete(BmcStatus bmcStatus) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().remove(objectDao.getEntityManager().contains(bmcStatus) ? bmcStatus : objectDao.getEntityManager().merge(bmcStatus));
        objectDao.commitAndCloseTransaction();
    }

    public List<BmcStatus> findAll(){
        List<BmcStatus> objectList;
        try {
            objectDao.beginTransaction();
            objectList = objectDao.getEntityManager().createNamedQuery("BmcStatus.findAll",BmcStatus.class).getResultList();
        }catch (Exception ex){
            objectList = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return objectList;
    }

    public BmcStatus findById(Integer id) {
        BmcStatus bmcStatus;
        try {
            objectDao.beginTransaction();
            bmcStatus = objectDao.getEntityManager().find(BmcStatus.class, id);
        }catch (Exception ex){
            bmcStatus = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return bmcStatus;
    }

    public BmcStatus findByStatus(String status){
        BmcStatus ms;
        try {
            objectDao.beginTransaction();
            ms = objectDao.getEntityManager().createNamedQuery("BmcStatus.findByStatus",BmcStatus.class)
                    .setParameter("status",status).getSingleResult();
        }catch (Exception ex){
            ms = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return ms;
    }

    private void initialize(){
        BmcStatus bs = new BmcStatus("Ready");
        create(bs);
        bs = new BmcStatus("Under construction");
        create(bs);
    }

}
