
# Footballer guesser app

This is an android mobile game of guessing football players based on their clubs, numbers, nationality and positions. It uses an external service to download data and manage user account.

## Features

- Ability to register and login
- Possibility to play an infinite number of random games
- Always up-to-date data of footballers thanks to the use of an external service
- Scoring points for correct answers
- Leaderboard with the best players


## Installation

Go to the project folder

Generate .apk file using gradle:

```bash
  gradlew assembleDebug
```

File should be located in:

```bash
  {project_folder}\app\build\outputs\apk
```

To install the app, you have to run this file on your android device.

## Usage

### Authorization

#### Sign in

After clicking the "SIGN IN" button in the start screen, the user
is redirected to the login screen. It allows you to log in to an existing account using your email address and password.

<img src="https://i.ibb.co/wQP5xGW/324951896-540204851370104-6314777450871377378-n.jpg" alt="sign-in-screen" width="20%">

#### Sign up

The second button in the start screen redirects the user to the registration screen. Users who do not have an account in the game can create one by entering their email, username and password.

<img src="https://i.ibb.co/KXsxhZw/rejestr.jpg" alt="sign-up-screen" width="20%">

### Game

#### Avaible leagues

After logging in to the account, the user is redirected to the available leagues screen. Here you can select one of the leagues in which you want to start the game.

<img src="https://i.ibb.co/GJzz7Hp/lig.jpg" alt="avaible-leagues-screen" width="20%">

#### Gameplay

After launching the game screen, a list of players playing in a given league is downloaded from the website. After downloading the data, one player is randomly selected and the user has to guess it.

<img src="https://i.ibb.co/1vL7Jck/gra.jpg" alt="gaming-screen" width="20%">

To select a player, the user must open the player search window and select one from all, or search for him by first name, last name or club. After selecting a player, he is added to the list of searched players and removed from the list of available players.

<img src="https://i.ibb.co/7yk4W6P/szuk.jpg" alt="search-window" width="20%">

The hint view is used to show the user which of the player's data he guessed. They are revealed when the player selected by the user has the same attribute as the player drawn to be guessed.

<img src="https://i.ibb.co/18WM2fV/fin.jpg" alt="win-window" width="20%">

### Scores

The leaderboards screen is used to view the scores of the top players. You can choose the rankings depending on the league and period. When you launch the leaderboards screen, the default leaderboard is displayed - results from all leagues and the entire time period.

<img src="https://i.ibb.co/27chTNf/scores.jpg" alt= “scores-screen” width="20%">

## Related

Here are some related projects

[Footballer Guesser Spring Service](https://github.com/GucioWons/footballer-guesser-service)
    
