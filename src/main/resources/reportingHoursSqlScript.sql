
drop database reporting_system;

CREATE DATABASE `reporting_system` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

use reporting_system;

CREATE TABLE `users` (
  `id` varchar(100) not NULL PRIMARY KEY ,
  `fullname` varchar(4000) DEFAULT NULL,
  `password` varchar(4000) DEFAULT NULL,
  `email` varchar(4000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `work_hours` (
  `id`  varchar(100) not NULL PRIMARY KEY,
  `enterance` datetime DEFAULT NULL,
  `exit` datetime DEFAULT NULL,
  `user` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



insert into users values(1,'asaf arviv' , '1', 'asaf.arviv@gmail.com');

insert into users values(2,'ariel arviv' , '2', 'ariel.arviv@gmail.com');

insert into users values(3,'adi arviv' , '3', 'adi.arviv@gmail.com');

select * from users;


INSERT INTO `reporting_system`.`work_hours` VALUES ( '1', now()  , now() , 'asaf' );

INSERT INTO `reporting_system`.`work_hours` VALUES ( '2', now()  , now() , 'mordechai1' );

INSERT INTO `reporting_system`.`work_hours` VALUES ( '3', now()  , now() , 'asaf' );

SELECT * FROM reporting_system.work_hours;

SELECT * FROM reporting_system.users;

/*SELECT * FROM reporting_system.work_hours;*/


SELECT * FROM reporting_system.users;


SELECT * FROM reporting_system.work_hours;



/*DELETE from USERS WHERE password = 2 ; */


use reporting_system;
commit;

