package kg.goent.models.questionnaire;

import javax.persistence.*;

/**
 * Created by B-207 on 5/6/2017.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "QuestionAnswer.findAll",
                query = "SELECT qa FROM QuestionAnswer qa")
})
public class QuestionAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String answer;

    @ManyToOne
    @JoinColumn(name = "questionId")
    private Question question;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
