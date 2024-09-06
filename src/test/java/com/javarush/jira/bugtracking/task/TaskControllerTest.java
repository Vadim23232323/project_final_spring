package com.javarush.jira.bugtracking.task;

import com.javarush.jira.AbstractControllerTest;
import com.javarush.jira.bugtracking.UserBelongRepository;
import com.javarush.jira.bugtracking.task.to.ActivityTo;
import com.javarush.jira.bugtracking.task.to.TaskToExt;
import com.javarush.jira.bugtracking.task.to.TaskToFull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.javarush.jira.bugtracking.ObjectType.TASK;
import static com.javarush.jira.bugtracking.task.TaskController.REST_URL;
import static com.javarush.jira.bugtracking.task.TaskService.CANNOT_ASSIGN;
import static com.javarush.jira.bugtracking.task.TaskService.CANNOT_UN_ASSIGN;
import static com.javarush.jira.bugtracking.task.TaskTestData.NOT_FOUND;
import static com.javarush.jira.bugtracking.task.TaskTestData.*;
import static com.javarush.jira.common.util.JsonUtil.writeValue;
import static com.javarush.jira.login.internal.web.UserTestData.*;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TaskControllerTest extends AbstractControllerTest {
    private static final String TASKS_REST_URL_SLASH = REST_URL + "/";
    private static final String TASKS_BY_PROJECT_REST_URL = REST_URL + "/by-project";
    private static final String TASKS_BY_SPRINT_REST_URL = REST_URL + "/by-sprint";
    private static final String ACTIVITIES_REST_URL = REST_URL + "/activities";
    private static final String ACTIVITIES_REST_URL_SLASH = REST_URL + "/activities/";
    private static final String CHANGE_STATUS = "/change-status";

    private static final String PROJECT_ID = "projectId";
    private static final String SPRINT_ID = "sprintId";
    private static final String STATUS_CODE = "statusCode";
    private static final String USER_TYPE = "userType";
    private static final String ENABLED = "enabled";

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private UserBelongRepository userBelongRepository;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void get() throws Exception {
        get(TASK1_ID, taskToFull1);
    }

    private void get(long taskId, TaskToFull taskToFull) throws Exception {
        perform(MockMvcRequestBuilders.get(TASKS_REST_URL_SLASH + taskId))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TASK_TO_FULL_MATCHER.contentJson(taskToFull));
    }

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(TASKS_REST_URL_SLASH + TASK1_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(TASKS_REST_URL_SLASH + NOT_FOUND))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAllBySprint() throws Exception {
        perform(MockMvcRequestBuilders.get(TASKS_BY_SPRINT_REST_URL)
                .param(SPRINT_ID, String.valueOf(SPRINT1_ID)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TASK_TO_MATCHER.contentJson(taskTo2, taskTo1));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAllByProject() throws Exception {
        perform(MockMvcRequestBuilders.get(TASKS_BY_PROJECT_REST_URL)
                .param(PROJECT_ID, String.valueOf(TaskTestData.PROJECT1_ID)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TASK_TO_MATCHER.contentJson(taskTo2, taskTo1));
    }

    @Test
    void getAllByProjectUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(TASKS_BY_PROJECT_REST_URL)
                .param(PROJECT_ID, String.valueOf(TaskTestData.PROJECT1_ID)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void updateTask() throws Exception {
        TaskToExt updatedTo = TaskTestData.getUpdatedTaskTo();
        perform(MockMvcRequestBuilders.put(TASKS_REST_URL_SLASH + TASK2_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(updatedTo)))
                .andDo(print())
                .andExpect(status().isNoContent());

        Task updated = new Task(updatedTo.getId(), updatedTo.getTitle(), updatedTo.getTypeCode(), updatedTo.getStatusCode(), updatedTo.getParentId(), updatedTo.getProjectId(), updatedTo.getSprintId());
        TASK_MATCHER.assertMatch(taskRepository.getExisted(TASK2_ID), updated);
        get(TASK2_ID, taskToFull2);
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void updateTaskWhenStateNotChanged() throws Exception {
        int activitiesCount = activityRepository.findAllByTaskIdOrderByUpdatedDesc(TASK2_ID).size();
        TaskToExt sameStateTo = new TaskToExt(TASK2_ID, taskTo2.getCode(), taskTo2.getTitle(), "Trees desc", taskTo2.getTypeCode(),
                taskTo2.getStatusCode(), "normal", null, 4, taskTo2.getParentId(), taskTo2.getProjectId(), taskTo2.getSprintId());
        perform(MockMvcRequestBuilders.put(TASKS_REST_URL_SLASH + TASK2_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(sameStateTo)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertEquals(activitiesCount, activityRepository.findAllByTaskIdOrderByUpdatedDesc(TASK2_ID).size());
    }

    @Test
    void updateTaskUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.put(TASKS_REST_URL_SLASH + TASK2_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(getUpdatedTaskTo())))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void updateTaskWhenProjectNotExists() throws Exception {
        TaskToExt notExistsProjectTo = new TaskToExt(TASK2_ID, "epic-2", "Trees UPD", "task UPD", "epic", "in_progress", "high", null, 4, null, NOT_FOUND, SPRINT1_ID);
        perform(MockMvcRequestBuilders.put(TASKS_REST_URL_SLASH + TASK2_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(notExistsProjectTo)))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void updateTaskIdNotConsistent() throws Exception {
        perform(MockMvcRequestBuilders.put(TASKS_REST_URL_SLASH + (TASK2_ID + 1))
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(getUpdatedTaskTo())))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void updateTaskWhenChangeProject() throws Exception {
        TaskToExt changedProjectTo = new TaskToExt(TASK2_ID, "epic-2", "Trees UPD", "task UPD", "epic", "in_progress", "high", null, 4, null, PROJECT1_ID + 1, SPRINT1_ID);
        perform(MockMvcRequestBuilders.put(TASKS_REST_URL_SLASH + TASK2_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(changedProjectTo)))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void updateSprintIdWhenDev() throws Exception {
        TaskToExt changedSprintTo = new TaskToExt(TASK2_ID, "epic-2", "Trees UPD", "task UPD", "epic", "in_progress", "high", null, 4, null, PROJECT1_ID, SPRINT1_ID + 1);
        perform(MockMvcRequestBuilders.put(TASKS_REST_URL_SLASH + TASK2_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(changedSprintTo)))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void updateSprintIdWhenAdmin() throws Exception {
        TaskToExt changedSprintTo = new TaskToExt(TASK2_ID, "epic-2", "Trees UPD", "task UPD", "epic", "in_progress", "high", null, 4, null, PROJECT1_ID, SPRINT1_ID + 1);
        perform(MockMvcRequestBuilders.put(TASKS_REST_URL_SLASH + TASK2_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(changedSprintTo)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertEquals(SPRINT1_ID + 1, taskRepository.getExisted(TASK2_ID).getSprintId());
    }

    @Test
    @WithUserDetails(value = MANAGER_MAIL)
    void updateSprintIdWhenManager() throws Exception {
        TaskToExt changedSprintTo = new TaskToExt(TASK2_ID, "epic-2", "Trees UPD", "task UPD", "epic", "in_progress", "high", null, 4, null, PROJECT1_ID, SPRINT1_ID + 1);
        perform(MockMvcRequestBuilders.put(TASKS_REST_URL_SLASH + TASK2_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(changedSprintTo)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertEquals(SPRINT1_ID + 1, taskRepository.getExisted(TASK2_ID).getSprintId());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void updateActivity() throws Exception {
        ActivityTo updatedTo = getUpdatedActivityTo();
        perform(MockMvcRequestBuilders.put(ACTIVITIES_REST_URL_SLASH + ACTIVITY1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(updatedTo)))
                .andDo(print())
                .andExpect(status().isNoContent());

        Activity updated = new Activity(updatedTo.getId(), updatedTo.getTaskId(), updatedTo.getAuthorId(), updatedTo.getUpdated(),
                updatedTo.getComment(), updatedTo.getStatusCode(), updatedTo.getPriorityCode(), updatedTo.getTypeCode(), updatedTo.getTitle(),
                updatedTo.getDescription(), updatedTo.getEstimate());
        ACTIVITY_MATCHER.assertMatch(activityRepository.getExisted(ACTIVITY1_ID), updated);
        updateTaskIfRequired(updated.getTaskId(), updated.getStatusCode(), updated.getTypeCode());
    }

    private void updateTaskIfRequired(long taskId, String activityStatus, String activityType) {
        if (activityStatus != null || activityType != null) {
            Task task = taskRepository.getExisted(taskId);
            if (activityStatus != null) assertEquals(task.getStatusCode(), activityStatus);
            if (activityType != null) assertEquals(task.getTypeCode(), activityType);
        }
    }

    @Test
    void updateActivityUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.put(ACTIVITIES_REST_URL_SLASH + ACTIVITY1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(getUpdatedTaskTo())))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void updateWhenTaskNotExists() throws Exception {
        ActivityTo notExistsActivityTo = new ActivityTo(ACTIVITY1_ID, NOT_FOUND, USER_ID, null, null,
                "in_progress", "low", "epic", null, null, 3, null);
        perform(MockMvcRequestBuilders.put(ACTIVITIES_REST_URL_SLASH + ACTIVITY1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(notExistsActivityTo)))
                .andDo(print())
                .andExpect(status().isConflict());
    }
}



