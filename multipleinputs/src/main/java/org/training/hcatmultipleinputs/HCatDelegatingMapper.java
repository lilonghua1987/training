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

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.ReflectionUtils;


class HCatDelegatingMapper<K1, V1, K2, V2> extends Mapper<K1, V1, K2, V2> {
    private Mapper<K1, V1, K2, V2> mapper;

    @Override
    @SuppressWarnings("unchecked")
    protected void setup(Context context)
        throws IOException, InterruptedException {

    	HCatMultipleInputs.InputInfo input = HCatMultipleInputs.readInputInfoFromSplit(context.getInputSplit());

        Class<? extends Mapper> mapperClass;
        try {
            if (input.mapperClass != null) {

                mapperClass = (Class<? extends Mapper>) Class.forName(input.mapperClass);

            } else {
                throw new NullPointerException("Mapper not set for input: "+input.table);
            }
            mapper = (Mapper<K1, V1, K2, V2>) ReflectionUtils.newInstance(mapperClass, context.getConfiguration());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(Context context)
        throws IOException, InterruptedException {
        setup(context);
        mapper.run(context);
        cleanup(context);
    }
}
