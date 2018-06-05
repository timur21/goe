package kg.goent.controllers;

import kg.goent.facade.project.ProjectFacade;
import kg.goent.facade.questionnaire.QuestionAnswerFacade;
import kg.goent.facade.questionnaire.QuestionAttributeScaleFacade;
import kg.goent.facade.questionnaire.QuestionFacade;
import kg.goent.facade.questionnaire.QuestionTypeFacade;
import kg.goent.models.bmc.Segment;
import kg.goent.models.hypothesis.Hypothesis;
import kg.goent.models.project.Project;
import kg.goent.models.questionnaire.Question;
import kg.goent.models.questionnaire.QuestionAnswer;
import kg.goent.models.questionnaire.QuestionAttributeScale;
import kg.goent.models.questionnaire.QuestionType;
import kg.goent.tools.Tools;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import java.util.HashMap;
import java.util.List;

@ManagedBean
@ViewScoped
public class QuestionController extends GetReqBean{

    private QuestionFacade questionFacade;
    private ProjectFacade projectFacade;
    private QuestionAnswerFacade qaf;
    private QuestionAttributeScaleFacade qasf;
    private QuestionTypeFacade qtf;
    private List<Question> questionList;
    private HashMap<Integer, QuestionAnswer> answerMap;
    private HashMap<Integer, Question> questionMap;

    @PostConstruct
    public void init(){
        questionFacade = new QuestionFacade();
        projectFacade = new ProjectFacade();
        qaf = new QuestionAnswerFacade();
        qasf = new QuestionAttributeScaleFacade();
        qtf = new QuestionTypeFacade();
        questionMap = new HashMap<>();
        answerMap = new HashMap<>();
    }

    public void setProjectId(int i){
        projectId = i;
        if(i > 0){
            Project p = projectFacade.findById(projectId);
            questionList  =  questionFacade.findByProject(p);
            if(questionList != null && questionList.size() > 0){
                for(Question q : questionList){
                    questionMap.put(q.getSegment().getSegmentId(),q);
                }
            }
        }
    }

    public Question getQuestionForSegment(Segment segment){
        return questionMap.get(segment.getSegmentId());
    }

    public boolean hasQuestion(Segment segment){
        return getQuestionForSegment(segment) != null;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public void createQuestionForSegment(Segment segment){
        Question question = new Question();
        question.setSegment(segment);
        question.setProject(segment.getSegmentContainer().getBmc().getProject());
        question.setQuestionType(new QuestionType());
        question.setQuestionAttributeScale(new QuestionAttributeScale());
        question.getQuestionType().setQuestionTypeId(0);
        questionMap.put(segment.getSegmentId(),question);

        System.out.println("added to " + segment.getSegmentId());
    }

    public List<QuestionType> getQuestionTypeList(){
        return qtf.findAll();
    }

    public QuestionAnswer getQuestionAnswerForQuestion(Question question){
        QuestionAnswer qa = null;
        if(!answerMap.containsKey(question.getQuestionId())){
            qa = new QuestionAnswer();
            qa.setQuestion(question);
            answerMap.put(question.getQuestionId(),qa);
        } else {
            qa = answerMap.get(question.getQuestionId());
        }
        return qa;
    }

    public void questionTypeUpdater(Segment s){
        System.out.println("updated: " + getQuestionForSegment(s).getQuestionType().getQuestionType());
    }

    public void questionTypeUpdate(AjaxBehaviorEvent event){
        System.out.println("e: " + event.getComponent().getClass().getName());
    }

    public void createQuestion(Segment segment){
        Question question = getQuestionForSegment(segment);
        if(question.getQuestionId() == null || question.getQuestionId() == 0)
            new QuestionFacade().create(question);
        else
            new QuestionFacade().update(question);
        if(question.getQuestionType().getQuestionTypeId() == 2){
            QuestionAttributeScale qas = question.getQuestionAttributeScale();
            if(qas.getId() == null || qas.getId() == 0)
                qasf.create(qas);
            else
                qasf.update(qas);
        }
        Tools.faceMessageWarn("saved question", "");
    }

    public void saveAnswers(){
        for(Question q : questionList){
            qaf.create(getQuestionAnswerForQuestion(q));
        }
        answerMap = new HashMap<>();
        Tools.faceMessageInfo("Survey data saved.","");
    }
}
