--
-- Table structure for table `aql_view_info`
--

DROP TABLE IF EXISTS `aql_view_info`;
CREATE TABLE `aql_view_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `avi_topic` varchar(100) NOT NULL DEFAULT '',
  `avi_alias` varchar(200) NOT NULL DEFAULT '',
  `avi_desc` varchar(500) NOT NULL DEFAULT '',
  `avi_state` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `topic_UNIQUE` (`avi_topic`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `aql_view_dimension`
--

DROP TABLE IF EXISTS `aql_view_dimension`;
CREATE TABLE `aql_view_dimension` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `avd_topic` varchar(100) NOT NULL DEFAULT '',
  `avd_name` varchar(100) NOT NULL DEFAULT '',
  `avd_alias` varchar(200) NOT NULL DEFAULT '',
  `avd_desc` varchar(500) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `topic_name_UNIQUE` (`avd_topic`,`avd_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `aql_view_dimension_value`
--

DROP TABLE IF EXISTS `aql_view_dimension_value`;
CREATE TABLE `aql_view_dimension_value` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `topic` varchar(100) NOT NULL DEFAULT '',
  `dimension` varchar(100) NOT NULL DEFAULT '',
  `dtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dvalue` varchar(200) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `TOPIC_DIMENSION_DTIME` (`topic`,`dimension`,`dtime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `aql_view_metric`
--

DROP TABLE IF EXISTS `aql_view_metric`;
CREATE TABLE `aql_view_metric` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `avm_topic` varchar(100) NOT NULL DEFAULT '',
  `avm_name` varchar(100) NOT NULL DEFAULT '',
  `avm_alias` varchar(200) NOT NULL DEFAULT '',
  `avm_desc` varchar(500) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `topic_name_UNIQUE` (`avm_topic`,`avm_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `aql_view_table_info`
--

DROP TABLE IF EXISTS `aql_view_table_info`;
CREATE TABLE `aql_view_table_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `avti_topic` varchar(100) NOT NULL DEFAULT '',
  `avti_name` varchar(200) NOT NULL DEFAULT '',
  `avti_data` int(11) NOT NULL DEFAULT '0',
  `avti_unit` varchar(1) NOT NULL DEFAULT '',
  `avti_period` int(11) NOT NULL DEFAULT '0',
  `avti_delay` int(11) NOT NULL DEFAULT '0',
  `avti_state` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `topic_name_UNIQUE` (`avti_topic`,`avti_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `aql_view_table_dimension`
--

DROP TABLE IF EXISTS `aql_view_table_dimension`;
CREATE TABLE `aql_view_table_dimension` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `avtd_topic` varchar(100) NOT NULL DEFAULT '',
  `avtd_table` varchar(100) NOT NULL DEFAULT '',
  `avtd_name` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `topic_table_name_UNIQUE` (`avtd_topic`,`avtd_table`,`avtd_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `aql_view_table_calculator`
--

DROP TABLE IF EXISTS `aql_view_table_calculator`;
CREATE TABLE `aql_view_table_calculator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `avtc_topic` varchar(100) NOT NULL DEFAULT '',
  `avtc_table` varchar(100) NOT NULL DEFAULT '',
  `avtc_metric` varchar(100) NOT NULL DEFAULT '',
  `avtc_type` varchar(50) NOT NULL DEFAULT '',
  `avtc_url` varchar(200) NOT NULL DEFAULT '',
  `avtc_user` varchar(100) NOT NULL DEFAULT '',
  `avtc_passwd` varchar(100) NOT NULL DEFAULT '',
  `avtc_sql` varchar(1000) DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `topic_table_metric_UNIQUE` (`avtc_topic`,`avtc_table`,`avtc_metric`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;