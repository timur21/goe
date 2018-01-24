package kg.goent.facade.questionnaire;

import kg.goent.dao.ObjectDao;
import kg.goent.models.questionnaire.QuestionProperty;

import java.util.List;

/**
 * Created by azire on 4/20/2017.
 */
public class QuestionPropertyFacade {
    private ObjectDao objectDao = new ObjectDao();

    public void create(QuestionProperty questionProperty) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().persist(questionProperty);
        objectDao.commitAndCloseTransaction();
    }

    public void update(QuestionProperty questionProperty) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().merge(questionProperty);
        objectDao.commitAndCloseTransaction();
    }

    public void delete(QuestionProperty questionProperty) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().remove(objectDao.getEntityManager().contains(questionProperty) ? questionProperty : objectDao.getEntityManager().merge(questionProperty));
        objectDao.commitAndCloseTransaction();
    }

    public List<QuestionProperty> findAll(){
        List<QuestionProperty> objectList;
        try {
            objectDao.beginTransaction();
            objectList = objectDao.getEntityManager().createNamedQuery("QuestionProperty.findAll",QuestionProperty.class).getResultList();
        }catch (Exception ex){
            objectList = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return objectList;
    }

    public QuestionProperty findById(Integer id) {
        QuestionProperty questionProperty;
        try {
            objectDao.beginTransaction();
            questionProperty = objectDao.getEntityManager().find(QuestionProperty.class, id);
        }catch (Exception ex){
            questionProperty = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return questionProperty;
    }

//    public QuestionProperty findByStatus(String status){
//        QuestionProperty ms;
//        try {
//            objectDao.beginTransaction();
//            ms = objectDao.getEntityManager().createNamedQuery("MemberStatus.findByMemberStatus",QuestionProperty.class)
//                    .setParameter("status",status).getSingleResult();
//        }catch (Exception ex){
//            ms = null;
//        }finally {
//            objectDao.commitAndCloseTransaction();
//        }
//        return ms;
//    }


}
