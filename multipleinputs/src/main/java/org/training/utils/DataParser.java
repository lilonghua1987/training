package org.training.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.hive.common.type.HiveDecimal;
import org.apache.hadoop.io.Text;
import org.apache.hive.hcatalog.data.DefaultHCatRecord;
import org.apache.hive.hcatalog.data.HCatRecord;
//import org.apache.spark.sql.DataFrame;	;
//import org.apache.spark.sql.catalyst.expressions.Row;



public class DataParser {

	public static String fieldDelimiter=",";
	public static String collectionDelimiter="\\|";
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public static HashMap<String,HashMap<String,String>> shieldFields = new HashMap<String,HashMap<String,String>>();	
	private static HashMap<Integer,Object> copyObjects = new HashMap<Integer,Object>();
	public static final HiveDecimal HIVEDECIMAL_MIN = HiveDecimal.create(Long.MIN_VALUE);
	private static List<String> nullDefaults = Arrays.asList(Long.MIN_VALUE + "", Double.MIN_VALUE + "", Integer.MIN_VALUE + "",HIVEDECIMAL_MIN.toString()+"");
	private static String className = "DataParser";
	private static HashMap<String,ArrayList<Field>> allFieldsMapping = new HashMap<String,ArrayList<Field>>(); 
	private static HashMap<String,ArrayList<Method>> allMethodsMapping = new HashMap<String,ArrayList<Method>>();
	private static HashMap<String,String> getters = new HashMap<String,String>();
	private static HashMap<String,String> setters = new HashMap<String,String>();
	

	public static<T> boolean parseNullsToMins(Text val,T entityObject){
		if( parseNullsToMins(val.toString(),entityObject)){
			if(shieldFields.get(entityObject.getClass().getSimpleName().toUpperCase()+"_FIELDS") != null) copyPhase(entityObject);
			return true;
		}
		return false;
	}
	
	public static<T> boolean parse(Text val,T entityObject){
		if( parse(val.toString(),entityObject)){
			if(shieldFields.get(entityObject.getClass().getSimpleName().toUpperCase()+"_FIELDS") != null) copyPhase(entityObject);
			return true;
		}
		return false;
	}
	

	private static<T> void copyPhase(T entityObject) {

		if(copyObjects.get(entityObject.hashCode()) == null){
			T copyObject = cloneObject(entityObject);
			if(copyObject != null) copyObjects.put(entityObject.hashCode(),copyObject);
		}
	}

