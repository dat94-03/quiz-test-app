package model;

import java.util.ArrayList;

public class Category {
    public String categoryName;
    public ArrayList<Category> subCategories;
    public int numQuestions;
    public String path;

    public Category(String categoryName,int numQuestions,String path){
        this.categoryName = categoryName;
        this.numQuestions = numQuestions;
        subCategories = new ArrayList<>();
        this.path = path;
   }
//test
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Category{");
        sb.append("name='").append(categoryName).append('\'');
        sb.append(", numQuestions=").append(numQuestions);
        sb.append(", subCategories=");
        for(Category c : subCategories)
            sb.append(c.categoryName+"|");
        sb.append('}');
        return sb.toString();
    }
}
