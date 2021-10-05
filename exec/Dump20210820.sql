-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: ssafy-a406-db.cbvcooe5koqq.ap-northeast-2.rds.amazonaws.com    Database: ggomzirak_db
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contents` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `edit_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `post_id` bigint DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `type_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `comments_fk_type_id` (`type_id`),
  KEY `comments_fk_user_id` (`user_id`),
  CONSTRAINT `comments_fk_type_id` FOREIGN KEY (`type_id`) REFERENCES `post_type` (`id`),
  CONSTRAINT `comments_fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (3,'저도 예전에 안됐는데요. 일단 샴푸뚜껑을 여는 방향 말고 반대 방향으로 끝까지 돌리세요. 아주 최대한 끝으로 꽉 잠그세요. 그런 다음에 원래 여는 방법대로 열어보세요. 그럼 될 거예요.',NULL,1,'2021-08-14 17:49:07',3,2),(4,'감사합니다 정보! 방충망 갈아야하나 고민하고 있었어요',NULL,1,'2021-08-14 20:03:29',1,2),(7,'생각치도 못한 수면양말의 활용이네요!',NULL,1,'2021-08-14 20:08:13',1,4),(8,'굿 꿀팁~!',NULL,1,'2021-08-14 20:08:26',1,5),(9,'오 집에 남아도는 샴푸있는데 해볼게요',NULL,2,'2021-08-14 20:10:16',1,5),(10,'진짜 좋을 거 같아요. 홈스타 냄새 대신 샴푸냄새라니',NULL,2,'2021-08-14 20:10:45',1,6),(11,'아.. 화장실 청소할 때가 됐네요',NULL,2,'2021-08-14 20:12:13',1,1),(12,'화장실 청소 오늘 할게요 화장실 꿀팁 보니 하고 싶어지네요',NULL,2,'2021-08-14 20:12:35',1,7),(13,'이 글 보고 화장실 가니까 물때가 보였어요 ㅋㅋㅋㅋㅋ',NULL,3,'2021-08-14 20:13:11',1,7),(14,'구연산이 진짜 의외로 쓸 곳이 참 많아요',NULL,3,'2021-08-14 20:13:27',1,5),(15,'맞아요 구연산이 물때 청소엔 최고라고 하더라구요',NULL,3,'2021-08-14 20:13:49',1,4),(16,'좋은 꿀팁 감사합니다 :)',NULL,3,'2021-08-14 20:14:05',1,1),(17,'저희 아들놈이 지금 문 잠그고 들어갔는데 해볼게요',NULL,4,'2021-08-14 20:14:37',1,1),(18,'저희 집 감시하고 계신가요? 깜짝 놀랐어요. 해볼게요',NULL,4,'2021-08-14 20:15:04',1,4),(19,'방에 강아지가 갇혀서 급하게 검색해보니 역시 이런 꿀팁이 감사합니다',NULL,4,'2021-08-14 20:15:30',1,5),(20,'글 잘 보고갑니다 ㅎㅎ',NULL,4,'2021-08-14 20:15:45',1,6),(21,'예전에도 들어봤던 거 같은데 참 실천이 쉽지 않아요',NULL,5,'2021-08-14 21:05:10',1,6),(22,'꿑팁이네요 걸레를 세면대 옆에 놔야겠어요',NULL,5,'2021-08-14 21:05:47',1,1),(23,'오늘부터 습관 들여볼게요',NULL,5,'2021-08-14 21:06:26',1,2),(26,'치약 끝까지 짜기 정말 힘든데 좋은 템이네요',NULL,6,'2021-08-14 22:04:45',1,2),(27,'오 다이소로 바로 가야겠어요',NULL,6,'2021-08-14 22:04:57',1,4),(28,'저는 이미 쓰고 있는데 정말 좋아요',NULL,6,'2021-08-14 22:05:09',1,7),(29,'손 대충 씻는 아이에게 보여주면 좋겠네요',NULL,7,'2021-08-14 22:08:11',1,7),(30,'양치질 3 3 3 법칙만 알았는데 새로운 상식!',NULL,7,'2021-08-14 22:08:28',1,5),(31,'우와 신기하네요 저런 제품이!',NULL,8,'2021-08-14 22:12:47',1,5),(32,'역시 사람들의 아이디어는 끝이 없어요',NULL,8,'2021-08-14 22:13:04',1,2),(33,'로봇청소기 사고 싶은데 돈이....',NULL,8,'2021-08-14 22:13:23',1,6),(34,'와인잔 정말 깨끗하게 닦기 어려워요..',NULL,9,'2021-08-14 22:13:55',1,7),(35,'마침 집에 린넨 천이 있는데 한번 닦아볼게요',NULL,9,'2021-08-14 22:14:19',1,2),(36,'꿀템 추천 감사요',NULL,9,'2021-08-14 22:14:42',1,6),(38,'날파리가 장난 아닌데 실천해볼게요',NULL,10,'2021-08-15 20:07:06',1,2),(39,'여름철만 되면 정말 스트레스예요',NULL,10,'2021-08-15 20:07:32',1,4),(40,'배달봉투 이때까지 다 버렸는데 모아볼게요',NULL,10,'2021-08-15 20:07:47',1,7),(41,'저도 이거 써요 짱좋',NULL,13,'2021-08-15 20:13:46',1,7),(42,'약국보다 훨씬 싸네요 굿',NULL,13,'2021-08-15 20:14:02',1,5),(43,'대박이다요!',NULL,13,'2021-08-15 20:14:12',1,1),(44,'폰이 정말 더럽다고 하더라구요.. 실천해볼게요',NULL,13,'2021-08-15 20:14:29',1,2),(45,'다이소는 정말 다있소',NULL,13,'2021-08-15 20:16:55',1,1),(46,'헉 예전에 버렸는데 이런 팁이',NULL,14,'2021-08-15 20:25:08',1,1),(47,'껌 붙은 자 여기.. 다행히 꿀팁에 있네요',NULL,14,'2021-08-15 20:25:32',1,14),(48,'헉 ㅋㅋㅋㅋ 옷에 껌이라니',NULL,14,'2021-08-15 20:25:48',1,2),(49,'딸꾹질 멈추지 않을 때 찾아서 해볼게요!',NULL,15,'2021-08-15 20:36:05',1,2),(50,'파값 정말 너무 비싸요....',NULL,16,'2021-08-15 20:41:28',1,2),(51,'저희도 키우고 있어요',NULL,16,'2021-08-15 20:42:17',1,1),(52,'아이들도 좋아하겠어요',NULL,16,'2021-08-15 20:42:29',1,4),(53,'펌프의 목 부분을 꽉 잠궈서 꼭지 부분만 반대 방향으로 돌리면 됩니다 :) 한번 해도 안되면 여러번 시도하면 열릴거에요~~',NULL,1,'2021-08-16 09:31:38',3,1),(54,'그거 봤어요! 꿀팁 페이지에 검색해보세요',NULL,4,'2021-08-16 12:34:08',3,1),(55,'인중 누르면 거기가 혈이라... 좀 낫던데',NULL,5,'2021-08-16 12:54:33',3,1),(56,'자기 자신을 꼬집으세요 감각 분산을 위하여',NULL,5,'2021-08-16 12:54:50',3,2),(57,'힘내세요.... 그 마음 알아요',NULL,5,'2021-08-16 12:55:03',3,6),(58,'제가 수박 박사인데요. 일단 수박 꽁지 부분이 작은 게 암컷인데 그게 작을수록 좋구요. 그리고 줄무늬가 뚜렷한 것이 좋아요. 겉부분에 흰색이 많이 올라와 있으면 그건 당분이에요.',NULL,6,'2021-08-16 13:32:23',3,6),(59,'일단 베개에 누우세요. 물이 들어간 방향을 아래로 해서 베개에 닿도록이요. 그러면 10분? 길어야 10분이고 5분 안에 물이 나옵니다.',NULL,7,'2021-08-16 13:55:15',3,2),(60,'수박은 대부분 수분이에요. 그래서 껍질을 믹서기에 가세요. 그리고 쫙 펼쳐서 좀 건조 후 버리시면 훨씬 부피는 줄어요. 근데 건조하는 동안 초파리는 조심하셔요. 냉장고에 넣어두든 해서요.',NULL,8,'2021-08-16 14:03:18',3,1),(61,'드라이시트라고 들어보셨나요? 건조기 할 때 향기시트를 넣으면 향이 훨씬 좋아요. 저는 르샤트라꺼 써요.',NULL,9,'2021-08-16 14:07:32',3,2),(62,'저는 양치를 해요',NULL,11,'2021-08-16 15:41:07',3,2),(63,'손끝으로 박수를 치듯이 해보세요',NULL,11,'2021-08-16 15:41:33',3,1),(64,'저는 그냥 잠깐 잡니다...',NULL,11,'2021-08-16 15:41:43',3,4),(65,'잠이 많은 저도 궁금해요',NULL,11,'2021-08-16 15:42:03',3,7),(66,'밥을 뒤집고 나서 바로 밥그릇에 소분해서 놓으세요. 그리고 밥이 다 식으면 랩핑해서 냉장고에 뒀다가 필요할 때 전자레인지에 돌려드세요. 그럼 처음 맛 그대로랑 비슷해요.',NULL,12,'2021-08-16 15:54:19',3,1),(67,'냉정하네요.. 아무도 댓글 안 달아주시고... 어떻게 되었는지는 묻지 마세요',NULL,13,'2021-08-16 15:57:43',3,4),(68,'심하지 않다면 휴지에 락스 묻혀서 곰팡이에 덮어두세요. 곰팡이는 락스가 직빵이에요.',NULL,14,'2021-08-16 16:03:24',3,6),(69,'맞아요. 씨없는 수박은 당도가 떨어지죠.. 일단 저는 무조건 젓가락을 하나 들고 입에 넣기 전에 쏙쏙 빼요.. 박혀있는 건 어쩔 수 없이 입으로 골라내죠..',NULL,15,'2021-08-16 16:05:49',3,7),(70,'바로바로 하는 게 진리라고 생각합니다.',NULL,16,'2021-08-16 16:08:57',3,2),(71,'물에 담가두는 거요. 그래야 안 굳어서 좀 나중에 해도 수월해요',NULL,16,'2021-08-16 16:09:35',3,4),(72,'식세기.... 식기 세척기가 하는 일이요...',NULL,16,'2021-08-16 16:09:54',3,5),(73,'제가 예전에 대창 먹다가 그랬는데요. 일단 뜨거운 물을 부어보시고 그래도 안되면 싱크대 하부장 열어서 아래 호스 돌려서 분해해서 기름 막힌 거 뺴내셔야 해요.. 저도 죽는 줄 알았는데 이거 못하면 기사 불러야 해서 20만원 깨져요.',NULL,18,'2021-08-16 16:13:18',3,1),(74,'저는 독박 집안일하는 남자입니다. 모든 일의 동기부여는 돈이랍니다. 아내가 집안일 하나 할 때마다 2000원씩 주는데 그 모으는 재미가 쏠쏠해요. 돈을 저금을 하든 모아보세요. 그리고 그 돈을 온전히 본인을 위해 쓰세요.',NULL,19,'2021-08-16 16:15:41',3,6),(76,'와 신기해',NULL,3,'2021-08-17 01:32:21',2,1),(77,'손목 아팠는데 좋네요',NULL,1,'2021-08-17 01:33:55',2,6),(81,'?',NULL,10,'2021-08-17 04:05:22',1,1),(88,'와!',NULL,20,'2021-08-18 01:22:16',1,1),(89,'악',NULL,14,'2021-08-18 01:33:32',1,1),(91,'모기 시러',NULL,18,'2021-08-18 02:23:38',1,6),(92,'수건인 줄 알았는데 수전이었네요!!',NULL,34,'2021-08-18 14:51:12',1,32),(93,'귀여운게 꿀팁이네요.',NULL,35,'2021-08-18 15:35:45',1,31),(94,'고양이 귀여워',NULL,35,'2021-08-18 15:49:19',1,32),(95,'껌던씹..',NULL,14,'2021-08-19 01:06:14',1,32),(96,'페트병 그냥 버리지 말고 써봐야겠네요',NULL,22,'2021-08-19 01:19:54',2,32),(97,'와 너무 좋아보여요','2021-08-19 03:17:38',18,'2021-08-19 03:17:38',1,21),(98,'너무 좋아보여요!',NULL,18,'2021-08-19 03:20:55',1,1),(99,'모기 날파리 다잡았어요! 꿀템!!','2021-08-19 03:21:43',18,'2021-08-19 03:21:43',1,16),(100,'이렇게 쓰면 되겠군요',NULL,33,'2021-08-19 05:19:37',1,6),(101,'회전까지..',NULL,18,'2021-08-19 05:36:25',1,23),(102,'귀가 화~ 할것 같아요ㅎㅎ..',NULL,19,'2021-08-19 06:06:01',2,1),(103,'알려줘요ㅠ',NULL,26,'2021-08-19 06:29:10',2,12),(104,'다이소는 정말..',NULL,6,'2021-08-19 06:29:53',1,15),(105,'여름 끝나기전에 사야겠어요~~',NULL,18,'2021-08-19 06:30:22',1,15),(106,'아니 왜 안 고양이요',NULL,26,'2021-08-19 07:01:25',2,32),(107,'낚였어',NULL,26,'2021-08-19 07:15:55',2,15),(108,'이거 해봤는데 진짜 좋아요!!!bb',NULL,21,'2021-08-19 07:28:51',2,21),(109,'댓글 달아주세요~~',NULL,53,'2021-08-19 09:20:31',1,6),(111,'500원이라니!',NULL,19,'2021-08-19 11:38:06',1,6),(113,'오 해보셨어요? 대박이다 해보신거 올려주세요ㅠㅠ',NULL,21,'2021-08-19 14:22:54',2,24),(114,'이름이 까망쓰라니!',NULL,35,'2021-08-19 15:03:48',1,23),(116,'물 넣기 싫으시다면 물을 마시면 어떻실까요.. ?',NULL,17,'2021-08-19 17:06:05',3,31),(117,'여름이 끝나가지만 좋은 정보네요.',NULL,52,'2021-08-19 17:53:26',1,31),(118,'유선 이어폰을 쓸때 알았더라면. .',NULL,19,'2021-08-19 17:54:14',2,31),(119,'전깃세는 미래의 자신이 내주지 않을까요?',NULL,21,'2021-08-19 17:55:13',3,31);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `post_id` bigint DEFAULT NULL,
  `type_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `likes_fk_type_id` (`type_id`),
  KEY `likes_fk_user_id` (`user_id`),
  CONSTRAINT `likes_fk_type_id` FOREIGN KEY (`type_id`) REFERENCES `post_type` (`id`),
  CONSTRAINT `likes_fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
