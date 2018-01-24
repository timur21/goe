package kg.goent.models.questionnaire;

import javax.persistence.*;

/**
 * Created by B-207 on 5/6/2017.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "QuestionProperties.findAll",
                query = "SELECT qp FROM QuestionProperty qp")
})
public class QuestionProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionPropertyId;

    @Column
    private String property;

    @Column
    private int min,max;

    @Column
    private boolean expectedAnswer;

    @ManyToOne
    @JoinColumn(name = "questionId")
    private Question question;

    public int getQuestionPropertyId() {
        return questionPropertyId;
    }

    public void setQuestionPropertyId(int questionPropertyId) {
        this.questionPropertyId = questionPropertyId;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public boolean isExpectedAnswer() {
        return expectedAnswer;
    }

    public void setExpectedAnswer(boolean expectedAnswer) {
        this.expectedAnswer = expectedAnswer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
