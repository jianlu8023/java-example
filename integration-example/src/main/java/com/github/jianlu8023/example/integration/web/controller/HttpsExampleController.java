package com.github.jianlu8023.example.integration.web.controller;


import com.alibaba.fastjson.*;
import com.alibaba.fastjson.serializer.*;
import com.github.jianlu8023.format.response.*;
import com.github.jianlu8023.format.response.ResponseStatus;
import org.slf4j.*;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import java.util.*;

@RequestMapping("https")
@RestController
public class HttpsExampleController {
    private static final Logger L = LoggerFactory.getLogger(HttpsExampleController.class);

    @RequestMapping(value = "rec", method = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
    public ApiResponse<Object> rec(
            HttpServletRequest request,
            @RequestParam("ip") String ip,
            @RequestParam("mac") String mac) {
        L.debug("request info \n-----------------------\n{}\n-----------------------", JSONObject.toJSONString(new HashMap<String, Object>() {{
            put("User-Agent", request.getHeader("User-Agent"));
            put("Content-Type", request.getHeader("Content-Type"));
            put("Content-Length", request.getHeader("Content-Length"));
            put("Form", request.getRemoteAddr());
            put("Params", new HashMap<String, Object>() {{
                put("ip", ip);
                put("mac", mac);
            }});
        }}, SerializerFeature.PrettyFormat));
        return ApiResponse.success(ResponseStatus.SUCCESS, "ok");
    }
}
