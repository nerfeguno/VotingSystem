# Voting System - Development Guide

## Overview
This repository contains the source code for the Voting System. This document serves as a comprehensive guide for developers to set up the local environment, manage the repository, and build the application.

## Prerequisites
Ensure you have the following installed on your local machine:
* **IDE:** [Apache NetBeans](https://netbeans.apache.org/) (Project is structured for NetBeans).
* **Java Development Kit (JDK):** Version 11 or higher (or the specific version required by your project).
* **Version Control:** Git and GitHub CLI (`gh`).
* **Database:** (e.g., MySQL, PostgreSQL, or SQLite) — *Update this based on your system's architecture.*

## Local Environment Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/nerfeguno/VotingSystem.git
   cd VotingSystem
   ```

2. **Open the project in NetBeans:**
   * Launch Apache NetBeans.
   * Go to **File** > **Open Project...** (or press `Ctrl+Shift+O`).
   * Navigate to `~/Workplace/Netbeans/VotingSystem` and select the folder.
   * NetBeans will automatically load the project configurations and dependencies.

3. **Database Configuration (If applicable):**
   * Create a local database for the system.
   * Update the connection credentials (URL, username, password) in the project's configuration file or database connection class.

## Git Development Workflow

This project uses `main` as the primary branch. Never commit directly to `main` if collaborating with others; use feature branches instead.

### 1. Synchronize your local repository
Always pull the latest changes before starting new work:
```bash
git checkout main
git pull origin main
```

### 2. Create a feature branch
```bash
git checkout -b feature/name-of-your-feature
```

### 3. Commit your changes
Write clear, descriptive commit messages. *(Note: If your local GPG keys are unconfigured, append `--no-gpg-sign` to bypass the signing error).*
```bash
git add .
git commit -m "Add descriptive message of what changed" --no-gpg-sign
```

### 4. Push to GitHub
```bash
git push -u origin feature/name-of-your-feature
```
Once pushed, open a Pull Request on GitHub to merge your feature into `main`.

## Build and Run

### Using NetBeans UI
* **Build:** Right-click the project node in the Projects window and select **Clean and Build**.
* **Run:** Click the green **Run Project** play button in the top toolbar (or press `F6`).

### Using CLI (If configured with Maven/Ant)
If your NetBeans project is built with Maven:
```bash
mvn clean install
mvn exec:java
```

## Troubleshooting Common Issues

* **GPG Signing Failed:** If Git throws a `gpg failed to sign the data` error during a commit, you can temporarily bypass it using `--no-gpg-sign`, or disable it globally for the repository using `git config commit.gpgsign false`.
* **Missing Dependencies:** If NetBeans shows red error badges on your project, right-click the project node and select **Resolve Project Problems** or ensure your `/lib` folder contains all necessary `.jar` files.
