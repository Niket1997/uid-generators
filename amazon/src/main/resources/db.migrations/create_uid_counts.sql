CREATE TABLE IF NOT EXISTS uid_counts
(
    id      int         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    service varchar(20) NOT NULL,
    count   BIGINT      NOT NULL DEFAULT 0,
    UNIQUE (service)
);