package feedbackgenerator.controllers.algorithms.knowledge.quizwise;

import feedbackgenerator.models.Quiz;
import feedbackgenerator.models.QuizAttempt;
import feedbackgenerator.models.QuizGrades;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Ershadi Sayuri on 2/13/2016.
 */
public class QWQuizKnowledge {

    /**
     * find the grading progress of a particular quiz
     *
     * @param userId
     * @param quizId
     * @return
     * @throws Exception
     */
    public double findQWQuizGradingProgress(int userId, int quizId) throws Exception {
        QuizAttempt quizAttempt = new QuizAttempt();
        ArrayList<QuizAttempt> quizAttempts = quizAttempt.getAttemptDataOfAQuiz(userId, quizId);

        Quiz quiz = new Quiz();
        quiz = quiz.getQuizData(quizId);
        double quizMaxTotal = quiz.getSumGrades();

        double quizGradingProgress = 0;

        ArrayList<Double> quizTotals = new ArrayList<Double>();

        for (int i = 0; i < quizAttempts.size(); i++) {
            double quizTotal = quizAttempts.get(i).getSumGrades() / quizMaxTotal * 100;

            quizTotals.add(quizTotal);
        }

        for (int i = 0; i < quizTotals.size() - 1; i++) {
            if (quizTotals.get(i + 1) - quizTotals.get(i) < -90) {
                quizGradingProgress += -0.9;
            } else if (quizTotals.get(i + 1) - quizTotals.get(i) < -70) {
                quizGradingProgress += -0.7;
            } else if (quizTotals.get(i + 1) - quizTotals.get(i) < -50) {
                quizGradingProgress += -0.5;
            } else if (quizTotals.get(i + 1) - quizTotals.get(i) < -30) {
                quizGradingProgress += -0.3;
            } else if (quizTotals.get(i + 1) - quizTotals.get(i) < -20) {
                quizGradingProgress += -0.2;
            } else if (quizTotals.get(i + 1) - quizTotals.get(i) < -10) {
                quizGradingProgress += -0.1;
            } else if (quizTotals.get(i + 1) - quizTotals.get(i) < -5) {
                quizGradingProgress += -0.05;
            } else if (quizTotals.get(i + 1) - quizTotals.get(i) < 0) {
                quizGradingProgress += 0;
            } else if (quizTotals.get(i + 1) - quizTotals.get(i) < 5) {
                quizGradingProgress += 0.05;
            } else if (quizTotals.get(i + 1) - quizTotals.get(i) < 10) {
                quizGradingProgress += 0.1;
            } else if (quizTotals.get(i + 1) - quizTotals.get(i) < 20) {
                quizGradingProgress += 0.2;
            } else if (quizTotals.get(i + 1) - quizTotals.get(i) < 30) {
                quizGradingProgress += 0.3;
            } else if (quizTotals.get(i + 1) - quizTotals.get(i) < 50) {
                quizGradingProgress += 0.5;
            } else if (quizTotals.get(i + 1) - quizTotals.get(i) < 70) {
                quizGradingProgress += 0.7;
            } else {
                quizGradingProgress += 0.9;
            }
        }

        double averageQuizGradingProgress;
        if (quizGradingProgress != 0.0) {
            averageQuizGradingProgress = quizGradingProgress / (quizTotals.size() - 1);
        } else {
            averageQuizGradingProgress = 0;
        }

        return averageQuizGradingProgress;
    }

    /**
     * this is the average grade of a user per a particular quiz out of 10
     *
     * @param userId
     * @param quizId
     * @return
     */
    public double findQWAverageGrade(int userId, int quizId) throws Exception {
        QuizGrades quizGrade = new QuizGrades();
        quizGrade = quizGrade.getQuizGradesData(userId, quizId);

        double quizAverageGrade = quizGrade.getGrade() / 10;

        return quizAverageGrade;
    }
}
