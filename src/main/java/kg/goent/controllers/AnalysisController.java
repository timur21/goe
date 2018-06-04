package kg.goent.controllers;

import kg.goent.facade.project.ProjectFacade;
import kg.goent.facade.questionnaire.QuestionAnswerFacade;
import kg.goent.facade.questionnaire.QuestionFacade;
import kg.goent.models.hypothesis.HypothesisContainer;
import kg.goent.models.project.Project;
import kg.goent.models.questionnaire.Question;
import kg.goent.models.questionnaire.QuestionAnswer;
import org.primefaces.model.chart.PieChartModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static kg.goent.tools.ViewPath.ANALYSIS_OVERVIEW;
import static kg.goent.tools.ViewPath.QUESTIONNAIRE_OVERVIEW;
import static kg.goent.tools.ViewPath.REDIRECT;


@ManagedBean
@ViewScoped
public class AnalysisController extends GetReqBean {

    private QuestionAnswerFacade qaFacade;

    private QuestionFacade questionFacade;

    private HypothesisContainer hypothesisContainer;

    private HashMap<Integer,PieChartModel> pieChartMap;

    private HashMap<Integer,String> chartMap;

    private List<Question> questionList;

    private HashMap<Integer,List<QuestionAnswer>> answerList;

    private ChartModelGenerator chartModelGenerator;

    @PostConstruct
    public void init(){
        qaFacade = new QuestionAnswerFacade();
        questionFacade = new QuestionFacade();
        chartModelGenerator = new ChartModelGenerator();
        questionList = new ArrayList<>();
        answerList = new HashMap<>();
        pieChartMap = new HashMap<>();
    }

    @Override
    public void setProjectId(int projectId) {
        super.setProjectId(projectId);
        Project project = new ProjectFacade().findById(projectId);
        questionList = questionFacade.findByProject(project);
        for( Question q : questionList ){
            if( q.getQuestionType().getQuestionTypeId() == 1 ){
                answerList.put( q.getQuestionId(),qaFacade.findAnswersByQuestion(q) );
                pieChartMap.put( q.getQuestionId(),
                        chartModelGenerator.generateBinaryPieChart(answerList.get(q.getQuestionId())) );
                pieChartMap.get(q.getQuestionId()).setTitle(q.getQuestion());
            }
        }
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public String initializeAnalysis() {
        return ANALYSIS_OVERVIEW + REDIRECT + "projectId=" + projectId;
    }

    public String analysisOverview(){
        String redirect = ANALYSIS_OVERVIEW + REDIRECT + "projectId=" + projectId;
        return redirect;
    }

    public PieChartModel getPieChart(int questionId){
        return pieChartMap.get(questionId);
    }
}
