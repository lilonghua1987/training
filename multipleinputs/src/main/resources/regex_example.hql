drop table apache_log;
add jar /opt/mapr/hive/hive/lib/hive-serde-0.13.0-mapr-1508-21228.jar;
add jar /opt/mapr/hive/hive/lib/hive-contrib-0.13.0-mapr-1508-21228.jar;
create external table apache_log(ip_address string,
log_date string,
log_time string,
log_ms string,
protocol string,
misc string
)
row format serde 'org.apache.hadoop.hive.contrib.serde2.RegexSerDe'
WITH SERDEPROPERTIES(
"input.regex" = "^([\.0-9]*?) - - \\[(.*?):(\\d+:\\d+:\\d+) -(.*?)\\] \"(.*?)\" (.*)$",
"output.format.string" = "%1$s %2$s %3$s %4$s %5$s %6$s"
)
location '/idn/home/aiyenger/apache';
select * from apache_log;