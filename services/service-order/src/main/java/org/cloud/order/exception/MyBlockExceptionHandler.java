package org.cloud.order.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.cloud.model.common.R;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;

/**
 * @author SuZiPing
 * @version 1.0
 */
@Component
public class MyBlockExceptionHandler implements BlockExceptionHandler {
    private ObjectMapper objectMapper= new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, String resourceName, BlockException e) throws Exception {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(429); // 429 Too Many Requests
        PrintWriter writer = response.getWriter();
        R error = R.error("访问被限流: " + e.getClass().getSimpleName(), null, 429);
        String json = objectMapper.writeValueAsString(error);
        writer.write(json);
        writer.flush();
    }
}
