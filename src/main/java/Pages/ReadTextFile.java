package Pages;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadTextFile {
    private String readCarReg;
    private FileReader fileReader;
    private BufferedReader bufferedReader;

    public ReadTextFile(String fileName) throws FileNotFoundException {
        try{
            fileReader = new FileReader(fileName);
        }catch(FileNotFoundException fileNotFound){
            throw new FileNotFoundException(fileName+"File not found");
        }
    }
    public ArrayList<String> readInputTextFile() throws IOException {
        ArrayList<String> carInputFile = new ArrayList<>();
        bufferedReader = new BufferedReader(fileReader);
        while((readCarReg= bufferedReader.readLine())!=null){
            carInputFile.add(readCarReg);
        }
        return carInputFile;
    }
}
