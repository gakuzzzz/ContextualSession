# --- !Ups

CREATE TABLE task (
    id         SERIAL   PRIMARY KEY,
    title      TEXT     NOT NULL,
    done       BOOLEAN  NOT NULL
);

# --- !Downs

DROP TABLE task;

