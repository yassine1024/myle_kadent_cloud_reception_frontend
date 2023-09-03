package agenda;

import Config.Const;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import rendezvous.Rendezvous;
import views.RendezvousController;
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
    private int week;


    public ScrollPane drawSchedule(List<Rendezvous> rendezvous, RendezvousController rendezvousController) {
        //There we should iterate for 24 hours each row has one hour
        for (int row = 0; row <= 23; row++) {
            GridPane gridPane = new GridPane();

            this.setStyleGridPane(gridPane);
            this.fullFillSchedule(gridPane, rendezvous, row);

            root.getChildren().add(gridPane);
        }

        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
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


                Label label1 = new Label("+");
                label1.getStyleClass().add("cell-box-add");

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
                    return label;
                })
                .collect(Collectors.toList());
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

        for (int day = this.week * 7; day < this.week * 7 + 7; day++) {
            this.weeklyTable[day - (this.week * 7)] = currentDate.plusDays(day).format(dateFormatter);
            this.weeklyTableTitle[day - (this.week * 7)] = currentDate.plusDays(day).format(dateFormatterTitle);

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
