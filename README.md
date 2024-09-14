# GitHub User Activity Fetcher

This Java application fetches and displays recent activities for a specified GitHub user. The application uses the GitHub API to retrieve event data and Jackson for JSON parsing. It now accepts user input for the GitHub username.

## Features

- Fetches recent user events from the GitHub API.
- Parses JSON response to extract relevant information.
- Displays details such as event ID, type, actor login, repository name, and commit message.
- Accepts a GitHub username as input from the user.

## Requirements

- Java 11 or later
- Maven (for dependency management)

## Dependencies

This project uses Jackson for JSON parsing. The necessary dependency is:

- `com.fasterxml.jackson.core:jackson-databind`


## Setup

1. **Clone the repository:**

   ```bash
   git clone https://github.com/yourusername/your-repository.git

2. **Navigate to the project directory:**

   ```bash
   cd your-repository

3. **Build the project using Maven:**

   ```bash
   mvn clean install

## Usage

1. **Run the Main class:**

   ```bash
   mvn exec:java -Dexec.mainClass="org.user.Main"

2. **When prompted, enter the GitHub username you want to query.**

## Example Output

```yaml
Event ID: 41921594418
Event Type: PushEvent
Actor Login: kamranahmedse
Repository Name: kamranahmedse/developer-roadmap
Commit Message: Update UI for changelog page
```

## Error Handling
The application handles errors such as missing fields in the JSON response gracefully. If a field is missing or null, it displays a default message.
