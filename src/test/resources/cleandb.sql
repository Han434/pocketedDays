use test_pocketed_days;
drop table if exists userProject, user, project, sheet, rowOfSheet;
CREATE TABLE user (
                                    userId int auto_increment primary key,
                                    name varchar(255),
                                    userName varchar(255),
                                    dateOfBirth date
);

CREATE TABLE project (
                         projectId int auto_increment primary key,
                         projectCreatorId int,
                         projectName varchar(255),
                         projectPassword varchar(255),
                         projectDescription varchar(255),
                         createdDate date
);

create table userProject
(
    id         int auto_increment
        primary key,
    projectId  int          not null,
    userId     int          not null,
    userType   varchar(100) null,
    joinInDate date         not null,
    constraint projectId_fk
        foreign key (projectId) references test_pocketed_days.project (projectId),
    constraint userId_fk
        foreign key (userId) references test_pocketed_days.user (userId)
);

CREATE TABLE sheet (
                                     sheetId int primary key auto_increment,
                                     projectId int,
                                     sheetCreatorId int,
                                     sheetDescription varchar(255),
                                     createdDate date,
                                     organization varchar(255),
                                     filePath varchar(255),
                                     note varchar(255),
                                     sheetType varchar(255),
                                     constraint sheet_project_fk
                                         foreign key (projectId) references test_pocketed_days.project (projectId)
);

CREATE TABLE rowOfSheet (
                                          rowId int primary key auto_increment,
                                          sheetId int,
                                          rowCreatorId int,
                                          createdDate date,
                                          rowDescription varchar(255),
                                          quantity varchar(255),
                                          costPerItem int,
                                          rowType varchar(255),
                                          tag varchar(255),
                                          constraint rowofsheet_sheet_fk
                                              foreign key (sheetId) references test_pocketed_days.sheet (sheetId)
);
delete from project;
INSERT INTO project (projectCreatorId, projectName, projectPassword, projectDescription, createdDate)
VALUES (1, "Manlay Website", "123", "This project is to create something new.", "2023-5-5");
delete from sheet;
INSERT INTO sheet (projectId, sheetCreatorId, sheetDescription, createdDate, organization, filePath, note, sheetType)
VALUES (1, 1, "Installing computer for ABC department", "2023-5-5", "TechLand", "invoice.png", "Note here", "Expense");
delete from rowOfSheet;
INSERT INTO rowOfSheet (sheetId, rowCreatorId, createdDate, rowDescription, quantity, costPerItem, rowType, tag)
VALUES (1, 1, "2023-5-5", "Socket", 5, 200, "Product", "dfas");