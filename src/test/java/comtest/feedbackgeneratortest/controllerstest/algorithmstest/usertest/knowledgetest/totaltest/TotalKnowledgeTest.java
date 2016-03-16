package comtest.feedbackgeneratortest.controllerstest.algorithmstest.usertest.knowledgetest.totaltest;

import com.feedbackgenerator.controllers.algorithms.user.total.TotalKnowledge;
import com.feedbackgenerator.models.Knowledge;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Ershadi Sayuri on 3/5/2016.
 */
public class TotalKnowledgeTest {

    @Test
    public void testCalculateUserTotalKnowledge() throws Exception {
        TotalKnowledge totalKnowledge = new TotalKnowledge();
        ArrayList<Knowledge> knowledges = totalKnowledge.calculateUserTotalKnowledge(3, 2);
        for (Knowledge k : knowledges) {
            System.out.println(k.getTopicKnowledge());
        }
    }
}