package com.sam.fun.view;

import com.sam.fun.utils.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.sam.fun.AMS;
import com.sam.fun.model.Mail;

public class MailOverviewController {
    @FXML
    private TextField mailTextField;
    @FXML
    private Label senderLabel;
    @FXML
    private Label recipientsLabel;
    @FXML
    private Label subjectLabel;
    @FXML
    private Label cronTimeLabel;

    @FXML
    private TableView<Mail> mailTable;
    @FXML
    private TableColumn<Mail, String> subjectColumn;
    @FXML
    private TableColumn<Mail, String> statusColumn;

    @FXML
    private Button newButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;



    // Ссылка на главное приложение.
    private AMS ams;

    /**
     * Конструктор.
     * Конструктор вызывается раньше метода initialize().
     */
    public MailOverviewController() {
    }

    /**
     * Инициализация класса-контроллера. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
        // Инициализация таблицы адресатов с двумя столбцами.
        subjectColumn.setCellValueFactory(cellData -> cellData.getValue().getSubject());
//        REMOVE, JUST SHOW IT VIA COLORS (IS ACTIVE OR NOT)
//        statusColumn.setCellValueFactory(cellData -> cellData.getValue().isInProcess());

        // Очистка дополнительной информации об адресате.
        showMailDetails(null);

        // Слушаем изменения выбора, и при изменении отображаем
        // дополнительную информацию об адресате.
        mailTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showMailDetails(newValue));
    }

    /*
     * Вызывается главным приложением, которое даёт на себя ссылку.
     */
    public void setMainApp(AMS ams) {
        this.ams = ams;

        // Добавление в таблицу данных из наблюдаемого списка
        mailTable.setItems(ams.getMailData());
    }

    /*
     * Заполняет все текстовые поля, отображая подробности об адресате.
     * Если указанный адресат = null, то все текстовые поля очищаются.
     */
    private void showMailDetails(Mail mail) {
        if (mail != null) {
            // Заполняем метки информацией из объекта mail.
            senderLabel.setText(mail.getSender().getValue());
            recipientsLabel.setText(mail.getRecipientsInString());
            subjectLabel.setText(mail.getSubject().getValue());
            cronTimeLabel.setText(" " + mail.getCronSec() + " | "
                    + mail.getCronMin() + " | "
                    + mail.getCronHours() + " | "
                    + mail.getCronDay() + " | "
                    + mail.getCronMonth() + " | "
                    + mail.getCronDayOfWeek() + " | "
                    + mail.getCronYear() + " | ");
//            timeOfSendingLabel.setText(DateUtil.format(mail.getDayOfSendingInMonth()));

            mailTextField.setText(mail.getText());
        } else {
            // Если Mail = null, то убираем весь текст.
            mailTextField.setText("");
        }
    }

    @FXML
    private void handleActivityOnMailTable() {
        editButton.setDisable(false);
        deleteButton.setDisable(false);
    }

    /**
     * Вызывается, когда пользователь кликает по кнопке удаления.
     */
    @FXML
    private void handleDeleteMail() {
        int selectedIndex = mailTable.getSelectionModel().getSelectedIndex();
        mailTable.getItems().remove(selectedIndex);
//        ams.getMailData().remove(mailTable.getSelectionModel().getSelectedItem());
    }



    /**
     * Вызывается, когда пользователь кликает по кнопке New...
     * Открывает диалоговое окно с дополнительной информацией нового адресата.
     */
    @FXML
    private void handleNewMail() {
        Mail tempMail = new Mail();
        boolean doneClicked = ams.showMailEditDialog(tempMail);
        if (doneClicked) {
            ams.getMailData().add(tempMail);
        }
    }

    /**
     * Вызывается, когда пользователь кликает по кнопка Edit...
     * Открывает диалоговое окно для изменения выбранного адресата.
     */
    @FXML
    private void handleEditMail() {
        Mail selectedMail = mailTable.getSelectionModel().getSelectedItem();
        if (selectedMail != null) {
            boolean okClicked = ams.showMailEditDialog(selectedMail);
            if (okClicked) {
                showMailDetails(selectedMail);
            }

        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(ams.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Mail Selected");
            alert.setContentText("Please select a mail in the table.");

            alert.showAndWait();
        }
    }

}
