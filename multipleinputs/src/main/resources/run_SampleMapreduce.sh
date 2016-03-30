export CLASSPATH=/opt/mapr/hadoop/hadoop/conf
export HADOOP_HOME=/opt/mapr/hadoop/hadoop
export HCAT_HOME=/opt/mapr/hive/hive/hcatalog
export LIB_JARS=dependency/jcodings-1.0.8.jar,dependency/jcommander-1.35.jar,dependency/hive-hcatalog-core-0.13.0.jar,dependency/hive-ant-0.13.0-mapr-1504.jar,dependency/antlr-runtime-3.4.jar,dependency/hive-metastore-0.13.0-mapr-1504.jar,dependency/libthrift-0.9.0.jar,dependency/hive-exec-0.13.0-mapr-1504.jar,dependency/hive-shims-0.20S-0.13.0.jar,dependency/libfb303-0.9.0.jar,dependency/datanucleus-api-jdo-3.2.6.jar,dependency/jdo-api-3.0.1.jar,dependency/datanucleus-core-3.2.10.jar,dependency/datanucleus-rdbms-3.2.9.jar,dependency/hbase-server-0.98.12-mapr-1506.jar,dependency/hbase-common-0.98.12-mapr-1506.jar,dependency/hbase-protocol-0.98.12-mapr-1506.jar,dependency/hbase-client-0.98.12-mapr-1506.jar,dependency/hbase-hadoop-compat-0.98.12-mapr-1506.jar,dependency/hbase-hadoop2-compat-0.98.12-mapr-1506.jar,dependency/htrace-core-2.04.jar,dependency/high-scale-lib-1.1.1.jar
export HADOOP_CLASSPATH=$(echo $LIB_JARS | tr ',' ':'):$HIVE_HOME/conf:$(echo $HBASE_HOME/lib/*.jar | tr ' ' ':'):$HADOOP_HOME/conf
export CLASSPATH=$CLASSPATH:$HADOOP_CLASSPATH
hadoop jar mul*.jar  org.training.mapreduce.SampleMapreduce \
-libjars ${LIB_JARS} -files /axp/rim/rdre/warehouse/rdredb.db/rdre_ctrprdc/Ctrprdc.txt,/axp/rim/rdre/warehouse/rdredb.db/rdre_cutmonth/Cutmonth.txt,/axp/rim/rdre/warehouse/rdredb.db/rdre_InitialValue/InitValue.txt \
-queue rdre \
-reducers 5 \
-market FIM \
-inputdb campus20 \
-smart_merge_demo_table gstar_demo \
-smart_merge_finanical_table gstar_finan \
-outputdb campus20 \
-output_account_table temp_gstar_finan \
-output_customer_table temp_gstar_demo \