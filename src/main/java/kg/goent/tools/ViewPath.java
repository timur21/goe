package kg.goent.tools;

import javax.faces.bean.ManagedBean;

/**
 * Created by azire on 5/5/2017.
 */
@ManagedBean
public class ViewPath {
    public static final String
        SIGN_IN = "/signin?",
        SIGN_UP = "/signup?",
        ACTIVATE = "/activate?",
        INDEX = "/INDEX?",
        BMC_FOLDER = "/pages/bmc",
        BMC_OVERVIEW = BMC_FOLDER +"/bmcOverview?",
        ADD_SEGMENT_CONTAINER = BMC_FOLDER +"/addSegmentContainer?",
        EDIT_SEGMENT_CONTAINER = BMC_FOLDER +"/editSegmentContainer?",

    PROJECT_FOLDER = "/pages/project",
        PROJECT_ADD_MEMEBER = PROJECT_FOLDER +"/projectAddMember?",
        PROJECT_EDIT = PROJECT_FOLDER +"/editProject?",
        PROJECT_OVERVIEW = PROJECT_FOLDER +"/projectOverview?",
        PROJECT_SETTINGS = PROJECT_FOLDER +"/projectSettings?",
        ADD_PROJECT = PROJECT_FOLDER +"/addProject?",

    HYPOTHESIS_FOLDER = "/pages/hypothesis",
        HYPOTHESIS_OVERVIEW = HYPOTHESIS_FOLDER+"/hypothesisOverview?",

    QUESTIONNAIRE_FOLDER = "/pages/questionnaire/",
        QUESTIONNAIRE_OVERVIEW = QUESTIONNAIRE_FOLDER + "QuestionnaireOverview?",

    ANALYSIS_FOLDER = "/pages/analysis/",
    ANALYSIS_OVERVIEW = ANALYSIS_FOLDER + "analysisOverview?";

    public static final String REDIRECT = "faces-redirect=true&";

    public final String
        URL_BASE = "/hibernate/javax.faces.resource",
        AUTH_TAIL = ".xhtml?ln=auth",
            AUTH_BG_VIDEO_WEBM = URL_BASE+"/movie/WEBM/For_Wes.webm"+AUTH_TAIL,
            AUTH_BG_VIDEO_MP4 = URL_BASE+"/movie/MP4/For_Wes.mp4"+AUTH_TAIL,

        CSS_TAIL = ".xhtml?ln=css",
        MAIN_TAIL = ".xhtml?ln=main",
        REGISTER_TAIL = ".xhtml?ln=register",
        TUTORIAL_TAIL = ".xhtml?ln=tutorial",
            TUTORIAL_MAIN_VIDEO = URL_BASE + "/videos/MP4/a-working-man.mp4" + TUTORIAL_TAIL,
            TUTORIAL_BG_HOME5 = URL_BASE + "/images/home5.jpg" + TUTORIAL_TAIL;

    public String getAuthBgVideoWebm() {
        return AUTH_BG_VIDEO_WEBM;
    }

    public String getAuthBgVideoMp4() {
        return AUTH_BG_VIDEO_MP4;
    }

    public String getTUTORIAL_MAIN_VIDEO() {
        return TUTORIAL_MAIN_VIDEO;
    }

    public String getTUTORIAL_BG_HOME5() {
        return TUTORIAL_BG_HOME5;
    }
}
