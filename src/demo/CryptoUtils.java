package demo;

import java.util.HashMap;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


	/**
	 * ������Ϣ���ܽ���
	 * Created by IntelliJ IDEA.
	 * User: wangpeng
	 * Date: 2016/12/9
	 * Time: 17:14
	 */
	public class CryptoUtils {

	    // ��־
	    private static final Logger log = LoggerFactory.getLogger(CryptoUtils.class);

	    public final static String SYS_CAR = "carLoan";
	    public final static String DBNAME_CAR = "loancj";

	    public final static String SYS = "srdbloan";
	    public final static String DBNAME = "loanxj";

	    private static long now = System.nanoTime();

	    /**
	     * �����ֻ���
	     * @param mphones
	     * @return
	     */
	    public static String encryptPhones(String mphones, String tabname, String enccols){
	        JSONObject json = encrypt("", "", "", "", now + "",
	                "", mphones, "", "", tabname, enccols);
	        return (String) json.get("MPHONES");
	    }

	    /**
	     * �����ֻ���
	     * @param mphones
	     * @return
	     */
	    public static String decryptPhones(String mphones, String tabname, String enccols){
	        JSONObject json = decrypt("", "", "", "", now + "",
	                "", mphones, "", "", tabname, enccols);
	        String result = (String) json.get("MPHONES");
	        if(result != null){
	            return result.substring(0, result.length()-1);
	        }
	        return "";
	    }

	    public static String getEncryptStr(String name, String flag, String ids, String userCode, String createby,
	                                 String objSig, String mphones, String psCode,
	                                 String tphones, String tabname, String enccols){
	        JSONObject json = encrypt("", "", "", "", now + "",
	                "", mphones, "", "", tabname, enccols);
	        return (String) json.get(name);
	    }

	    public static String getDecryptStr(String name, String flag, String ids, String userCode, String createby,
	                                       String objSig, String mphones, String psCode,
	                                       String tphones, String tabname, String enccols){
	        JSONObject json = decrypt("", "", "", "", now + "",
	                "", mphones, "", "", tabname, enccols);
	        return (String) json.get(name);
	    }

	    /**
	     * �����ֻ���
	     * @param mphones
	     * @return
	     */
	    public static JSONObject encrypt(String flag, String ids, String userCode, String createby,
	                                String objSig, String mphones, String psCode,
	                                String tphones, String tabname, String enccols){
	        return encrypt(flag, ids, userCode, createby, now + "",
	                objSig, mphones, psCode, tphones, tabname, enccols);
	    }

	    /**
	     *
	     * @param mphones
	     * @return
	     */
	    public static JSONObject decrypt(String flag, String ids, String userCode, String createby,
	                                String objSig, String mphones, String psCode,
	                                String tphones, String tabname, String enccols){
	        return decrypt(flag, ids, userCode, createby, now + "",
	                objSig, mphones, psCode, tphones, tabname, enccols);
	    }

	    /**
	     *
	     * @param flag
	     * @param ids
	     * @param userCode
	     * @param createby
	     * @param createTime
	     * @param objSig
	     * @param mphones
	     * @param psCode
	     * @param tphones
	     * @param tabname
	     * @param enccols
	     * @return
	     */
	    private static JSONObject encrypt(String flag, String ids, String userCode, String createby,
	                                 String createTime, String objSig, String mphones, String psCode,
	                                 String tphones, String tabname, String enccols){
	        try {
	            return crypto(0, flag, ids, userCode, createby, createTime, objSig, mphones, psCode, tphones, tabname, enccols);
	        } catch (Exception ex) {
	            log.error("����������Ϣ���ܳ����ˡ�����", ex);
	            log.error("������flag = [" + flag + "], ids = [" + ids + "], userCode = [" + userCode + "], createby = ["
	                    + createby + "], createTime = [" + createTime + "], objSig = [" + objSig + "], mphones = [" + mphones + "], psCode = ["
	                    + psCode + "], tphones = [" + tphones + "], tabname = [" + tabname + "], enccols = [" + enccols + "]");
	        }
	        return null;
	    }

	    /**
	     *
	     * @param flag
	     * @param ids
	     * @param userCode
	     * @param createby
	     * @param createTime
	     * @param objSig
	     * @param mphones
	     * @param psCode
	     * @param tphones
	     * @param tabname
	     * @param enccols
	     * @return
	     */
	    private static JSONObject decrypt(String flag, String ids, String userCode, String createby,
	                                  String createTime, String objSig, String mphones, String psCode,
	                                  String tphones, String tabname, String enccols){
	        try {
	            return crypto(1, flag, ids, userCode, createby, createTime, objSig, mphones, psCode, tphones, tabname, enccols);
	        } catch (Exception ex) {
	            log.error("����������Ϣ���ܳ����ˡ�����", ex);
	            log.error("������flag = [" + flag + "], ids = [" + ids + "], userCode = [" + userCode + "], createby = ["
	                    + createby + "], createTime = [" + createTime + "], objSig = [" + objSig + "], mphones = [" + mphones + "], psCode = ["
	                    + psCode + "], tphones = [" + tphones + "], tabname = [" + tabname + "], enccols = [" + enccols + "]");

	        }
	        return null;
	    }


	    private static JSONObject crypto(int fg, String flag, String ids, String userCode, String createby,
	                                     String createTime, String objSig, String mphones, String psCode, String tphones,
	                                     String tabname, String enccols) throws Exception{
	        try {
	            HashMap<String, String> map = new HashMap<String, String>();
	            map.put("flg", flag); //�ݿ�
	            map.put("SYS", tabname.toUpperCase().contains("JK") ? SYS : SYS_CAR);//������ܵ�ϵͳ
	            map.put("IDS", ids);//�û�ID
	            map.put("USERCODE", userCode);//�û���ǡ��û�����
	            map.put("CREATEUSER", createby); //������
	            map.put("CREATTIME", createTime);//����ʱ�� ����long����
	            map.put("OBJSIG", objSig);//�û������ֶ�
	            map.put("MPHONES", mphones+",");//�ֻ��� ԭ�� 13716396384,13716396385,
	            map.put("PSLCODE", psCode);//֤����
	            map.put("TPHONES", tphones);//�̶��绰��
	            map.put("DBNAME", tabname.toUpperCase().contains("JK") ? DBNAME : DBNAME_CAR); //������
	            map.put("TABNAME", tabname);//������
	            map.put("ENCCOLS", enccols);//�����ֶ� ��������ֶα���ʹ�ð�Ƕ��ŷָ���
	            String str = JSON.toJSONString(map);
	            //���ܶ���
	            TcpClient client = new TcpClient();
	            //���ܽ��
	            String strRe = "";
	            if(fg == 0){
	                strRe = client.disEncrypt(str);
	            }
	            if(fg == 1){
	                strRe = client.disDecrypt(str);
	            }
	            return JSON.parseObject(strRe);
	        } catch (Exception ex) {
	            throw ex;
	        }
	    }

	}

}
