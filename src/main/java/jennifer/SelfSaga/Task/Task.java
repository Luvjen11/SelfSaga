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
import jennifer.SelfSaga.Quest.Quest;
import jennifer.SelfSaga.User.User;

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

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = true)
    @JoinColumn(name = "quest_id", nullable = true) // Tasks can belong to quests
    private Quest quest;


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

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Quest getQuest() {
        return quest;
    }
    
    public void setQuest(Quest quest) {
        this.quest = quest;
    }

}
