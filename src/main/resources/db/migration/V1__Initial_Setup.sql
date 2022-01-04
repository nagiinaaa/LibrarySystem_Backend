CREATE TABLE books (id SERIAL PRIMARY KEY, title VARCHAR(255), author VARCHAR(255), bookFormat VARCHAR(255),
 numberOfCopies INT, copiesInUse INT DEFAULT 0, copiesAvailable INT, bookCover VARCHAR(255));

CREATE TABLE users (id SERIAL PRIMARY KEY, username VARCHAR(255) UNIQUE, password VARCHAR(255),
 librarian BOOLEAN DEFAULT 'false', totalLoans int DEFAULT 5, currentLoans int DEFAULT 0, remainingLoans int DEFAULT 5);

CREATE TABLE loanSystem (id SERIAL PRIMARY KEY, userid INT REFERENCES users(id), bookid INT REFERENCES books(id));

CREATE TABLE shelves (id SERIAL PRIMARY KEY, userid INT REFERENCES users(id), bookid INT REFERENCES books(id),
readBook BOOLEAN DEFAULT 'no', toBeRead BOOLEAN DEFAULT 'no');

INSERT INTO books (title, author, bookFormat, numberOfCopies, copiesInUse, copiesAvailable, bookCover) VALUES ('The Diviners', 'Libba Bray', 'eBook',
 2, 1, 1, 'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1350039340l/15927006.jpg');
 INSERT INTO books (title, author, bookFormat, numberOfCopies, copiesInUse, copiesAvailable, bookCover) VALUES ('The Diviners',
  'Libba Bray', 'Audiobook', 3, 0, 3, 'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1350039340l/15927006.jpg');
INSERT INTO books (title, author, bookFormat, numberOfCopies, copiesInUse, copiesAvailable, bookCover) VALUES ('Six of Crows', 'Leigh Bardugo',
'Audiobook', 4, 1, 3, 'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1628438817l/23437156._SX318_.jpg');
INSERT INTO books (title, author, bookFormat, numberOfCopies, copiesInUse, copiesAvailable, bookCover) VALUES ('The Picture of Dorian Gray',
 'Oscar Wilde', 'eBook', 10, 1, 9, 'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1546103428l/5297.jpg');
INSERT INTO books (title, author, bookFormat, numberOfCopies, copiesAvailable, bookCover) VALUES ('Homegoing', 'Yaa Gyasi', 'Audiobook',
5, 5, 'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1448108591l/27071490.jpg');
INSERT INTO books (title, author, bookFormat, numberOfCopies, copiesAvailable, bookCover) VALUES ('Homegoing', 'Yaa Gyasi', 'eBook',
4, 4, 'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1448108591l/27071490.jpg');
INSERT INTO books (title, author, bookFormat, numberOfCopies, copiesAvailable, bookCover) VALUES ('Homegoing', 'copycat', 'eBook',
6, 6, 'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1448108591l/27071490.jpg');
INSERT INTO books (title, author, bookFormat, numberOfCopies, copiesAvailable, bookCover) VALUES ('Stalking Jack the Ripper', 'Kerri
Maniscalco', 'eBook', 6, 6, 'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1530823449l/40727470._SY475_.jpg');

INSERT INTO users (username, password, librarian, currentLoans, remainingLoans) VALUES ('user1', 'password', 'false', 1, 4);
INSERT INTO users (username, password, librarian, currentLoans, remainingLoans) VALUES ('janedoe', 'enter', 'false', 2, 3);
INSERT INTO users (username, password, librarian, currentLoans, remainingLoans) VALUES ('thelibrarian', 'password', 'true', 0, 5);

INSERT INTO loanSystem (userid, bookid) VALUES (1, 1);
INSERT INTO loanSystem (userid, bookid) VALUES (2, 2);
INSERT INTO loanSystem (userid, bookid) VALUES (2, 3);

INSERT INTO shelves (userid, bookid, readBook, toBeRead) VALUES(1, 1, 'yes', 'no');
INSERT INTO shelves (userid, bookid, readBook, toBeRead) VALUES(1, 2, 'no', 'yes');
INSERT INTO shelves (userid, bookid, readBook, toBeRead) VALUES(2, 1, 'yes', 'no');
INSERT INTO shelves (userid, bookid, readBook, toBeRead) VALUES(3, 1, 'no', 'yes');

