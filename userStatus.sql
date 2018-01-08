LOCK TABLES 'userstatus' WRITE;
INSERT INTO userstatus Values('activated'),('banned'),('frozen'),('not activated');
UNLOCK TABLES;