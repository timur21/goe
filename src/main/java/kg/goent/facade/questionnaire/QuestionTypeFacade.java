package kg.goent.facade.questionnaire;

import kg.goent.dao.ObjectDao;
import kg.goent.models.questionnaire.QuestionType;

import java.util.List;

/**
 * Created by timur on 4/20/2017.
 */
public class QuestionTypeFacade {
    private ObjectDao objectDao = new ObjectDao();

    public QuestionTypeFacade(){
        if(findAll().size() == 0){
            initialize();
        }
    }

    public void create(QuestionType questionType) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().persist(questionType);
        objectDao.commitAndCloseTransaction();
    }

    public void update(QuestionType questionType) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().merge(questionType);
        objectDao.commitAndCloseTransaction();
    }

    public void delete(QuestionType questionType) {
        objectDao.beginTransaction();
        objectDao.getEntityManager().remove(objectDao.getEntityManager().contains(questionType) ? questionType : objectDao.getEntityManager().merge(questionType));
        objectDao.commitAndCloseTransaction();
    }

    public List<QuestionType> findAll(){
        List<QuestionType> objectList;
        try {
            objectDao.beginTransaction();
            objectList = objectDao.getEntityManager().createNamedQuery("QuestionType.findAll",QuestionType.class).getResultList();
        }catch (Exception ex){
            objectList = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return objectList;
    }

    public QuestionType findById(Integer id) {
        QuestionType questionType;
        try {
            objectDao.beginTransaction();
            questionType = objectDao.getEntityManager().find(QuestionType.class, id);
        }catch (Exception ex){
            questionType = null;
        }finally {
            objectDao.commitAndCloseTransaction();
        }
        return questionType;
    }

    private void initialize(){
        QuestionType qt = new QuestionType("True/False");
        create(qt);
        qt = new QuestionType("Numerical");
        create(qt);
        qt = new QuestionType("Text");
        create(qt);
        
    }

}
