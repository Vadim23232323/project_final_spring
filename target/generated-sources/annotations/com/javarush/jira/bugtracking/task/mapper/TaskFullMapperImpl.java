package com.javarush.jira.bugtracking.task.mapper;

import com.javarush.jira.bugtracking.project.Project;
import com.javarush.jira.bugtracking.sprint.Sprint;
import com.javarush.jira.bugtracking.task.Task;
import com.javarush.jira.bugtracking.task.to.ActivityTo;
import com.javarush.jira.bugtracking.task.to.TaskToFull;
import com.javarush.jira.common.to.CodeTo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-13T12:03:23+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class TaskFullMapperImpl implements TaskFullMapper {

    @Override
    public Task toEntity(TaskToFull to) {
        if ( to == null ) {
            return null;
        }

        Task task = new Task();

        task.setTitle( to.getTitle() );
        task.setTypeCode( to.getTypeCode() );
        task.setStatusCode( to.getStatusCode() );
        task.setParent( codeToToTask( to.getParent() ) );
        task.setParentId( to.getParentId() );
        task.setProject( codeToToProject( to.getProject() ) );
        if ( to.getProjectId() != null ) {
            task.setProjectId( to.getProjectId() );
        }
        task.setSprint( codeToToSprint( to.getSprint() ) );
        task.setSprintId( to.getSprintId() );

        return task;
    }

    @Override
    public List<Task> toEntityList(Collection<TaskToFull> tos) {
        if ( tos == null ) {
            return null;
        }

        List<Task> list = new ArrayList<Task>( tos.size() );
        for ( TaskToFull taskToFull : tos ) {
            list.add( toEntity( taskToFull ) );
        }

        return list;
    }

    @Override
    public Task updateFromTo(TaskToFull to, Task entity) {
        if ( to == null ) {
            return entity;
        }

        entity.setTitle( to.getTitle() );
        entity.setTypeCode( to.getTypeCode() );
        entity.setStatusCode( to.getStatusCode() );
        if ( to.getParent() != null ) {
            if ( entity.getParent() == null ) {
                entity.setParent( new Task() );
            }
            codeToToTask1( to.getParent(), entity.getParent() );
        }
        else {
            entity.setParent( null );
        }
        entity.setParentId( to.getParentId() );
        if ( to.getProject() != null ) {
            if ( entity.getProject() == null ) {
                entity.setProject( new Project() );
            }
            codeToToProject1( to.getProject(), entity.getProject() );
        }
        else {
            entity.setProject( null );
        }
        if ( to.getProjectId() != null ) {
            entity.setProjectId( to.getProjectId() );
        }
        if ( to.getSprint() != null ) {
            if ( entity.getSprint() == null ) {
                entity.setSprint( new Sprint() );
            }
            codeToToSprint1( to.getSprint(), entity.getSprint() );
        }
        else {
            entity.setSprint( null );
        }
        entity.setSprintId( to.getSprintId() );

        return entity;
    }

    @Override
    public List<TaskToFull> toToList(Collection<Task> entities) {
        if ( entities == null ) {
            return null;
        }

        List<TaskToFull> list = new ArrayList<TaskToFull>( entities.size() );
        for ( Task task : entities ) {
            list.add( toTo( task ) );
        }

        return list;
    }

    @Override
    public TaskToFull toTo(Task task) {
        if ( task == null ) {
            return null;
        }

        Long id = null;
        String title = null;
        String statusCode = null;
        String code = null;
        String typeCode = null;
        CodeTo parent = null;
        CodeTo project = null;
        CodeTo sprint = null;

        id = task.getId();
        title = task.getTitle();
        statusCode = task.getStatusCode();
        code = task.getCode();
        typeCode = task.getTypeCode();
        parent = taskToCodeTo( task.getParent() );
        project = projectToCodeTo( task.getProject() );
        sprint = sprintToCodeTo( task.getSprint() );

        String description = null;
        String priorityCode = null;
        LocalDateTime updated = null;
        Integer estimate = null;
        List<ActivityTo> activityTos = null;

        TaskToFull taskToFull = new TaskToFull( id, code, title, description, typeCode, statusCode, priorityCode, updated, estimate, parent, project, sprint, activityTos );

        taskToFull.setEnabled( task.isEnabled() );

        return taskToFull;
    }

    protected Task codeToToTask(CodeTo codeTo) {
        if ( codeTo == null ) {
            return null;
        }

        Task task = new Task();

        task.setId( codeTo.getId() );
        task.setEnabled( codeTo.isEnabled() );

        return task;
    }

    protected Project codeToToProject(CodeTo codeTo) {
        if ( codeTo == null ) {
            return null;
        }

        Project project = new Project();

        project.setId( codeTo.getId() );
        project.setEnabled( codeTo.isEnabled() );
        project.setCode( codeTo.getCode() );

        return project;
    }

    protected Sprint codeToToSprint(CodeTo codeTo) {
        if ( codeTo == null ) {
            return null;
        }

        Sprint sprint = new Sprint();

        sprint.setId( codeTo.getId() );
        sprint.setEnabled( codeTo.isEnabled() );
        sprint.setCode( codeTo.getCode() );

        return sprint;
    }

    protected void codeToToTask1(CodeTo codeTo, Task mappingTarget) {
        if ( codeTo == null ) {
            return;
        }

        mappingTarget.setId( codeTo.getId() );
        mappingTarget.setEnabled( codeTo.isEnabled() );
    }

    protected void codeToToProject1(CodeTo codeTo, Project mappingTarget) {
        if ( codeTo == null ) {
            return;
        }

        mappingTarget.setId( codeTo.getId() );
        mappingTarget.setEnabled( codeTo.isEnabled() );
        mappingTarget.setCode( codeTo.getCode() );
    }

    protected void codeToToSprint1(CodeTo codeTo, Sprint mappingTarget) {
        if ( codeTo == null ) {
            return;
        }

        mappingTarget.setId( codeTo.getId() );
        mappingTarget.setEnabled( codeTo.isEnabled() );
        mappingTarget.setCode( codeTo.getCode() );
    }

    protected CodeTo taskToCodeTo(Task task) {
        if ( task == null ) {
            return null;
        }

        Long id = null;
        String code = null;

        id = task.getId();
        code = task.getCode();

        CodeTo codeTo = new CodeTo( id, code );

        codeTo.setEnabled( task.isEnabled() );

        return codeTo;
    }

    protected CodeTo projectToCodeTo(Project project) {
        if ( project == null ) {
            return null;
        }

        Long id = null;
        String code = null;

        id = project.getId();
        code = project.getCode();

        CodeTo codeTo = new CodeTo( id, code );

        codeTo.setEnabled( project.isEnabled() );

        return codeTo;
    }

    protected CodeTo sprintToCodeTo(Sprint sprint) {
        if ( sprint == null ) {
            return null;
        }

        Long id = null;
        String code = null;

        id = sprint.getId();
        code = sprint.getCode();

        CodeTo codeTo = new CodeTo( id, code );

        codeTo.setEnabled( sprint.isEnabled() );

        return codeTo;
    }
}
