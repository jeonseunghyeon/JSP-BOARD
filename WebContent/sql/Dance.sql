create table Dance(
	DanceID int,
	DanceTitle varchar(50),
	userID varchar(20),
	DanceDate datetime,
	DanceContent varchar(2048),
	DanceAvailable int,
	primary key(DanceID)
);