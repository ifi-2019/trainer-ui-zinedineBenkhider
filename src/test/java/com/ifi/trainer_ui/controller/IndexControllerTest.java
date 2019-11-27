package com.ifi.trainer_ui.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;

class IndexControllerTest {

    @Test
    void controllerShouldBeAnnotated(){
        assertNotNull(IndexController.class.getAnnotation(Controller.class));
    }

    @Test
    void index_shouldReturnTheNameOfTheIndexTemplate() {
        IndexController indexController = new IndexController();
        String viewName = indexController.index();

        assertEquals("index", viewName);
    }

    @Test
    void index_shouldBeAnnotated() throws NoSuchMethodException {
        Method indexMethod = IndexController.class.getMethod("index");
        GetMapping getMapping = indexMethod.getAnnotation(GetMapping.class);

        assertNotNull(getMapping);
        assertArrayEquals(new String[]{"/"}, getMapping.value());
    }

    @Test
    void registerNewTrainer_shouldReturnAModelAndView(){
        IndexController indexController = new IndexController();
        ModelAndView modelAndView = indexController.registerNewTrainer("Blue");

        assertNotNull(modelAndView);
        assertEquals("register", modelAndView.getViewName());


        assertEquals("Blue", modelAndView.getModel().get("name"));
    }

    @Test
    void registerNewTrainer_shouldBeAnnotated() throws NoSuchMethodException {
        Method registerMethod = IndexController.class.getDeclaredMethod("registerNewTrainer", String.class);
        PostMapping postMapping = registerMethod.getAnnotation(PostMapping.class);

        assertNotNull(postMapping);
        assertArrayEquals(new String[]{"/registerTrainer"}, postMapping.value());
    }

}