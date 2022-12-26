package com.wzq.springclouduserconsumer.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSON;
import org.bouncycastle.asn1.ocsp.ResponseData;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class SentinelExceptionHandler  implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        Map<String,Object> info=new HashMap<>();
        if (e instanceof FlowException) {
            info.put("-1", "流控规则被触发......");
        } else if (e instanceof DegradeException) {
            info.put("-2", "降级规则被触发...");
        } else if (e instanceof AuthorityException) {
            info.put("-3", "授权规则被触发...");
        } else if (e instanceof ParamFlowException) {
            info.put("-4", "热点规则被触发...");
        } else if (e instanceof SystemBlockException) {
            info.put("-5", "系统规则被触发...");
        }
        httpServletResponse.setHeader("content-type","application/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(info));
    }
}
