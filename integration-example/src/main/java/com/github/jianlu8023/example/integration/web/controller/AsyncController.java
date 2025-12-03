package com.github.jianlu8023.example.integration.web.controller;


import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.*;

import javax.servlet.http.*;
import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.time.*;
import java.util.*;
import java.util.concurrent.*;

@RestController
@RequestMapping("/async")
public class AsyncController {
    private static final Logger L = LoggerFactory.getLogger(AsyncController.class);
    private static final Map<String, SseEmitter> EMITTER_MAP = new ConcurrentHashMap<>();


    @GetMapping("/bodyEmitter")
    public ResponseBodyEmitter bodyEmitter() {
        // 创建一个ResponseBodyEmitter，-1代表不超时
        ResponseBodyEmitter emitter = new ResponseBodyEmitter(-1L);
        // 异步执行耗时操作
        CompletableFuture.runAsync(() -> {
            try {
                // 模拟耗时操作
                for (int i = 0; i < 100; i++) {
                    L.debug("bodyEmitter {}", i);
                    // 发送数据
                    emitter.send("bodyEmitter " + i + " @ " + new Date() + "\n");
                    Thread.sleep(2000);
                }
                // 完成
                emitter.complete();
            } catch (Exception e) {
                // 发生异常时结束接口
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }


    @GetMapping("/subSseEmitter/{userId}")
    public SseEmitter sseEmitter(@PathVariable String userId) {
        L.info("sseEmitter: {}", userId);
        SseEmitter emitterTmp = new SseEmitter(-1L);
        EMITTER_MAP.put(userId, emitterTmp);
        CompletableFuture.runAsync(() -> {
            try {
                SseEmitter.SseEventBuilder event = SseEmitter.event()
                        .data("sseEmitter" + userId + " @ " + LocalTime.now())
                        .id(String.valueOf(userId))
                        .name("sseEmitter");
                emitterTmp.send(event);
            } catch (Exception ex) {
                emitterTmp.completeWithError(ex);
            }
        });
        return emitterTmp;
    }

    @GetMapping("/sendSseMsg/{userId}")
    public void sseEmitter(@PathVariable String userId, String msg) throws IOException {
        SseEmitter sseEmitter = EMITTER_MAP.get(userId);
        if (sseEmitter == null) {
            return;
        }
        sseEmitter.send(msg);
    }

    @GetMapping("/streamingResponse")
    public ResponseEntity<StreamingResponseBody> handleRbe() {

        StreamingResponseBody stream = out -> {
            String message = "streamingResponse";
            for (int i = 0; i < 100; i++) {
                try {
                    out.write(((message + i) + "\r\n").getBytes());
                    out.write("\r\n".getBytes());
                    //调用一次flush就会像前端写入一次数据
                    out.flush();
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(stream);
    }

    @GetMapping("/streamingDownload")
    public ResponseEntity<StreamingResponseBody> downloadFile() throws IOException {
        // 1. 生成临时文件并写入 "Hello World"
        File tempFile = File.createTempFile(UUID.randomUUID().toString(), ".txt");
        System.out.println(tempFile.getName());
        tempFile.deleteOnExit();
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write("Hello World".getBytes());
        }

        // 2. 设置响应头信息
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", String.format("attachment; filename=%s", tempFile.getName()));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        // 3. 使用StreamingResponseBody来实现文件的流式下载
        StreamingResponseBody stream = outputStream -> {
            Path path = Paths.get(tempFile.getAbsolutePath());
            Files.copy(path, outputStream);
            outputStream.flush();
        };

        // 4. 返回响应实体
        return ResponseEntity.ok().headers(headers).body(stream);
    }

    // public ResponseEntity<Object> streamingDownload(HttpServletResponse response) {
    //
    //     try {
    //         final File tempFile = File.createTempFile(UUID.randomUUID().toString(), ".txt");
    //
    //         tempFile.deleteOnExit();
    //
    //         download(response, tempFile);
    //         return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_OCTET_STREAM)
    //                 .body("download success".getBytes(StandardCharsets.UTF_8));
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    //     }
    // }
    //
    // private void download(HttpServletResponse response, File tempFile) throws IOException {
    //     // response.setContentType("application/octet-stream");
    //     response.setHeader("content-type", "application/octet-stream");
    //     // response.setHeader("Content-Disposition", "attachment; filename=\"" + tempFile.getName() + "\"");
    //     response.setHeader("Content-Length", "" + tempFile.length());
    //     response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(tempFile.getName(), "UTF-8"));
    //     StreamingResponseBody responseBody = outputStream -> {
    //         // 从文件或其他数据源读取数据
    //         // 将数据写入输出流
    //
    //         outputStream.write("Hello, World!".getBytes());
    //         outputStream.write("nihao shijie ".getBytes(StandardCharsets.UTF_8));
    //         outputStream.flush();
    //     };
    //
    //     responseBody.writeTo(response.getOutputStream());
    // }
}
