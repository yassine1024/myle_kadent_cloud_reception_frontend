package agenda;

import Config.Const;
import com.google.inject.Inject;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import rendezvous.EventBus;
import rendezvous.Rendezvous;
import rendezvous.RendezvousEvent;
import rendezvous.RendezvousListener;
import views.RendezvousController;
import views.RendezvousControllerImpl;
import views.addRendezvousController;
import views.addRendezvousControllerImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class RendezvousSchedule {

    VBox root;
    LocalDate currentDate;
    DateTimeFormatter dateFormatter;
    DateTimeFormatter dateFormatterTitle;
    String[] weeklyTable;
    String[] weeklyTableTitle;
    private int week = 0;
private RendezvousController rendezvousController;

    public ScrollPane drawSchedule(List<Rendezvous> rendezvous, RendezvousController rendezvousController) {
        //There we should iterate for 24 hours each row has one hour

        this.rendezvousController = rendezvousController;
        root.getChildren().clear();
        for (int row = 0; row <= 23; row++) {
            GridPane gridPane = new GridPane();

            this.setStyleGridPane(gridPane);
            this.fullFillSchedule(gridPane, rendezvous, row);

            root.getChildren().add(gridPane);
        }

        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        System.out.println(this.weeklyTable[0] + "------------------->" + this.weeklyTable[6]);
        rendezvousController.updateDateLabel(this.weeklyTable[0], this.weeklyTable[6]);
        return scrollPane;
    }


    private void fullFillSchedule(GridPane gridPane, List<Rendezvous> rendezvous, int row) {

        String time = String.format("%02d", row) + ":" + Const.TIME_BUNCH;

        for (int col = 0; col <= 7; col++) {
            //This for filling the date
            if (row == 0 && col > 0) {
                Label dateLabel = new Label(this.weeklyTableTitle[col - 1]);
                dateLabel.getStyleClass().add("cell-box-header");
                gridPane.add(dateLabel, col, row);
                RowConstraints rowConstraints = new RowConstraints();
                rowConstraints.setVgrow(Priority.ALWAYS); // Allow rows to grow vertically
                gridPane.getRowConstraints().add(rowConstraints);
                //this for filling the time
            } else if (col == 0 && row > 0) {
                Label timeLabel = new Label(time);
                timeLabel.getStyleClass().add("cell-box-time");
                gridPane.add(timeLabel, col, row);
            } else if (col > 0 && row > 0) {
                VBox cellBox = new VBox();
                cellBox.setAlignment(Pos.CENTER);
//                cellBox.getStyleClass().add("root");
                EventBus eventBus = new EventBus();

                int dayIndex = col - 1;
                Label label1 = new Label("+");
                label1.getStyleClass().add("cell-box-add");
                label1.setOnMouseClicked(event -> {
                    // Call the method you want when the label is clicked
                    /*openAddRendezvousWithDetails(RendezvousControllerImpl.medecinFullName,
                            this.weeklyTable[dayIndex], time); // Replace 'yourMethodName' with the actual method name
*/
                    System.out.println("Event fire!");
                    RendezvousListener rdvListener = new addRendezvousControllerImpl();
                    RendezvousEvent rendezvousEvent = new RendezvousEvent(this, RendezvousControllerImpl.medecinFullName,
                            this.weeklyTable[dayIndex], time);
                    eventBus.addListener(rdvListener);
                    eventBus.fireEvent(rendezvousEvent);
                });

                if (rendezvous != null) {
                    cellBox.getChildren().addAll(
//                        label1, label2, label3,
                            // Add more labels or content as needed
                            fillAppointments(rendezvous, col - 1, time)
                    );
                }
                cellBox.getChildren().add(label1);
                gridPane.add(cellBox, col, row);
            }
        }
    }


    private void openAddRendezvousWithDetails(String medecinFullName, String date, String time) {

        System.out.println(medecinFullName + " " + date + " " + time);
        RendezvousEvent event = new RendezvousEvent(this, medecinFullName, date, time);
        addRendezvousController aRDV = new addRendezvousControllerImpl();
        //aRDV.fireEvent(event);

        this.rendezvousController.addRendezvous(null);


    }

    private Collection<? extends Label> fillAppointments(List<Rendezvous> rendezvous, int col, String time) {

        if (rendezvous == null) {
            return null;
        }
        return rendezvous.stream()
                .filter(appointment -> (appointment.getDate() + "_" + appointment.getTime())
                        .equals(this.weeklyTable[col] + "_" + time))
                .map(appointment -> {
                    Label label = new Label(appointment.getPatient().getFirstName() + " "
                            + appointment.getPatient().getLastName());
                    label.getStyleClass().add(new Const().ACTE_CELLULE.get(appointment.getActeToPerform()));
                    label.setOnMouseClicked(event -> {
                        // Call the method you want when the label is clicked
                        showAppointmentDetails(appointment); // Replace 'yourMethodName' with the actual method name
                    });
                    return label;
                })
                .collect(Collectors.toList());
    }

    private void showAppointmentDetails(Rendezvous appointment) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Appointment Details");
        alert.setHeaderText("Détails du rendez-vous");
        alert.setContentText("le patient: " + appointment.getPatient().getLastName() + " " + appointment.getPatient().getFirstName() +
                "\na un rendezvous avec Docteur: " + appointment.getMedecin().getLastName() + " " + appointment.getMedecin().getFirstName() +
                "\nà " + appointment.getTime() + " le " + appointment.getDate() +
                "\npour " + appointment.getActeToPerform());
        alert.showAndWait();
    }

    private void setStyleGridPane(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.getStyleClass().add("grid-pane");

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(100.0 / 8); // Divide into 8 columns
        gridPane.getColumnConstraints().add(columnConstraints);

       /* RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setContentDisplay(ContentDisplay.GRAPHIC_ONLY); // Divide into 8 rows
        gridPane.getRowConstraints().add(rowConstraints);*/
    }


    public RendezvousSchedule() {
        this.initializeSchedule();
        this.setWeeklyTable();
    }

    private void setWeeklyTable() {
        this.weeklyTable = new String[7];
        this.weeklyTableTitle = new String[7];
        for (int day = 0; day < 7; day++) {
            this.weeklyTable[day] = currentDate.plusDays(day).format(dateFormatter);
            this.weeklyTableTitle[day] = currentDate.plusDays(day).format(dateFormatterTitle);

        }
    }

    public void weekAfter() {
        this.week++;
        System.out.println(this.week + " after");
        int after = this.week < 0 ? -1 * this.week : this.week;
        if (this.week > 0) {
            this.weekPlusDays(after);
        } else if (this.week < 0) {
            this.weekMinusDays(after);
        } else {
            this.setWeeklyTable();
        }

    }

    private void weekMinusDays(int weight) {
        int i = 0;
        for (int day = (weight - 1) * 7 + 6; day >= (weight - 1) * 7; day--) {
            this.weeklyTable[i] = currentDate.minusDays(day).format(dateFormatter);
            this.weeklyTableTitle[i] = currentDate.minusDays(day).format(dateFormatterTitle);
            i++;

        }
    }

    private void weekPlusDays(int weight) {
        int i = 0;
        for (int day = weight * 7; day < weight * 7 + 7; day++) {
            this.weeklyTable[i] = currentDate.plusDays(day).format(dateFormatter);
            this.weeklyTableTitle[i] = currentDate.plusDays(day).format(dateFormatterTitle);
            i++;
        }
    }

    public void weekBefore() {
        this.week--;

        System.out.println(this.week + " before");
        int before = this.week < 0 ? -1 * this.week : this.week;
        if (this.week > 0) {
            this.weekPlusDays(before);

        } else if (this.week < 0) {
            this.weekMinusDays(before);
        } else {
            this.setWeeklyTable();
        }


    }

    private void initializeSchedule() {
        currentDate = LocalDate.now();
        dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dateFormatterTitle = DateTimeFormatter.ofPattern("EEEE", Locale.CANADA_FRENCH); // Pattern for day of the week

        root = new VBox();
        root.setAlignment(Pos.CENTER);

    }
}
