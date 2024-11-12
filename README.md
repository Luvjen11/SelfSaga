# SelfSaga - Personal Progress & Goal Tracking Application

## Table of Contents

- [Project Overview][1]
- [Technologies used][2]
- [Installation & Setup][3]
- [Requirements][7]
- [MVP, core features and POST-MVP][4]
- [User Story ][5]
- [API Endpoints][6]

## Project Overview

### Concept:

An app that combines productivity and personal growth in a gamified, community-based experience. Users level up at their own pace by completing daily tasks and engaging in exercises designed to improve their lives while healing their inner child. It’s a space for self-reflection, personal development, and fun.

### Why?
Many people struggle with sticking to their goals, feeling overwhelmed by life’s pressures. Traditional productivity apps are too rigid or uninspiring. 
**SelfSaga** solves this by making personal growth fun, non-competitive, and self-paced. It promotes mental wellness, normalises failures, and encourages self-compassion.

### How?

1. **Gamified Productivity**:
Users earn points, level up, and unlock badges for completing tasks and goals. There’s no punishment for failing, only rewards for trying.

2. **Daily & Routine Tasks**:
 Users choose tasks to complete daily, weekly, or monthly. Tasks can range from personal growth exercises (like writing a personal statement) to basic productivity actions.

3. **Inner Child Healing Journey**:
The app guides users through a journey of personal growth, starting with healing their inner child. This concept turns self-development into an adventure, with exercises like journaling, meditation, and creative tasks.

4. **Custom Profiles & Badges**:
Each user has a personal profile where they can showcase their victories, challenges, and milestones. They can also see their progress on a visual life vision board. Badges are awarded for completing tasks, streaks, and special events.

5. **Community Space**:
 A space to showcase victories, share failures, and get feedback from peers. Challenges such as "best idea" competitions help users bond and grow.

6. **In-App Store & Events**:
Users can use points earned from tasks to buy customisation items (e.g. avatar outfits, themes) and participate in limited-time events for special rewards.

### Problems it Solves
- **Procrastination**: By breaking big goals into smaller tasks and offering immediate rewards, users stay motivated.
- **Lack of Engagement in Traditional Productivity Apps**: Gamification and personal growth exercises make the process fun and engaging.
- **Normalising Failure**: The app embraces failure as part of growth, providing a safe space for users to share struggles without judgment.

## Technologies Used
- Backend
  - **Spring Boot**: Used to build a RESTful API for handling business logic, user authentication, and data persistence.
  - **Spring Security**: Configured with JWT-based authentication to secure API endpoints, including custom login and registration flows.
  - **Authentication**: Secured access to most endpoints, allowing public access only to specific endpoints like registration, login, and Swagger UI.
  - **Authorization**: Configured to restrict access to certain paths, only allowing authenticated users to interact with protected resources.
- Testing
  - **JUnit 5**: Used for unit testing.
- Database
  - **MySQL**: Relational database for persisting user data, goals, tasks, and other application-related information.
- API Documentation
  - **Swagger (OpenAPI 3)**: Provides interactive API documentation for easier testing and development.
Accessible through /swagger-ui/index.html for easy access to API details.
Publicly accessible endpoints configured to allow usage without authentication.
- Frontend (Planned for Future Implementation)
  - **React**: Planned frontend framework to build a dynamic, responsive user interface that will interact with the backend API.

## Installation & Setup

### Clone Repository
1. Fork this repository in your GitHub account
2. Clone your fork locally or open in CodeSpaces.

### Create Database
1. Login to MySQL:

```sh
mysql -u root -p
```

2. Create a new database:

```sh
CREATE DATABASE IF NOT EXISTS selfsaga;
exit;
```

