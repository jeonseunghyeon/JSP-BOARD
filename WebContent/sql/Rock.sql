create table Rock(
	RockID int,
	RockTitle varchar(50),
	userID varchar(20),
	RockDate datetime,
	RockContent varchar(2048),
	RockAvailable int,
	primary key(RockID)
);