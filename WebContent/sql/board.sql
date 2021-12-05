create table board(
	boardID int,
	boardTitle varchar(50),
	userID varchar(20),
	boardDate datetime,
	boardContent varchar(2048),
	boardAvailable int,
	primary key(boardID)
);