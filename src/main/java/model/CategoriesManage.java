package model;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class CategoriesManage {
    String dataPath = "src/main/java/data/QuizTestAppData.xlsx";
    public ArrayList<Category> categories = new ArrayList<>();
    XSSFWorkbook data;
    Sheet category;

    public CategoriesManage() throws IOException {
        buildTree();
    }
    public  void buildTree() throws IOException {
        categories.clear();
        FileInputStream fis = new FileInputStream(dataPath);
        data = new XSSFWorkbook(fis);
        category = data.getSheet("Category");
        //create a list of Category
        for (Row r : category){
            String path = r.getCell(0).getStringCellValue();

            Category c;
            if(path.equals("root")){
                c = new Category(
                        "root",
                        (int) r.getCell(1).getNumericCellValue(),
                        path
                );
            }else {
                c = new Category(
                        path.substring(path.lastIndexOf('/') + 1),
                        (int) r.getCell(1).getNumericCellValue(),
                        path
                );
            }
            categories.add(c);
        }
        // add children category
        for (Category cate1: categories){
            for(Category cate2 : categories){
                if(cate1 != cate2){
                    if(!cate1.path.equals("root")){
                        if(cate1.path.substring(0,cate1.path.lastIndexOf('/')).equals(cate2.path)){
                            cate2.subCategories.add(cate1);
                        }
                    }
                }
            }
        }
    }


    public Category getRoot(){
        for(Category cate : categories){
            if(cate.categoryName.equals("root"))
                return cate;
        }
        return null;
    }
}
