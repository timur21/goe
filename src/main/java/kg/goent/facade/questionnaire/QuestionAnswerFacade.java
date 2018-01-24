package kg.goent.facade.questionnaire;

import kg.goent.dao.ObjectDao;
import kg.goent.models.questionnaire.QuestionAnswer;

import java.util.List;

/**
 * Created by azire on 4/20/2017.
 */
public class QuestionAnswerFacade {
    private ObjectDao objectDao = new ObjectDao();

    public void create(QuestionAnswer questionAnswer) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().persist(questionAnswer);
        objectDao.commitAndCloseTransaction();
    }

    public void update(QuestionAnswer questionAnswer) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().merge(questionAnswer);
        objectDao.commitAndCloseTransaction();
    }

    public void delete(QuestionAnswer questionAnswer) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().remove(objectDao.getEntityManager().contains(questionAnswer) ? questionAnswer : objectDao.getEntityManager().merge(questionAnswer));
        objectDao.commitAndCloseTransaction();
    }

    public List<QuestionAnswer> findAll(){
        List<QuestionAnswer> objectList;
        try {
            objectDao.beginTransaction();
            objectList = objectDao.getEntityManager().createNamedQuery("QuestionAnswer.findAll",QuestionAnswer.class).getResultList();
        }catch (Exception ex){
            objectList = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return objectList;
    }

    public QuestionAnswer findById(Integer id) {
        QuestionAnswer questionAnswer;
        try {
            objectDao.beginTransaction();
            questionAnswer = objectDao.getEntityManager().find(QuestionAnswer.class, id);
        }catch (Exception ex){
            questionAnswer = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return questionAnswer;
    }

//    public QuestionAnswer findByStatus(String status){
//        QuestionAnswer ms;
//        try {
//            objectDao.beginTransaction();
//            ms = objectDao.getEntityManager().createNamedQuery("MemberStatus.findByMemberStatus",QuestionAnswer.class)
//                    .setParameter("status",status).getSingleResult();
//        }catch (Exception ex){
//            ms = null;
//        }finally {
//            objectDao.commitAndCloseTransaction();
//        }
//        return ms;
//    }


}
