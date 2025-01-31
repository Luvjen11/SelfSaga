package jennifer.SelfSaga.Goal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jennifer.SelfSaga.Quest.Quest;
import jennifer.SelfSaga.Task.Task;
import jennifer.SelfSaga.User.User;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="goal")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @OneToMany(mappedBy = "goal")
    private List<Task> tasks;

    private Boolean isCompleted = false;  // Ito add XP if goal is completed

    @Enumerated(EnumType.STRING)
    private GoalType goalType;

    @ManyToOne(optional = true)
    @JoinColumn(name = "quest_id", nullable = true) // Optional quest association
    private Quest quest;



    //private String username;

    public Goal() {
        
    }

    public Goal(String title, String description, LocalDate startDate, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.dueDate = dueDate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public GoalType getGoalType() {
        return goalType;
    }

    public void setGoalType(GoalType goalType) {
        this.goalType = goalType;
    }

    public Quest getQuest() {
        return quest;
    }
    
    public void setQuest(Quest quest) {
        this.quest = quest;
    }
    
    // public String getUsername() {
    //     return username;
    // }

    // public void setUsername(String username) {
    //     this.username = username;
    // }

}
