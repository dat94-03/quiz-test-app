package model;



import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.ArrayList;

public class Category {
    public String name;
    public ArrayList<Category> subCategories = new ArrayList<>();
    public int numQuestions;
    String dataPath = "src/main/java/data/QuizTestAppData.xlsx";
    XSSFWorkbook data;
    Sheet category;

    public Category(String name) throws IOException {
        FileInputStream fis = new FileInputStream(dataPath);
        data = new XSSFWorkbook(fis);
        category = data.getSheet("Category");

        this.name = name;
        for(Row row : category){
            if(name.equals(row.getCell(0).getStringCellValue())){
                this.numQuestions = (int) row.getCell(2).getNumericCellValue();
                String cate= row.getCell(1).getStringCellValue();
                if(!cate.equals("undefine")){
                    for(String category : cate.split("`")){
                        subCategories.add(new Category(category));
                    }
                }
                break;
            }
        }



    }
//test
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Category{");
        sb.append("name='").append(name).append('\'');
        sb.append(", numQuestions=").append(numQuestions);
        sb.append(", subCategories=").append(subCategories);
        sb.append('}');
        return sb.toString();
    }
}
