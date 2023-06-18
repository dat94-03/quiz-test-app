package model;

import javafx.scene.control.TreeItem;

public class LibraryForUs {
    public static void accessChildrenTreeView(Category category, TreeItem<String> item){
        if(category == null)    return;
        else {
            item.setExpanded(true);
            for(Category tmp : category.subCategories){
                System.out.println(tmp);
                TreeItem<String> tmpItem = new TreeItem<>(tmp.categoryName + " (" + tmp.numQuestions + ")");
                accessChildrenTreeView(tmp, tmpItem);
                item.getChildren().add(tmpItem);
            }
        }
    }
}
