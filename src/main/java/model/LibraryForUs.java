package model;

import javafx.scene.control.TreeItem;

import java.io.IOException;
import java.util.ArrayList;

public class LibraryForUs {
    public static void accessChildrenTreeView(Category category, TreeItem<String> item){
        if(category == null)    return;
        else {
            item.setExpanded(true);
            for(Category tmp : category.subCategories){
//                System.out.println(tmp);
                TreeItem<String> tmpItem = new TreeItem<>(tmp.categoryName + " (" + tmp.numQuestions + ")");
                accessChildrenTreeView(tmp, tmpItem);
                item.getChildren().add(tmpItem);
            }
        }
    }

    public static String getFullyCategory(TreeItem<String> selectedItem){
        String fullyPath = new String();
        String[] fullyCategory = new String[10];
        TreeItem<String> tmp = selectedItem;
        int i = 0;
        fullyCategory[i] = removeQuestionNumber(tmp.getValue());
        while (tmp.getParent() != null){
            tmp = tmp.getParent();
            fullyCategory[++i] = removeQuestionNumber(tmp.getValue());
        }
        for(int j = i; j >= 1; j--){
            fullyPath += fullyCategory[j] + "/";
        }
        fullyPath += fullyCategory[0];

        return fullyPath;
    }

    public static String removeQuestionNumber(String src){
        String des = new String();
        int locate = src.indexOf('(');
        if(locate != -1 ){
            des = src.substring(0, locate - 1);
        }
        else {
            des = src;
        }
        return des;
    }

    public static String updateNumberQuestion(String previousCate, int numberQuesChange){
        String des = new String();
        int locate1 = previousCate.indexOf('(');
        int locate2 = previousCate.indexOf(')');
        String tmp = previousCate.substring(locate1 + 1, locate2);
        int oldNum = Integer.parseInt(tmp);
        oldNum += numberQuesChange;
        tmp = Integer.toString(oldNum);

        des = previousCate.substring(0, locate1 - 1);
        des = des + " (" + tmp + ")";
        return des;
    }

    public static String getLastCategory(String fullCate){
        String[] categories = fullCate.split("/");
        return categories[categories.length - 1];
    }

    public static boolean checkQuestionExistOnQuiz(Question question, Quiz quiz) throws IOException {
        String[] tmp = quiz.quizQuestions.split(",");
        ArrayList<Integer> idQuestions = new ArrayList<>();
        for(String str : tmp){
            str = str.trim();
            if(IntegerCheck.isInteger(str)){
                idQuestions.add(Integer.valueOf(str));
            }
        }

        for(int i : idQuestions){
            if(question.id == i)    return true;
        }
        return false;
    }

    public static ArrayList<Integer> getQuestionIdFromQuiz(Quiz quiz){
        String[] tmp = quiz.quizQuestions.split(",");
        ArrayList<Integer> idQuestions = new ArrayList<>();
        for(String str : tmp){
            str = str.trim();
            if(IntegerCheck.isInteger(str)){
                idQuestions.add(Integer.valueOf(str));
            }
        }
        return idQuestions;
    }
    public static void deleteQuestionInQuiz(Question question, Quiz quiz) throws IOException {
//        QuestionManage questionManage = new QuestionManage();
        if(checkQuestionExistOnQuiz(question, quiz)){
            System.out.println("Exist");
            String[] tmp = quiz.quizQuestions.split(",");
            String questionOnQuiz = new String();
            ArrayList<Integer> idQuestions = new ArrayList<>();
            for(String str : tmp){
                str = str.trim();
                if(IntegerCheck.isInteger(str)){
                    idQuestions.add(Integer.valueOf(str));
                }
            }

            for(int i : idQuestions){
                if(question.id == i)    continue;
                questionOnQuiz = questionOnQuiz + "," + Integer.toString(i);
            }
//            quiz.quizQuestions = questionOnQuiz;
            QuizzesManage quizzesManage = new QuizzesManage();
            quizzesManage.editingQuiz(quiz.id, questionOnQuiz);
        }
    }

    private class IntegerCheck {
        public static boolean isInteger(String str) {
            try {
                Integer.parseInt(str); // Chuyển đổi chuỗi thành số nguyên
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }
}


