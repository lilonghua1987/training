package org.training.utils;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

//import org.americanexpress.rdre.entities.QualifierMappings;
//import org.americanexpress.rdre.entities.SourceField;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.api.MetaException;


public class Utils {


	private static HashMap<String,Method[]> orderInfo = new HashMap<String,Method[]>();
	
    public static byte[] serialize(Object obj) {
        try {
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bout);
			out.writeObject(obj);
			return bout.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not dserialize object: "+DataParser.toString(obj));
			e.printStackTrace();
			return null;
		}
    }

    public static Object deserialize(byte[] bytes) {
        try {
			ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
			ObjectInputStream in = new ObjectInputStream(bin);
			return in.readObject();
		} catch (ClassNotFoundException| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Unable to Deserialize bytes: "+bytes);
			return null;
		}
    }

	public static int convertToJulian(int date, int month, int year){
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, date);
		Date dt = calendar.getTime();
		return convertToJulian(dt);
	}


	public static int convertToJulian(Date date){
		try{ 
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int year = calendar.get(Calendar.YEAR);
			int julian = Integer.parseInt(String.format("%d%03d",year,calendar.get(Calendar.DAY_OF_YEAR)));
			return julian;
		}
		catch(NullPointerException e){
			return Integer.MIN_VALUE;		
		}
	}

	public static int getMonthFromJulian(int JulianDate){
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(getDateFromJulian7(JulianDate));
			return calendar.get(Calendar.MONTH);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Integer.MIN_VALUE;
		}

	}

	public static int getYearFromJulian(int JulianDate){
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(getDateFromJulian7(JulianDate));
			return calendar.get(Calendar.YEAR);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Integer.MIN_VALUE;
		}

	}

	public static int getDayFromJulian(int JulianDate){
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(getDateFromJulian7(JulianDate));
			return calendar.get(Calendar.DAY_OF_MONTH);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Integer.MIN_VALUE;
		}

	}

	public static Date getDateFromJulian7(Integer julianDate) throws ParseException{
		return new SimpleDateFormat("yyyyD").parse(julianDate.toString());
	}

	public static Integer dateDifference(Integer julianDate1,Integer julianDate2) throws ParseException{
		/*Date d1 = getDateFromJulian7(julianDate1);
		Date d2 = getDateFromJulian7(julianDate2);
		Long diff = d1.getTime() - d2.getTime();
		return diff.intValue();*/
		
		return (julianDate1-julianDate2);
	}

	public static Integer adjustJulianDate(Integer julianDate, Integer daysDiff) throws ParseException{
		Calendar c = Calendar.getInstance();
		c.setTime(getDateFromJulian7(julianDate));
		c.add(Calendar.DAY_OF_YEAR, daysDiff);
		return convertToJulian(c.getTime());
	}

//	public static boolean callAllPublicMethods(Class<?> cl,Object... obj){
//		Method[] methods;
//		if(orderInfo.get(cl.getSimpleName()) == null){
//			methods = cl.getMethods();
//			
//			Arrays.sort(methods, new Comparator<Method>() {
//				@Override
//				public int compare(Method o1, Method o2) {
//					Order or1 = o1.getAnnotation(Order.class);
//					Order or2 = o2.getAnnotation(Order.class);
//					// nulls last
//					if (or1 != null && or2 != null) {
//						return or1.value() - or2.value();
//					} else
//						if (or1 != null && or2 == null) {
//							return -1;
//						} else
//							if (or1 == null && or2 != null) {
//								return 1;
//							}
//					return o1.getName().compareTo(o2.getName());
//				}
//			});
//			orderInfo.put(cl.getSimpleName(), methods);
//		}else
//			methods = orderInfo.get(cl.getSimpleName());
//
//		for(Method method: methods) {
//
//			try {
//				//				System.out.println("method name:"+method.getName()+"modifier: "+method.getModifiers());				
//				if(method.getModifiers() == 9) method.invoke(null,obj);
//		
//
//			} catch (Exception e) {
//				System.err.println("Unable to call the Update logic method :"+method.getName()+" modifier: "+method.getModifiers()+" exception: "+e.getMessage());
//				e.printStackTrace();
//			//	return false;
//			}
//		}
//		return true;
//
//	}	
//	/**
//	 * Helper method. If the passed in string can be converted to an integer, retun the converted value
//	 * else return null
//	 * @param integer
//	 * @return
//	 */
//	public static Integer tryParseInteger(String integer){
//		if(integer.matches("(\\+|-)?[0-9]+")){
//			try{
//				return Integer.parseInt(integer);
//			}
//			catch(NumberFormatException e){
//				return null;
//			}
//		}
//		else{
//			return null;
//		}
//	}
//	/***************************************************************************************************************/

	public static ResultSet readHiveTable(String tableName,String dbname,Connection conHolder) throws MetaException, InstantiationException, IllegalAccessException{
		HiveConf conf = new HiveConf(); // should pull hive-site.xml automatically
		Connection conn = null;
		ResultSet rs;
		try {
			conf.addResource(new Path("/opt/mapr/hive/hive/conf/hive-site.xml"));
			Class.forName("org.apache.hive.jdbc.HiveDriver");

			String url1 = "jdbc:hive2://gold-hiveserver.db.aexp.com:10000/";
			String url2 = url1+dbname;
			conn = DriverManager.getConnection(
					url2,
					"aiyenger",
					"kJQw28tt");

			Statement st = conn.createStatement();
			rs = st.executeQuery("select * from "+tableName);
			conHolder = conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("sql exception:"+e.getMessage());
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Class not found:"+e.getMessage());
			return null;
		}
		return rs;
	}

	public static<T> T readXML(Class<T> cl,String xmlFile){
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(cl);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			@SuppressWarnings("unchecked")
			T xmlObject =  (T) jaxbUnmarshaller.unmarshal(new File(xmlFile));
			return xmlObject;
		}
		catch (Exception e){
			System.out.println("Error reading configuration file:");
			System.out.println(e.getMessage());
			return null;
		}
	}	

//	public static<T> void generateMappingsFile(Class<T> cl,String xmlFile){
//		try {
//			System.out.println("Starting...");
//			JAXBContext jaxbContext = JAXBContext.newInstance(QualifierMappings.class);
//			Marshaller jaxbmarshaller = jaxbContext.createMarshaller();
//			QualifierMappings qm = new QualifierMappings();
//			ArrayList<SourceField> l = new ArrayList<SourceField>();
//			Integer i = 0;
//			System.out.println("Loopingss...");
//
//			for(Field field: cl.getDeclaredFields()) {
//				System.out.println("..."+field.getName());
//				SourceField sf = new SourceField();
//				sf.setFieldName(field.getName());
//				sf.setQualifierValue(i.toString());
//				i++;
//				l.add(sf);
//			}
//			qm.setFieldsList(l);
//			System.out.println("done reading the complete fields");
//			jaxbmarshaller.marshal(qm,new File(xmlFile));
////			return xmlObject;
//		}
//		catch (Exception e){
//			System.out.println("Error reading configuration file:");
//			System.out.println(e.getMessage());
////			return null;
//		}
//	}


}
