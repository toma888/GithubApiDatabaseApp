# GithubApiDatabaseApp
Android Application represents Square Repositories on https://github.com/square

Application makes http requests for the official GitHub API, saves to the database and shows it in the mobile-interface.
Documentation - https://developer.github.com/v3/repos/
 What downloads - list of repositories https://github.com/square

How it shows:
Without access to an Internet connection, shows only data from the database
The first screen is a list of repositories.
In each cell of the list - the name of the repository, the number of stars and forks.
By clicking on the repository, goes to the second screen - repository details, wich contains:
full name, description, url, the number of stars, forks, watches, issues.

Technologies: 
1. RxJava 2
2. Retrofit 2
3. SQLite
4. OkHttp 3
5. MVVM architecture
