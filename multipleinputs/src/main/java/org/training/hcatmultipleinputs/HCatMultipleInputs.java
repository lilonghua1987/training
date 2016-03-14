package org.training.hcatmultipleinputs;


/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */


import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hive.hcatalog.mapreduce.HCatSplit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class supports MapReduce jobs that use multiple HCatalog
 * tables as input. Usage is similar to the MultipleInputs class,
 * which allows a user to specify a different InputFormat for each input Path.
 *
 * <p>
 * Usage pattern for job submission:
 *
 * <pre>
 * Configuration conf = new Configuration();
 *
 * Job job = new Job(conf);
 * job.setJarByClass(this.getClass());
 *
 * job.setOutputFormatClass(TextOutputFormat.class);
 *
 * HCatMultipleInputs.addInput(job, test_table1, "default", null, SequenceMapper.class);
 * HCatMultipleInputs.addInput(job, test_table2, null, "part='1'", TextMapper1.class);
 * HCatMultipleInputs.addInput(job, test_table2, null, "part='2'", TextMapper2.class);
 *
 * job.setMapOutputKeyClass(Text.class);
 * job.setMapOutputValueClass(DefaultHCatRecord.class);
 *
 * Path path = new Path(TEST_DATA_DIR, "output");
 *
 * TextOutputFormat.setOutputPath(job, path);
 *
 * job.setReducerClass(MyReducer.class);
 *
 * return job.waitForCompletion(true);
 *
 */

public class HCatMultipleInputs {

    private static final Logger LOGGER = LoggerFactory.getLogger(HCatMultipleInputs.class.getName());

    public static final String CONF_TABLES    = "hcat.input.multi.tables";
    public static final String CONF_INPUTINFO = "hcat.input.multi.inputinfo";

    private static void addInput(Job job, InputInfo info) {

        Configuration conf = job.getConfiguration();

        String newTableInfo = info.toString();

        String storedTables = conf.get(CONF_TABLES);

        conf.set(CONF_TABLES,
            storedTables == null ? newTableInfo : storedTables + ","  + newTableInfo);

        job.setInputFormatClass(HCatDelegatingInputFormat.class);

        job.setMapperClass(HCatDelegatingMapper.class);
    }

    /**
     * @param job    The {@link Job} itself.
     * @param table  Table name to
     * @param dbName The database that the table belongs to. If null, the default database will be used.
     * @param filter The partition filter that should be applied to the table, can be null.
     * @param mapperClass The mapper that should be used for this input, not null.
     *
     */

    @SuppressWarnings("rawtypes")
    public static void addInput(Job job, String table, String dbName, String filter, Class<? extends Mapper> mapperClass) {
        addInput(job, new InputInfo(table, dbName, filter, mapperClass));
    }

    static void writeInputInfoToSplit(InputSplit split, InputInfo info) {
        try {
            writeInputInfoToSplit(InternalUtil.castToHCatSplit(split), info);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    static void writeInputInfoToSplit(HCatSplit split, InputInfo info) {
        Properties props = split.getPartitionInfo().getInputStorageHandlerProperties();
        props.setProperty(HCatMultipleInputs.CONF_INPUTINFO, info.toString());
    }

    static InputInfo readInputInfoFromSplit(InputSplit split) {
        try {
            return readInputInfoFromSplit(InternalUtil.castToHCatSplit(split));
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    static InputInfo readInputInfoFromSplit(HCatSplit split) {

        String inputInfoString = split.getPartitionInfo().getInputStorageHandlerProperties().getProperty(HCatMultipleInputs.CONF_INPUTINFO);

        InputInfo input = InputInfo.fromString(inputInfoString);

        return input;

    }

    protected static Set<InputInfo> getTableInfoSet(Configuration conf) {
        Set<InputInfo> tableInfoSet = new HashSet<InputInfo>();
        String infos[] = conf.getStrings(CONF_TABLES);
        for (String encodedInfo : infos) {
            InputInfo info = InputInfo.fromString(encodedInfo);
            tableInfoSet.add(info);
        }
        return tableInfoSet;
    }

    static class InputInfo {
        public String table;
        public String dbName;
        public String filter;
        public String mapperClass;

        public InputInfo (String table, String dbName, String filter) {
            this.table = table;
            this.dbName = dbName;
            this.filter = filter;
            this.mapperClass = null;
        }

        @SuppressWarnings("rawtypes")
        public InputInfo(String table, String dbName, String filter, Class<? extends Mapper> mapperClass) {
            this.table = table;
            this.dbName = dbName;
            this.filter = filter;
            if (mapperClass != null) {
                this.mapperClass = mapperClass.getName();
            } else {
                mapperClass = null;
            }
        }

        private InputInfo(String table, String dbName, String filter, String mapperClass) {
            this.table = table;
            this.dbName = dbName;
            this.filter = filter;
            this.mapperClass = mapperClass;
        }

        @Override
        public String toString() {
            return table+";"+dbName+";"+filter+";"+mapperClass;
        }

        public static InputInfo fromString(String ser) {
        	System.out.println("Ser:"+ser);
            String split[] = ser.split(";");
            for (int i = 0; i < split.length; i++) {
                if (split[i] == null || split[i].equals("null")) {
                    split[i] = null;
                }
            }
            return new InputInfo(split[0], split[1], split[2], split[3]);
        }
    }

}
