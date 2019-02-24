package ph.edu.dlsu.ian_ona.habify;

public class Model {

    private String task;
    private String type;
    private String date;

    public Model(String task){
        this.task = task;
    }

    public Model(String type, String task) {
        this.type = type;
        this.task = task;
    }

    public Model(String type, String task, String date) {
        this.task = task;
        this.type = type;
        this.date = date;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
