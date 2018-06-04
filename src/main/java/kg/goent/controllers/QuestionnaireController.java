package kg.goent.controllers;

import kg.goent.facade.MemberRoleFacade;
import kg.goent.facade.bmc.SegmentContainerFacade;
        import kg.goent.facade.hypothesis.HypothesisContainerFacade;
        import kg.goent.facade.hypothesis.HypothesisFacade;
        import kg.goent.facade.project.ProjectFacade;
        import kg.goent.models.bmc.Segment;
        import kg.goent.models.bmc.SegmentContainer;
        import kg.goent.models.hypothesis.Hypothesis;
        import kg.goent.models.hypothesis.HypothesisContainer;
import kg.goent.models.project.MemberRole;
import kg.goent.models.project.Project;
        import kg.goent.tools.Tools;

        import javax.faces.bean.ManagedBean;
        import javax.faces.bean.ViewScoped;
        import java.util.ArrayList;
        import java.util.List;

        import static kg.goent.tools.ViewPath.*;


@ManagedBean
@ViewScoped
public class QuestionnaireController extends GetReqBean {

    private HypothesisContainer hypothesisContainer;



    public String initializeQuestionnaire() {
        return QUESTIONNAIRE_OVERVIEW + REDIRECT + "projectId=" + projectId;
    }

    public String questionnaireOverView(){
        String redirect = QUESTIONNAIRE_OVERVIEW + REDIRECT + "projectId=" + projectId;
        return redirect;
    }

}
