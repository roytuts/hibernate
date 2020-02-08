DELIMITER $$
CREATE
    PROCEDURE `jeejava`.`getCds`(cdid BIGINT)
    BEGIN
    SELECT * FROM cd WHERE id=cdid;
    END$$
DELIMITER ;

CREATE TABLE IF NOT EXISTS `cd` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `artist` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `cd` (`id`, `title`, `artist`) VALUES
	(1, 'Title 1', 'Artist 1'),
	(2, 'Title 2', 'Artits 2'),
	(3, 'Title 3', 'Artist 3'),
	(4, 'Title 4', 'Artist 4'),
	(5, 'Title 5', 'Artist 5'),
	(10, 'Single Title', 'Single Artist');