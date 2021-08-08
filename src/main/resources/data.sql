-- -----------------------------------------------------
-- Table "movierankdb"."academy_award"
-- -----------------------------------------------------

--DROP TABLE IF EXISTS "academy_award";


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
--DROP TABLE IF EXISTS "user_auth" ;

CREATE TABLE IF NOT EXISTS "user_auth" (
"id_user" SERIAL NOT NULL,
"username" VARCHAR(45) NULL,
"password" VARCHAR(255) NULL,
"blocked" BOOLEAN DEFAULT FALSE,
PRIMARY KEY ("id_user"));

--DROP TABLE IF EXISTS "movie_rating" ;

CREATE TABLE movie_rating
(
    id_movie_rank SERIAL NOT NULL,
    movie_identifier VARCHAR(255),
    user_id SERIAL,
    user_rating SMALLINT,
    voting_count SMALLINT
    PRIMARY KEY (id_movie_rank)
)


--INSERT INTO "user_auth"("username", "password", "blocked") VALUES ('admin','admin', false);