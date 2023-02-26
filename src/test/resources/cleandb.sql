drop table if exists pocketed_days.user, pocketed_days.project, pocketed_days.userProject, pocketed_days.sheet, pocketed_days.expense, pocketed_days.income;
CREATE TABLE pocketed_days.user (
                                    userId int auto_increment primary key,
                                    name varchar(255),
                                    userName varchar(255),
                                    dateOfBirth date
);

CREATE TABLE pocketed_days.project (
                                       projectId int auto_increment primary key,
                                       creatorId int,
                                       projectName varchar(255),
                                       projectPassword varchar(255),
                                       projectDescription varchar(255),
                                       createdDate date
);

CREATE TABLE pocketed_days.userProject (
                                           projectId int auto_increment primary key,
                                           userId int,
                                           userType varchar(255),
                                           joinInDate date
);

CREATE TABLE pocketed_days.sheet (
                                     sheetId int primary key auto_increment,
                                     projectId int,
                                     sheetCreatorId int,
                                     sheetDescription varchar(255),
                                     createdDate date,
                                     organization varchar(255),
                                     filePath varchar(255),
                                     note varchar(255)
);

CREATE TABLE pocketed_days.expense (
                                       expenseId int primary key auto_increment,
                                       sheetId int,
                                       rowCreatorId int,
                                       createdDate date,
                                       rowDescription varchar(255),
                                       quantity varchar(255),
                                       costPerItem int,
                                       expenseType varchar(255),
                                       tag varchar(255)
);

CREATE TABLE pocketed_days.income (
                                      incomeId int primary key auto_increment,
                                      sheetId int,
                                      rowCreatorId int,
                                      createdDate date,
                                      rowDescription varchar(255),
                                      quantity varchar(255),
                                      costPerItem int,
                                      incomeType varchar(255),
                                      tag varchar(255)
);
delete from pocketed_days.project;
INSERT INTO pocketed_days.project (creatorId, projectName, projectPassword, projectDescription, createdDate)
VALUES (1, "hi", "hi", "hi", "1996-5-5");
INSERT INTO pocketed_days.project (creatorId, projectName, projectPassword, projectDescription, createdDate)
VALUES (2, "testing", "testing", "testing", "1995-5-5");