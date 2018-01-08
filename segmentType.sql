LOCK TABLES `SegmentType` WRITE;
/*!40000 ALTER TABLE `SegmentType` DISABLE KEYS */;
INSERT INTO `SegmentType` VALUES (1,1,'Customer Segment'),(2,2,'Value Proposition'),(3,3,'Distribution Channel'),(4,4,'Customer Relationship'),(5,5,'Revenue Streams'),(6,6,'Key Resources'),(7,7,'Key Activities'),(8,8,'Key Partners'),(9,9,'Cost Structure');
/*!40000 ALTER TABLE `SegmentType` ENABLE KEYS */;
UNLOCK TABLES;