3. Create a new Spring Boot project using [Spring Initializr](https://start.spring.io/). <br>
#### Choose this dependencies:
- Spring Boot Data JPA
- Spring Security test
- Spring-boot-starter-security
- Spring-boot-starter-thymeleaf
- Spring-boot-starter-web
- Spring-boot-devtools 
- Spring-boot-openapi
- Slf4j-api
 4. Unzip the downloaded archive into your repository.
 Ensure that your local repository is the current working directory in the terminal, then extract the downloaded zip file. **IMPORTANT:** Do NOT unzip the archive in (macOS) Finder or (Windows) Explorer as the extracted files won't be correctly positioned.
   - macOS / Git Bash: `tar -xvf [download directory]/selfsaga.zip --strip=1 -C .`, e.g. `tar -xvf ~/Downloads/selfsaga.zip --strip=1 -C .`
   - Windows Command Prompt: `tar -xvf [download directory]\selfsaga.zip --strip=1 -C %cd%`, e.g. `tar -xvf %USERPROFILE%\Downloads\selfsaga.zip --strip=1 -C %cd%`
5. Open your repository in VS Code
6. Add the following values to src/main/resources/application.properties:

```properties
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.open-in-view=true
spring.config.import=optional:./local.properties
```
7. In order to prevent sensitive values from being committed to version control, add a new entry to the .gitignore file:

```
local.properties
```

8. Create a new file at src/main/resources/local.properties and paste in the following: 

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/selfsaga

# Replace "root" with your database user, if applicable
spring.datasource.username=root

# Specify your database user's password, if applicable. If your database user doesn't have a password set, delete the line below
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

9. Replace the username and password values with your MySQL credentials. **IMPORTANT:** Ensure there are no spaces before or after the password.

### Export Database

To ensure that your project can be assessed correctly, you **must** include a database dump file that can be used to restore a usable database and document the file's location in your `README.md` file.

You can generate a dump file using the command line or MySQL Workbench:

#### Command Line

Execute the following command to export the database:

```sh
mysqldump -u [username] -p --databases [database_name] > [dump_file.sql]
```

Replace `[username]`, `[database_name]`, and `[dump_file.sql]` with your MySQL username, the name of the database you want to dump, and the desired filename for the dump file, respectively. Remove the `-p` flag if you don't have a password set for your MySQL user.

#### MySQL Workbench

1. Open MySQL Workbench.
2. Connect to your MySQL server.
3. Go to the "Server" menu and select "Data Export".
4. Select the database you want to export:
   - In the "Data Export" tab, check the box next to the database you want to export.
5. Choose the Export Options:
   - Select "Dump Structure and Data" to include both the database schema and the data.
   - Choose "Export to Self-Contained File" and provide a filename for the dump file.
6. Include the Create Database Statement:
   - In the "Advanced Options" section, ensure that the "Add DROP DATABASE" and "Add CREATE DATABASE" options are checked. This ensures that the dump file will contain statements to drop and create the database.
7. Start the Export:
   - Click the "Start Export" button to generate the dump file.


### Run Application

To start the API, run the following command:

#### macOS / Git Bash

```sh
./mvnw spring-boot:run
```

#### Windows Command Prompt

```cmd
mvnw spring-boot:run
```

### Stop Application
Stop the application by pressing `Ctrl + C`


## Requirements

- [X] Demonstrate appropriate use of HTTP methods, supporting full CRUD operations
- [X] Utilise data persistence in a MySQL database
- [X] Create at least one endpoint that can have its results filtered using a Spring Data JPA query method (custom or derived)
- [X] Implement unit tests for at least one class
- [X] Incorporate exception handling to ensure your API is resilient, secure, and user-friendly
- [X] Evidence of inheritance
- [X] Provide documentation explaining the purpose, configuration, and usage of your API

## MVP, core features and POST-MVP 

### MVP
- User Registration/Authentication
- Goal & Task Creation
- XP & Level Progression: Users gain XP for completing tasks and goals. As they accumulate XP, they level up.
    - Daily Tasks: 10 points per task
    - Routine Tasks: 15 points per task
    - Goal Completion:
        - **Simple Goal:**
          - **Tasks:** 5–10 tasks
          - **Points:** 100 points (if all tasks are completed)
        - **Moderate Goal:**
          - **Tasks:** 11–20 tasks
          - **Points:** 250 points (if all tasks are completed)
        - **Complex Goal:**
          - **Tasks:** 21–30 tasks
          - **Points:** 500 points (if all tasks are completed)
- Leveling System: Users start at Level 1 and need to accumulate progressively more XP for each level
 - **Leveling Calculation**:
   - XP is accumulated by completing tasks and goals. Task XP values are based on `TaskType`, and users level up when they reach XP thresholds.
   - XP thresholds increase as follows: 100 XP for Level 2, 250 XP for Level 3, 400 XP for Level 4, etc., with subsequent levels requiring progressively higher XP.
- Progress Tracking & Status Updates — show users’ progress made
- Basic Profile with XP and Level Display

### POST-MVP
- Vision Board and Quests/Chapters (e.g., healing journey exercises)
- **Chapter one**: Heal Your Inner Child
  - #### Narrative:
    Welcome to the first chapter of your SelfSaga journey! In this chapter, we focus on self-care and foundational tasks to help you connect with and heal your inner child. Imagine this as an adventure where you embark on a magical journey to a whimsical land, guided by the wisdom of your younger self. You'll unlock memories, rediscover joy, and build a nurturing routine that honors and heals your inner child.

    #### Story Outline:

    Once upon a time, in a land called SelfSaga, there was a hero who felt a deep longing to reconnect with their inner child. This hero was you. One day, while wandering through a mysterious forest, you stumbled upon an ancient, enchanted journal. As you opened it, the pages began to glow, revealing a path to healing and self-discovery. Each task in this journal was a step towards embracing your inner child and finding the joy and peace you deserve.

    #### Tasks:

    1. **Journaling Prompts**:
        - **"Magical Memories"**: Write about a happy memory from your childhood. Describe it in vivid detail and how it made you feel.
        - **"Overcoming the Shadows"**: Reflect on a challenging experience from your childhood. How did it shape you? What did you learn from it?
        - **"Dream Diary"**: Write a letter to your younger self, expressing love, understanding, and forgiveness.
    2. **Creative Activities**:
        - **"Art from the Heart"**: Draw or color a picture that represents a happy moment from your childhood. Let your creativity flow freely.
        - **"Playtime Fun"**: Spend 15 minutes playing a simple game you enjoyed as a child. It could be hopscotch, hide and seek, or even a board game.
    3. **Affirmations**:
        - **Daily Affirmation**: "I am worthy of love and respect."
        - **Self-Love Mantra**: "I embrace my inner child with compassion and kindness."
    4. **Mindfulness and Meditation**:
        - **"Safe Haven"**: Engage in a guided meditation where you visualize a safe, comforting space from your childhood. Imagine yourself there, feeling safe and happy.
        - **"Inner Child Conversation"**: Practice a mindfulness exercise where you imagine having a loving conversation with your inner child.
    5. **Self-Care Routines**:
        - **"Bath of Bliss"**: Take a warm bath, using your favorite bath salts or bubbles. Let yourself relax and unwind.
        - **"Childhood Rewind"**: Read a favorite childhood book or watch a beloved childhood movie.
    6. **Gratitude Exercises**:
        - **"Thankful Thoughts"**: Keep a gratitude journal. Each day, write down three things you are thankful for. Reflect on why you appreciate them.
    7. **Connecting with Nature**:
        - **"Nature Walk"**: Go for a walk in a park or garden. Pay attention to the sights, sounds, and smells around you.
        - **"Garden Therapy"**: Spend time gardening or simply sitting outside, soaking in the beauty of nature.
    8. **Healthy Boundaries**:
        - **"Boundary Builder"**: Reflect on your childhood experiences with boundaries. Identify one area in your current life where you need to set a healthy boundary, and take a step to enforce it.
    9. **Letter Writing**:
        - **"Letter to Little Me"**: Write a heartfelt letter to your younger self. Express love, understanding, and forgiveness. Acknowledge their struggles and celebrate their strengths.

    #### Goals:

    - **Establish a Self-Care Routine**: Build daily habits that nurture and care for yourself.
    - **Reconnect with Your Inner Child**: Engage in activities that bring joy and comfort, helping you reconnect with your younger self.
    - **Develop Self-Compassion**: Cultivate a sense of compassion and understanding towards yourself.

    #### Leveling Up:

    - **XP Distribution**:
        - **Journaling Prompts**: 20 XP per completed prompt
        - **Creative Activities**: 15 XP per activity
        - **Affirmations**: 5 XP per day
        - **Mindfulness and Meditation**: 20 XP per session
        - **Self-Care Routines**: 10 XP per task
        - **Gratitude Exercises**: 10 XP per day
        - **Connecting with Nature**: 15 XP per activity
        - **Healthy Boundaries**: 20 XP per exercise
        - **Letter Writing**: 25 XP per letter
- Community features (e.g., peer feedback, shared challenges)
- Badge System
- In-App Store

## User Story
| Story | Feature |
| --- | --- |
|As a user I want to create a personal account, with my email, password and username.| Account registration with email, password and username |
|As a user I want to create goals with a description, a reflection, a progress bar depending on my tasks, a status, a start date, a due date, tasks. Finally for each goal I will gain points.| Goal creation with progress tracking |
|As a user I want to add a bio, a profile picture or avatar image and a vision Board.| Profile and Vision Board |
|As a user besides from goals, I want to create independent daily tasks and track them with a status so that I can stay organized.| Single independent tasks |
|As a user I want to accumulate points and level up and also gain badges so that I stay motivated.| XP and Leveling system with points for task/goal completion|
|As a user I want to find quests/chapters to follow when I feel like I’m lost | chapter and quests creation |

## API Endpoints

### General Endpoints

| HTTP Method | Endpoint                             | Description                                                       |
|-------------|--------------------------------------|-------------------------------------------------------------------|
| GET         | `/selfsaga`                          | Get the welcome page of the web application (public).             |
| GET         | `/selfsaga/home`                     | Get the home page after login/registration.                       |
| GET         | `/selfsaga/login`                    | Get the login page.                                               |
| POST        | `/selfsaga/users/register`           | Register a new user with email, password, and username.           |
| POST        | `/selfsaga/login`                    | Authenticate a user.                                              |
| GET         | `/selfsaga/users/{username}`         | Returns user dashboard/profile details (goals, tasks, progress, level, badge, etc.). |

### Goal Endpoints

| HTTP Method | Endpoint                                              | Description                                                        |
|-------------|-------------------------------------------------------|--------------------------------------------------------------------|
| POST        | `/selfsaga/users/{username}/goals`                    | Create a new goal (title, description, start date, due date).      |
| GET         | `/selfsaga/users/{username}/goals`                    | Get all goals of a specific user.                                  |
| PUT         | `/selfsaga/users/{username}/goals/{goalId}`           | Update goal details (title, description, start date, due date, goal type). |
| DELETE      | `/selfsaga/users/{username}/goals/{goalId}`           | Delete a specific goal.                                            |
| PATCH       | `/selfsaga/users/{username}/goals/{goalId}/complete`  | Mark a goal as complete and gain XP.                               |

### Task Endpoints

| HTTP Method | Endpoint                                                         | Description                                                                       |
|-------------|------------------------------------------------------------------|-----------------------------------------------------------------------------------|
| GET         | `/selfsaga/users/{username}/tasks`                               | Get all tasks for a specific user.                                                |
| POST        | `/selfsaga/users/{username}/tasks`                               | Create an independent task for a user.                                            |
| PUT         | `/selfsaga/users/{username}/tasks/{taskId}`                      | Update details of an independent task.                                            |
| DELETE      | `/selfsaga/users/{username}/tasks/{taskId}`                      | Delete an independent task.                                                       |
| PATCH       | `/selfsaga/users/{username}/tasks/{taskId}/complete`             | Mark an independent task as complete and gain XP.                                 |

### Goal-Task Endpoints

| HTTP Method | Endpoint                                                                 | Description                                                             |
|-------------|--------------------------------------------------------------------------|-------------------------------------------------------------------------|
| GET         | `/selfsaga/users/{username}/goals/{goalId}/tasks`                        | Get all tasks of a specific goal by `goalId`.                           |
| POST        | `/selfsaga/users/{username}/goals/{goalId}/tasks`                        | Create a task under a specific goal for a user.                         |
| PUT         | `/selfsaga/users/{username}/goals/{goalId}/tasks/{taskId}`               | Update a task under a specific goal.                                    |
| DELETE      | `/selfsaga/users/{username}/goals/{goalId}/tasks/{taskId}`               | Delete a task under a specific goal.                                    |
| PATCH       | `/selfsaga/users/{username}/goals/{goalId}/tasks/{taskId}/complete`      | Mark a task connected to a specific goal as complete and gain XP.       |


[1]: #project-overview
[2]: #technologies-used
[3]: #installation--setup
[4]: #mvp-core-features-and-post-mvp
[5]: #user-story
[6]: #api-endpoints
[7]: #requirements