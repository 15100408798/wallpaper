package com.yushang.wallpaper.weixin.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebUtils {
	
	private static final String basePath = "https://app.loadingtech.cn/bicycle";//正式地址
	
	public static String getBasePath() {
		return basePath;
	}
	
	private static Logger logger = LoggerFactory.getLogger(WebUtils.class);

	/**
	 * 随机字符串
	 * @return
	 */
	public static String generate() {
		return UUID.randomUUID().toString().trim().replaceAll("-", "");
	}

	/**
	 * @param response 
	 * @Description: 获取客户端真实IP地址 
	 * @throws
	 */
	public static String getIpAddress(HttpServletRequest request) {
		// 避免反向代理不能获取真实地址, 取X-Forwarded-For中第一个非unknown的有效IP字符串
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
    /**
	 * 
	 * 方法用途: 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序），并且生成url参数串<br>
	 * 实现步骤: <br>
	 * 
	 * @param paraMap     要排序的Map对象
	 * @param urlEncode   是否需要URLENCODE
	 * @param keyToLower  是否需要将Key转换为全小写 true:key转化成小写，false:不转化
	 * @return
	 */
	public static String formatUrlMap(Map<String, String> paraMap, boolean urlEncode, boolean keyToLower) {
		String buff = "";
		Map<String, String> tmpMap = paraMap;
		try {
			List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(tmpMap.entrySet());
			// 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
			Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
				@Override
				public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
					return (o1.getKey()).toString().compareTo(o2.getKey());
				}
			});
			// 构造URL 键值对的格式
			StringBuilder buf = new StringBuilder();
			for (Map.Entry<String, String> item : infoIds) {
				if (StringUtils.isNotBlank(item.getKey())) {
					String key = item.getKey();
					String val = item.getValue();
					if (urlEncode) {
						val = URLEncoder.encode(val, "utf-8");
					}
					if (keyToLower) {
						buf.append(key.toLowerCase() + "=" + val);
					} else {
						buf.append(key + "=" + val);
					}
					buf.append("&");
				}

			}
			buff = buf.toString();
			if (buff.isEmpty() == false) {
				buff = buff.substring(0, buff.length() - 1);
			}
		} catch (Exception e) {
			return null;
		}
		return buff;
	}

	
	/**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, String> doXMLParse(String strxml) throws Exception {
		if (null == strxml || "".equals(strxml)) {
			return null;
		}

		Map<String, String> m = new HashMap<String, String>();
		InputStream in = String2Inputstream(strxml);
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(in);
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if (children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = getChildrenText(children);
			}

			m.put(k, v);
		}

		// 关闭流
		in.close();

		return m;
	}
	
	private static InputStream String2Inputstream(String str) {
		return new ByteArrayInputStream(str.getBytes());
	}
	


	/**
	 * 获取子结点的xml
	 */
	@SuppressWarnings("rawtypes")
	private static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if (!children.isEmpty()) {
			Iterator it = children.iterator();
			while (it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if (!list.isEmpty()) {
					sb.append(getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}

		return sb.toString();
	}

	
	/**
     * 获取一定长度的随机字符串
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    
    
    /**
	 * 方法名: getRemotePortData 
	 * 描述: 发送远程请求 获得代码示例 
	 * 参数： @param urls 访问路径 
	 * 创建时间: 2017年3月6日 下午3:20:32 
	 * 版本号: v1.0 
	 * 返回类型: String
	 */
	public static String getRemotePortData(String urls, String param) throws Exception {
		try {
			URL url = new URL(urls);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置连接超时时间
			conn.setConnectTimeout(30000);
			// 设置读取超时时间
			conn.setReadTimeout(30000);
			conn.setRequestMethod("POST");
			if (StringUtils.isNotBlank(param)) {
				conn.setRequestProperty("Origin", "https://sirius.searates.com");// 主要参数
				conn.setRequestProperty("Referer",
						"https://sirius.searates.com/cn/port?A=ChIJP1j2OhRahjURNsllbOuKc3Y&D=567&G=16959&shipment=1&container=20st&weight=1&product=0&request=&weightcargo=1&");
				conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");// 主要参数
			}
			// 需要输出
			conn.setDoInput(true);
			// 需要输入
			conn.setDoOutput(true);
			// 设置是否使用缓存
			conn.setUseCaches(false);
			// 设置请求属性
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			conn.setRequestProperty("Charset", "UTF-8");

			if (StringUtils.isNotBlank(param)) {
				// 建立输入流，向指向的URL传入参数
				DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
				dos.writeBytes(param);
				dos.flush();
				dos.close();
			}
			// 输出返回结果
			InputStream input = conn.getInputStream();
			int resLen = 0;
			byte[] res = new byte[1024];
			StringBuilder sb = new StringBuilder();
			while ((resLen = input.read(res)) != -1) {
				sb.append(new String(res, 0, resLen));
			}
			System.out.println(sb.toString());
			return sb.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			logger.info("港距查询抓取数据----抓取外网港距数据发生异常：" + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("港距查询抓取数据----抓取外网港距数据发生异常：" + e.getMessage());
		}
		logger.info("港距查询抓取数据----抓取外网港距数据失败, 返回空字符串");
		return "";
	}
	
	/**
	 * 向指定 URL 发送POST方法的请求
	 *
	 * @param url 发送请求的 URL
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, Map<String, ?> paramMap) {
	    PrintWriter out = null;
	    BufferedReader in = null;
	    String result = "";
	 
	    String param = "";
	    Iterator<String> it = paramMap.keySet().iterator();
	 
	    while (it.hasNext()) {
	        String key = it.next();
	        param += key + "=" + paramMap.get(key) + "&";
	    }
	 
	    try {
	        URL realUrl = new URL(url);
	        // 打开和URL之间的连接
	        URLConnection conn = realUrl.openConnection();
	        // 设置通用的请求属性
	        conn.setRequestProperty("accept", "*/*");
	        conn.setRequestProperty("connection", "Keep-Alive");
	        conn.setRequestProperty("Accept-Charset", "utf-8");
	        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	        // 发送POST请求必须设置如下两行
	        conn.setDoOutput(true);
	        conn.setDoInput(true);
	        // 获取URLConnection对象对应的输出流
	        out = new PrintWriter(conn.getOutputStream());
	        // 发送请求参数
	        out.print(param);
	        // flush输出流的缓冲
	        out.flush();
	        // 定义BufferedReader输入流来读取URL的响应
	        in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	        String line;
	        while ((line = in.readLine()) != null) {
	            result += line;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    //使用finally块来关闭输出流、输入流
	    finally {
	        try {
	            if (out != null) {
	                out.close();
	            }
	            if (in != null) {
	                in.close();
	            }
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }
	    return result;
	}

}
