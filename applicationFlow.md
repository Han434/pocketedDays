# Pocketed Days : Application Flow

## User Related User's Stories
### Log in
1. User can log in by clicking the "Log in" button in the "User" Menu.
2. User will be brought to the Login page upon clicking.
3. logIn servlet will forward to logIn jsp 
   * User can fill out the form with the following inputs
      a. Name
      b. User Name
      c. Password
     (or)
   * User can sign up by clicking the "Sign up" button.
4. Request goes to LogIn servlet
5. LogIn Servlet will check data are submitted or not, set session of userId and return the result.
6. logInVerify jsp
   * User will see "Success message" and a button to "Home Page" if user is authenticated.
   * User will see "Failure message" and a button to "Log in" Page if user is authenticated.
    

### Sign up
1. User will reach to the "Sign Up" page by clicking the "Sign up" button in "Log in" page.
2. User will fill out the form with the following input on the "Sign in" page which SignUp servlet forward to.
   a. User Name
   b. Password
3. SignUp servlet will check if data are submitted or not and create a new user and add data to the database if submitted.
4. signUpVerify jsp
    * User will see "Success message" and a button to "Home Page" if user is authenticated.
    * User will see "Failure message" and a button to "Sign up" Page if user is authenticated.

### Log out
1. User can simply click the "Log out" button in "User" Menu if user has already log in.
2. logOut servlet will log user out by removing userId session variable and bring the user back to HomePage.

### View Profile Information
1. User can view the User Profile Information by clicking the "My Account" button in the Menu.

### Edit Profile
1. User can click "Edit" button in the "My account" page.
2. User will be brought to the "Edit Profile" page which EditProfile servlet forward to.
3. EditProfile servlet will get the data and add to the form.
4. User will edit the information.
5. EditProfile servlet will check if data is submitted or not and edit the information if submitted and brings to editProfile jsp.
6. editProfileVerify jsp
    * User will see "Success message" and a button to "Home Page" if everything is fine.
    * User will see "Failure message" and a button to "Home Page" if not.

## Page Related User's Stories
### View contact Information
1. User can navigate to the "Contact" page by clicking the "Contact" button in the Menu.
2. User can see the contact information within it.

### View Homepage
1. User will be brought to home page right after entering the website.

### View Navigation Menu
1. User will see the Navigation Menu in every page of the website.

### View Created Projects
1. User can log in to the specific project or view created projects in the Workspace page.
2. workspace servlet will create project object for each project that user created or log in and display it as cards in workspace jsp.
3. workspace jsp will show user created projects and a new card to create or log in into the new project.

### Give Feedback
1. User can navigate to helpUsImprove by clicking "Help us Improve" page in the Menu.
2. User will fill out the form and submit.
3. helpUsImproveVerify servlet will add the data into the database and show Message in helpUsImproveVerify jsp.

## Project Related User's Stories
### Create New Project
1. User can click "New" button on Workspace page and navigate to "CreateProject" page.
2. CreateProject servlet will forward to the createProject jsp
   * User can fill the form with the following inputs and submit
      a. Project Name
      b. Project Description
      c. Project Password
   * User can log into the project by clicking "Log in to the Project" button.
3. createProject servlet will check if the data is submitted or not and add data to the database if submitted.
4. User will be brought to "ProjectHome" page

### Give Project Name, Password and Description
1. Project Name, Password and Description can be given to the project during project creation process.

### View Project Information
1. User can view Project Information in "Project Home" page
2. projectHome servlet will forward to projectHome jsp
3. projectHome servlet will set session attribute of projectId
4. projectHome servlet will get data related to project from the database
5. projectHome jsp will output those project information

### Log in to the project
1. User can log into the project by clicking "Log in to the Project" button in "Create Project" page.
2. User will enter the following in logIntoProject jsp which logIntoProject servlet forward to.
   a. Project Name
   b. Project Id
   c. Project Password
3. Request will be received by logIntoProject servlet and check if data is submitted or not
4. userId of session variable will be added to database along with projectId to assign user to the project.
5. logIntoProject servlet
   * User will be brought to "ProjectHome" page upon successful login.
   * User will be brought back to "Log in to the Project" page.

