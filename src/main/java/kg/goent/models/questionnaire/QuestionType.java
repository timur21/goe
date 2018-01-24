package kg.goent.models.questionnaire;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by B-207 on 5/6/2017.
 */
@Entity
@NamedQueries({
        @NamedQuery(name="QuestionType.findAll",
                query = "SELECT qt FROM QuestionType qt")
})
public class QuestionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionTypeId;
    @Column
    private String questionType;

    @OneToMany(mappedBy = "questionType")
    private List<Question> question = new ArrayList<Question>();

    public QuestionType() {}

    public QuestionType(String questionType) {
        this.questionType = questionType;
    }

    public int getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(int questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public List<Question> getQuestion() {
        return question;
    }

    public void setQuestion(List<Question> question) {
        this.question = question;
    }
}
