package service;

import org.apache.commons.collections4.ListUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static utils.Constants.clCardsFileName;
import static utils.Variables.clCards;

public class FileService {

    public void writeClCardsToFile(){
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(clCardsFileName);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            ListUtils.emptyIfNull(clCards).forEach(printWriter::println);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
