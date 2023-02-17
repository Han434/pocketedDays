# Pocketed Days : Application Flow

## User Related User's Stories
### Log in
1. User clicks the "Log in" button in the "User" Menu.
2. LogIn servlet forwards to logIn jsp.
   * User fills out the form with the following inputs when there is no data submitted.
     a. User Name
     b. Password
     (or)
   * User click "Sign up" button.
3. Request goes to LogIn servlet.
4. LogIn Servlet checks data are submitted or not.
5. If yes, servlet sets session of userId.
6. Servlet forwards to logIn jsp.
7. login jsp with result.
   * User see "Success message" and a button to "Home Page" if user is authenticated.
   * User see "Failure message" and a button to "Log in" Page if user is authenticated.
    

### Sign up
1. User reaches to the "Sign Up" page by clicking the "Sign up" button in "Log in" page.
2. SingUp servlet forwards to signUp jsp.
3. User fills out the form with the following input on the "Sign in" page when there is no data submitted.
   a. Name
   b. User Name
   c. Date of Birth
   d. Password
4. SignUp servlet gets the request.
5. SignUp servlet checks if data are submitted or not 
6. Servlet creates a new user and add data to the database if submitted.
7. Servlet forwards to signUp jsp
8. signUp jsp with result.
    * User sees "Success message" and a button to "Home Page" if user is authenticated.
    * User sees "Failure message" and a button to "Sign up" Page if user is authenticated.

### Log out
1. User clicks "Log out" button in "User" Menu if user has already log in.
2. LogOut servlet logs user out by removing userId session variable 
3. Servlet brings user back to HomePage.

### View Profile Information
1. User clicks the "My Account" button in the Main Menu.
2. ViewUserInformation Servlet gets the request.
3. Servlet gets user id from session variable.
4. Servlet gets data from database.
5. Servlet creates user object and assign the data
6. Servlet forwards to viewUserInformation jsp.

### Edit Profile
1. User clicks "Edit" button in the "My account" page.
2. EditProfile Servlet gets user id from session variable.
3. EditProfile servlet gets the data .
4. Servlet forwards to editProfile jsp when there is no data submitted.
5. User edits the information.
6. EditProfile servlet gets the request.
7. EditProfile servlet checks if data is submitted or not and edit the information if submitted.
8. Servlet forwards to editProfile jsp.
9. editProfile jsp.
    * User sees "Success message" and a button to "Home Page" if everything is fine.
    * User sees "Failure message" and a button to "Home Page" if not.

## Page Related User's Stories
1. User clicks "Contact" button in the Main Menu.
2. User sees the contact information within it.

### View Homepage
1. User views "Home Page" right after entering the website.

### View Navigation Menu
1. User sees the Navigation Menu on every page of the website.

### View Created Projects
1. User logs in to the specific project or view created projects in the Workspace page.
2. Workspace servlet creates project object for each project that user created or log in.
3. Servlet forwards to workspace jsp.
4. Displays them as cards in workspace jsp.
5. workspace jsp shows user created projects and a new card to create or log in into the new project.

### Give Feedback
1. User clicks "Help us Improve" page in the Main Menu.
2. helpUsImprove Servlet forwards to helpUsImprove jsp.
3. User fills the form and submit.
4. helpUsImprove servlet gets the request.
5. Servlet adds the data into the database.
6. Servlet forwards to helpUsImprove.jsp.
7. Show Message in helpUsImproveVerify jsp.

## Project Related User's Stories
1. User clicks "Add New" button on Workspace page.
2. CreateProject servlet gets the request.
3. CreateProject servlet forwards to the createProject jsp when no data is submitted.
   * User fills the form with the following inputs and submit
      a. Project Name
      b. Project Description
      c. Project Password
   * User logs into the project by clicking "Log in to the Project" button.
4. CreateProject servlet gets the request.
5. CreateProject servlet checks if the data is submitted or not.
6. Servlet gets userId from session attribute
7. Sets projectId as session attribute.
8. Servlet adds data to the database if submitted.
9. Servlet forwards to ProjectHomeServlet.

### Give Project Name, Password and Description
1. Project Name, Password and Description can be given during project creation process.

### View Project Information
1. User creates or logs in to the project to get to "Project Home" page.
2. ProjectHome servlet gets the request.
3. Servlet gets userId and projectId as session attribute.
4. Servlet gets data from database.
5. Servlet sets session attribute of projectId.
6. Servlet forwards to projectHome jsp.
7. projectHome jsp will output those project information.

### Log in to the project
1. User clicks "Log into the Project" button in "Create Project" page.
2. LogIntotPorjectServlet gets the request.
3. Servlet forwards to logIntotProject jsp.
4. User enters the following in logIntoProject jsp when no data is submitted.
   a. Project Name
   b. Project Id
   c. Project Password
5. Request receives by LogIntotPorject servlet. 
6. Servlet checks if data is submitted or not.
7. Gets userId and projectId from session attribute.
8. userId of session variable will be added to database along with projectId to assign user to the project.
9. Servlet
   * User sees "Project Home" page upon successful login.
   * User sees "Log in to the Project" page if login fail.

