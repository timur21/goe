package kg.goent.models.questionnaire;

import javax.persistence.*;

/**
 * Created by B-207 on 5/6/2017.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "QuestionAnswer.findAll",
                query = "SELECT qa FROM QuestionAnswer qa"),
        @NamedQuery(name = "QuestionAnswer.findByQuestion",
                query = "SELECT qa FROM QuestionAnswer qa WHERE qa.question = :question")
})
public class QuestionAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "questionId")
    private Question question;

    @Column
    private Boolean answer_bool;

    @Column
    private Integer answer_scale;

    @Column
    private String answer_text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Boolean getAnswer_bool() {
        return answer_bool;
    }

    public void setAnswer_bool(Boolean answer_bool) {
        this.answer_bool = answer_bool;
    }

    public Integer getAnswer_scale() {
        return answer_scale;
    }

    public void setAnswer_scale(Integer answer_scale) {
        this.answer_scale = answer_scale;
    }

    public String getAnswer_text() {
        return answer_text;
    }

    public void setAnswer_text(String answer_text) {
        this.answer_text = answer_text;
    }
}