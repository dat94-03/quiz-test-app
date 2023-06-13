package model;

import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
public class CheckAikenFormat {
    String path; // path to file docx
    int numQuestion = 0;
    public boolean isAiken;

    public CheckAikenFormat(String path){
        this.path = path;
    }

    public void readQuestion(){
        try{
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            XWPFDocument docx = new XWPFDocument(fis);

            int flag = 0, i = 0;
            boolean flag2 = true;

//            loop for each paragraph in file docx,
            for(XWPFParagraph paragraph : docx.getParagraphs()){
                i++;
                String text = paragraph.getText(); // text get content of paragraph
                text = text.trim();
                System.out.println("Dong nay la : " + text);
//
                if(text.length() <= 2)  continue; // skip space line
//
//                if text is'nt answer A,B,C,C or "ANSWER: ....."
                if(text.charAt(1) != '.' && text.charAt(6) != ':' && flag == 0){
                    continue;
                }
//                if text start with "A."
                else if(text.startsWith("A.")){
                    flag = 1;
                    continue;
                }
//                if text start with "B." or "C.", "D.", ....
                else if(text.charAt(1) == '.' && (flag == 1 || flag == 2)){
                    flag = 2;
                    continue;
                }
//                if text start with "ANSWER: ..."
                else if(text.startsWith("ANSWER:") && flag == 2){
                    flag = 0;
                    numQuestion++;
                    continue;
                }
//                else : break loop, announce paragraph i is wrong in aiken format
                else {
                    System.out.println("Error at " + i + " " + text);
                    flag2 = false;
                    break;
                }
//
            }
            if(flag2 == true){
                System.out.println("Success " + numQuestion);
            }
            isAiken = flag2;

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean isAiken(){
        return isAiken;
    }
}


