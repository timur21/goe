package kg.goent.facade.questionnaire;

import kg.goent.dao.ObjectDao;
import kg.goent.models.questionnaire.QuestionAttributeScale;

import java.util.List;

/**
 * Created by azire on 4/20/2017.
 */
public class QuestionAttributeScaleFacade {
    private ObjectDao objectDao = new ObjectDao();

    public void create(QuestionAttributeScale object) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().persist(object);
        objectDao.commitAndCloseTransaction();
    }

    public void update(QuestionAttributeScale object) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().merge(object);
        objectDao.commitAndCloseTransaction();
    }

    public void delete(QuestionAttributeScale object) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().remove(objectDao.getEntityManager().contains(object) ? object : objectDao.getEntityManager().merge(object));
        objectDao.commitAndCloseTransaction();
    }

    public List<QuestionAttributeScale> findAll(){
        List<QuestionAttributeScale> objectList;
        try {
            objectDao.beginTransaction();
            objectList = objectDao.getEntityManager().createNamedQuery("QuestionAttributeScale.findAll",QuestionAttributeScale.class).getResultList();
        }catch (Exception ex){
            objectList = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return objectList;
    }

    public QuestionAttributeScale findById(Integer id) {
        QuestionAttributeScale object;
        try {
            objectDao.beginTransaction();
            object = objectDao.getEntityManager().find(QuestionAttributeScale.class, id);
        }catch (Exception ex){
            object = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return object;
    }

//    public QuestionAttributeScale findByStatus(String status){
//        QuestionAttributeScale ms;
//        try {
//            objectDao.beginTransaction();
//            ms = objectDao.getEntityManager().createNamedQuery("MemberStatus.findByMemberStatus",QuestionAttributeScale.class)
//                    .setParameter("status",status).getSingleResult();
//        }catch (Exception ex){
//            ms = null;
//        }finally {
//            objectDao.commitAndCloseTransaction();
//        }
//        return ms;
//    }


}
