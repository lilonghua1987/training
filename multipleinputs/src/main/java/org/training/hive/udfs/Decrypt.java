package org.training.hive.udfs;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;
@Description(name = "decrypt", value = "_FUNC_(x) - returns returns an decrypted value of the text "
	    + "NULL otherwise", extended = "Example:\n"
	    + "  > SELECT _FUNC_(\"~Wm\") FROM src LIMIT 1;\n" + "FIM\n")
public class Decrypt  extends UDF{
	public Text evaluate(final Text input){
		byte[] i = input.getBytes();
		byte[] key = "12345678".getBytes();
		byte[] res = new byte[i.length];
		for(int k = 0; k < i.length; k++) 
			res[k] = (byte) (i[k] ^ key[k%key.length]);
		
		return new Text(res);
	}
}
