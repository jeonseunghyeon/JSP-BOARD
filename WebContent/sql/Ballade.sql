create table Ballade(
	BalladeID int,
	BalladeTitle varchar(50),
	userID varchar(20),
	BalladeDate datetime,
	BalladeContent varchar(2048),
	BalladeAvailable int,
	primary key(BalladeID)
);
