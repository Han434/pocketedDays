drop table if exists pocketed_days.user, pocketed_days.project, pocketed_days.userProject, pocketed_days.sheet, pocketed_days.rowOfSheet;
CREATE TABLE pocketed_days.user (
                                    userId int auto_increment primary key,
                                    name varchar(255),
                                    userName varchar(255),
                                    dateOfBirth date
);

CREATE TABLE pocketed_days.project (
                                       projectId int auto_increment primary key,
                                       projectCreatorId int,
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
                                     note varchar(255),
                                     sheetType varchar(255)
);

CREATE TABLE pocketed_days.rowOfSheet (
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
delete from pocketed_days.rowOfSheet;
INSERT INTO pocketed_days.rowOfSheet (sheetId, rowCreatorId, createdDate, rowDescription, quantity, costPerItem, rowType, tag)
VALUES (1, 1, "2023-5-5", "Socket", 5, 200, "Product", "");