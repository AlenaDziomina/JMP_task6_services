CREATE TABLE userservices.user_avatar
(
    id_avatar INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    image BLOB NOT NULL
);
CREATE UNIQUE INDEX user_avatar_id_avatar_uindex ON userservices.user_avatar (id_avatar);

CREATE TABLE userservices.user_profile
(
    id_user INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    avatar_id INT(11),
    CONSTRAINT user_profile_user_avatar_id_avatar_fk FOREIGN KEY (avatar_id) REFERENCES user_avatar (id_avatar) ON DELETE SET NULL
);
CREATE UNIQUE INDEX user_profile_id_user_uindex ON userservices.user_profile (id_user);
CREATE INDEX user_profile_user_avatar_id_avatar_fk ON userservices.user_profile (avatar_id);