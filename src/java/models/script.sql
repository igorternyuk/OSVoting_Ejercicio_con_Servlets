--DROP DATABASE votesOS;

CREATE DATABASE votesOS;

USE votesOS;

CREATE TABLE operatingSystem(
    id CHAR(38) PRIMARY KEY,
    name VARCHAR(100)    
);

CREATE TABLE vote(
    id CHAR(38) PRIMARY KEY,
    id_os CHAR(38),
    fecha DATETIME,
    FOREIGN KEY(id_os) REFERENCES operatingSystem(id)
);

INSERT INTO operatingSystem VALUES(UUID(),'Linux');
INSERT INTO operatingSystem VALUES(UUID(),'FreeBSD');
INSERT INTO operatingSystem VALUES(UUID(),'Solaris');
INSERT INTO operatingSystem VALUES(UUID(),'Android');
INSERT INTO operatingSystem VALUES(UUID(),'Windows');
INSERT INTO operatingSystem VALUES(UUID(),'MacOS');

CREATE VIEW countVotes AS
SELECT
	os.name AS os,
    count(v.id_os) AS votes
FROM
	operatingSystem os
INNER JOIN
	vote v
ON
	os.id = v.id_os
GROUP BY
	os.name
ORDER BY
	count(v.id_os) DESC;

