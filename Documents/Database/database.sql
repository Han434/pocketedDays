use pocketed_days;
drop table if exists userProject, user, project, sheet, rowOfSheet;
CREATE TABLE user (
                      userId int auto_increment primary key,
                      firstName varchar(100),
                      lastName varchar(100),
                      userName varchar(255),
                      gender varchar(20),
                      email varchar(255),
                      dateOfBirth date
);

CREATE TABLE project (
                         projectId int auto_increment primary key,
                         projectName varchar(255),
                         projectPassword varchar(255),
                         projectDescription varchar(255),
                         updatedDate date
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
        foreign key (projectId) references pocketed_days.project (projectId),
    constraint userId_fk
        foreign key (userId) references pocketed_days.user (userId)
);

CREATE TABLE sheet (
                       sheetId int primary key auto_increment,
                       projectId int,
                       sheetCreatorId int,
                       sheetDescription varchar(255),
                       updatedDate date,
                       organization varchar(255),
                       filePath varchar(255),
                       note varchar(255),
                       sheetType varchar(255),
                       constraint sheet_project_fk
                           foreign key (projectId) references pocketed_days.project (projectId)
);

CREATE TABLE rowOfSheet (
                            rowId int primary key auto_increment,
                            sheetId int,
                            rowCreatorId int,
                            updatedDate date,
                            rowDescription varchar(255),
                            quantity varchar(255),
                            costPerItem int,
                            rowType varchar(255),
                            tag varchar(255),
                            constraint rowofsheet_sheet_fk
                                foreign key (sheetId) references pocketed_days.sheet (sheetId)
);