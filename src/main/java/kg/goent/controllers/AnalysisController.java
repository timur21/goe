package kg.goent.controllers;

import kg.goent.models.hypothesis.HypothesisContainer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import static kg.goent.tools.ViewPath.ANALYSIS_OVERVIEW;
import static kg.goent.tools.ViewPath.QUESTIONNAIRE_OVERVIEW;
import static kg.goent.tools.ViewPath.REDIRECT;


@ManagedBean
@ViewScoped
public class AnalysisController extends GetReqBean {

    private HypothesisContainer hypothesisContainer;



    public String initializeAnalysis() {
        return ANALYSIS_OVERVIEW + REDIRECT + "projectId=" + projectId;
    }

    public String analysisOverview(){
        String redirect = ANALYSIS_OVERVIEW + REDIRECT + "projectId=" + projectId;
        return redirect;
    }
}
