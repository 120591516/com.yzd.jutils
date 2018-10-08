package com.yzd.jutils.bean;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.yzd.jutils.print.PrintUtil;

/**
 * Created by zd.yao on 2017/6/15.
 */
public class BeanToMapUtilsTest {
    /**
     * JavaBean与Map相互转换
     * https://my.oschina.net/sodeve/blog/533933
     * @param args
     */
    public static void main(String[] args) {
        Person person=new Person();
        person.setAge("1");
        person.setName("name");
        Map<String,Object> map= BeanToMapUtils.convertBean(person);
        PrintUtil.outLn(map);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("age1", "1");
		dataMap.put("name", "name");
		Person Person = (Person) BeanToMapUtils.convertMap(Person.class, dataMap);
		System.out.println(JSONObject.toJSON(Person));
    }
}
