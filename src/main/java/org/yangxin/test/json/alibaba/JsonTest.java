package org.yangxin.test.json.alibaba;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yangxin
 * 2021/7/6 14:50
 */
class JsonTest {

    public static void main(String[] args) {
//        test1();
        test2();
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