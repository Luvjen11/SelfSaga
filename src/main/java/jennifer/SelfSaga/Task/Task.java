package jennifer.SelfSaga.Task;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jennifer.SelfSaga.Goal.Goal;

@Entity
@Table(name="task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate dueDate;
    private String status;
    private boolean isCompleted = false;

    @ManyToOne(optional = true)
    @JoinColumn(name = "goal_id", nullable = true) // Allow tasks without goals
    private Goal goal;

    @Enumerated(EnumType.STRING)
    private TaskType taskType;

    public Task() {

    }

    public Task(String title, LocalDate startDate, LocalDate dueDate, String status) {

        this.title = title;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.status = status;

    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

}
