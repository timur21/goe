package kg.goent.controllers;

import kg.goent.models.questionnaire.Question;
import kg.goent.models.questionnaire.QuestionAnswer;
import org.primefaces.model.chart.PieChartModel;

import java.util.List;

public class ChartModelGenerator {
    private PieChartModel pcm;

    public ChartModelGenerator(){
        pcm = new PieChartModel();
    }

    public PieChartModel generateBinaryPieChart(List<QuestionAnswer> qaList){
        int yes = 0, no = 0;
        pcm.set("no",no);
        pcm.set("yes",yes);
        if(qaList == null || qaList.size() == 0)
            return pcm;

        for(QuestionAnswer qa : qaList){
            if(qa.getAnswer_bool()) { yes++; } else { no++; }
        }
        pcm.set("no",no);
        pcm.set("yes",yes);
        pcm.setLegendPosition("w");
        pcm.setShowDataLabels(true);
        return pcm;
    }
}
