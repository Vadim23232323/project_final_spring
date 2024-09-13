package com.javarush.jira.bugtracking.task.mapper;

import com.javarush.jira.bugtracking.task.Task;
import com.javarush.jira.bugtracking.task.to.TaskTo;
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
public class TaskMapperImpl implements TaskMapper {

    @Override
    public Task toEntity(TaskTo to) {
        if ( to == null ) {
            return null;
        }

        Task task = new Task();

        task.setId( to.getId() );
        task.setEnabled( to.isEnabled() );
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
    public List<Task> toEntityList(Collection<TaskTo> tos) {
        if ( tos == null ) {
            return null;
        }

        List<Task> list = new ArrayList<Task>( tos.size() );
        for ( TaskTo taskTo : tos ) {
            list.add( toEntity( taskTo ) );
        }

        return list;
    }

    @Override
    public Task updateFromTo(TaskTo to, Task entity) {
        if ( to == null ) {
            return entity;
        }

        entity.setId( to.getId() );
        entity.setEnabled( to.isEnabled() );
        entity.setTitle( to.getTitle() );
        entity.setTypeCode( to.getTypeCode() );
        entity.setStatusCode( to.getStatusCode() );
        entity.setParentId( to.getParentId() );
        if ( to.getProjectId() != null ) {
            entity.setProjectId( to.getProjectId() );
        }
        entity.setSprintId( to.getSprintId() );

        return entity;
    }

    @Override
    public List<TaskTo> toToList(Collection<Task> entities) {
        if ( entities == null ) {
            return null;
        }

        List<TaskTo> list = new ArrayList<TaskTo>( entities.size() );
        for ( Task task : entities ) {
            list.add( toTo( task ) );
        }

        return list;
    }

    @Override
    public TaskTo toTo(Task task) {
        if ( task == null ) {
            return null;
        }

        Long id = null;
        String title = null;
        String statusCode = null;
        String code = null;
        String typeCode = null;
        Long parentId = null;
        Long projectId = null;
        Long sprintId = null;

        id = task.getId();
        title = task.getTitle();
        statusCode = task.getStatusCode();
        code = task.getCode();
        typeCode = task.getTypeCode();
        parentId = task.getParentId();
        projectId = task.getProjectId();
        sprintId = task.getSprintId();

        TaskTo taskTo = new TaskTo( id, code, title, typeCode, statusCode, parentId, projectId, sprintId );

        taskTo.setEnabled( task.isEnabled() );

        return taskTo;
    }
}
