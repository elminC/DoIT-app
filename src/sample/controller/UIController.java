package sample.controller;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import sample.model.Task;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class UIController implements Initializable {

    Task currentTask = new Task();

    ObservableList<Task> tasks = FXCollections.observableArrayList();

    private final HashMap<Integer, Task> tasksMap = new HashMap<>();

    @FXML
    TableView<Task> tasksTable;

    @FXML
    TableColumn<Task, String> priorityColumn;

    @FXML
    TableColumn<Task, String> descriptionColumn;

    @FXML
    TableColumn<Task, Integer> progressColumn;

    @FXML
    ComboBox<String> priorities;

    @FXML
    public TextField taskName;

    @FXML
    Button addButton;

    @FXML
    Spinner<Integer> spinner;

    @FXML
    CheckBox checkBox;

    @FXML
    Button cancelButton;

    @FXML
    ProgressBar progressBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        taskName.setText("Dummy data for task description");
        priorities.getItems().addAll("Low", "Medium", "High");
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));
        spinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                if (newValue.intValue() == 100) {
                    checkBox.setSelected(true);
                } else {
                    checkBox.setSelected(false);
                }
                //  progressBar.setProgress(1.0 * newValue/100);
                System.out.println(currentTask.getDescription());
                System.out.println(currentTask.getPriority());
                System.out.println(currentTask.getProgress());

            }
        });
        ReadOnlyIntegerProperty intProgress = ReadOnlyIntegerProperty.readOnlyIntegerProperty(spinner.valueProperty());

        progressBar.progressProperty().bind(intProgress.divide(100.0));
        priorities.valueProperty().bindBidirectional(currentTask.priorityProperty());
        taskName.textProperty().bindBidirectional(currentTask.descriptionProperty());
        spinner.getValueFactory().valueProperty().bindBidirectional(currentTask.progressProperty());


        tasksTable.setItems(tasks);
        priorityColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Task, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Task, String> param) {
                return param.getValue().priorityProperty();
            }
        });
        descriptionColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Task, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Task, String> param) {
                return param.getValue().descriptionProperty();
            }
        });
        progressColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Task, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Task, Integer> param) {
                return param.getValue().progressProperty();
            }
        });

        //String priority, String description, Integer progress, Integer id
//        tasks.addAll(new Task("HIGH", "Complete Java course", 80, 1),
//                new Task("HIGH", "Learn english ", 40, 2),
//                new Task("HIGH", "Do homework", 70, 3));


        tasksTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Task>() {
            @Override
            public void changed(ObservableValue<? extends Task> observable, Task oldValue, Task newValue) {
                setCurrentTask(newValue);
            }


        });


        StringBinding addButtonTextBinding = new StringBinding() {
            {
                super.bind(currentTask.idProperty());
            }

            @Override
            protected String computeValue() {
                if (currentTask.getId() == null) {
                    return "Add";
                } else {
                    return "Update";
                }
            }
        };

        addButton.textProperty().bind(addButtonTextBinding);
        addButton.disableProperty().bind(Bindings.greaterThan(3, currentTask.descriptionProperty().length()));

//        addButton.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                System.out.println("Filter: Mouse pressed");
//            }
//        });
//
//        addButton.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                System.out.println("Filter: Mouse " + event.getEventType().getName());
//            }
//        });

//        addButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Action");
//            }
//        });

//       addButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
//           @Override
//           public void handle(ActionEvent event) {
//               System.out.println("Action trough handler");
//           }
//       });

        taskName.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Text Field action");
            }
        });

        priorities.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Combo box action");
            }
        });

        checkBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Check box on action");
            }
        });


    }

    public void setCurrentTask(Task newValue) {

        if (newValue != null) {
            currentTask.setId(newValue.getId());
            currentTask.setDescription(newValue.getDescription());
            currentTask.setProgress(newValue.getProgress());
            currentTask.setPriority(newValue.getPriority());
        } else { // no tasks selected in table view
            currentTask.setId(null);
            currentTask.setDescription("");
            currentTask.setProgress(0);
            currentTask.setPriority("");
        }
    }


    int lasTaskId = 0;

    public void addButtonClicked(ActionEvent actionEvent) {
        if(currentTask.getId() == null) {
            Task t = new Task(currentTask.getPriority(), currentTask.getDescription(), currentTask.getProgress(), ++lasTaskId);
            tasks.add(t);
            tasksMap.put(t.getId(), t);
        } else {
            Task t = tasksMap.get(currentTask.getId());
            t.setPriority(currentTask.getPriority());
            t.setProgress(currentTask.getProgress());
            t.setDescription(currentTask.getDescription());
        }
        setCurrentTask(null);
    }

    public void cancelButtonClicked(ActionEvent actionEvent) {
        setCurrentTask(null);
        tasksTable.getSelectionModel().clearSelection();
    }
    
}
