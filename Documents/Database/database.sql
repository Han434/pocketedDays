use pocketed_days;
drop table if exists user, project, userProject, sheet, rowOfSheet;
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

CREATE TABLE userProject (
                             projectId int auto_increment primary key,
                             userId int,
                             userType varchar(255),
                             joinInDate date
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
                       sheetType varchar(255)
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
                            tag varchar(255)
);