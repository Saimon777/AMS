package com.sam.fun;

/*import com.sam.fun.controller.MainController;
import com.sam.fun.utils.DbUtils;
import com.sam.fun.utils.SpringFXMLLoader;*/
import com.sam.fun.model.Mail;
import com.sam.fun.view.MailEditDialogController;
import com.sam.fun.view.MailOverviewController;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class AMS extends Application {
    private static Logger LOG = Logger.getLogger(AMS.class);

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Mail> mailsData = FXCollections.observableArrayList();

    public AMS() {
        //Test credentials:
        String sender = "testspam@iqbc.ua";
        ArrayList<StringProperty> recipients = new ArrayList<StringProperty>();
        recipients.add(new SimpleStringProperty("Saimon333777@gmail.com"));
        String subject = "Test subject";
        String text = "Test text";
        ArrayList<File> attachments = new ArrayList<File>();
        boolean inProcess = true;
        String cronSec = "*";
        String cronMin = "*";
        String cronHours = "*";
        String cronDay = "*";
        String cronMonth = "*";
        String cronDayOfWeek = "*";
        String cronYear = "*";


        mailsData.add(new Mail(sender, recipients, subject, "Mail 1", attachments, inProcess, cronSec, cronMin, cronHours, cronDay, cronMonth, cronDayOfWeek, cronYear));
        mailsData.add(new Mail(sender, recipients, "Test subject 2", "Mail 2", attachments, inProcess, cronSec, cronMin, cronHours, cronDay, cronMonth, cronDayOfWeek, cronYear));
        mailsData.add(new Mail(sender, recipients, "Test subject 3", "Mail 3", attachments, inProcess, cronSec, cronMin, cronHours, cronDay, cronMonth, cronDayOfWeek, cronYear));
    }

    public ObservableList<Mail> getMailData() {
        return mailsData;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        Scene scene = new Scene(root, 300, 275);
        primaryStage.setTitle("Todolist");
        primaryStage.setScene(scene);
        primaryStage.show();*/
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();

        showMailOverview();


    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AMS.class.getResource("/fxml/view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMailOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AMS.class.getResource("/fxml/view/MailOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(personOverview);

            MailOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showMailEditDialog(Mail mail) {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AMS.class.getResource("/fxml/view/MailEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Mail");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём адресата в контроллер.
            MailEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMail(mail);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controller.isDoneClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

}