INSERT INTO `likes` VALUES (1,1,1,1),(2,5,2,1),(3,5,2,2),(4,5,2,13),(5,20,1,5),(6,18,1,5),(7,14,1,5),(8,20,1,6),(9,18,1,6),(10,19,1,6),(11,10,1,6),(12,6,1,6),(13,34,1,6),(14,4,1,6),(15,34,1,32),(16,33,1,1),(17,20,1,21),(18,16,1,7),(19,18,1,21),(20,10,2,1),(21,12,2,1),(22,33,1,32),(23,22,2,32),(24,21,2,32),(25,19,2,32),(26,20,2,32),(27,23,2,32),(28,22,2,6),(29,26,2,6),(30,35,1,32),(31,26,2,32),(32,35,1,21),(33,34,1,1),(34,18,1,1),(35,13,1,21),(36,9,1,1),(37,35,1,31),(38,18,2,32),(39,17,2,32),(40,11,2,21),(41,20,1,32),(42,1,2,32),(43,18,1,7),(44,33,1,6),(45,13,1,34),(46,13,1,7),(47,14,1,7),(48,13,1,12),(49,14,1,12),(50,13,1,29),(51,6,1,29),(52,6,1,7),(53,18,1,23),(54,13,1,23),(55,6,1,23),(56,35,1,23),(57,26,2,1),(58,19,2,1),(59,2,1,6),(60,13,1,14),(61,26,2,12),(62,13,1,1),(63,13,1,15),(64,6,1,15),(65,4,1,15),(66,4,1,7),(67,20,2,15),(68,18,2,15),(69,26,2,15),(70,13,1,24),(71,18,1,24),(72,18,1,28),(73,13,1,28),(74,21,2,21),(75,26,2,28),(76,53,1,6),(77,52,1,21),(78,35,1,6),(79,15,2,32),(80,26,2,31),(81,52,1,31),(82,19,2,31),(83,7,1,31),(84,21,2,31),(85,29,2,32);
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `live`
--

