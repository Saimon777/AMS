package com.sam.fun.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.sam.fun.model.Mail;

public class MailEditDialogController {

    @FXML
    private TextField senderTextField;
    @FXML
    private TextField recipientsTextField;
    @FXML
    private TextField subjectTextField;
    @FXML
    private CheckBox inProcessCheckBox;
    @FXML
    private TextField cronSecTextField;
    @FXML
    private TextField cronMinTextField;
    @FXML
    private TextField cronHoursTextField;
    @FXML
    private TextField cronDayTextField;
    @FXML
    private TextField cronMonthTextField;
    @FXML
    private TextField cronDayOfWeekTextField;
    @FXML
    private TextField cronYearTextField;

    @FXML
    private TextField mailTextTextField;


    private Stage dialogStage;
    private Mail mail;
    private boolean doneClicked = false;

    /**
     * Инициализирует класс-контроллер. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Устанавливает сцену для этого окна.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Задаёт адресата, информацию о котором будем менять.
     *
     * @param mail
     */
    public void setMail(Mail mail) {
        this.mail = mail;

        senderTextField.setText(mail.getSender().getValue());
        recipientsTextField.setText(mail.getRecipientsInString());
        subjectTextField.setText(mail.getSubject().getValue());
        if (mail.isInProcess().getValue().equals("true")) {
            inProcessCheckBox.setSelected(true);
        } else {
            inProcessCheckBox.setSelected(false);
        }
        //TODO make methocd which will check cron regexp
        cronSecTextField.setText(String.valueOf(mail.getCronSec()));
        cronMinTextField.setText(String.valueOf(mail.getCronMin()));
        cronHoursTextField.setText(String.valueOf(mail.getCronHours()));
        cronDayTextField.setText(String.valueOf(mail.getCronDay()));
        cronMonthTextField.setText(String.valueOf(mail.getCronMonth()));
        cronDayOfWeekTextField.setText(String.valueOf(mail.getCronDayOfWeek()));
        cronYearTextField.setText(String.valueOf(mail.getCronYear()));

        mailTextTextField.setText(mail.getText());
    }

    /**
     * Returns true, если пользователь кликнул Done, в другом случае false.
     *
     * @return
     */
    public boolean isDoneClicked() {
        return doneClicked;
    }

    /**
     * Вызывается, когда пользователь кликнул по кнопке Done.
     */
    @FXML
    private void handleDone() {
        if (isInputValid()) {
            mail.setSender(senderTextField.getText());
//            mail.setRecipients(recipientsTextField.getText());
            mail.setSubject(subjectTextField.getText());
            mail.setInProcess(inProcessCheckBox.isSelected());

            mail.setCronSec(cronSecTextField.getText());
            mail.setCronMin(cronMinTextField.getText());
            mail.setCronHours(cronHoursTextField.getText());
            mail.setCronDay(cronDayTextField.getText());
            mail.setCronMonth(cronMonthTextField.getText());
            mail.setCronDayOfWeek(cronDayOfWeekTextField.getText());
            mail.setCronYear(cronYearTextField.getText());

            mail.setText(mailTextTextField.getText());

            doneClicked = true;
            dialogStage.close();
        }

    }

    /**
     * Вызывается, когда пользователь кликнул по кнопке Cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Проверяет пользовательский ввод в текстовых полях.
     *
     * @return true, если пользовательский ввод корректен
     */
    private boolean isInputValid() {
        StringBuilder errorMessage = new StringBuilder();

        validateThatFieldHasText(senderTextField.getText(), "sender", errorMessage);
        validateThatFieldHasText(recipientsTextField.getText(), "recipients list", errorMessage);
        validateThatFieldHasText(subjectTextField.getText(), "subject", errorMessage);
        validateThatFieldHasText(mailTextTextField.getText(), "mail text", errorMessage);
        /*if (dayOfSendingInMonthTextField.getText() == null || dayOfSendingInMonthTextField.getText().length() == 0) {
            errorMessage.append("No valid day of sending in month!\n");
        } else {
            // пытаемся преобразовать почтовый код в int.
            try {
                Integer.parseInt(dayOfSendingInMonthTextField.getText());
            } catch (NumberFormatException e) {
                errorMessage.append("No valid day of sending in month (must be an integer)!\n");
            }
        }*/
        validateThatFieldHasText(cronSecTextField.getText(), "Cron sec", errorMessage);
        validateThatFieldHasText(cronMinTextField.getText(), "Cron min", errorMessage);
        validateThatFieldHasText(cronHoursTextField.getText(), "Cron hours", errorMessage);
        validateThatFieldHasText(cronDayTextField.getText(), "Cron day", errorMessage);
        validateThatFieldHasText(cronMonthTextField.getText(), "Cron month", errorMessage);
        validateThatFieldHasText(cronDayOfWeekTextField.getText(), "Cron day of week", errorMessage);
        validateThatFieldHasText(cronYearTextField.getText(), "Cron year", errorMessage);

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage.toString());

            alert.showAndWait();

            return false;
        }
    }

    private void validateThatFieldHasText(String inputText, String meaningOfField, StringBuilder errorMessage) {
        if (inputText == null || inputText.length() == 0) {
            errorMessage.append("No valid " + meaningOfField + "!\n");
        }
    }
}
