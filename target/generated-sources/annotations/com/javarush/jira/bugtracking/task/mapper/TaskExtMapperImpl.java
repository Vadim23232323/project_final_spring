package com.javarush.jira.bugtracking.task.mapper;

import com.javarush.jira.bugtracking.task.Task;
import com.javarush.jira.bugtracking.task.to.TaskToExt;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-13T12:03:22+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class TaskExtMapperImpl implements TaskExtMapper {

    @Override
    public Task toEntity(TaskToExt to) {
        if ( to == null ) {
            return null;
        }

        Task task = new Task();

        task.setTitle( to.getTitle() );
        task.setTypeCode( to.getTypeCode() );
        task.setStatusCode( to.getStatusCode() );
        task.setParentId( to.getParentId() );
        if ( to.getProjectId() != null ) {
            task.setProjectId( to.getProjectId() );
        }
        task.setSprintId( to.getSprintId() );

        return task;
    }

    @Override
    public List<Task> toEntityList(Collection<TaskToExt> tos) {
        if ( tos == null ) {
            return null;
        }

        List<Task> list = new ArrayList<Task>( tos.size() );
        for ( TaskToExt taskToExt : tos ) {
            list.add( toEntity( taskToExt ) );
        }

        return list;
    }

    @Override
    public List<TaskToExt> toToList(Collection<Task> entities) {
        if ( entities == null ) {
            return null;
        }

        List<TaskToExt> list = new ArrayList<TaskToExt>( entities.size() );
        for ( Task task : entities ) {
            list.add( toTo( task ) );
        }

        return list;
    }

    @Override
    public TaskToExt toTo(Task task) {
        if ( task == null ) {
            return null;
        }

        Long id = null;
        String title = null;
        String statusCode = null;
        String code = null;
        String typeCode = null;
        Long parentId = null;
        long projectId = 0L;
        Long sprintId = null;

        id = task.getId();
        title = task.getTitle();
        statusCode = task.getStatusCode();
        code = task.getCode();
        typeCode = task.getTypeCode();
        parentId = task.getParentId();
        projectId = task.getProjectId();
        sprintId = task.getSprintId();

        String description = null;
        String priorityCode = null;
        LocalDateTime updated = null;
        Integer estimate = null;

        TaskToExt taskToExt = new TaskToExt( id, code, title, description, typeCode, statusCode, priorityCode, updated, estimate, parentId, projectId, sprintId );

        taskToExt.setEnabled( task.isEnabled() );

        return taskToExt;
    }

    @Override
    public Task updateFromTo(TaskToExt taskToExt, Task task) {
        if ( taskToExt == null ) {
            return task;
        }

        task.setTitle( taskToExt.getTitle() );
        task.setTypeCode( taskToExt.getTypeCode() );
        task.setStatusCode( taskToExt.getStatusCode() );
        task.setParentId( taskToExt.getParentId() );

        task.setProjectId( TaskExtMapper.checkProjectBelong(taskToExt.getProjectId(), task) );
        task.setSprintId( TaskExtMapper.checkUserAuthorities(taskToExt.getSprintId(), task) );

        return task;
    }
}
