-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.5.23 - MySQL Community Server (GPL)
-- ОС Сервера:                   Win32
-- HeidiSQL Версия:              9.2.0.4947
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
-- Дамп данных таблицы ventilation.equipment: ~0 rows (приблизительно)
DELETE FROM `equipment`;
/*!40000 ALTER TABLE `equipment` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipment` ENABLE KEYS */;

-- Дамп данных таблицы ventilation.factory: ~4 rows (приблизительно)
DELETE FROM `factory`;
/*!40000 ALTER TABLE `factory` DISABLE KEYS */;
INSERT INTO `factory` (`id`, `name`, `country`, `cage`, `number_of_heads`, `length`, `width`, `height_min`, `height_max`) VALUES
	(1, 'Крю', 'Украина', 'ТБК', 96360, 96, 18, 3, 0),
	(2, 'Трям', 'Алжир', 'ТББ', 72000, 84, 15, 4, 5),
	(4, 'Название фабирики', 'Страна', 'ТБК', 96360, 96, 18, 4, 0),
	(15, 'ООО «Гиждувон Агро парранда»', 'Узбекистан ', 'ТБЦ', 43008, 65, 12, 3.6, 0);
/*!40000 ALTER TABLE `factory` ENABLE KEYS */;

-- Дамп данных таблицы ventilation.hibernate_sequence: ~1 rows (приблизительно)
DELETE FROM `hibernate_sequence`;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(16);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
