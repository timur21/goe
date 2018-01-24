package kg.goent.tools;

/**
 * Created by azire on 5/5/2017.
 */
public class ViewPath {
    public static final String
        INDEX = "/INDEX",
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
        HYPOTHESIS_OVERVIEW = HYPOTHESIS_FOLDER+"/hypothesisOverview?";

    public static final String REDIRECT = "faces-redirect=true&";
}
