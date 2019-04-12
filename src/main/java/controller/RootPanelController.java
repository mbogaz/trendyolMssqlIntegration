package controller;

import com.microsoft.sqlserver.jdbc.StringUtils;
import entity.ClCard;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.apache.commons.collections4.ListUtils;
import org.json.JSONObject;
import service.FileService;
import service.LogoService;
import utils.Constants;
import utils.TrendyolParser;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static utils.Variables.clCards;

public class RootPanelController {

    @FXML
    private TextField sqlServerIpField;

    @FXML
    private Button trendyolSubmitBtn;

    @FXML
    private Button printClCardsBtn;

    @FXML
    private Button saveClCardsBtn;

    private TrendyolParser trendyolParser = new TrendyolParser();
    private FileService fileService;
    private LogoService logoService;

    public void setViews() {
        loadServices();

        sqlServerIpField.setText(Constants.SQL_SERVER_NAME);

        printClCardsBtn.setOnAction(event -> {
            if(fileService == null) {
                JOptionPane.showMessageDialog(null, "File Service henüz hazır değil");
                return;
            }
            fileService.writeClCardsToFile();
        });

        saveClCardsBtn.setOnAction(event -> {
            if (logoService == null) {
                JOptionPane.showMessageDialog(null, "Logo Service henüz hazır değil");
                return;
            }
            ListUtils.emptyIfNull(clCards).forEach(logoService::createClCard);
        });

        trendyolSubmitBtn.setOnAction(event -> {
            String myJson = null;
            try {
                myJson = new Scanner(new File("src/main/resources/trendyol_sample.json")).useDelimiter("\\Z").next();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            assert myJson != null;
            trendyolParser.parse(new JSONObject(myJson));

        });
    }

    private void loadServices() {
        Thread loadServicesThread = new Thread(() -> {
            fileService = new FileService();
            logoService = new LogoService();
        });

        loadServicesThread.start();
    }

}
