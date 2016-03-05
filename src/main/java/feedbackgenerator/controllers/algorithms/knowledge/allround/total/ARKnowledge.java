package feedbackgenerator.controllers.algorithms.knowledge.allround.total;

import feedbackgenerator.controllers.algorithms.knowledge.allround.categories.ARDifficultyOrTopicKnowledge;
import feedbackgenerator.controllers.algorithms.knowledge.allround.categories.ARDifficultyTopicKnowledge;
import feedbackgenerator.controllers.algorithms.knowledge.allround.categories.ARQuestionKnowledge;
import feedbackgenerator.controllers.algorithms.knowledge.quizwise.categories.QWDifficultyOrTopicKnowledge;
import feedbackgenerator.controllers.algorithms.knowledge.quizwise.categories.QWDifficultyTopicKnowledge;
import feedbackgenerator.controllers.algorithms.knowledge.quizwise.total.QWOverallQuizKnowledge;
import feedbackgenerator.models.QuizSlot;
import feedbackgenerator.models.Title;

import java.util.ArrayList;

/**
 * Created by Ershadi Sayuri on 2/27/2016.
 */
public class ARKnowledge {

    public void findAllRoundKnowledge(int userId, int quizId) throws Exception {
        ARQuestionKnowledge arQuestionKnowledge = new ARQuestionKnowledge();
        double arQuizAttemptProgress = arQuestionKnowledge.findQuizAttemptProgress(userId);
        double arQuizGrade = arQuestionKnowledge.findQuizGrade(userId);

        QWOverallQuizKnowledge qwOverallQuizKnowledge = new QWOverallQuizKnowledge();
        double qwQuizGrade = qwOverallQuizKnowledge.findAverageGrade(userId, quizId);
        double qwQuizAttemptProgress = qwOverallQuizKnowledge.findQuizGradingProgress(userId, quizId);

        ARDifficultyOrTopicKnowledge arDifficultyOrTopicKnowledge = new ARDifficultyOrTopicKnowledge();

        double arAdvancedQuestionGrade = arDifficultyOrTopicKnowledge.findTopicOrDifficultyGrade(userId, "advanced");
        double arAdvancedQuestionProgress = arDifficultyOrTopicKnowledge.findTopicOrDifficultyProgress(userId,
                "advanced");
        double arAdvancedGrade = (arAdvancedQuestionGrade + arAdvancedQuestionProgress) / 2;

        double arEasyQuestionGrade = arDifficultyOrTopicKnowledge.findTopicOrDifficultyGrade(userId, "easy");
        double arEasyQuestionProgress = arDifficultyOrTopicKnowledge.findTopicOrDifficultyProgress(userId, "easy");
        double arEasyGrade = (arEasyQuestionGrade + arEasyQuestionProgress) / 2;

        QWDifficultyOrTopicKnowledge qwDifficultyOrTopicKnowledge = new QWDifficultyOrTopicKnowledge();

        double qwAdvancedQuestionGrade = qwDifficultyOrTopicKnowledge.findTopicOrDifficultyGrade(userId, quizId,
                "advanced");
        double qwAdvancedQuestionProgress = qwDifficultyOrTopicKnowledge.findTopicOrDifficultyProgress(userId, quizId,
                "advanced");
        double qwAdvancedGrade = (qwAdvancedQuestionGrade + qwAdvancedQuestionProgress) / 2;

        double qwEasyQuestionGrade = qwDifficultyOrTopicKnowledge.findTopicOrDifficultyGrade(userId, quizId, "easy");
        double qwEasyQuestionProgress = qwDifficultyOrTopicKnowledge.findTopicOrDifficultyProgress(userId, quizId,
                "easy");
        double qwEasyGrade = (qwEasyQuestionGrade + qwEasyQuestionProgress) / 2;

        QuizSlot quizSlot = new QuizSlot();
        ArrayList<Title> arDifferentNamesOfQuestions = quizSlot.getDifferentNamesOfQuestions();
        ArrayList<String> qwDifferentNamesOfQuestions = quizSlot.getDifferentNamesOfQuestionsByQuiz(quizId);

        // finding similar elements of 2 arrays of topics
        for (int i = 0; i < arDifferentNamesOfQuestions.size(); i++) {
            for (int j = 0; j < qwDifferentNamesOfQuestions.size(); j++) {
                if (arDifferentNamesOfQuestions.get(i).getTitle().equals(qwDifferentNamesOfQuestions.get(j))) {
                    arDifferentNamesOfQuestions.get(i).setDuplicability(true);
                }
            }
        }

        double arAverageDifficultyOrTopicGrade = 0;
        double arDifficultyOrTopicProgress = 0;
        double arAdvancedTopicGrade = 0;
        double arAdvancedTopicProgress = 0;
        double arEasyTopicGrade = 0;
        double arEasyTopicProgress = 0;
        double arTopicKnowledge = 0;
        double arAdvanceTopicKnowledge = 0;
        double arEasyTopicKnowledge = 0;
        double arKnowledge = 0;

        double qwAverageDifficultyOrTopicGrade = 0;
        double qwDifficultyOrTopicProgress = 0;
        double qwAdvancedTopicGrade = 0;
        double qwAdvancedTopicProgress = 0;
        double qwEasyTopicGrade = 0;
        double qwEasyTopicProgress = 0;
        double qwTopicKnowledge = 0;
        double qwAdvanceTopicKnowledge = 0;
        double qwEasyTopicKnowledge = 0;
        double qwKnowledge = 0;

        for (int i = 0; i < arDifferentNamesOfQuestions.size(); i++) {
            /**
             * all round knowledge
             */

            // topic
            arAverageDifficultyOrTopicGrade += arDifficultyOrTopicKnowledge.findTopicOrDifficultyGrade(userId,
                    arDifferentNamesOfQuestions.get(i).getTitle());
            arDifficultyOrTopicProgress += arDifficultyOrTopicKnowledge.findTopicOrDifficultyProgress(userId,
                    arDifferentNamesOfQuestions.get(i).getTitle());

            //difficulty topic
            ARDifficultyTopicKnowledge arDifficultyTopicKnowledge = new ARDifficultyTopicKnowledge();
            arAdvancedTopicGrade = arDifficultyTopicKnowledge.findTopicDifficultyGrade(userId,
                    arDifferentNamesOfQuestions.get(i).getTitle(), "advanced");
            arAdvancedTopicProgress = arDifficultyTopicKnowledge.findTopicDifficultyProgress(userId,
                    arDifferentNamesOfQuestions.get(i).getTitle(), "advanced");
            arEasyTopicGrade = arDifficultyTopicKnowledge.findTopicDifficultyGrade(userId,
                    arDifferentNamesOfQuestions.get(i).getTitle(), "easy");
            arEasyTopicProgress = arDifficultyTopicKnowledge.findTopicDifficultyProgress(userId,
                    arDifferentNamesOfQuestions.get(i).getTitle(), "easy");

            arTopicKnowledge = (arAdvancedTopicGrade + arAdvancedTopicProgress + arEasyTopicGrade + arEasyTopicProgress)
                    / 4;

            arAdvanceTopicKnowledge = (arAdvancedTopicGrade + arAdvancedTopicProgress) / 2;
            arEasyTopicKnowledge = (arEasyTopicGrade + arEasyTopicProgress) / 2;

            arKnowledge = (arQuizAttemptProgress + arQuizGrade +
                    arAdvancedQuestionGrade + arAdvancedQuestionProgress + arEasyQuestionGrade + arEasyQuestionProgress +
                    arAverageDifficultyOrTopicGrade + arDifficultyOrTopicProgress + arTopicKnowledge) / 11;

            /**
             * quiz wise knowledge
             */

            if (arDifferentNamesOfQuestions.get(i).isDuplicability()) {
                qwAverageDifficultyOrTopicGrade += qwDifficultyOrTopicKnowledge.findTopicOrDifficultyGrade(userId,
                        quizId, arDifferentNamesOfQuestions.get(i).getTitle());
                qwDifficultyOrTopicProgress += qwDifficultyOrTopicKnowledge.findTopicOrDifficultyProgress(userId,
                        quizId, arDifferentNamesOfQuestions.get(i).getTitle());

                QWDifficultyTopicKnowledge qwDifficultyTopicKnowledge = new QWDifficultyTopicKnowledge();
                qwAdvancedTopicGrade = qwDifficultyTopicKnowledge.findTopicDifficultyGrade(userId, quizId,
                        arDifferentNamesOfQuestions.get(i).getTitle(), "advanced");
                qwAdvancedTopicProgress = qwDifficultyTopicKnowledge.findTopicDifficultyProgress(userId, quizId,
                        arDifferentNamesOfQuestions.get(i).getTitle(), "advanced");
                qwEasyTopicGrade = qwDifficultyTopicKnowledge.findTopicDifficultyGrade(userId, quizId,
                        arDifferentNamesOfQuestions.get(i).getTitle(), "easy");
                qwEasyTopicProgress = qwDifficultyTopicKnowledge.findTopicDifficultyProgress(userId, quizId,
                        arDifferentNamesOfQuestions.get(i).getTitle(), "easy");

                qwTopicKnowledge = (qwAdvancedTopicGrade + qwAdvancedTopicProgress + qwEasyTopicGrade + qwEasyTopicProgress)
                        / 4;

                qwAdvanceTopicKnowledge = (qwAdvancedTopicGrade + qwAdvancedTopicProgress) / 2;
                qwEasyTopicKnowledge = (qwEasyTopicGrade + qwEasyTopicProgress) / 2;

                qwKnowledge = (qwQuizAttemptProgress + qwQuizGrade + qwAdvancedQuestionGrade +
                        qwAdvancedQuestionProgress + qwEasyQuestionGrade + qwEasyQuestionProgress +
                        qwAverageDifficultyOrTopicGrade + qwDifficultyOrTopicProgress + qwTopicKnowledge) / 11;
            }

            System.out.println("Topic " + arDifferentNamesOfQuestions.get(i));
            System.out.println("Topic Knowledge " + (arTopicKnowledge + qwTopicKnowledge) / 2);
            System.out.println("Overall Topic Knowledge " + arTopicKnowledge);
            System.out.println("Quiz Topic Knowledge " + qwTopicKnowledge);
            System.out.println("Advance Topic Knowledge " + (arAdvanceTopicKnowledge + qwAdvanceTopicKnowledge) / 2);
            System.out.println("Overall Advance Topic Knowledge " + arAdvanceTopicKnowledge);
            System.out.println("Quiz Advance Topic Knowledge " + qwAdvanceTopicKnowledge);
            System.out.println("Easy Topic Knowledge " + (arEasyTopicKnowledge + qwEasyTopicKnowledge) / 2);
            System.out.println("Overall Easy Topic Knowledge " + arEasyTopicKnowledge);
            System.out.println("Quiz Easy Topic Knowledge " + qwEasyTopicKnowledge);
            System.out.println("Knowledge " + (arKnowledge + qwKnowledge) / 2);
            System.out.println("Overall knowledge " + arKnowledge);
            System.out.println("Quiz knowledge " + qwKnowledge);
            System.out.println("Advanced knowledge " + (arAdvancedGrade + qwAdvancedGrade) / 2);
            System.out.println("Overall advanced knowledge " + arAdvancedGrade);
            System.out.println("Quiz advanced knowledge " + qwAdvancedGrade);
            System.out.println("Easy knowledge " + (arEasyGrade + qwEasyGrade) / 2);
            System.out.println("Overall easy knowledge " + arEasyGrade);
            System.out.println("Quiz easy knowledge " + qwEasyGrade);

        }
    }
}
