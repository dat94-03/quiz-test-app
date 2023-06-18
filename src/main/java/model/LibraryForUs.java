package model;

import javafx.scene.control.TreeItem;

import java.io.IOException;

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
}


