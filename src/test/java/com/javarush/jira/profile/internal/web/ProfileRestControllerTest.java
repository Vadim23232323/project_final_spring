package com.javarush.jira.profile.internal.web;

import com.javarush.jira.login.Role;
import com.javarush.jira.login.User;
import com.javarush.jira.login.AuthUser;
import com.javarush.jira.profile.ProfileTo;
import com.javarush.jira.AbstractControllerTest;
import com.javarush.jira.profile.internal.ProfileUtil;
import com.javarush.jira.profile.internal.model.Profile;
import com.javarush.jira.profile.internal.ProfileMapper;
import com.javarush.jira.profile.internal.ProfileRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import java.util.Set;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.assertj.core.api.Assertions.assertThat;

// Работает с тестовоей БД, для работы код раскомментировать
public class ProfileRestControllerTest extends AbstractControllerTest {

//    private static final String REST_URL = ProfileRestController.REST_URL;
//    private ProfileTo ProfileToTest;
//    private AuthUser authUserTest;
//
//    @Autowired
//    private ProfileRepository profileRepositoryTest;
//    @Autowired
//    protected ProfileMapper profileMapperTest;
//    @Autowired
//    private ObjectMapper objectMapperTest;
//
//    @BeforeEach
//    public void reg() {
//        User UserTest = new User();
//        UserTest.setId(1L);
//        UserTest.setPassword("passwordTest");
//        UserTest.setEmail("userTest@mail.com");
//        UserTest.setRoles(Set.of(Role.ADMIN));
//        authUserTest = new AuthUser(UserTest);
//        ProfileToTest = new ProfileTo(1L, Set.of("three_days_before_deadline"), null);
//        profileRepositoryTest.save(profileMapperTest.updateFromTo(new Profile(), ProfileToTest));
//    }
//
//    @Test
//    void getFoundUser() throws Exception {
//        perform(MockMvcRequestBuilders.get(REST_URL).with(user(authUserTest)))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(ProfileToTest.getId().intValue()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.mailNotifications[0]").value("three_days_before_deadline"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.contacts").isEmpty())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
//    }
//
//    @Test
//    void getIsUnauthorized() throws Exception {
//        perform(MockMvcRequestBuilders.get(REST_URL))
//                .andExpect(status().isUnauthorized());
//    }
//
//    @Test
//    public void testUpdate() throws Exception {
//        ProfileTo updatedProfileTo = new ProfileTo(ProfileToTest.getId(), Set.of("deadline"), null);
//
//        perform(MockMvcRequestBuilders.put(REST_URL).contentType(MediaType.APPLICATION_JSON).content(objectMapperTest.writeValueAsString(updatedProfileTo)).with(user(authUserTest)))
//                .andExpect(status().isNoContent());
//
//        Profile updatedProfile = profileRepositoryTest.getOrCreate(ProfileToTest.getId());
//        assertThat(updatedProfile.getMailNotifications()).isEqualTo(ProfileUtil.notificationsToMask(Set.of("deadline")));
//    }
}