### Delete Project
1. User clicks "Delete" button on "Project Home" page.
2. DeleteProject servlet gets the request. 
3. Servlet gets projectId and userId from session attribute
4. Servlet deletes project from database.
5. Forwards to deleteProject jsp
6. deleteProject jsp
   * User sees "Success message" and a button to "Workspace" if everything is alright.
   * User sees "Failure message" and a button to "Project Home" Page if not.

### View Project Menu
1. Project menu will be available on project pages by clicking an icon on the top left side of the page.

### Remove Team Member from the project
1. Project creator clicks "Remove team member" on "Project Home" page.
2. RemoveTeamMember servlet gets the request.
3. Servlet gets projectId and userId from session attribute.
4. Servlet forwards to removeTeamMember jsp.
5. Clicks "Remove" button beside of the team member name that user like to remove.
6. RemoveTeamMember servlet gets the request.
7. Servlet removes user from the project.
8. Servlet deletes data from the database
9. Servlet forwards to "Project Home" page

### View Financial Manager
1. User clicks on "Financial Manager" button to view "Financial Manager" page

## View Related User's Stories
### View the view menu
1. User sees the view menu as user enter into any view of the project.

### View page for list of sheets
1. User views the list of sheets in the "View Sheets" page.
2. ViewSheet servlet gets the request.
3. Servlet gets data form database.
4. Creates sheet object and assign value into the instance variables.
5. Servlet gets the type of list of sheets from the url and projectId from session variable.
6. Servlet forwards to viewSheet jsp.
7. User views them in viewSheet jsp

### Run reports by default or condition
1. User clicks "Run Report" in View menu.
2. If nothing is submitted, RunReport servlet will forward to runReport jsp ask user the following
    Choose
      * Revenue or Expense or Both
      * Default or condition
3. If user chooses to run report by condition and submit
4. Jsp generates different form and present it to user
5. Fill it out and run the report.
6. Result Servlet gets the request.
7. Servlet gets projectId from session variable.
8. Servlet will create row object for each row of the database
9. Servlet do every necessary calculation
10. Servlet forwards to result jsp. 
11. result jsp displays them to the user.

## Sheet Related User's Stories
1. User clicks "Add New Sheet" on the "View" page.
2. CreateNewSheet Servlet gets the request.
3. Servlet forwards to createNewSheet jsp.
4. A form with the following inputs will be there
   a. Sheet Description
   b. Organization
   c. File
   d. Note
5. CreateNewSheet Servlet gets the request.
6. servlet gets projectId from session attribute.
7. Servlet gets the request, check if data is submitted or not.
8. Adds new sheet to the database.
9. User will be brought to "Sheet Home" page

### View each sheet
1. User clicks "View Sheet in Detail" on "View Sheets" page.
2. ViewSheetDetails Servlet gets the request.
3. Servlet gets the sheet id from the url.
4. Servlet gets projectId from session attribute.
5. Servlet sets session attribute of sheetId
6. Access data from the database and create row objects for each rows of result from the database.
7. Servlet forwards to viewSheetDetails jsp
8. User can view them in viewSheetDetails jsp

### Delete Sheet
1. User clicks "Delete Sheet" button on "View Sheet in Details" page
2. DeleteSheet Servlet gets the request.
3. Servlet gets projectId and sheetId from session attribute
4. deleteSheet servlet deletes data from database
5. deleteSheet servlet forwards to deletedSheet jsp
6. deleteSheet jsp
   * User sees "Success message" and a button to "View Sheet" if everything is alright.
   * User sees "Failure message" and a button to "Project Home" Page if not.

### Add a new row
1. User clicks "Add New Row" button on "View Sheet in Details" page.
2. addNewRow servlet gets the request and forward to addNewRow jsp.
3. User will fill the form with the following inputs when no data is submitted.
   a. Description
   b. Amount
   c. Cost
   d. Type : Service or Product or Donation
   e. Tag
4. User submits the form.
5. AddNewRow servlet gets the request and check if data is submitted
6. Servlet gets projectId and sessionId from session attribute
7. If data is submitted, servlet adds data into the database
8. User will be brought back to "View Sheet in Details" page

## Row Related User"s Stories
1. User clicks "View Row in Detail" on "View Sheet in Detail" page.
2. ViewRowDetails servlet gets the row id from the url
3. Servlet gets projectId from session attribute
4. Access data from the database and creates row object for each of the row within the result set
5. Servlet forwards to viewRowDetails jsp
6. User views it in viewRowDetails jsp

### Edit rows
1. User edits a row by clicking "Edit" button on "View Row in Detail" page
2. EditRowServlet get the request.
3. Servlet gets the row id from the url.
4. Servlet gets the data.
5. Servlet forwards to editRow jsp.
6. User edits the information.
7. EditRowServlet gets the request.
8. EditRow servlet checks if data is submitted or not and edit the information if submitted and brings to editRow jsp.
9. editRow jsp
   * User will see "Success message" and a button to "View Sheet in Details" if everything is fine.
   * User will see "Failure message" and a button to "View Sheet in Details" if not.

### Delete row
1. User can delete a row by clicking "Delete Row" button on "View Row in Detail" page
2. DeleteRow servlet will get the request.
3. Get the prjectId and sheetId from session attribute.
4. Delete the data from the database.
5. DeleteRow servlet will forward to "View Sheet in Details".

