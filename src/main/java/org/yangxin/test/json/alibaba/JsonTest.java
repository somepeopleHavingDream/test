package org.yangxin.test.json.alibaba;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yangxin
 * 2021/7/6 14:50
 */
@SuppressWarnings("CommentedOutCode")
class JsonTest {

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
        test5();
    }

    private static void test5() {
        List<String> fieldList = new ArrayList<>();
        fieldList.add("操作人");
        fieldList.add("应用标题");
        fieldList.add("应用内容");

        JSONArray jsonArray = new JSONArray();
        for (String field : fieldList) {
            if (StringUtils.isBlank(field)) {
                continue;
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put(field, "固定值");
            jsonArray.add((jsonObject));
        }

        System.out.println(jsonArray.toJSONString());
    }

    private static void test4() {
        String json = "[\n" +
                "    {\n" +
                "        \"key\": \"title\",\n" +
                "        \"value\": \"养成打卡\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"key\": \"fromUserName\",\n" +
                "        \"value\": \"谢老师\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"key\": \"content\",\n" +
                "        \"value\": \"123\\n21天，每天\\n123\"\n" +
                "    }\n" +
                "]";
        List<Entry> entryList = JSON.parseArray(json, Entry.class);
        System.out.println(entryList);

        JSONObject jsonObject = new JSONObject();
        for (Entry entry : entryList) {
            if (entry == null) {
                continue;
            }

            jsonObject.put(entry.getKey(), entry.getValue());
        }

        System.out.println(jsonObject);
    }

    private static void test3() {
        String json = "[\n" +
                "    [\n" +
                "        {\n" +
                "            \"key\": \"fromUserId\",\n" +
                "            \"value\": \"郭海玲老师\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"time\",\n" +
                "            \"value\": \"07-20\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"title\",\n" +
                "            \"value\": \"研讨会\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"place\",\n" +
                "            \"value\": \"（本部）会议室1\"\n" +
                "        }\n" +
                "    ],\n" +
                "    [\n" +
                "        {\n" +
                "            \"key\": \"fromUserId\",\n" +
                "            \"value\": \"陈专明老师\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"time\",\n" +
                "            \"value\": \"06-29\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"title\",\n" +
                "            \"value\": \"测试\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"key\": \"place\",\n" +
                "            \"value\": \"（本部）纳斯达克\"\n" +
                "        }\n" +
                "    ]\n" +
                "]";
        List<Entry[]> entryList = JSON.parseArray(json, Entry[].class);
        for (Entry[] entryArray : entryList) {
            for (Entry entry : entryArray) {
                System.out.println(entry);
            }
        }

        List<List<Entry>> list = new ArrayList<>();
        List<Entry> entryList1 = new ArrayList<>();
        entryList1.add(new Entry("fromUserId", "郭海玲老师"));
        entryList1.add(new Entry("time", "07-20"));
        entryList1.add(new Entry("title", "研讨会"));
        entryList1.add(new Entry("place", "（本部）会议室1"));

        List<Entry> entryList2 = new ArrayList<>();
        entryList2.add(new Entry("fromUserId", "陈专明老师"));
        entryList2.add(new Entry("time", "06-29"));
        entryList2.add(new Entry("title", "测试"));
        entryList2.add(new Entry("place", "（本部）纳斯达克"));

        list.add(entryList1);
        list.add(entryList2);
        System.out.println(JSON.toJSONString(list));
    }

    private static void test2() {
        StreamsDTO dto = new StreamsDTO();
        dto.setDate(new Date());

        String s = JSON.toJSONString(dto);
        System.out.println(s);
        System.out.println(JSON.parseObject(s, StreamsDTO.class));
    }

    /**
     * 嵌套json解析
     */
    private static void test1() {
        String json = "{\n" +
                "    \"code\": 200,\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"deviceId\": null,\n" +
                "            \"name\": \"\",\n" +
                "            \"status\": null,\n" +
                "            \"steams\": {\n" +
                "                \"flv\": \"https://p13092.monitor.cmii.aishangxue.online:1939/flv/s1000992/4014b00c9721135617074cfb5e3cb94a?expire=1625555476&uid=123&md5=0wzy4Lj-a0CmeeQrfkuLtQ\",\n" +
                "                \"hls\": \"\",\n" +
                "                \"legacy\": \"\",\n" +
                "                \"preview\": \"\",\n" +
                "                \"rtmp\": \"\",\n" +
                "                \"url\": \"\"\n" +
                "            },\n" +
                "            \"thirdDeviceId\": \"8540\",\n" +
                "            \"url\": \"\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"message\": \"成功\"\n" +
                "}";

        JSONObject jsonObject = JSON.parseObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("data");

        List<DeviceStreamBO> deviceStreamBOList = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject tmpJsonObject = jsonArray.getJSONObject(0);
            int deviceId = tmpJsonObject.getIntValue("deviceId");
            String name = tmpJsonObject.getString("name");
            int status = tmpJsonObject.getIntValue("status");

            JSONObject streamsJsonObject = tmpJsonObject.getJSONObject("steams");
            StreamsDTO streamsDTO = streamsJsonObject.toJavaObject(StreamsDTO.class);

            DeviceStreamBO deviceStreamBO = DeviceStreamBO.builder()
                    .deviceId(deviceId)
                    .name(name)
                    .status(status)
                    .streams(streamsDTO)
                    .build();
            deviceStreamBOList.add(deviceStreamBO);
        }

        System.out.println(deviceStreamBOList);
    }
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class DeviceStreamBO implements Serializable {

    private static final long serialVersionUID = -5739676644351841517L;

    private Integer deviceId;

    private String name;

    private Integer status;

    private StreamsDTO streams;
}

@Data
class StreamsDTO implements Serializable {

    private static final long serialVersionUID = 5555281882323132023L;

    private String flv;

    private String hls;

    private String legacy;

    private String preview;

    private String rtmp;

    private String url;

    private Date date;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Entry implements Serializable {

    private static final long serialVersionUID = 3288756381935636515L;

    private String key;

    private String value;
}