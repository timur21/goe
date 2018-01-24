package kg.goent.facade.questionnaire;

import kg.goent.dao.ObjectDao;
import kg.goent.models.questionnaire.Question;

import java.util.List;

/**
 * Created by azire on 4/20/2017.
 */
public class QuestionFacade {
    private ObjectDao objectDao = new ObjectDao();

    public void create(Question question) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().persist(question);
        objectDao.commitAndCloseTransaction();
    }

    public void update(Question question) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().merge(question);
        objectDao.commitAndCloseTransaction();
    }

    public void delete(Question question) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().remove(objectDao.getEntityManager().contains(question) ? question : objectDao.getEntityManager().merge(question));
        objectDao.commitAndCloseTransaction();
    }

    public List<Question> findAll(){
        List<Question> objectList;
        try {
            objectDao.beginTransaction();
            objectList = objectDao.getEntityManager().createNamedQuery("Question.findAll",Question.class).getResultList();
        }catch (Exception ex){
            objectList = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return objectList;
    }

    public Question findById(Integer id) {
        Question question;
        try {
            objectDao.beginTransaction();
            question = objectDao.getEntityManager().find(Question.class, id);
        }catch (Exception ex){
            question = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return question;
    }

//    public Question findByStatus(String status){
//        Question ms;
//        try {
//            objectDao.beginTransaction();
//            ms = objectDao.getEntityManager().createNamedQuery("MemberStatus.findByMemberStatus",Question.class)
//                    .setParameter("status",status).getSingleResult();
//        }catch (Exception ex){
//            ms = null;
//        }finally {
//            objectDao.commitAndCloseTransaction();
//        }
//        return ms;
//    }


}
