package kg.goent.models.questionnaire;

import kg.goent.models.bmc.Segment;
import kg.goent.models.project.Project;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by B-207 on 5/6/2017.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Question.findAll",
                query = "SELECT q FROM Question q"),
        @NamedQuery(name = "Question.findBy",
                query = "SELECT q FROM Question q"),
        @NamedQuery(name = "Question.findByProject",
                query = "SELECT q FROM Question q where q.project = :project" ),


})
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionId;

    @Column
    private String question;

    @OneToOne(mappedBy = "question", cascade = CascadeType.ALL)
    private QuestionAttributeScale questionAttributeScale;

    @ManyToOne
    @JoinColumn(name = "questionTypeId")
    private QuestionType questionType;

    @OneToOne
    @JoinColumn(name = "segmentId")
    private Segment segment;

    @Column
    private Boolean expected;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;

    @OneToMany(mappedBy = "question")
    private List<QuestionAnswer> questionAnswerList;

    public List<QuestionAnswer> getQuestionAnswerList() {
        return questionAnswerList;
    }

    public void setQuestionAnswerList(List<QuestionAnswer> questionAnswerList) {
        this.questionAnswerList = questionAnswerList;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public Segment getSegment() {
        return segment;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Boolean getExpected() {
        return expected;
    }

    public void setExpected(Boolean expected) {
        this.expected = expected;
    }

    public QuestionAttributeScale getQuestionAttributeScale() {
        return questionAttributeScale;
    }

    public void setQuestionAttributeScale(QuestionAttributeScale questionAttributeScale) {
        this.questionAttributeScale = questionAttributeScale;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
