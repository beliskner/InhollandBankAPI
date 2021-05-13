DROP TABLE IF EXISTS TBL_HOLDERS;

CREATE TABLE TBL_HOLDERS (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  email VARCHAR(250) DEFAULT NULL
);

INSERT INTO TBL_HOLDERS (first_name, last_name, email) VALUES
  ('Wilmar', 'de Hoogd', '567659@student.inholland.nl'),
  ('Rene', 'Steneker', '567659@student.inholland.nl'),
  ('Tim', 'Ranzijn', '566810@student.inholland.nl'),
  ('Lorain', 'Tachefini', '620651@student.inholland.nl');