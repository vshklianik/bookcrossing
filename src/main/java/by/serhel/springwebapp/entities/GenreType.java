package by.serhel.springwebapp.entities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import java.util.Properties;

public enum GenreType {
    CRIME, DETECTIVE, SCIENCE, CYBERPUNK, FANTASY, HORROR, COMEDY, ROMANCE;

//    @Value("${spring-web-app.locale}")
//    private String locale;
//
//    private String value;
//    private Properties prop;
//    private final Logger logger = LogManager.getLogger(GenreType.class);
//
//    GenreType(){
//        init();
//    }
//
//    public void init(){
//        try{
//            if(prop == null) {
//                prop = new Properties();
//                prop.load(GenreType.class.getClassLoader().getResourceAsStream("messages.properties"));
//            }
//            setValue(prop.getProperty(this.name()));
//        }
//        catch (Exception e){
//            logger.warn("Initialization error");
//        }
//    }
//
//    public String getValue() {
//        return value;
//    }
//
//    public void setValue(String value) {
//        this.value = value;
//    }
}
