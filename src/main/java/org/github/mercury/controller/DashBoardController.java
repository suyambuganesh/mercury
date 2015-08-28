package org.github.mercury.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
public class DashBoardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DashBoardController.class);

    @RequestMapping("events")
    public SseEmitter getData() {
        LOGGER.info("initiate event stream");
        SseEmitter sseEmitter = new SseEmitter();
        try {
            sseEmitter.send("1");
        }
        catch (IOException e) {
            LOGGER.error("Error while sending message to SSE stream. {}", e);
        }
        return sseEmitter;
    }

    @RequestMapping("events/{application}")
    public SseEmitter getEvents(@PathVariable
    final String application) {
        LOGGER.info("initiate event stream for application {}", application);
        SseEmitter sseEmitter = new SseEmitter();
        try {
            sseEmitter.send("1");
        }
        catch (IOException e) {
            LOGGER.error("Error while sending message to SSE stream. {}", e);
        }
        return sseEmitter;
    }

    @RequestMapping(value = "/")
    public String loadHomePage(final Model model) {
        return "index.html";
    }

    @RequestMapping(value = "views/{widget}")
    public String getWidget(@PathVariable
    final String widget) {
        LOGGER.info("request widget for {}", widget);
        return "redirect:/widgets/" + widget + "/" + widget + ".html";
    }

    @RequestMapping(value = "/{dashboard}")
    public String loadDashboard(@PathVariable
    final String dashboard) {
        return dashboard + ".html";
    }
}