DROP TABLE IF EXISTS `live`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `live` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contents` text COLLATE utf8mb4_unicode_ci,
  `endtime` datetime(6) DEFAULT NULL,
  `maxviewers` int DEFAULT NULL,
  `starttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `thumbnail` text COLLATE utf8mb4_unicode_ci,
  `title` text COLLATE utf8mb4_unicode_ci,
  `type` int DEFAULT NULL,
  `video` text COLLATE utf8mb4_unicode_ci,
  `viewers` int DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `live_fk_user_id` (`user_id`),
  CONSTRAINT `live_fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `live`
--

LOCK TABLES `live` WRITE;
/*!40000 ALTER TABLE `live` DISABLE KEYS */;
INSERT INTO `live` VALUES (89,'이거 보고 하세요','2021-08-19 22:42:50.780000',4,'2021-08-19 13:40:37','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/5ce1e073-7b1f-4538-9b90-552bf5808fefKakaoTalk_20210819_132921288.jpg','고양이 놀아주기',1,'https://i5a406.p.ssafy.io:8443/openvidu/recordings/89/89.mp4',4,6),(90,'키보드를 닦아봐요','2021-08-19 22:58:56.772000',1,'2021-08-19 13:57:25','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/695e0397-f6b1-4c44-93d1-69b8d179d6f6Keychron-K3-main.jpg','키보드 청소',1,'https://i5a406.p.ssafy.io:8443/openvidu/recordings/90/90.mp4',0,1),(91,'청소 꿀팁 대방출해요~~~~','2021-08-19 23:14:53.896000',1,'2021-08-19 14:14:46','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/c05639b4-511e-4cbd-b763-86d27d2e0f04%EB%B0%A9%EC%B2%AD%EC%86%8C.jpg','가을 맞이 방 대청소 합니다!!!!',1,NULL,0,12),(92,'청소 꿀팁 대방출 합니닷','2021-08-19 23:18:43.038000',1,'2021-08-19 14:18:30','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/10104892-fa7a-411e-8b97-e13a1fc1b6d8%EB%B0%A9%EC%B2%AD%EC%86%8C.jpg','가을 맞이 방 대청소 해요!!!!',1,NULL,0,12),(93,'청소 꿀팁 대방출~~~','2021-08-19 23:20:13.998000',1,'2021-08-19 14:19:53','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/15b4314b-c14c-4c4b-bb49-94a71cd5504b%EB%B0%A9%EC%B2%AD%EC%86%8C.jpg','가을 맞이 대청소 같이해요!!!!',1,'https://i5a406.p.ssafy.io:8443/openvidu/recordings/93/93.mp4',1,12),(94,'궁금하죵??????????','2021-08-19 23:22:59.619000',1,'2021-08-19 14:22:37','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/6e06935b-5dd3-458f-b4ba-01c4809ff7ec%EB%AA%A8%EC%9E%90.jpg','걸리적 거리는 모자 깔끔하게 정리 합니다',1,'https://i5a406.p.ssafy.io:8443/openvidu/recordings/94/94.mp4',0,12),(95,'나두 알고 싶다....','2021-08-19 23:29:32.956000',1,'2021-08-19 14:28:10','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/b10c8afd-6cc7-4449-a8d5-672804b2e033KakaoTalk_20210625_122810686.jpg','고양이랑 친해지는 법도 아시나요?!ㅎㅎ',1,'https://i5a406.p.ssafy.io:8443/openvidu/recordings/95/95.mp4',1,15),(96,'알려줘요....','2021-08-19 23:37:31.363000',1,'2021-08-19 14:35:22','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/448ec9e1-312e-44a0-9779-a9576ea82dee%EC%98%A4%EB%94%A7%EC%84%B8%EC%9D%B4.jpg','개.발.천.재.완.소.꿀.팁',1,'https://i5a406.p.ssafy.io:8443/openvidu/recordings/96/96.mp4',1,23),(97,'버릴거야..?ㅠㅠ','2021-08-19 23:45:20.471000',2,'2021-08-19 14:42:00','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/49b078ec-6cf4-42cb-8ddb-3bb1f03f753c%EC%9D%B8%ED%98%95.jpg','안쓰는 인형 어떻게 버리는지 아시나요??!',1,'https://i5a406.p.ssafy.io:8443/openvidu/recordings/97/97.mp4',2,23),(98,'호캉스 가고싶다~~~~','2021-08-19 23:50:32.582000',1,'2021-08-19 14:50:21','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/979dcd5a-446c-452a-b08a-622adbd4eb86%EC%B9%A8%EB%8C%80.jpg','호텔 침대처럼 정리하는 법><',1,NULL,0,23),(99,'나도 몰러;','2021-08-19 23:51:49.568000',1,'2021-08-19 14:51:28','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/816015b3-3c48-4eae-afc6-b71ddf4262bb%EC%B9%A8%EB%8C%80.jpg','호텔처럼 침대 정리하기><',1,'https://i5a406.p.ssafy.io:8443/openvidu/recordings/99/99.mp4',0,23),(100,'세팅중 입니다. 들어오지 마세요.','2021-08-20 02:56:54.408000',1,'2021-08-19 17:56:45','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/0ffcbf58-33c2-426e-b7af-86cfc2177ade%EA%B0%9C%EA%B0%95.jpg','방송 세팅중',1,NULL,0,31);
/*!40000 ALTER TABLE `live` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_type`
--

DROP TABLE IF EXISTS `post_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_type`
--

