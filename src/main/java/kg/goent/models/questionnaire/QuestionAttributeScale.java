package kg.goent.models.questionnaire;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name="QuestionAttributeScale.findAll",
            query = "SELECT qas FROM QuestionAttributeScale qas"),
        @NamedQuery(name="QuestionAttributeScale.findByQuestion",
                query = "SELECT qas FROM QuestionAttributeScale qas where qas.question = :question")
})
public class QuestionAttributeScale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "questionId")
    private Question question;

    @Column
    private Integer sc_start;

    @Column
    private Integer sc_end;

    @Column
    private Integer expected;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Integer getSc_start() {
        return sc_start;
    }

    public void setSc_start(Integer sc_start) {
        this.sc_start = sc_start;
    }

    public Integer getSc_end() {
        return sc_end;
    }

    public void setSc_end(Integer sc_end) {
        this.sc_end = sc_end;
    }

    public Integer getExpected() {
        return expected;
    }

    public void setExpected(Integer expected) {
        this.expected = expected;
    }
}
