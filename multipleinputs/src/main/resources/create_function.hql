drop function encrypt;
drop function decrypt;

CREATE FUNCTION encrypt AS 'org.training.hive.udfs.Encrypt'
USING JAR 'maprfs:///idn/home/aiyenger/projects/training/multipleinputs-0.0.1-SNAPSHOT.jar';


CREATE FUNCTION decrypt AS 'org.training.hive.udfs.Decrypt'
USING JAR 'maprfs:///idn/home/aiyenger/projects/training/multipleinputs-0.0.1-SNAPSHOT.jar';


show functions;

describe function encrypt;
describe function extended encrypt;