LOCK TABLES `post_type` WRITE;
/*!40000 ALTER TABLE `post_type` DISABLE KEYS */;
INSERT INTO `post_type` VALUES (1,'꿀팁글'),(2,'shorts'),(3,'qna');
/*!40000 ALTER TABLE `post_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qna`
--

DROP TABLE IF EXISTS `qna`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qna` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contents` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `edit_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `image` text CHARACTER SET utf8,
  `tags` text CHARACTER SET utf8,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `qna_fk_user_id` (`user_id`),
  CONSTRAINT `qna_fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qna`
--

LOCK TABLES `qna` WRITE;
/*!40000 ALTER TABLE `qna` DISABLE KEYS */;
INSERT INTO `qna` VALUES (1,'샴푸가 안나오는데 어떻게 하면 좋을까요? 펌핑 샴푸인데ㅠㅠ',NULL,'','샴푸;','2021-08-16 08:42:35','샴푸가 안나와요',1),(4,'제곧내 딸꾹질 빨리 멈추는 법 아시는 분?',NULL,'','딸꾹질;딸꾹질멈추는법;','2021-08-16 12:27:12','딸꾹질이 안멈춰요',2),(5,'진짜 급해요',NULL,'','배아파요;변참는법;','2021-08-16 12:54:01','배가 아픈데 참는 방법 있을까요? 버스라서...',4),(6,'맛있는 수박 고르는 방법 아시는 분 있나요?',NULL,'','수박고르기;생활꿀팁;','2021-08-16 12:57:56','맛있는 수박 고르는 방법 아시는 분?',4),(7,'빼는 법 아시는 분 있나요?',NULL,'','귀에물들어갔어요;귀;','2021-08-16 13:52:42','귀에 물이 들어갔는데 계속 안빠져요 ',5),(8,'종량제 몇개를 써야하는 건지..',NULL,'','수박;음식물쓰레기;생활꿀팁;','2021-08-16 13:57:14','수박 껍질 너무 많은데 무게 좀 줄이는 법 있나요?',4),(9,'방법 있나요? 섬유유연제를 더 넣어야 하나..',NULL,'','섬유유연제;빨래;청소꿀팁;','2021-08-16 14:06:51','건조기를 돌리고 나면 섬유유연제 향이 날아가는 거 같아요',1),(10,'이런 꿀팁 누가 전수 좀....',NULL,'','인생꿀팁;삶이란;','2021-08-16 14:09:01','인생 잘 헤쳐나가는 방법',4),(11,'너무 졸려요 각자 꿀팁 공유 좀',NULL,'','졸려요;잠참는법;','2021-08-16 15:40:51','졸린 거 참는 법',6),(12,'밥 맛 유지하는 꿀팁 아시는 분?',NULL,'','밥통;주방꿀팁;생활꿀팁;','2021-08-16 15:53:20','밥통에 오래두면 맛없어요',7),(13,'미치겠네요... 이런 것도 팁 있나요',NULL,'','화장실;휴지없어요;죽겠어요;','2021-08-16 15:57:16','화장실인데 휴지가 없어요... ',4),(14,'해결방법 있나요?',NULL,'','화장실청소;변기청소;청소꿀팁;','2021-08-16 16:02:28','변기 아래 실리콘 부분에 곰팡이가 피었어요',2),(15,'수박을 정말 좋아하는데 수박씨 빼기가 정말 귀찮네요. \n\n씨없는 수박은 당도가 떨어지고요..',NULL,'','수박;생활꿀팁;수박씨골라내기;','2021-08-16 16:04:40','수박 씨 잘 골라내는 법 아시는 분',4),(16,'설거지... 하기 싫어서 뻘 생각 올려봅니다',NULL,'','생활꿀팁;설거지;하기싫어;','2021-08-16 16:08:38','설거지의 기본 뭐라고 생각하시나요?',1),(17,'물 넣긴 싫은데...',NULL,'','라면물조절;라면;','2021-08-16 16:11:09','라면이 너무 짜요... 물 조절 실패.. 방법 있나요',5),(18,'진짜 큰일 났어요.. 곱창 먹고 기름 부었다가... 어쩌죠',NULL,'','싱크대;싱크대막힘;살려주세요;','2021-08-16 16:12:18','싱크대가 막혔어요......',6),(19,'집안일이 정말 하기 싫어요.. 동기부여 할 수 있는 방법 있나요? ',NULL,'','집안일;집안일즐기기;하기싫다;','2021-08-16 16:14:32','집안일을 즐기는 법 아시는 분?',2),(21,'에어컨 틀면 전기료가 많이 나오고 안틀면 죽을 것 같아요 그럼 트는게 맞겠죠? 살려주세요',NULL,'','전기료;에어컨;','2021-08-17 15:50:56','너무 더워요',1),(25,'세탁기 청소 방법 아시는 분 저한테 도움을 주세요ㅠㅠㅠㅠㅠ너무 어렵습니다..',NULL,'','청소;세탁기;','2021-08-19 07:02:08','세탁기 청소',24),(26,'고양이랑 친해지고 싶은데\n\n고양이는 뭘 좋아하나요?\n\n우리집앞 고양이 보고가세요',NULL,'https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/75c5dae0-d10a-4cd0-aa5e-0d43f44f1a1eKakaoTalk_20210819_132921288.jpg;','고양이;애옹;','2021-08-19 07:20:32','고양이랑 친해지기',15),(27,'카페에 가서 커피 찌꺼기를 챙겨왔는데요\n\n활용방법 뭐가 있을까요??',NULL,'','커피;카페;커피 찌꺼기;','2021-08-19 11:22:28','커피 찌꺼기',6),(30,'너무 졸려요 각자 꿀팁 공유 좀',NULL,'','졸려요;잠참는법;','2021-08-19 20:22:25','졸린 거 참는 법',1);
/*!40000 ALTER TABLE `qna` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ranking`
--

DROP TABLE IF EXISTS `ranking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ranking` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `likes` int NOT NULL,
  `posts` int NOT NULL,
  `ranking` int NOT NULL,
  `views` int NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rank_fk_user_id` (`user_id`),
  CONSTRAINT `rank_fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ranking`
--

LOCK TABLES `ranking` WRITE;
/*!40000 ALTER TABLE `ranking` DISABLE KEYS */;
INSERT INTO `ranking` VALUES (1,3,14,1,77,1),(2,0,11,2,84,5),(3,0,11,3,50,2);
/*!40000 ALTER TABLE `ranking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salt`
--

DROP TABLE IF EXISTS `salt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salt` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `salt` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salt`
--

LOCK TABLES `salt` WRITE;
/*!40000 ALTER TABLE `salt` DISABLE KEYS */;
INSERT INTO `salt` VALUES (1,'$2a$10$E8xHx1d5rODu2i0LVhxcf.'),(2,'$2a$10$a5nzsfzj/okkJg/n4SSfGu'),(3,'$2a$10$f7CDK8HrdghO4/Co5ZQpTu'),(5,'$2a$10$NaUx7jYHuOL1I71j7mffq.'),(6,'$2a$10$XdEU3.BN9/8CFg7oZnV75u'),(7,'$2a$10$zJw3Yt1.RkL8GCd0ZkWBaO'),(8,'$2a$10$tYM5vKm3TI9DV/7bTo07We'),(9,'$2a$10$z7T9hXuGYGUQX3uq6x8Weu'),(10,'$2a$10$LpCTW7U6aCV9892Q0fjX.u'),(11,'$2a$10$eh8i.T87ZNPUctNZrAZv/e'),(12,'$2a$10$qvrFW80stpPpjI0lcI10Y.'),(13,'$2a$10$ypkic.gCZZm4FGmbz2kFge'),(14,'$2a$10$1bJk56XR2VtHvrTxWezeQu'),(15,'$2a$10$DkDjWRspOXUnV9gjP4kff.'),(16,'$2a$10$ymwG0/src/IQ9gQk8zJFwO'),(17,'$2a$10$FhozStBKyl2yoAtS9wA5b.'),(18,'$2a$10$8JE.vddD64TzcR8Ov7QNwO'),(19,'$2a$10$FphVIoKWQTzlnvQaGlAbs.'),(20,'$2a$10$c0f2UEYUyWv9SHDSo5yP1O'),(21,'$2a$10$1ba9MH2OUxKHWw5hsDxXXO'),(22,'$2a$10$jzGMPXT9lHq8NlLJa3JS.O'),(23,'$2a$10$uzISzEQtrq7wR50dEw.7ze'),(24,'$2a$10$PBiXGyVkbGoZWcuRJP3kvu'),(25,'$2a$10$cki/8zqfOYctVgOV2gIOVe'),(26,'$2a$10$SVWMP5Q2aWdkpld8Xs4aVe'),(27,'$2a$10$K1aKGDxf.0LLjc28gLDxIO'),(28,'$2a$10$fLVsy3ejdooPHrcNm9RO2u'),(29,'$2a$10$PjIl6H9htaY546TevH5Mce'),(31,'$2a$10$c03JxkUVwQa1oxbU.V8dRu'),(32,'$2a$10$UlYoEx5V6kyYK51IceZ6de'),(33,'$2a$10$0QA.RAESqvzVXGIGWZuzl.'),(34,'$2a$10$a6h6kH/BF7OQ0vL/igURHu'),(35,'$2a$10$P7NTjUrwphZS6Q2kq0q2Du'),(36,'$2a$10$JLDsMW.54jVuq5BbkplvQO'),(37,'$2a$10$JQrwt0Ek8P2A79OP/kFQR.'),(38,'$2a$10$G2Rp.sj7FE5FmYn4Prdkqu'),(39,'$2a$10$1z3W2wgqKhbavSOY8CdV8O'),(40,'$2a$10$wXu8AqbWmRClx02QXgqPqu');
/*!40000 ALTER TABLE `salt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shorts`
--

DROP TABLE IF EXISTS `shorts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shorts` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contents` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `edit_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `likes` int NOT NULL DEFAULT '0',
  `tags` text CHARACTER SET utf8,
  `thumbnail` text CHARACTER SET utf8,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `video` text CHARACTER SET utf8,
  `views` bigint NOT NULL DEFAULT '0',
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `shorts_fk_user_id` (`user_id`),
  CONSTRAINT `shorts_fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shorts`
--

LOCK TABLES `shorts` WRITE;
/*!40000 ALTER TABLE `shorts` DISABLE KEYS */;
INSERT INTO `shorts` VALUES (1,'걸레 짜느라 손목 아프셨던 분들 보세요!','2021-08-20 01:27:18',1,'걸레짜기;걸레짜는법;청소꿀템;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/af13c11b-5ee3-4094-82bb-5a4a764ac1b9%EA%B1%B8%EB%A0%88thumb.png','2021-08-14 17:53:14','걸레를 쉽게 짜는 방법','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/6632c454-d9dd-4d02-a5a9-96fc3921ec3f%EA%B1%B8%EB%A0%88%20%EB%AC%BC%EC%A7%9C%EB%8A%94%20%EB%8F%84%EA%B5%AC%20mop%20wringer.mp4',68,13),(5,'냄비가 탔을 때 보세요','2021-08-20 01:20:45',3,'탄냄비;케찹;생활꿀팁;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/47de81b2-54d1-454d-aabc-af4c318d8b0fketchupsum.png','2021-08-16 12:47:44','탄 냄비 닦는 법','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/33c1c8f2-0936-460d-9611-0bde934c92ccketchup.mp4',79,31),(6,'작은 구석구석의 먼지를 제거하고 싶다면 보세요','2021-08-19 13:41:20',0,'깔대기;구석청소;청소꿀팁;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/7dae5f7a-abcc-455a-af7d-75128a4c91a8vacuumsum.png','2021-08-16 18:48:06','깔대기 활용법','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/fc581694-b2f0-4063-b906-2997d4355fffvacuum.mp4',30,2),(7,'도마의 냄새를 없애보세요','2021-08-19 00:48:20',0,'치약;도마;생활꿀팁;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/047886d7-d81b-48d0-aa6b-87361c81fe6etoothpaste.png','2021-08-17 01:24:11','치약 활용법','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/b40a62d7-87e9-43d2-ac3a-812b39e1d5eatoothpastecook.mp4',22,5),(8,'포크면 끝!','2021-08-17 06:51:38',0,'빗;포크;생활꿀팁;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/343743da-d932-4cfc-9500-f49dc1503207combsum.png','2021-08-17 01:41:12','빗에 엉킨 머리카락 빼기','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/43dc8723-1476-417c-9b2d-a535c8ea02b05%20%EB%B9%97-%ED%8F%AC%ED%81%AC.mp4',3,2),(9,'그냥 버리지 말고 집에서 재활용!','2021-08-19 07:01:04',0,'페트병;음식물쓰레기;생활꿀팁;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/4a88e0d8-8cf2-41b7-9925-c34fa6891208petsum.png','2021-08-17 01:43:43','페트병 활용법','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/4da14e87-ae3c-41bd-8f7a-67848697e8b76%20%EC%9D%8C%EC%93%B0.mp4',5,6),(10,'급할 땐 이 방법도 좋아요 :)','2021-08-20 01:16:28',1,'쓰레받이;은박접시;청소꿀팁;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/6d0eb931-db67-4ce7-bc52-cc3b5f32404eplatesum.png','2021-08-17 01:47:34','쓰레받이가 없을 때','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/866c31d8-2d2c-417a-b634-81239b878ba39%20%EC%9D%80%EB%B0%95%20%EC%B2%AD%EC%86%8C%EB%B0%9B%EC%B9%A8%EB%8C%80.mp4',11,5),(11,'머리카락이 달라붙지 않아 좋아요','2021-08-19 07:27:36',1,'빗자루;비닐봉지;청소꿀팁;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/40c0f018-cb3f-4075-932f-0fc6e0b86434cleansum.png','2021-08-17 01:52:15','빗자루 깨끗하게 쓰기','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/9b720ac7-f509-4aad-a53c-36a6a773edd612%20%EB%A8%B8%EB%A6%AC%EC%B9%B4%EB%9D%BD.mp4',14,1),(12,'의외의 활용이죠?','2021-08-19 02:41:57',1,'반지;립스틱;생활꿀팁;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/299eda38-d1cb-4475-8c96-bb115ffc790dringsum.png','2021-08-17 01:59:16','반지를 반짝이게 하는 꿀팁','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/eff72d39-e918-4296-a0a5-36f88078ba0112%20%EB%A8%B8%EB%A6%AC%EC%B9%B4%EB%9D%BD.mp4',5,2),(15,'야옹이에게 양보하세요','2021-08-20 01:14:47',1,'양말;고양이방석;생활꿀팁;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/d0dd4982-8ba5-4c78-af1a-11cd7fd7ca65socksum.png','2021-08-17 02:08:57','구멍난 양말 활용법','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/e0f47bc1-ef35-4a35-b7c8-e2f4ea51e41c%EC%96%91%EB%A7%90%ED%99%9C%EC%9A%A9.mp4',8,5),(16,'실용적인지는 잘 모르겠지만요 :0','2021-08-20 01:20:24',0,'병;자석;청소꿀팁;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/a1adaa1a-f183-44e0-96f8-2c29e5605386bottlesum.png','2021-08-17 02:16:50','병 속을 닦는 복잡한 방법','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/fbfd309f-ab3a-4b04-8d28-be3b3f2498b3%EB%B3%91%EB%8B%A6%EA%B8%B0.mp4',3,31),(17,'일상에 있는 것으로 깨끗하게!','2021-08-18 17:20:23',1,'거울닦기;유리세정제;청소꿀팁;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/eba0d6fe-c81e-4eae-bb7a-d7bb3703d1f9glasssum.png','2021-08-17 02:23:11','유리세정제없이 거울 닦기','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/b0225f7c-44bd-435a-9b66-eb52f977b91a%EC%B0%BD%EB%AC%B8%EA%B9%A8%EB%81%97.mp4',9,2),(18,'아이가 낙서한 곳에 사용해보세요','2021-08-20 01:54:31',2,'유성매직;네일리무버;생활꿀팁;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/5d24d6e1-39ec-49ae-b80a-613e913c5806markersum.png','2021-08-17 02:26:46','유성매직 지우는 꿀템','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/cd8d8c3e-19de-4b25-a27a-2fee0ba95f19%EC%B0%BD%EB%AC%B8%EA%B9%A8%EB%81%97.mp4',32,7),(19,'더러워진 이어폰 닦아보세요','2021-08-19 17:54:03',3,'이어폰;치약;생활꿀팁;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/7c13e1d5-2ed0-482f-86ed-59a413ae048dearsum.png','2021-08-17 02:38:11','더러워진 이어폰을 새 거처럼!','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/6d245803-e465-425f-a9da-7d403f5ee288%EC%9D%B4%EC%96%B4%ED%8F%B0.mp4',21,5),(20,'완전 쉬운데요?','2021-08-20 01:20:32',2,'냉장고;드라이기;생활꿀팁;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/95891349-1b06-4175-9f9e-b6a0a110a84ddryersum.png','2021-08-17 02:43:16','헐거워진 냉장고 고무 되돌리기','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/7d69ef86-cf14-4b78-b9da-56d3c515eb8c%EB%83%89%EC%9E%A5%EA%B3%A0%EA%B3%A0%EB%AC%B4.mp4',51,31),(22,'남는 페트병으로 사용해서 주기적으로 갈면 되니까 더 위생적일 거 같기도 하네요','2021-08-20 01:54:42',2,'페트병;싱크대;재활용;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/75e0a459-aa6e-43c3-a57e-8dde5335bb6epetpetsum.png','2021-08-17 04:03:07','페트병 재활용 팁!','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/8313c2e9-7be9-4a08-975a-3e63bcae5971%ED%8E%98%ED%8A%B8%EB%B3%91.mp4',203,5),(26,'큐티 고앵','2021-08-20 01:54:30',7,'고양이;앵;큐티;방법;친;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/cacff0f8-0e37-44ad-bbed-e437c30dbb0cKakaoTalk_20210625_122810686.jpg','2021-08-18 15:42:04','고양이랑 친해지는 방법 알고 계신가요??','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/43f995d9-1ebe-431c-9dba-2ba32c12e11b%EB%AC%B8%EC%84%9C1%20-%20Word%202021-07-07%2017-03-08.mp4',53,1),(29,'걸레 짜느라 손목 아프셨던 분들 ~~ 보세요','2021-08-20 01:27:43',1,'걸레;손목;꿀팁;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/2499c043-f6f4-40ea-93d1-bd19b4c1da86squeeze.jpg','2021-08-19 07:31:12','걸레 쉽게 짜는 꿀팁!','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/86bd066b-a9c7-4dc8-80d6-f47ebf2ff483%EA%B1%B8%EB%A0%88%20%EB%AC%BC%EC%A7%9C%EB%8A%94%20%EB%8F%84%EA%B5%AC%20mop%20wringer.mp4',26,28);
/*!40000 ALTER TABLE `shorts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tips`
--

DROP TABLE IF EXISTS `tips`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tips` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contents` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `edit_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `image` text CHARACTER SET utf8,
  `likes` int NOT NULL DEFAULT '0',
  `tags` text CHARACTER SET utf8,
  `thumbnail` text CHARACTER SET utf8,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tips_fk_user_id` (`user_id`),
  CONSTRAINT `tips_fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tips`
--

LOCK TABLES `tips` WRITE;
/*!40000 ALTER TABLE `tips` DISABLE KEYS */;
INSERT INTO `tips` VALUES (1,'꼬질꼬질한 먼지 꼬인 방충망때문에 고민하고 계신가요?\n\n먼지꼬인 방충망을 청소하기 위해 필요한 것은 단 두가지!\n\n퐁퐁과 자동차먼지털이!! 자동차 먼지털이가 없다면 수면양말도 좋아요\n\n우선 세제를 물에 희석해서 비눗물을 만들어줍니다.\n\n그리고 자동자 먼지떨이에 비눗물을 묻혀주세요.\n\n세번 정도 방충망을 쓱쓱 닦아주세요\n\n그리고 헹군 먼지떨이로 한번 더 쓱쓱 이렇게 하면 끝이랍니다!\n\n너무나 간단하죠? 단, 방충망 청소 시 추락하지 않도록 조심해주세요','2021-08-17 06:56:17','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/f3e90a97-28c3-4995-84ab-c4a9ea3da66dscreen.jpg;',1,'방충망;방충망청소;퐁퐁활용법;수면양말;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/e54cedfc-953e-465c-a846-bcb9a508fe40screen.jpg','2021-08-14 17:20:22','방충망 청소 방법',1),(2,'더러워진 변기때문에 고민하고 계신가요? 사실 변기는 화장실 전용 세제를 이용해 솔로 잘 닦아준다면 청소가 쉬운 편인데요\n\n과연 화장실 전용 세제가 없을 땐 어떻게 하면 좋을까요?\n\n여기서 바로 필요한 것은 샴푸?! 오잉 웬 샴푸냐구요?\n\n더러워진 변기에 못쓰거나 오래된 샴푸를 짜보세요.\n\n그리고 청소솔로 쓱쓱!! 반짝반짝 변기로 변신해 있을 거예요\n\n샴푸의 재탄생에 놀라셨나요?? \n','2021-08-19 06:11:49','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/27b2d868-ffc7-4595-a7a6-4affeb12aeeftoilet.jpg;',1,'샴푸활용법;변기청소법;화장실청소;화장실;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/c793c61b-0593-4c8e-8a9c-3ce76960e6d8toilet.jpg','2021-08-14 17:28:19','안 쓰는 샴푸 활용법',2),(3,'우리가 매일 사용하는 곳은 화장실! 이 중 양치, 세수, 손 닦기를 반복하는 이 곳은 수전입니다\n\n수전을 반복해서 사용하다보면 어느덧 물때가 끼곤 하는데요. 물때를 쉽게 지울 수 있는 잇템!\n\n그것은 바로바로 천연 세제! 구연산입니다 :)\n\n의외의 등장에 놀라셨나요? 사실 구연산은 그 어느 세제보다도 물때 제거에 탁월하답니다.\n\n구연산을 일정양 덜어 수전에 쓱쓱 묻힌 후 수세미로 수전을 닦아보세요\n\n그리고 물을 뿌리면 반짝반짝 새 것 같은 수전을 만날 ',NULL,'https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/717102ff-a688-48bf-94dd-c666a1eaeb89bathtap.jpg;',0,'수전;구연산;화장실청소;화장실;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/ebc991c6-84b1-4ee6-b361-1033355984fbbathtap.jpg','2021-08-14 17:31:37','수전 물때 청소법',4),(4,'자식이 속을 썩이나요? 속을 썩인 자식이 문을 쾅 잠그고 열지 않고 있나요?\n\n혹은 아이가 실수로 문을 닫았는데 잠겨 열지 못하고 계신가요?\n\n그 때 우리가 필요한 것은 단 하나! 뾰족하고 긴 물건.\n\n머리실핀도 좋고 면봉도 좋습니다.\n\n문 옆 작은 구멍에 면봉으로 정면을 쭉 밀어넣어보세요\n\n무언가 닿는 느낌이 든 다면 그 길로 쭉 밀어보세요\n\n그럼 마법과 같이 문이 열릴 거예요','2021-08-20 01:21:15','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/23bbeef9-22f4-47cd-b3db-2c86ffec1035opendoor.jpg;',3,'면봉활용법;방문잠겼을때;방문열기;생활꿀팁;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/45a0edb0-2994-4f1b-8318-606bd8e7728copendoor.jpg','2021-08-14 17:34:37','방문 잠겼을 때',31),(5,'양치하고 혹은 세수를 하고 아~ 개운하다 하고 누워서 폰을 켜신 분 있나요?\r \r 지금 당장 일어나세요! 화장실의 청소의 마무리는 바로 습기 제거!\r \r 화장실은 습기에 취약하기 때문에 청소를 해준 날 바로 어느정도 습기를 해주어야\r \r 다음 청소까지 시간을 벌 수 있다구요! 전용 걸레가 있다면 더 좋겠지만 그냥 일반 걸레로도 충분합니다\r \r 매일 단 2분으로 화장실 청소 주기를 늘려보세요 ','2021-08-20 01:28:12','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/382da9a4-44ec-48e6-a819-6192a0398716afterclean.jpg;',0,'화장실청소;화장실;청소꿀팁;생활꿀팁;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/720e1917-241b-4f46-87c6-d79f4d6c988bafterclean.jpg','2021-08-14 21:00:53','화장실 청소 주기 늘리는 2분의 습관',5),(6,'치약 중간중간 짜기 너무 귀찮고.... 막바지엔 끝까지 쓰기 너무 어렵나요?\n\n단 500원!만 있다면 치약을 손 쉽게 끝까지 깔끔하게 쓸 수 있답니다\n\n치약짜개라고 들어보셨나요? 이 단순한 플라스틱이 그 스트레스를 줄여준답니다\n\n다이소에서 2개에 1000원에도 팔아요! 다들 가서 구매해보세요','2021-08-20 01:21:15','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/d30504f9-1e79-44aa-ba2d-4879850065f2toothpaste.jpg;',5,'치약짜기;치약짜개;500원;생활꿀템;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/803804d4-0288-4433-838e-e53ed7ee7a7atoothpaste.jpg','2021-08-14 22:03:51','치약을 끝까지 쓰는 500원의 기적',31),(7,'코로나가 계속되는 요즘 손을 씻는 일상은 도저히 우리와 뗄레야 뗄 수 없는 관계가 되었는데요\n\n다들 세정 비누 묻혀서 대충 한방향으로 쓱쓱 닦으시는 거 아니죠?\n\n그렇게 하면 손에 세균이 여전히 드글드글 남아있답니다\n\n양치의 3 3 3 규칙처럼 손씻기 6단계 들어보셨나요?\n\n아니 못 들어보셨다구요!? 지금부터 이 그림을 보며 6단계 꼭꼭 지켜봐요\n\n손씻기 6단계로 청결한 손 유지해보아요 :slightly_smiling_face:','2021-08-20 01:13:11','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/ac9149f7-49c4-4aba-a247-5d4cff6ed813washhands.jpg;',1,'손씻기;생활꿀팁;손씻기6단계;코로나;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/4bf25e10-4ec2-401e-ba35-547e5b1c8c06washhands.jpg','2021-08-14 22:06:53','손 씻기 6단계',1),(8,'요즘은 고층 건물은 많아졌지만 아파트 유리창 청소는 많아야 1년에 한번이죠\n\n얼마전 제가 엄청난 꿀템을 발견했는데요 바로 이 자석 유리창 청소기!\n\n한쪽을 밖에 붙이고 안에서도 착 붙이면 쓱싹 쓱싹 깨끗하죠?\n\n혹시 금전적 여유가 있는 분이라면 샤오미 유리창 청소로봇을 구매해보세요\n\n혼자서 얼마나 깨끗하게 청소를 해주는지 완전 꿀템!',NULL,'https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/5e24fc76-8555-4167-9a12-b8e567865b6ewindow.jpg;',0,'외부유리창청소;유리창청소;유리창;청소꿀팁;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/0abf00b6-2d3d-4c8b-a906-6f7b70752fdcwindow.jpg','2021-08-14 22:10:40','외부유리창 청소 꿀템',7),(9,'와인잔을 깨끗하게 아무리 닦아보려해도 물자국때문에 계속 스트레스 받은 분 계신가요?\n\n여러분 제가 청소에 대해 꼭 드리고 싶은 말이 있는데요\n\n그건 바로 청소는 템빨이라는 것!!!!!!!\n\n와인잔 닦기의 핵심은 바로 린넨! 이 린넨 소재만이 와인잔을 투명하게 할 수 있는데요\n\n단돈 7000원의 행복 아닐까요? 이 크리너로 와인잔을 닦아보세요\n\n그동안의 스트레스가 싹 사라질 거예요','2021-08-19 04:19:17','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/81a0ced0-0491-42d1-8bdd-e317aa29eca6wineglass.jpg;',1,'와인잔닦기;린넨;와인잔;생활꿀템;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/636e2bf9-f14b-43a0-abd1-2855bff29bf7wineglass.jpg','2021-08-14 22:12:15','와인잔을 투명하게!',4),(10,'여름철에 종량제 봉투 차기까지 오래 걸려서\n\n초파리나 날파리 꼬여보이신 분 많으시죠?\n\n저만의 꿀팁을 공유해드릴게요\n\n일단 배달음식 같은 거에서 나오는 깨끗한 비닐을 버리지 말고 보관하세요\n\n그리고 작은 쓰레기통에 그 봉지를 끼우고 채워지면 꽉 묶어서 20L 정도 종량제에 넣으세요\n\n여기서 포인트는 꽉이에요\n\n봉지별로 나눠서 채우다보면 벌레가 들어갈 틈이 없어요\n\n그래도 여름철은 20L나 10L 적은 용량을 사용하는 게 가장 좋을 거 같아요','2021-08-20 01:21:15','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/4d3f76b0-3fca-47c5-a068-663317908178trash.jpg;',1,'쓰레기;청소꿀팁;쓰레기꿀팁;여름철꿀팁;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/75cac4f7-2b99-450f-89f8-6a1a763d79e2trash.jpg','2021-08-15 14:22:17','여름철 종량제 봉투에 벌레 꼬이지 않는 꿀팁',31),(13,'온 종일 만지는 휴대폰 찝찝한 분들 많으시죠?\n\n얼마전 지인한테서 추천 받아서 샀는데 이거 정말 꿀템이네요\n\n다이소에서 1000원 주고 샀는데 생각보다 양도 많고\n\n외출 갔다오면 이제 늘 이거 소독합니다\n\n강추해요!','2021-08-19 07:24:31','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/12020709-776a-4a40-b155-12dc37ca3405swap.jfif;',11,'1000원;휴대폰소독;코로나;다이소꿀템;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/af6cc6ed-2c43-4f98-acfa-7b09408ee1cdswap.jfif','2021-08-15 20:13:16','단돈 1000원으로 폰 소독하기',4),(14,'가끔 취해서 자다보면 옷에 껌이 붙을 때가 있었는데\n\n그 때 옷을 그냥 버리지 말고\n\n비닐에 얼음을 넣어 껌 묻은 부분에 비벼보세요\n\n그럼 찐득한 껌이 굳어서 뗄 수 있게 굳을 거예요','2021-08-19 05:33:16','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/875dfe97-f3ea-4816-8b44-eda279d49d4dgum.jfif;',3,'껌붙었을때;생활꿀팁;껌;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/a44762bc-cb3c-4124-a2b8-d7c899906ff6gum.jfif','2021-08-15 20:24:28','옷에 껌이 붙었을 때 꿀팁',5),(15,'가끔씩 딸꾹질 안 멈추면 진짜 스트레스 받으시죠?\n\n저는 두가지 방법을 사용하는데요\n\n가장 많이 사용한 방법은 물을 입에 머금고\n\n코를 막고 위를 본 상태로 물을 삼켜보세요\n\n이 방법이 최고로 많이 사용한 방법이구요\n\n그리고 두번째는 요새 쓰는데요\n\n가끔씩 첫번째 방법이 안들을 때\n\n비닐봉지를 가져와서 입과 코를 전체 넣고\n\n계속 들여마셨다가 내쉬었다가를 반복하면 멈춰요 ',NULL,'https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/156290a9-7f10-4077-b828-af1ede6d85ebhiccup-2794251_1920.jpg;',0,'딸꾹질;생활꿀팁;딸꾹질멈추는법;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/f158c61a-4cfb-4b47-8e29-42770ea06415hiccup-2794251_1920.jpg','2021-08-15 20:33:05','딸꾹질 멈추지 않을 때',1),(16,'요즘 파값 장난 아니죠?\n\n화분과 흙만 있다면 파 값을 더이상 지불하지 않아도 됩니다\n\n파를 그대로 꽂아두세요\n\n그리고 가끔씩 물 주시고 드실 때는 윗부분만 잘라서 드세요\n\n파는 정말 쉽게 잘 자라요','2021-08-18 12:11:26','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/1fe88e0b-a228-453e-8fb7-53e098e6376bgreenonion.jfif;',1,'파키우기;파테크;생활꿀팁;주방꿀팁;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/0b0efa08-ab06-4d64-9071-1254fd876ac6greenonion.jfif','2021-08-15 20:41:02','세상 쉬운 파 키우기',6),(18,'제가 모기 진짜 싫어하는데요\n\n\n이거 하나로 정말 모기 잡는 거에 스트레스가 거의 사라졌어요 \n\n\n배송비 포함해서 한 14000원정도?\n \n \n 이게 회전 모기채라 벽이나 천장에 붙은 거 잡기도 정말 쉬워요\n \n \n 정말 강추합니다? ','2021-08-19 07:24:24','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/df4d9d93-19c2-45bf-b84e-43e512b1f162mos.jfif;',8,'전자모기채;모기잡기;생활꿀팁;여름철꿀팁;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/cdd008ab-6b13-4654-bd52-f0dcd200c58cmos.jfif','2021-08-15 20:56:19','이거 하나면 모기 걱정 끝',5),(19,'마스크 집에 오면 맨날 걸어둘 곳도 없고....\n \n 잠깐 나갔다 들어왔을 때 애매하잖아요\n \n 500원짜리 이 자석 고리를 현관문에 붙여보세요\n \n 들어오면서 딱 걸고 나갈 때 딱 쓰고\n \n 정말 용이해요?','2021-08-18 02:29:13','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/ba91a552-071c-40ba-aa22-ec3e08cb25a5mask.jpg;',1,'코로나;마스크;생활꿀팁;500원;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/94d82515-6552-49ac-a0c7-bf845ac833f8mask.jpg','2021-08-15 21:05:20','500원으로 마스크 보관 해결!',2),(20,'콜라 큰 거 사놓으면 꼭 다 못 마시고 냉장고에 보관하다가 김 빠진 경험 다들 해보셨죠?\n\n김 빠진 콜라는 산성물질이기에 찌든 때 제거에 효과적이랍니다\n\n김 빠진 콜라를 변기에 붓고 물을 내려보세요\n\n찌든 때가 씻겨나갈 거예요','2021-08-19 01:21:35','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/0ea6825b-26b6-443a-ab2b-d21462c73bbftoiletcola.jpg;',4,'콜라활용법;청소꿀팁;생활꿀팁;화장실청소;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/8c5b6d36-4293-4090-8fc9-ab514fb60f9atoiletcola.jpg','2021-08-15 21:34:09','김 빠진 콜라 버리지 말고 여기에 양보!',5),(33,'더러워진 변기때문에 고민하고 계신가요? 사실 변기는 화장실 전용 세제를 이용해 솔로 잘 닦아준다면 청소가 쉬운 편인데요\n \n 과연 화장실 전용 세제가 없을 땐 어떻게 하면 좋을까요?\n \n 여기서 바로 필요한 것은 샴푸?! 오잉 웬 샴푸냐구요?\n \n 더러워진 변기에 못쓰거나 오래된 샴푸를 짜보세요.\n \n 그리고 청소솔로 쓱쓱!! 반짝반짝 변기로 변신해 있을 거예요\n \n 샴푸의 재탄생에 놀라셨나요??','2021-08-19 05:20:17','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/5070687c-378d-4797-bee7-e6aa9a5da2e0%EB%8B%A4%EC%9A%B4%EB%A1%9C%EB%93%9C.jfif;https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/d181dcbd-d7b3-4dac-a55c-572f84dc0bbbscreen.jpg;',3,'샴푸;변기;청소;화장실;청소솔;세제;전용세제;화장실변기청소;고민;도와주세요제발;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/996c6993-c69e-4887-83ab-892c859a3182%EB%8B%A4%EC%9A%B4%EB%A1%9C%EB%93%9C.jfif','2021-08-17 16:28:41','안 쓰는 샴푸 활용법',1),(34,'우리가 매일 사용하는 곳은 화장실! 이 중 양치, 세수, 손 닦기를 반복하는 이 곳은 수전입니다\n \n 수전을 반복해서 사용하다보면 어느덧 물때가 끼곤 하는데요. 물때를 쉽게 지울 수 있는 잇템!\n \n 그것은 바로바로 천연 세제! 구연산입니다 :)\n \n 의외의 등장에 놀라셨나요? 사실 구연산은 그 어느 세제보다도 물때 제거에 탁월하답니다.\n \n 구연산을 일정양 덜어 수전에 쓱쓱 묻힌 후 수세미로 수전을 닦아보세요\n \n 그리고 물을 뿌리면 반짝반짝 새 것 같은 수전을 만날 수 있습니다!','2021-08-19 07:23:25','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/e657d86c-29f2-40a9-9d67-02a725672db0afterclean.jpg;https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/7380cc81-2443-465f-9d48-98452bfa2ed5bathtap.jpg;https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/68479a26-0e38-47e3-be90-fc4d9e34e3bcimg.png;',3,'수전;물때;구연산;사용;반복;곳;세제;등장;후;청소;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/68479a26-0e38-47e3-be90-fc4d9e34e3bcimg.png','2021-08-18 03:37:45','수전 물때 청소법',6),(35,'까망스 ㄱㅇㅇ..','2021-08-19 13:46:01','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/7786e7e4-b98b-48db-874f-6350a4173a2aKakaoTalk_20210625_122810686.jpg;',5,'꿀팁;고양이;귀여워;애옹;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/6b8532da-29be-4d53-8f27-65a2ae9d4670KakaoTalk_20210625_122810686.jpg','2021-08-18 15:02:37','꿀팁입니다',21),(52,'무더운 여름철, 에어컨을 틀었는데도 아직 덥다면 이렇게 틀어보세요!\n\n냉방 모드로 낮추고 제습 모드로 유지하자!\n\n원하는 실내 온도까지 냉방 운전으로 낮춘 다음, 제습 기능을 사용하는 게 빨리 시원해지며 전기도 아낄 수 있어요.\n\n목표 온도 까지 도달시키는 데는 냉방 모드가 제습 모드보다 빠르기 때문입니다.\n\n또한, 실외기 지붕에 그늘을 만들어주면 실외기 온도를 낮춰 에너지 효율을 높일 수 있어요!','2021-08-19 17:53:37','',2,'에어컨;제습;','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/6c21cba2-4058-431b-a03e-93179af70e32%EC%97%90%EC%96%B4%EC%BB%A8%EB%A9%94%EC%9D%B8.jpg','2021-08-19 07:06:59','에어컨 시원하게 틀자!',16);
/*!40000 ALTER TABLE `tips` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `points` int NOT NULL DEFAULT '0',
  `refreshtoken` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt_id` bigint DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `UK_a3imlf41l37utmxiquukk8ajc` (`user_id`),
  KEY `FK4vy61b2ek7vg2rjvi0u6bum73` (`salt_id`),
  CONSTRAINT `FK4vy61b2ek7vg2rjvi0u6bum73` FOREIGN KEY (`salt_id`) REFERENCES `salt` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'ggomzirak@google.com','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/7947c062-7b12-4f5c-8259-cdd84e90487bgirl4.png','1','꼼지락 ',13800,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLroZzqt7jsnbjthqDtgbAiLCJleHAiOjE2MzAwMDAyODR9.pYOOlaKDn1tSOojA7UvyGszAWQsPySHGQhuQrnMNlbI','ggomzirak1','$2a$10$P7NTjUrwphZS6Q2kq0q2DuEwpoeGC0XHf/eBmppsBW0wurP0TiMAC',35,NULL),(2,'jejin009@google.com','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/4059193d-f5e5-4555-8398-3c849a24ce64girl3.png','1','정은진',2550,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLroZzqt7jsnbjthqDtgbAiLCJleHAiOjE2Mjk3MDU3NjJ9.NiCzmFBYiDgatOuNlCLe7wauZ1fgNJAJj5KAHa07RXc','jejin009','$2a$10$f7CDK8HrdghO4/Co5ZQpTuL1QoQqAGuzuY3WEuLc0ZgumL9SM6iQG',3,NULL),(4,'ssafy@naver.com','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/e6ae5885-9a80-44e3-b875-66b71748b062boy1.png','1','김싸피',900,NULL,'ssafy00','$2a$10$NaUx7jYHuOL1I71j7mffq.lELZIV1iF5TlaSCWG93BRdQNxtOprJW',5,NULL),(5,'ssafy11@naver.com','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/292ca788-0b40-477f-9397-9e7c23fdbaf1girl2.png','1','정싸피',2200,NULL,'ssafy11','$2a$10$XdEU3.BN9/8CFg7oZnV75uUhQxTnO9DwRsZh9V3wFEgLYPBCNOXri',6,NULL),(6,'eoeo@eo.com','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/83cabeb9-9acd-43d4-a452-c319a7c9cca6girl2.png','1','어지예',2950,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLroZzqt7jsnbjthqDtgbAiLCJleHAiOjE2MzAwMjYyNTJ9.UiTni_b3OELBHbW_o2TVQVW2bfGzW2F_6ONAOQK3KFk','eojiye','$2a$10$zJw3Yt1.RkL8GCd0ZkWBaOwfrXKSehdxoAW3LaOLdqI99IinQEysS',7,NULL),(7,'eoeo@eo.com','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/defaultImg.PNG','1','어싸피',650,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLroZzqt7jsnbjthqDtgbAiLCJleHAiOjE2MzAwMjQwNDV9._H5BEyYodBiWSEnm7t_GW15vgp3kAKrvtzFjvq_Cklc','eossafy','$2a$10$tYM5vKm3TI9DV/7bTo07WeBhAKX0euH7/MMq.8ijuaxge673o/noq',8,NULL),(11,'111111@eo.com','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/1b4a97c7-d13a-4fd3-b8fc-67287b3fcb5cKakaoTalk_20210615_002734877.png','1','장싸피',0,NULL,'junsu','$2a$10$qvrFW80stpPpjI0lcI10Y.VYo1UmUshfx3waw4D/nrxqNKf605Kum',12,NULL),(12,'yejin@gmail.com','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/125a0b8e-5452-41c1-a665-a2db85484461KakaoTalk_20210615_002734877.png','1','예진',0,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLroZzqt7jsnbjthqDtgbAiLCJleHAiOjE2Mjk5OTgwMjJ9.L2FmD9EpNxnhPZb3KLlWKqQp4HwV7gELCCMK87X5cug','yejin','$2a$10$ypkic.gCZZm4FGmbz2kFgeQIOcMRrytqrWSNV9.VxCf3X6C/Sq02q',13,NULL),(13,'ssafyssafy@naver.com','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/7b510653-7b43-4bd6-a259-d9a273e95575girl2.png','1','싸피싸피',0,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLroZzqt7jsnbjthqDtgbAiLCJleHAiOjE2Mjk5Njc4MzR9.HIy8lk4z8c05EHEmFzBh6k_-HYPdzR4h8MimRqP6btY','ssafyssafy','$2a$10$1bJk56XR2VtHvrTxWezeQuTWcGuI/ld62D.bQhncj/qjDfsWbbUh2',14,NULL),(14,'1234@naver.com','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/0a1d522f-306a-42aa-9fef-72c8e4497240girl1.png','1','양싸피',0,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLroZzqt7jsnbjthqDtgbAiLCJleHAiOjE2Mjk5NTkyODB9.GlnYHqRU_cjXS1ONRTgvCh-7EFm5X0Y1FDbGuu0fotA','1234','$2a$10$DkDjWRspOXUnV9gjP4kff.2fA06ZrYMtcex6K7Nw1l7RiiDidR7rW',15,NULL),(15,'kyoung@naver.com','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/defaultImg.PNG','1','갱이',150,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLroZzqt7jsnbjthqDtgbAiLCJleHAiOjE2Mjk5OTAwNjJ9.mLEptL5uCP6koGEpMIcexJTvxbmYL5i2UtXEYewPKoY','ykyung','$2a$10$ymwG0/src/IQ9gQk8zJFwOOaCq6Lbwmmf4Tgft8d0Es2nU/v5eow6',16,NULL),(16,'undefined','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/defaultImg.PNG','1','꼼이',0,NULL,'undefined','$2a$10$FhozStBKyl2yoAtS9wA5b.q.mkdAtS03TM2eHmmJiHi7tELXYOwW.',17,NULL),(17,'undefined1@naver.com','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/27d6b8dd-87e1-4e1b-b7af-696128d29ea3girl2.png','1','까망이',0,NULL,'user1','$2a$10$8JE.vddD64TzcR8Ov7QNwO80LsnIP3LxUPXmLV8LSCceMvjA5q9/m',18,NULL),(18,'undefined2@naver.com','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/defaultImg.PNG','1','가을이',0,NULL,'user2','$2a$10$FphVIoKWQTzlnvQaGlAbs.5Belu3J35FdIdc23jy9W.fwAXJOsfai',19,NULL),(19,'undefined3@naver.com','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/0b8a5849-8c99-4d83-b3c6-611dd1b236ccgirl4.png','1','여름이',0,NULL,'user3','$2a$10$c0f2UEYUyWv9SHDSo5yP1O9pKaMfJ8ZB/XKhmnvAEEhW56Bi6Ivcu',20,NULL),(20,'ssafy@naver.com','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/4f87a34f-9d8b-432b-8d56-d40a040ca29cgirl4.png','1','ssafyssafyssafy',0,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLroZzqt7jsnbjthqDtgbAiLCJleHAiOjE2Mjk3ODQ4MDZ9.IaSDhyQBuiXLmNlQ8jDPWzvmzL7H82z5IcNrKGUwh5U','ssafyssafyssafy','$2a$10$1ba9MH2OUxKHWw5hsDxXXOWMdUbc.C43dVk77vF.J.GkN8igWIqKy',21,NULL),(21,'eunjin@gmail.com','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/4e8a31b8-d49b-44f1-b1d4-cc4bc9ef2393girl3.png','1','은진',100,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLroZzqt7jsnbjthqDtgbAiLCJleHAiOjE2MzAwMjgyNzh9.JHD_mEnlsmuLGbb1Rq8L8huTN7hWe_cgd3EyirOCClU','eunjin','$2a$10$jzGMPXT9lHq8NlLJa3JS.OJpzaDmSfol8UQxfZZAuEFf0Mq9Nl5HS',22,NULL),(22,'eunjin@gmail.com','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/defaultImg.PNG','1','은진은진',0,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLroZzqt7jsnbjthqDtgbAiLCJleHAiOjE2Mjk5OTA4NTl9.pdpXRAvD1lRkblert-rmA19ugdNyj4Z0ORm3ZQYIdHM','eunjin11','$2a$10$uzISzEQtrq7wR50dEw.7zeuSFWQVUX.HNDV7uuYNLxqTDUNeRbTee',23,NULL),(23,'ssafy@naver.com','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/0a248273-6067-4a38-b114-903fffc0e321girl1.png','1','ssafy 5기',0,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLroZzqt7jsnbjthqDtgbAiLCJleHAiOjE2Mjk5ODg3MTR9.N2OaUp665MpVlXEQ1NPmf-91FEUBJrIAr8KBCwlMmn8','ssafy55','$2a$10$PBiXGyVkbGoZWcuRJP3kvuiv1LYIehwLFlSI.06TRgO3CRIt/Dt2S',24,NULL),(24,'nbn0809@naver.com','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/defaultImg.PNG','1','꿀팁대마왕',50,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLroZzqt7jsnbjthqDtgbAiLCJleHAiOjE2Mjk5ODgyNzN9.qLPbIIfWhuaCPnE_1fzmk1ap5-sAlmDu3k-hiNcS7Ec','nbn0809','$2a$10$cki/8zqfOYctVgOV2gIOVeINffDdj0SW2NhJcivjjbvGXFX.d9Z9.',25,NULL),(25,'ssafy@naver.com','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/7c59197b-6ee2-49eb-addb-70be75073fe6girl2.png','1','한글아이디',0,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLroZzqt7jsnbjthqDtgbAiLCJleHAiOjE2Mjk4MTY1MDl9.lmh9A7wdV9uf0M60X31Dg3wTtiVsvzZqi0QJ-5AWNCU','korean','$2a$10$PjIl6H9htaY546TevH5Mce3283kRbFgYiY1lN7gIu14w5YrIaWPNC',29,NULL),(28,'ssafy@naver.com','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/52ff35d1-a805-4b62-a91b-8fb23815960eboy1.png','1','찌니',200,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLroZzqt7jsnbjthqDtgbAiLCJleHAiOjE2Mjk5NjI2NTh9.F6qNWdgUVXlJDqD6STts_ZoLLz6fcosr9HedFbKpVBc','user4','$2a$10$c03JxkUVwQa1oxbU.V8dRujM0oDlUKrHL.w5vTIUS1eWW8hg9ViBa',31,NULL),(29,'eunjin@gmail.com','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/defaultImg.PNG','1','eunjin',0,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLroZzqt7jsnbjthqDtgbAiLCJleHAiOjE2Mjk5NTYwNTR9.CA2hsfnsPJOEfrhJKQf8MAjeBSw94qT2KyKBERSGsnQ','eunjinid','$2a$10$UlYoEx5V6kyYK51IceZ6denV61KjPQ.iWm3/aRlTIgrHsEjEclMXm',32,NULL),(31,'try615@naver.com','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/7e72a8f2-a366-4a70-83cf-c75735ed0030%EA%B0%9C%EA%B0%95.jpg','1','PSC',900,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLroZzqt7jsnbjthqDtgbAiLCJleHAiOjE2MzAwMjcxMjB9.f7Wp4_JGVfb9q__ZJTvStt4heEMfWYPNbld3jTegU8Y','try615','$2a$10$0QA.RAESqvzVXGIGWZuzl.RAEXgOk9BhclPcFDvMn4ZCGFpFsStyq',33,NULL),(32,'ragnaroik@naver.com','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/449b497d-b767-4513-b657-e527d57c78aeboy2.png','1','js2js2',0,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLroZzqt7jsnbjthqDtgbAiLCJleHAiOjE2MzAwMjc2NTV9.Ssr97-DWc4Gt9avjt8edDq19f3WnP1o0WWWvuWGIKLc','jsjsjs','$2a$10$a6h6kH/BF7OQ0vL/igURHuWpanmbBZcfrJvb8.ST1MTiAHNRKEAmG',34,NULL),(33,'ssafy@ssafy.com','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/0b8a5849-8c99-4d83-b3c6-611dd1b236ccgirl4.png','1','싸피',0,NULL,'ssafytest','$2a$10$JLDsMW.54jVuq5BbkplvQO9cFr1cxSCUSUB.Ex3fDzrqRmFjekJ/2',36,NULL),(34,'ssafy@ssafy.com','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/0c6781c9-3bb1-4902-b13d-cb3b361a215fboy2.png','1','박싸피',0,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLroZzqt7jsnbjthqDtgbAiLCJleHAiOjE2Mjk5NTU0NTV9.b5AnaeVkCk2wp2lpCh99LcUj4cTBsJ0MGgEpwrZ4Qt0','ssafyboy','$2a$10$JQrwt0Ek8P2A79OP/kFQR.zDF8Z3xXQgBdFf/3guzC875T/UnXMzW',37,NULL),(35,'ssafy@naver.com','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/defaultImg.PNG','1','aerrhhre',0,NULL,'dfgreah','$2a$10$G2Rp.sj7FE5FmYn4PrdkquWoCOgI20XLIW0mLjnASUmkjecvXd1nu',38,NULL),(36,'naver@naver.com','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/dd374a6f-88c8-4c54-93f4-3f597df43f92boy1.png','1','테스트계정1',0,NULL,'ABASDDS','$2a$10$1z3W2wgqKhbavSOY8CdV8O0tlVBYzxazxAsOTzeg0/C8MRGCqor8K',39,NULL),(37,'try615@naver.com','https://ggomzirakimg.s3.ap-northeast-2.amazonaws.com/static/e6b0627d-442d-4e26-83b2-6fd32e5b7423%EA%B0%9C%EA%B0%95.jpg','1','박성철',0,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiLroZzqt7jsnbjthqDtgbAiLCJleHAiOjE2MzAwMjY2NTB9.2MGBPBsde9xIVeN8D2PMygo9KaeGTVBJQF3beRiils0','try6150','$2a$10$wXu8AqbWmRClx02QXgqPquOgX2KFV0SPa./sTOH70LnOM0Vp.Xl52',40,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-20 11:00:16
