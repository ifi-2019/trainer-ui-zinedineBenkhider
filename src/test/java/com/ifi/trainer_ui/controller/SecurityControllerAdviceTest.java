package com.ifi.trainer_ui.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.lang.reflect.Method;
import java.security.Principal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SecurityControllerAdviceTest {

        @Test
        void securityControllerAdvice_shouldBeAControllerAdvice() {
            assertNotNull(SecurityControllerAdvice.class.getAnnotation(ControllerAdvice.class));
        }

        @Test
        void principal_shouldUseModelAttribute() throws NoSuchMethodException {
            Method principalMethod = SecurityControllerAdvice.class.getDeclaredMethod("principal");
            ModelAttribute annotation = principalMethod.getAnnotation(ModelAttribute.class);
            assertNotNull(annotation);
            assertEquals("user", annotation.value());
        }

        @Test
        void principal_shouldAddThePrincipalToTheModel() throws NoSuchMethodException {
            SecurityControllerAdvice advice = new SecurityControllerAdvice();

            // mocking data
            Authentication authentication = mock(Authentication.class);
            User user = mock(User.class);
            when(authentication.getPrincipal()).thenReturn(user);

            // setting security to the mocked auth !
            SecurityContextHolder.getContext().setAuthentication(authentication);

            Object result = advice.principal();
            assertEquals(user, result);
        }

    }