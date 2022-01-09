package sample.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Task {

    StringProperty priority = new SimpleStringProperty();
    StringProperty description = new SimpleStringProperty();
    ObjectProperty<Integer> progress = new SimpleObjectProperty<>(0);

    ObjectProperty<Integer> id = new SimpleObjectProperty<>(null);

    public Task() {
    }

    public Task(String priority, String description, Integer progress, Integer id) {
        this.priority.set(priority);
        this.description.set(description);
        this.progress.set(progress);
        this.id.set(id);
    }

    public Integer getId() {
        return id.get();
    }

    public ObjectProperty<Integer> idProperty() {
        return id;
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public String getPriority() {
        return priority.get();
    }

    public StringProperty priorityProperty() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority.set(priority);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public Integer getProgress() {
        return progress.get();
    }

    public ObjectProperty<Integer> progressProperty() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress.set(progress);
    }
}
