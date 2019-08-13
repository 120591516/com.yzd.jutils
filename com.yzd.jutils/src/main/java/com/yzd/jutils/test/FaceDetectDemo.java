package com.yzd.jutils.test;

import com.alibaba.fastjson.JSONObject;
import com.yzd.jutils.httpExt.HttpUtil;
import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URL;
import java.util.*;

public class FaceDetectDemo {

    private static final String CONTENT_CHARSET = "UTF-8";
    private static final String secret = "123";
    private static final String appKey = "321";
    private static String uri = "/ai-cloud-face/face/tool/detect";

    public static void main(String[] args) {
        try {
            Map<Object, Object> parameters = new HashMap<>();
            String nonceStr = "12345678";
            parameters.put("appKey", appKey);
            parameters.put("nonceStr", nonceStr);
            parameters.put("img", Base64.getEncoder().encodeToString(IOUtils.toByteArray(new URL("http://39.105.143.187:40874/upload/face/ce8d342a-66f0-49c8-9246-4020e81e90d3.jpg").openStream())));
            parameters.put("sign", createSign(parameters));
            System.out.println(JSONObject.toJSON(parameters));
//            HttpUtil.post("https://api-ai.cloudwalk.cn" + uri, JSONObject.toJSONString(parameters));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String createSign(Map<Object, Object> parameters) throws Exception {
        SortedMap<Object, Object> parametersForSign = new TreeMap<>();
        parametersForSign.put("uri", uri);
        parametersForSign.putAll(parameters);
        StringBuilder sb = new StringBuilder();
        Set es = parametersForSign.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                if (!it.hasNext()) {
                    sb.append(k).append("=").append(v);
                } else {
                    sb.append(k).append("=").append(v).append("&");
                }
            }
        }
        Mac mac = Mac.getInstance("HmacSHA1");
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes("UTF-8"), mac.getAlgorithm());
        mac.init(secretKey);
        byte[] hash = mac.doFinal(sb.toString().getBytes(CONTENT_CHARSET));
        return new BASE64Encoder().encode(hash);
    }
}