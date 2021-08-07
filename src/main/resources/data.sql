-- -----------------------------------------------------
-- Table "movierankdb"."academy_award"
-- -----------------------------------------------------

DROP TABLE IF EXISTS "academy_award";


CREATE TABLE IF NOT EXISTS "academy_award" (
 "id_academy_award" SERIAL NOT NULL,
 "year" VARCHAR(255) NULL,
 "category" VARCHAR(255) NULL,
 "nominee" TEXT NULL,
 "additional_info" VARCHAR(255) NULL,
 "won" TEXT NULL,
 PRIMARY KEY ("id_academy_award"));


-- -----------------------------------------------------
-- Table "movierankdb"."user"
-- -----------------------------------------------------
DROP TABLE IF EXISTS "user" ;

CREATE TABLE IF NOT EXISTS "user" (
"id_user" SERIAL NOT NULL,
"username" VARCHAR(45) NULL,
"password" VARCHAR(255) NULL,
PRIMARY KEY ("id_user"))