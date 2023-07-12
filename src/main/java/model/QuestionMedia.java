package model;

import java.io.File;

public class QuestionMedia {
    public String mediaType;
    public File mediaFile;
    QuestionMedia(String mediaType, String path){
        this.mediaType = mediaType;
        this.mediaFile = new File(path);
    }
}
