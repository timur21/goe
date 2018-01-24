package kg.goent.models.questionnaire;

import kg.goent.models.bmc.Segment;

import javax.persistence.*;
import java.util.List;

/**
 * Created by B-207 on 5/6/2017.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Question.findAll",
                query = "SELECT q FROM Question q"),
        @NamedQuery(name = "Question.findBy",
                query = "SELECT q FROM Question q")
})
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionId;

    @Column
    private String question;

    @OneToMany(mappedBy = "question")
    private List<QuestionAnswer> questionAnswerList;

    @ManyToOne
    @JoinColumn(name = "questionTypeId")
    private QuestionType questionType;

    @ManyToOne
    @JoinColumn(name = "segmentId")
    private Segment segment;

    @OneToMany(mappedBy = "question",fetch = FetchType.EAGER)
    private List<QuestionProperty> questionPropertyList;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<QuestionAnswer> getQuestionAnswerList() {
        return questionAnswerList;
    }

    public void setQuestionAnswerList(List<QuestionAnswer> questionAnswerList) {
        this.questionAnswerList = questionAnswerList;
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

    public List<QuestionProperty> getQuestionPropertyList() {
        return questionPropertyList;
    }

    public void setQuestionPropertyList(List<QuestionProperty> questionPropertyList) {
        this.questionPropertyList = questionPropertyList;
    }
}
