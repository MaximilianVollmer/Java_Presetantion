# Universal Planer <hr>
This project is build for our presentation in our OOP-Course. Contributors are Markus, Hasan and Maximilian.

The goal of this project is to be a mock of a personal planer software, where we show the basic features with the topics we covered in our Java lectures.
## Content: <hr>
<ul>
    <li>Shopping List</li>
    <li>Calender</li>
    <li>TODO</li>
</ul>

## Environment Setup <hr>

Clone the Repository with
```
git clone https://github.com/MaximilianVollmer/Java_Presetantion.git
```
\
If not already done set your git username and email:
```
git config --local user.name <Username>
git config --local user.email <Email>
```
Replace \<Username> and \<Email> respectively.\
If you like you can type to automatically set the upstream branch
```
git config --global push.autoSetupRemote true
```
\
\
Create a new branch from develop
```
git checkout -b <branch-name> d
```
You can now work from the new branch
\
\
To get changes onto the remote repository
```
git add .
// . means "add all new files", git add README.md targets only the README file
git commit -m "Some Commit Message"
// Make commit, it's like saving the changes to git
git push 
or if error >> git push -u origin <remotebranch-name>
// pushes the changes into the remote repository
```
//TODO