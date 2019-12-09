package com.ifi.trainer_ui.config;

import static org.junit.jupiter.api.Assertions.*;

import com.ifi.trainer_ui.bo.Trainer;
import com.ifi.trainer_ui.trainers_service.TrainerService;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class SecurityConfigTest {
    @Test
    void securityConfig_shouldExtendWebSecurityConfigurerAdapter(){
        assertTrue(WebSecurityConfigurerAdapter.class.isAssignableFrom(SecurityConfig.class));
    }

    @Test
    void passwordEncoder_shouldBeBCryptPasswordEncoder() {
        SecurityConfig securityConfig = new SecurityConfig();
        PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();
        assertNotNull(passwordEncoder);
        assertEquals(BCryptPasswordEncoder.class, passwordEncoder.getClass());
    }

    @Test
    void userDetailsService_shouldUseTrainerService() {
        SecurityConfig securityConfig = new SecurityConfig();

        TrainerService trainerService = mock(TrainerService.class);
        Trainer trainer = new Trainer();
        trainer.setName("Garry");
        trainer.setPassword("secret");
        when(trainerService.getTrainer("Garry")).thenReturn(trainer);

        securityConfig.setTrainerService(trainerService);

        UserDetailsService userDetailsService = securityConfig.userDetailsService();

        UserDetails garry = userDetailsService.loadUserByUsername("Garry");

        // mock should be called
        verify(trainerService).getTrainer("Garry");

        assertNotNull(garry);
        assertEquals("Garry", garry.getUsername());
        assertEquals("secret", garry.getPassword());
        assertTrue(garry.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Test
    void userDetailsService_shouldThrowABadCredentialsException_whenUserDoesntExists() {
        SecurityConfig securityConfig = new SecurityConfig();

        // the mock returns null
        TrainerService trainerService = mock(TrainerService.class);
        securityConfig.setTrainerService(trainerService);

        UserDetailsService userDetailsService = securityConfig.userDetailsService();

        BadCredentialsException exception = assertThrows(BadCredentialsException.class, () -> userDetailsService.loadUserByUsername("Garry"));
        assertEquals("No such user", exception.getMessage());

        // mock should be called
        verify(trainerService).getTrainer("Garry");
    }
}