### Delete Project
1. User can delete project by clicking "Delete" button on "Project Home" page.
2. deleteProject servlet will delete project from database
3. Forward to deleteProject jsp
4. deleteProject jsp
   * User will see "Success message" and a button to "Workspace" if everything is alright.
   * User will see "Failure message" and a button to "Project Home" Page if not.

### View Project Menu
1. Project menu will be available on project pages by clicking an icon on the head of the page.

### View Financial Manager
1. User can click on "Financial Manager" button to view "Financial Manager" page

## View Related User's Stories
### View the view menu
1. User can see the view menu as user enter into any view of the project.

### View page for list of sheets
1. User can view the list of sheets in the "View Sheets" page.
2. viewSheet servlet will forward to viewSheet jsp
3. viewSheet servlet will get the type of list of sheets from the url and projectId from session variable
4. viewSheet servlet will create sheet object for each row of the database
5. User can view them in viewSheet jsp

### Run reports by default or condition
1. User can run reports by navigation to "Run Report" via View menu.
2. If nothing is submitted, runReport servlet will forward to runReport jsp ask user the following
    Choose
      * Revenue or Expense or Both
      * Default or condition
3. If user choose to run report by condition and submit
4. runReport servlet will forward to runReport jsp but  with different form
5. Fill it out and run the report.
6. User will be brought to "Result" page.
7. result servlet will forward to result jsp
8. result servlet will get projectId from session variable
9. result servlet will create row object for each row of the database
10. result jsp will display them to the user

## Sheet Related User's Stories
### Create New sheet
1. User can create new sheet by clicking "Add New Sheet" on the "View" page
2. createNewSheet servlet will forward to createNewSheet jsp
3. A form with the following inputs will be there
   a. Sheet Description
   b. Organization
   c. Note
4. createNewSheet servlet will get projectId from session attribute
5. createNewSheet servlet will get the request, check if data is submitted or not and add new sheet to the database.
6. User will be brought to "Sheet Home" page

### View each sheet
1. User can view the details of the sheet by clicking "View Sheet in Detail" on "View Sheets" page.
2. viewSheetDetails servlet will forward to viewSheetDetails jsp
3. servlet will get the sheet id from the url and set session variable of it
4. servlet will get projectId from session attribute
5. Access data from the database and create sheet objects
6. User can view them in viewSheetDetails jsp

### Delete Sheet
1. User can delete by clicking "Delete Sheet" button on "View Sheet in Details" page
2. servlet will get projectId and sheetId from session attribute
3. deleteSheet servlet will delete data from database
4. deleteSheet servlet will forward to deletedSheet jsp
5. deleteProject jsp
   * User will see "Success message" and a button to "View Sheet" if everything is alright.
   * User will see "Failure message" and a button to "Project Home" Page if not.

### Add a new row
1. User can add new row by clicking "Add New Row" button on "View Sheet in Details" page
2. addNewRow servlet will get the request and forward to addNewRow jsp
3. User will fill the form with the following inputs
   a. Description
   b. Amount
   c. Cost
   d. Tag
4. User will submit the form
5. addNewRow servlet will get the request and check if data is submitted
6. servlet will get projectId from session attribute
7. If data is submitted, servlet will add data into the database
8. User will be brought back to "View Sheet in Details" page

## Row Related User"s Stories
### View rows in details
1. User can view the details of the row by clicking "View Row in Detail" on "View Sheet in Detail" page.
2. viewRowDetails servlet will forward to viewRowDetails jsp
3. servlet will get the row id from the url
4. servlet will get projectId from session attribute
5. Access data from the database and create row object
6. User can view it in viewRowDetails jsp

### Edit rows
1. User can edit a row by clicking "Edit" button on "View Row in Detail" page
2. EditRow servlet will forward to editRow jsp
3. servlet will get the row id from the url
4. EditRow servlet will get the data and add to the form.
5. User will edit the information.
6. EditRow servlet will check if data is submitted or not and edit the information if submitted and brings to editProfile jsp.
6. editRow jsp
   * User will see "Success message" and a button to "View Sheet in Details" if everything is fine.
   * User will see "Failure message" and a button to "View Sheet in Details" if not.

### Delete row
1. User can delete a row by clicking "Delete Row" button on "View Row in Detail" page
2. deleteRow servlet will get the request
3. Delete the data from the database
4. deleteRow servlet will forward to "View Sheet in Details"

