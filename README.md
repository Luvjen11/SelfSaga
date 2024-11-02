# SelfSaga - Personal Progress & Goal Tracking Application

## Table of Contents

- [Project Overview][1]
- [Technologies used][2]
- [Installation & Setup][3]
- [Requirements][7]
- [MVP, core features and POST-MVP][4]
- [User Story ][5]
- [API Endpoints][6]
- [Running Tests][8]

## Project Overview

### Concept:

An app that combines productivity and personal growth in a gamified, community-based experience. Users level up at their own pace by completing daily tasks and engaging in exercises designed to improve their lives while healing their inner child. It’s a space for self-reflection, personal development, and fun.

### Why?
Many people struggle with sticking to their goals, feeling overwhelmed by life’s pressures. Traditional productivity apps are too rigid or uninspiring. 
**SelfSaga** solves this by making personal growth fun, non-competitive, and self-paced. It promotes mental wellness, normalises failures, and encourages self-compassion.

### How?

1. Gamified Productivity:
Users earn points, level up, and unlock badges for completing tasks and goals. There’s no punishment for failing, only rewards for trying.

2. Daily & Routine Tasks:
 Users choose tasks to complete daily, weekly, or monthly. Tasks can range from personal growth exercises (like writing a personal statement) to basic productivity actions.

3. Inner Child Healing Journey:
The app guides users through a journey of personal growth, starting with healing their inner child. This concept turns self-development into an adventure, with exercises like journaling, meditation, and creative tasks.

4. Custom Profiles & Badges:
Each user has a personal profile where they can showcase their victories, challenges, and milestones. They can also see their progress on a visual life vision board. Badges are awarded for completing tasks, streaks, and special events.

5. Community Space:
 A space to showcase victories, share failures, and get feedback from peers. Challenges such as "best idea" competitions help users bond and grow.

6. In-App Store & Events:
Users can use points earned from tasks to buy customisation items (e.g. avatar outfits, themes) and participate in limited-time events for special rewards.

### Problems it Solves
- **Procrastination**: By breaking big goals into smaller tasks and offering immediate rewards, users stay motivated.
- **Lack of Engagement in Traditional Productivity Apps**: Gamification and personal growth exercises make the process fun and engaging.
- **Normalising Failure**: The app embraces failure as part of growth, providing a safe space for users to share struggles without judgment.

## Technologies Used
- Backend: Spring Boot (RESTful API)
- Testing: JUnit 5 for unit testing (TDD approach)
- Database: MySQL
- Frontend: (Future implementation - planned to use React)

## Installation & Setup

## Requirements

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

## Running Tests

[1]: #project-overview
[2]: #technologies-used
[3]: #installation--setup
[4]: #mvp-core-features-and-post-mvp
[5]: #user-story
[6]: #api-endpoints
[7]: #requirements
[8]: #running-tests