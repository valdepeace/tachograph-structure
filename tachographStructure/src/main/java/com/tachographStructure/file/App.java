package com.tachographStructure.file;

import java.io.File;
import java.io.FileWriter;

/**
 * This class used for call from terminal:
 * $/: java -cp tachographStructure.jar com.tachographStructure.file.App get_json "path to .tgd"
 */
public class App {
    public static void main(String[] args) {
        if (args[0].equals("get_json") && !args[1].isEmpty() && !args[2].isEmpty()){
            try {
                String dataJson = FileTGD.generateJSONFromBinaryFile(args[1]);
                FileWriter fw = new FileWriter(args[2]);
                fw.write(dataJson);
                fw.close();
                System.out.println("File generated.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