	public static<T> T cloneObject(T entityObject){
		T copyObject = null;
		@SuppressWarnings("unchecked")
		Class<T> cl = (Class<T>) entityObject.getClass();
		try {
			copyObject = (T)cl.newInstance();
			for(Field field: getAllFields(cl)) {
				Method method = (Method)cl.getMethod(getGetter(field));
				cl.getMethod(getSetter(field),field.getType()).invoke(copyObject,method.invoke(entityObject) );
			}			

		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//		System.out.println("copy object:"+copyObject);
		return copyObject;
	}

	public static<T> boolean parseNullsToMins(String val,T entityObject) {
		sdf.setLenient(false);
		String[] parts = val.split(fieldDelimiter, -1); 
		int i = 0;
		@SuppressWarnings("unchecked")
		Class<T> cl = (Class<T>) entityObject.getClass();
		for (Field field : getAllFields(cl))
		{
			Class fieldType = field.getType();
			field.setAccessible(true);
			try {
				if(field.getGenericType().toString().contains("List"))
					cl.getMethod(getSetter(field),fieldType).invoke(entityObject, getListNullsToMins(parts[i++],Class.forName(getListType(field))));
				else
					cl.getMethod(getSetter(field),fieldType).invoke(entityObject, getDataNullsToMins(parts[i++],fieldType));

			} catch (Exception e) {
				LogManager.writeErrorToLog(className, "parse", "General exception while parsing field: "+field.getName()+"\n **Message: "+e.getMessage());
				System.out.println(className+ " method ==> parse "+ "exception while parsing field: "+field.getName()+"\n **Message: "+e.getMessage());
				e.printStackTrace();
				return false;
			}
		}

		return true;

	}

	public static<T> boolean parse(String val,T entityObject) {
		sdf.setLenient(false);
		String[] parts = val.split(fieldDelimiter, -1); 
		int i = 0;
		@SuppressWarnings("unchecked")
		Class<T> cl = (Class<T>) entityObject.getClass();
		for (Field field : getAllFields(cl))
		{
			Class fieldType = field.getType();
			field.setAccessible(true);
			try {
				if(field.getGenericType().toString().contains("List"))
					cl.getMethod(getSetter(field),fieldType).invoke(entityObject, getList(parts[i++],Class.forName(getListType(field))));
				else
					cl.getMethod(getSetter(field),fieldType).invoke(entityObject, getData(parts[i++],fieldType));

			} catch (Exception e) {
				LogManager.writeErrorToLog(className, "parse", "General exception while parsing field: "+field.getName()+"\n **Message: "+e.getMessage());
				System.out.println(className+ " method ==> parse "+ "exception while parsing field: "+field.getName()+"\n **Message: "+e.getMessage());
				e.printStackTrace();
				return false;
			}
		}

		return true;

	}
	


	public static<T> boolean parse(HCatRecord val,T entityObject) {
		int i = 0;
		sdf.setLenient(false);
		@SuppressWarnings("unchecked")
		Class<T> cl = (Class<T>) entityObject.getClass();

		boolean useMethods = false;
		ArrayList<Method> mList = allMethodsMapping.get(cl.getName()+"_SET");
		if(mList != null) 
			useMethods = true;
		else
			mList = new ArrayList<Method>();

		int index = 0;
		for(Field field: getAllFields(cl)) {
//			EncryptData ed = field.getAnnotation(EncryptData.class);			
			try {
				Class fieldType = field.getType();
				Method m; 

				if(useMethods) m = mList.get(index++); 
				else 
					m = cl.getMethod(getSetter(field),fieldType);
				Object valObject;
				//				if(ed != null) valObject = decrypt(field,val.get(i++));
				//				else 
				valObject = val.get(i++);

				//				if(valObject != null && (valObject.getClass().getName().contains("HiveDecimal")
				//						|| (field.getGenericType().toString().contains("List") && getListType(field).contains("HiveDecimal")) )){
				//					if(field.getGenericType().toString().contains("List")){
				//						List<HiveDecimal> bDList = new ArrayList<HiveDecimal>();
				//						for(org.apache.hadoop.hive.common.type.HiveDecimal hd:(List<org.apache.hadoop.hive.common.type.HiveDecimal>)valObject)
				//							if(hd != null)bDList.add((HiveDecimal)hd.HiveDecimalValue());
				//						cl.getMethod(getSetter(field),fieldType).invoke(entityObject,bDList);						
				//					}
				//					else{
				//						HiveDecimal bd = (HiveDecimal)((org.apache.hadoop.hive.common.type.HiveDecimal)valObject).HiveDecimalValue();
				//						cl.getMethod(getSetter(field),fieldType).invoke(entityObject, bd);					
				//					}	
				//				}
				//				else
				m.invoke(entityObject,valObject);
				if(!useMethods) mList.add(m);

			} catch (Exception e) {
				LogManager.writeErrorToLog(className, "parse", "General exception while parsing HCcatRecord: "+e.getMessage());
				System.out.println("Erro processing Hcat record: "+e.getMessage()+"index: "+(i-1) +" Hcat rec: "+val.get(i-1)+" field: "+field.getName());
				System.out.println("HCAT record is:"+val);
				e.printStackTrace();
				//				return false;
			}
		}
		if(!useMethods) allMethodsMapping.put(cl.getName()+"_SET", mList);
		return true;
	}	
	
//	public static<T> boolean loadCollectedData(T entityObject,HashMap<String,String> collectedData) {
//		@SuppressWarnings("unchecked")
//		Class<T> cl = (Class<T>) entityObject.getClass();
//		boolean useMethods = false;
//		ArrayList<Method> mList = allMethodsMapping.get(cl.getName()+"_SET");
//		if(mList != null) 
//			useMethods = true;
//		else
//			mList = new ArrayList<Method>();
//
//		int index = 0;
//
//		for (Field field : getAllFields(cl))
//		{
//			Class fieldType = field.getType();
//			field.setAccessible(true);
//			int skip = -1;
//			String compositeData;
//			String parts[] = null;
//			Method m;
//
//			try {
//				if(useMethods) 
//					m = mList.get(index++); 
//				else {
//					m = cl.getMethod(getSetter(field),fieldType);
//					mList.add(m);
//				}
//				SourceCode source = field.getAnnotation(SourceCode.class);
//				if(source != null) {
//					compositeData = collectedData.get(source.value()+"");
//					if(compositeData !=  null) parts = compositeData.split(",");
//					if(parts != null && parts.length > 0){
//
//						for(String s1:parts) {
//							skip++;
//							String[] dataAndType = null;
//							if(! "".contentEquals(s1)) dataAndType = s1.split(":");
//							if(dataAndType!= null && dataAndType.length > 1){
//								if(fieldType.toString().contains(dataAndType[0])){
//									m.invoke(entityObject, getData(dataAndType[1],fieldType));
//									break;
//								}
//							}else {
//								System.out.println("Error processing DataAndType: "+s1);
//							}
//						}
//					}
//					else{
//						LogManager.writeErrorToLog(className, "loadCollectedData", "collected data has no value  for source: "+source.value()+" Data:"+compositeData);
////						System.out.println();
//					}
//
//
//				}
//				if(skip != -1 && parts != null){
//					String out="";
//					int place = 0;
//					for(String s2: parts) if(place != skip) out = parts[place++]+",";
//					collectedData.put(""+source.value(), out);
//				}
//
//			} catch (Exception e) {
//				System.out.println("MainframeDataParser"+ " method ==> parse "+ "exception while parsing field: "+field.getName()+"\n **Message: "+e.getMessage());
//				e.printStackTrace();
//				return false;
//			}
//		}
//		if(!useMethods) allMethodsMapping.put(cl.getName()+"_SET", mList);
//		return true;
//	}
	
	
//	public static<T> boolean setIntitialValuesForNewAct(T entityObject) {
//		System.out.println("");
//		int i = 0;
//		sdf.setLenient(false);
//		@SuppressWarnings("unchecked")
//		Class<T> cl = (Class<T>) entityObject.getClass();
//	
//		
//		for(Field field: getAllFields(cl)) {			
////			EncryptData ed = field.getAnnotation(EncryptData.class);			
//			try {
//				Class fieldType = field.getType();
//				String fieldName = field.getName();
//				System.out.println("fieldName = "+fieldName);
//				if (!fieldName.startsWith("afa") && LoadData.hashInitialValue.containsKey(fieldName)){
//					System.out.println("FieldName in hashInitialValue");
//					InitialValue initValue = LoadData.hashInitialValue.get(field.getName());
//					if(field.getGenericType().toString().contains("List"))
//						cl.getMethod(getSetter(field),fieldType).invoke(entityObject, getListNullsToMins(initValue.getFieldInitValue(),Class.forName(getListType(field))));
//					else{
//						cl.getMethod(getSetter(field),fieldType).invoke(entityObject, getDataNullsToMins(initValue.getFieldInitValue(),fieldType));
//					}
//				}
////				}else {
////					System.out.println("FieldName not in hashInitialValue");
////					//No need to process other fields
////				}
//			} catch (Exception e) {
//				LogManager.writeErrorToLog(className, "setIntitialValuesForNewAct", "Exception with hashInitialValue or while parsing "+e.getMessage());
//				
//				e.printStackTrace();
//				//				return false;
//			}
//		}
//		return true;
//	}
	
	public static<T> boolean parseAndNullsToMins(HCatRecord val,T entityObject) {
		int i = 0;
		sdf.setLenient(false);
		@SuppressWarnings("unchecked")
		Class<T> cl = (Class<T>) entityObject.getClass();
		
		boolean useMethods = false;
		ArrayList<Method> mList = allMethodsMapping.get(cl.getName()+"_SET");
		if(mList != null) 
			useMethods = true;
		else
			mList = new ArrayList<Method>();
		int index = 0;

		for(Field field: getAllFields(cl)) {
//			EncryptData ed = field.getAnnotation(EncryptData.class);			
			try {
				Class fieldType = field.getType();
				Object valObject;
				Method m;
				if(useMethods) m = mList.get(index++);
				else m = cl.getMethod(getSetter(field),fieldType);
//				if(ed != null) valObject = decrypt(field,val.get(i++));
//				else 
					valObject = val.get(i++);
				valObject=nullsToMins(valObject,field);

//				if(valObject != null && (valObject.getClass().getName().contains("HiveDecimal")
//						|| (field.getGenericType().toString().contains("List") && getListType(field).contains("HiveDecimal")) )){
//					if(field.getGenericType().toString().contains("List")){
//						List<HiveDecimal> bDList = new ArrayList<HiveDecimal>();
//						for(org.apache.hadoop.hive.common.type.HiveDecimal hd:(List<org.apache.hadoop.hive.common.type.HiveDecimal>)valObject)
//							if(hd != null)bDList.add((HiveDecimal)hd.HiveDecimalValue());
//							else bDList.add(HIVEDECIMAL_MIN);
//						cl.getMethod(getSetter(field),fieldType).invoke(entityObject,bDList);						
//					}
//					else{
//						HiveDecimal bd = (HiveDecimal)((org.apache.hadoop.hive.common.type.HiveDecimal)valObject).HiveDecimalValue();
//						cl.getMethod(getSetter(field),fieldType).invoke(entityObject, bd);					
//					}	
//				}
//				else
					m.invoke(entityObject,valObject);
					if(!useMethods) mList.add(m);

			} catch (Exception e) {
				LogManager.writeErrorToLog(className, "parse", "General exception while list type: "+e.getMessage());
				System.out.println("Erro processing Hcat record: "+e.getMessage()+" Hcat rec: "+val.get(i-1)+" field: "+field.getName());
				System.out.println("Erro processing Hcat record: "+e.getMessage()+"index: "+(i-1) +" Hcat rec: "+val.get(i-1)+" field: "+field.getName());
				System.out.println("HCAT record is:"+val);
				e.printStackTrace();
				//				return false;
			}
		}
		if(!useMethods) allMethodsMapping.put(cl.getName()+"_SET", mList);
		return true;
	}	


	
	private static Object nullsToMins(Object valObject,Field field){
		Class fieldType = field.getType();
		String className = fieldType.getSimpleName();
		//System.out.println("className-->"+className+"valObject-->"+valObject);
//		try {
//			if(valObject != null && (valObject.getClass().getName().contains("HiveDecimal")
//					|| (field.getGenericType().toString().contains("List") && getListType(field).contains("HiveDecimal")) )) return valObject;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("In nullsToMins method processing field: "+field.getName());
//		}
		if(field.getGenericType().toString().contains("List")){
			try {
				String getListType = getListType(field);
				if(getListType.contains("HiveDecimal")){
					if(valObject == null) {
						valObject =new ArrayList<HiveDecimal>();
						return valObject;
					}
					for(Object o:(List)valObject)	if(o == null) o = HIVEDECIMAL_MIN;					
				}
				if(getListType.contains("Integer")){
					if(valObject == null) {
						valObject =new ArrayList<Integer>();
						return valObject;
					}
					for(Object o:(List)valObject)	if(o == null) o = Integer.MIN_VALUE;					
				}

				if(getListType.contains("Long")){
					if(valObject == null) {
						valObject =new ArrayList<Long>();
						return valObject;
					}
					for(Object o:(List)valObject)	if(o == null) o = Long.MIN_VALUE;					
				}

				if(getListType.contains("Double")){
					if(valObject == null) {
						valObject =new ArrayList<Double>();
						return valObject;
					}
					for(Object o:(List)valObject)	if(o == null) o = Double.MIN_VALUE;					
				}

				if(getListType.contains("String")){
					if(valObject == null) {
						valObject =new ArrayList<String>();
						return valObject;
					}
					for(Object o:(List)valObject)	if(o == null) o = " ";					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("nullsToMins error processing list "+field.getName());
			}
		}
		else{
			if(valObject == null){
				if(className.contains("Integer")) {
					valObject = Integer.MIN_VALUE;
					return valObject;
				}
				if(className.contains("Long")) {
					valObject = Long.MIN_VALUE;
					return valObject;
				}

				if(className.contains("Double")) {
					valObject = Double.MIN_VALUE;
					return valObject;
				}

				if(className.contains("HiveDecimal")) {
					valObject = HIVEDECIMAL_MIN;
					return valObject;
				}

				if(className.contains("String")) {
					valObject = " ";
					return valObject;
				}
				if(className.contains("Boolean")) {
					valObject = false;
					return valObject;
				}
			}
		}
		//System.out.println("default return");
		return valObject;
	}
//	public static<T> boolean parse(Row val,T entityObject) {
//		int i = 0;
//		sdf.setLenient(false);
//		@SuppressWarnings("unchecked")
//		Class<T> cl = (Class<T>) entityObject.getClass();
//
//		for(Field field: getAllFields(cl)) {
//			EncryptData ed = field.getAnnotation(EncryptData.class);			
//			try {
//				Class fieldType = field.getType();
//				Object valObject;
//				if(ed != null) valObject = decrypt(field,val.getAs(i++));
//				else valObject = val.getAs(i++);
//
//				if(val.getAs(i-1) != null && val.getAs(i-1).getClass().getName().contains("org.apache.hadoop.hive.common.type.HiveDecimal")){
//					if(field.getGenericType().toString().contains("List")){
//						List<HiveDecimal> bDList = new ArrayList<HiveDecimal>();
//						for(org.apache.hadoop.hive.common.type.HiveDecimal hd:(List<org.apache.hadoop.hive.common.type.HiveDecimal>)valObject)
//							bDList.add(hd.HiveDecimalValue());
//						cl.getMethod(getSetter(field),fieldType).invoke(entityObject,bDList);						
//					}
//					else{
//						HiveDecimal bd = ((org.apache.hadoop.hive.common.type.HiveDecimal)valObject).HiveDecimalValue();
//						cl.getMethod(getSetter(field),fieldType).invoke(entityObject, bd);					
//					}	
//				}
//				else
//					cl.getMethod(getSetter(field),fieldType).invoke(entityObject,valObject);
//
//			} catch (Exception e) {
//				LogManager.writeErrorToLog(className, "parse", "General exception while list type: "+e.getMessage());
//				System.out.println("Erro processing Hcat record: "+e.getMessage()+" Hcat rec: "+val.getAs(i-1)+" val type is:"+val.getAs(i-1).getClass().getName()+" field: "+field.getName());
//				e.printStackTrace();
//				//				return false;
//			}
//		}
//		return true;
//	}	


	public static<T> boolean parse(ResultSet val,T entityObject) {
		int i = 1;
		sdf.setLenient(false);
		@SuppressWarnings("unchecked")
		Class<T> cl = (Class<T>) entityObject.getClass();

		for(Field field: getAllFields(cl)) {

			try {
				Method method = (Method)cl.getMethod(getGetter(field));
				cl.getMethod(getSetter(field),method.getReturnType()).invoke(entityObject, val.getObject(i++));
			} catch (Exception e) {
				String logMessage = "Error while processing result data for field " + field.getName() + ": " + e.getMessage();
				LogManager.writeErrorToLog(className, "parse", logMessage);
				System.out.println("Exception while processing result data: "+e.getMessage());
				System.out.println("Error while processing field: "+field.getName());
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}


	public static<T> ArrayList<Field> getAllFields(Class<T> cl){
		ArrayList<Field> allFields;
		allFields = allFieldsMapping.get(cl.getSimpleName()+"_AllFields");
		if(allFields == null){
			allFields = new ArrayList<Field>();
			allFields.addAll(Arrays.asList(cl.getSuperclass().getDeclaredFields()));
			allFields.addAll(Arrays.asList(cl.getDeclaredFields()));
			allFieldsMapping.put(cl.getSimpleName()+"_AllFields", allFields);
		}
		return allFields;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static<T> String toString(T entityObject) {
		StringBuilder total = new StringBuilder("");
		Class<T> cl = (Class<T>) entityObject.getClass();

		for(Field field: getAllFields(cl)) {
			try {
				Method method = (Method)cl.getMethod(getGetter(field));
				if(field.getGenericType().toString().contains("List"))
					total = total.append(getListData((List)method.invoke(entityObject), Class.forName(getListType(field)))).append(fieldDelimiter);
				else{
					if(method.invoke(entityObject) !=  null){
						if(field.getGenericType().toString().contains("Date"))
							total = total.append(sdf.format((Date)method.invoke(entityObject))).append(fieldDelimiter);
						else
							total = total.append(method.invoke(entityObject)).append(fieldDelimiter);
					}else total = total.append("").append(fieldDelimiter);
				}

			} catch (Exception e) {
				LogManager.writeErrorToLog(className, "toString", "General exception while list type:"+e.getMessage());
				System.out.println(className+ " toString "+ "Error during to string: "+e.getMessage()+" field: "+field.getName());
				e.printStackTrace();
				return null;
			}
		}

		return total.substring(0, total.length()-1);

	}


	@SuppressWarnings("unchecked")
	// Edited to translate from min values to nulls
	// Treats the shielding of fields
	public static<T>  HCatRecord getRecord(T entityObject){
		Class<T> cl = (Class<T>) entityObject.getClass();
		HCatRecord record = new DefaultHCatRecord(cl.getDeclaredFields().length);
//		T copyObject = (T)copyObjects.remove(entityObject.hashCode());
		int i = 0;
//		boolean dataPreserve;

//		String key = cl.getSimpleName().toUpperCase()+"_FIELDS";
//		HashMap<String,String> fields = shieldFields.get(key);
		ArrayList<Method> mList = allMethodsMapping.get(cl.getName()+"_GET");
		if(mList != null){
			for(Method m1: mList){
					try {
						if(m1.getReturnType().getSimpleName().contentEquals("List"))
							record.set(i++, parseListForNulls((List<T>)m1.invoke(entityObject)));
						else
							record.set(i++, valNullCheck(m1.invoke(entityObject)));	
					} catch (Exception e) {
						// TODO Auto-generated catch block
						LogManager.writeErrorToLog(className, "getRecord", "General exception while list type:"+e.getMessage()+" field name: "+m1.getName());
						System.out.println("General exception while list type:"+e.getMessage()+" field name: "+m1.getName());
						e.printStackTrace();
		
						e.printStackTrace();
					}
			}
			return record;
		}

		mList = new ArrayList<Method>();

		for(Field field: getAllFields(cl)) {			
//			dataPreserve=false;
//			if(fields != null) {
//				if(copyObject != null && fields.containsKey(field.getName())){
//					dataPreserve = true;
//				}
//			}
			try {

				Method method = (Method)cl.getMethod(getGetter(field));
//				EncryptData ed = field.getAnnotation(EncryptData.class);

//				if(dataPreserve){
//					if(ed != null){
//						if(method.getReturnType().getSimpleName().contentEquals("List"))
//							record.set(i++, encrypt(field,parseListForNulls((List<T>)method.invoke(copyObject))));
//						else
//							record.set(i++, encrypt(field,valNullCheck(method.invoke(copyObject))));	
//					}else{
//						if(method.getReturnType().getSimpleName().contentEquals("List"))
//							record.set(i++, parseListForNulls((List<T>)method.invoke(copyObject)));
//						else
//							record.set(i++, valNullCheck(method.invoke(copyObject)));	
//					}
//				}
//				else{
//					if(ed != null){
//						if(method.getReturnType().getSimpleName().contentEquals("List"))
//							record.set(i++, encrypt(field,parseListForNulls((List<T>)method.invoke(entityObject))));
//						else
//							record.set(i++, encrypt(field,valNullCheck(method.invoke(entityObject))));
//					}
//					else{
						if(method.getReturnType().getSimpleName().contentEquals("List"))
							record.set(i++, parseListForNulls((List<T>)method.invoke(entityObject)));
						else
							record.set(i++, valNullCheck(method.invoke(entityObject)));	
//					}

						mList.add(method);
//				}
			} catch (Exception e) {
				LogManager.writeErrorToLog(className, "getRecord", "General exception while list type:"+e.getMessage()+" field name: "+field.getName());
				System.out.println("General exception while list type:"+e.getMessage()+" field name: "+field.getName());
				e.printStackTrace();
				return null;
			}
		}
		allMethodsMapping.put(cl.getName()+"_GET", mList);
		return (HCatRecord)record;
	}





	public static<T> T decrypt(Field field,T obj){
		//		code to perform decryption
		LogManager.writeDebugToLog(className, "decrypt", "Decrypting field: "+field.getName());
		//		System.out.println("Decrypting field: "+field.getName());
		return obj;

	}

	public static<T> T encrypt(Field field,T obj){
		//		encrypt
		LogManager.writeDebugToLog(className, "encrypt", "Encrypting field: "+field.getName());
		System.out.println("Encrypting field: "+field.getName());
		return obj;
	}


	// Edited to used helper methods instead of T.parseT()
	private static<T> List<T> getListNullsToMins(String input,Class<T> cl){
		//		System.out.println(" get List called:"+cl.getSimpleName()+"data is"+input);  

		String[] stringList = input.split(collectionDelimiter, -1);
		List<T> lout = new ArrayList<T>();
		for(String v: stringList){
			try {
				if(v.contentEquals("null")) ;
				else{
					if(cl.getSimpleName().contentEquals("Integer") || cl.getSimpleName().contentEquals("int")) lout.add(cl.cast(parseIntegerField(v)));
					if(cl.getSimpleName().contentEquals("Long") || cl.getSimpleName().contentEquals("long")) lout.add(cl.cast(parseLongField(v)));
					if(cl.getSimpleName().contentEquals("Double") || cl.getSimpleName().contentEquals("double")) lout.add(cl.cast(parseDoubleField(v)));
					if(cl.getSimpleName().contentEquals("Boolean") || cl.getSimpleName().contentEquals("boolean")) lout.add(cl.cast(Boolean.parseBoolean(v)));
					if(cl.getSimpleName().contentEquals("String")) lout.add(cl.cast(v));
					if(cl.getSimpleName().contentEquals("HiveDecimal")) lout.add(cl.cast(parseHiveDecimalField(v)));
					if(cl.getSimpleName().contentEquals("Date")) lout.add(cl.cast(sdf.parse(v)));
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				lout.add(null);
				LogManager.writeErrorToLog(className, "getList", "length:"+stringList.length+" Unable to parse the input: "+v+" Message is: "+e.getMessage());
				System.out.println("length:"+stringList.length+" Unable to parse the input: "+v+" Message is: "+e.getMessage());
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				lout.add(null);
				LogManager.writeErrorToLog(className, "getLost", "Unable to parse input to Date.");
				System.out.println("Unable to parse input to date. Input="+input);
				e.printStackTrace();
			}
		}
		//		System.out.println(" get List called:"+lout.toString());
		return lout;
	}
	
	private static<T> List<T> getList(String input,Class<T> cl){
		//		System.out.println(" get List called:"+cl.getSimpleName()+"data is"+input);  

		String[] stringList = input.split(collectionDelimiter, -1);
		List<T> lout = new ArrayList<T>();
		for(String v: stringList){
			try {
				if(v.contentEquals("null")) lout.add(null);
				else{
					if(cl.getSimpleName().contentEquals("Integer") || cl.getSimpleName().contentEquals("int")) lout.add(cl.cast(parseInteger(v)));
					if(cl.getSimpleName().contentEquals("Long") || cl.getSimpleName().contentEquals("long")) lout.add(cl.cast(parseLong(v)));
					if(cl.getSimpleName().contentEquals("Double") || cl.getSimpleName().contentEquals("double")) lout.add(cl.cast(parseDouble(v)));
					if(cl.getSimpleName().contentEquals("Boolean") || cl.getSimpleName().contentEquals("boolean")) lout.add(cl.cast(Boolean.parseBoolean(v)));
					if(cl.getSimpleName().contentEquals("String")) lout.add(cl.cast(v));
					if(cl.getSimpleName().contentEquals("HiveDecimal")) lout.add(cl.cast(parseHiveDecimal(v)));
					if(cl.getSimpleName().contentEquals("Date")) lout.add(cl.cast(sdf.parse(v)));
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				lout.add(null);
				LogManager.writeErrorToLog(className, "getList", "length:"+stringList.length+" Unable to parse the input: "+v+" Message is: "+e.getMessage());
				System.out.println("length:"+stringList.length+" Unable to parse the input: "+v+" Message is: "+e.getMessage());
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				lout.add(null);
				LogManager.writeErrorToLog(className, "getLost", "Unable to parse input to Date.");
				System.out.println("Unable to parse input to date. Input="+input);
				e.printStackTrace();
			}
		}
		//		System.out.println(" get List called:"+lout.toString());
		return lout;
	}
	

	// Edited to handle case where input is null and to print | in case where first value(s) in list are null
	private static<T> String getListData(List<T> input, Class<T> cl){
		StringBuilder sb = new StringBuilder("");
		String prepend = "";
		if(input == null) //TODO: Case if our whole list is null?
			return "";
		for(T val : input) {
			sb.append(prepend);
			if(val != null)
				sb.append((cl.getSimpleName().contentEquals("Date") ? sdf.format((Date)val) : val.toString()));	
			prepend = "|";
		}
		return sb.toString();
	}


	@SuppressWarnings("unchecked")
	// Edited to use helper methods instead of T.parseT()
	private static<T> T getDataNullsToMins(String input,Class<T> cl) {

		try {
			Object ob = null;
			if(cl.getSimpleName().contentEquals("Integer") || cl.getSimpleName().contentEquals("int")) ob = parseIntegerField(input);
			if(cl.getSimpleName().contentEquals("Long") || cl.getSimpleName().contentEquals("long")) ob = parseLongField(input);
			if(cl.getSimpleName().contentEquals("Double") || cl.getSimpleName().contentEquals("double")) ob = parseDoubleField(input);
			if(cl.getSimpleName().contentEquals("Boolean") || cl.getSimpleName().contentEquals("boolean")) ob = Boolean.parseBoolean(input);
			if(cl.getSimpleName().contentEquals("String")) ob = new String(input); //String null case will handle automatically
			if(cl.getSimpleName().contentEquals("HiveDecimal")) ob = parseHiveDecimalField(input);
			if(cl.getSimpleName().contentEquals("Date")) {
				if(input.contentEquals("")) return null;
				ob = sdf.parse(input);
			}
			if(ob != null) return (T)ob;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			LogManager.writeErrorToLog(className, "getData", "Number format exception"+e.getMessage());
			System.out.println("Number format exception"+e.getMessage());
			e.printStackTrace();
		} catch (ParseException e){
			LogManager.writeErrorToLog(className, "getData", "Error parsing date field: " + e.getMessage());
			System.out.println("Error parsing date field: " + e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block"Error parsing date field: " + e.getMessage()
			LogManager.writeErrorToLog(className, "getData", "Error in getData routine: " + e.getMessage());
			System.out.println("Error in getData routine: "+e.getMessage());
			e.printStackTrace();
		}	
		return null;
	}

	private static<T> T getData(String input,Class<T> cl) {

		try {
			Object ob = null;
			if(cl.getSimpleName().contentEquals("Integer") || cl.getSimpleName().contentEquals("int")) ob = parseInteger(input);
			if(cl.getSimpleName().contentEquals("Long") || cl.getSimpleName().contentEquals("long")) ob = parseLong(input);
			if(cl.getSimpleName().contentEquals("Double") || cl.getSimpleName().contentEquals("double")) ob = parseDouble(input);
			if(cl.getSimpleName().contentEquals("Boolean") || cl.getSimpleName().contentEquals("boolean")) ob = Boolean.parseBoolean(input);
			if(cl.getSimpleName().contentEquals("String")) ob = new String(input); //String null case will handle automatically
			if(cl.getSimpleName().contentEquals("HiveDecimal")) ob = parseHiveDecimal(input);
			if(cl.getSimpleName().contentEquals("Date")) {
				if(input.contentEquals("")) return null;
				ob = sdf.parse(input);
			}
			if(ob != null) return (T)ob;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			LogManager.writeErrorToLog(className, "getData", "Number format exception"+e.getMessage());
			System.out.println("Number format exception"+e.getMessage());
			e.printStackTrace();
		} catch (ParseException e){
			LogManager.writeErrorToLog(className, "getData", "Error parsing date field: " + e.getMessage());
			System.out.println("Error parsing date field: " + e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block"Error parsing date field: " + e.getMessage()
			LogManager.writeErrorToLog(className, "getData", "Error in getData routine: " + e.getMessage());
			System.out.println("Error in getData routine: "+e.getMessage());
			e.printStackTrace();
		}	
		return null;
	}

	//	private static String getFieldName(Method methodName){
	//		return (methodName.getName().substring(3,4).toLowerCase()+methodName.getName().substring(4));
	//	}


	private static String getListType(Field field) throws Exception{
		//		System.out.println("Simple List type field:"+ field.getType());
		//		System.out.println("Long List type:"+ field.getGenericType().toString());
		Pattern p = Pattern.compile(".*?<(.*)>");
		Matcher matcher = p.matcher(field.getGenericType().toString());

		if(matcher.find())
			return matcher.group(1);
		throw new Exception("Simple type:"+field.getType()+" Unable to get the List type:"+field.getGenericType().toString());
	}

	private static String getSetter(Field field){
		String name = field.getName();
		String set = setters.get(name);
		if(set != null) return set;
		set = "set"+name.substring(0,1).toUpperCase()+name.substring(1);
		setters.put(name, set);
		return set;
	}


	private static String getGetter(Field field){
		String name = field.getName();
		String get = getters.get(name);
		String type = field.getType().toString();
		if(get != null) return get;
		
		if(type.contains("boolean") || type.contains("Boolean")) {
			get = "is"+name.substring(0,1).toUpperCase()+name.substring(1);
		}else{
			get = "get"+name.substring(0,1).toUpperCase()+name.substring(1);
		}
		getters.put(name, get);
		return get;

	}



	// Helper methods for changing nulls to min values, and back
	private static Integer parseIntegerField(String input){
		input = input.trim();
		if(input.equalsIgnoreCase(""))
			return Integer.MIN_VALUE;
		return Integer.parseInt(input);
	}

	private static Double parseDoubleField(String input){
		input = input.trim();
		if(input.equalsIgnoreCase(""))
			return Double.MIN_VALUE;
		return Double.parseDouble(input);
	}

	private static Long parseLongField(String input){
		input = input.trim();
		if(input.equalsIgnoreCase(""))
			return Long.MIN_VALUE;
		return Long.parseLong(input);
	}

	private static HiveDecimal parseHiveDecimalField(String input){
		input = input.trim();
		if(input.equalsIgnoreCase(""))
			return HIVEDECIMAL_MIN;
		return HiveDecimal.create(input);
	}
	// Helper methods for parsing the data. Nulls will will be Nulls
	private static Integer parseInteger(String input){
		input = input.trim();
		if(input.equalsIgnoreCase(""))
			return null;
		return Integer.parseInt(input);
	}

	private static Double parseDouble(String input){
		input = input.trim();
		if(input.equalsIgnoreCase(""))
			return null;
		return Double.parseDouble(input);
	}

	private static Long parseLong(String input){
		input = input.trim();
		if(input.equalsIgnoreCase(""))
			return null;
		return Long.parseLong(input);
	}

	private static HiveDecimal parseHiveDecimal(String input){
		input = input.trim();
		if(input.equalsIgnoreCase(""))
			return null;
		return HiveDecimal.create(input);
	}

	
	public static<T> Object valNullCheck(T val){
		if(val == null || nullDefaults.contains(val.toString())){
			return null;
		}
		if(val.getClass().getSimpleName().contentEquals("HiveDecimal")){
			LogManager.writeDebugToLog(className, "valNullCheck", "bigdecimal value: "+val);
			return (Object)HiveDecimal.create(HiveDecimal.enforcePrecisionScale(((HiveDecimal) val).bigDecimalValue() , 17, 5));
		}
		else 
			return val;
	}

	
	public static<T> List<?> parseListForNulls(List<T> vals){
		if(vals == null) return null;
		else{
			boolean bigDecimalType = false;

			for(int i = 0; i < vals.size(); i++){
				//	System.out.println("vals"+vals+"size"+vals.size()+"i"+i);
				if(vals.get(i) != null){
					if( nullDefaults.contains(vals.get(i).toString())) vals.set(i, null);				
					if(vals.get(i) != null)
						if(vals.get(i).getClass().getSimpleName().contentEquals("HiveDecimal")) 
							bigDecimalType = true;
				}
			}
			if(!bigDecimalType) return vals;
			else{
				LogManager.writeDebugToLog(className, "parseListForNulls", "Found bigdecimal list: "+vals);
				List<HiveDecimal> hiveDeciList = new ArrayList<HiveDecimal>();
				for(T v: vals)
					hiveDeciList.add(((v != null)?HiveDecimal.create(HiveDecimal.enforcePrecisionScale(((HiveDecimal) v).bigDecimalValue() , 17, 5)) : null));
				return hiveDeciList;
			}
		}
		
	}

}
