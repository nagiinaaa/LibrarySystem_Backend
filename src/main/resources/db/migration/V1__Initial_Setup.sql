CREATE TABLE books (id SERIAL PRIMARY KEY, title VARCHAR(255), author VARCHAR(255), bookFormat VARCHAR(255),
 numberOfCopies INT, copiesInUse INT DEFAULT 0, copiesAvailable INT, bookCover VARCHAR(255));

CREATE TABLE users (id SERIAL PRIMARY KEY, username VARCHAR(255), password VARCHAR(255), librarian BOOLEAN DEFAULT 'false');

CREATE TABLE libSystem (id SERIAL PRIMARY KEY, userid INT REFERENCES users(id), bookid INT REFERENCES books(id));

INSERT INTO books (title, author, bookFormat, numberOfCopies, copiesAvailable, bookCover) VALUES ('The Diviners', 'Libba Bray', 'eBook',
 2, 2, 'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1350039340l/15927006.jpg');
INSERT INTO books (title, author, bookFormat, numberOfCopies, copiesAvailable, bookCover) VALUES ('Six of Crows', 'Leigh Bardugo',
'Audiobook', 4, 4, 'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1628438817l/23437156._SX318_.jpg');
INSERT INTO books (title, author, bookFormat, numberOfCopies, copiesAvailable, bookCover) VALUES ('The Picture of Dorian Gray',
 'Oscar Wilde', 'eBook', 10, 10, 'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1546103428l/5297.jpg');
INSERT INTO books (title, author, bookFormat, numberOfCopies, copiesAvailable, bookCover) VALUES ('Homegoing', 'Yaa Gyasi', 'Audiobook',
5, 5, 'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1448108591l/27071490.jpg');
INSERT INTO books (title, author, bookFormat, numberOfCopies, copiesAvailable, bookCover) VALUES ('Stalking Jack the Ripper', 'Kerri
Maniscalco', 'eBook', 6, 6, 'https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1530823449l/40727470._SY475_.jpg');

INSERT INTO users (username, password, librarian) VALUES ('user1', 'password', 'false');
INSERT INTO users (username, password, librarian) VALUES ('janedoe', 'enter', 'false');
INSERT INTO users (username, password, librarian) VALUES ('thelibrarian', 'password', 'true');

INSERT INTO libSystem (userid, bookid) VALUES (1, 1);
INSERT INTO libSystem (userid, bookid) VALUES (2, 2);
INSERT INTO libSystem (userid, bookid) VALUES (2, 3);