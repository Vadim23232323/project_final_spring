package com.javarush.jira.bugtracking.tree;

import com.javarush.jira.bugtracking.ObjectType;
import com.javarush.jira.bugtracking.project.to.ProjectTo;
import com.javarush.jira.bugtracking.sprint.to.SprintTo;
import com.javarush.jira.bugtracking.task.to.TaskTo;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-13T12:03:23+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class NodeMapperImpl implements NodeMapper {

    @Override
    public NodeTo fromProject(ProjectTo project) {
        if ( project == null ) {
            return null;
        }

        long id = 0L;
        String code = null;
        Long parentId = null;

        if ( project.getId() != null ) {
            id = project.getId();
        }
        code = project.getCode();
        parentId = project.getParentId();

        ObjectType type = ObjectType.PROJECT;

        NodeTo nodeTo = new NodeTo( id, code, type, parentId );

        nodeTo.setEnabled( project.isEnabled() );

        return nodeTo;
    }

    @Override
    public NodeTo fromSprint(SprintTo sprint) {
        if ( sprint == null ) {
            return null;
        }

        long id = 0L;
        String code = null;

        if ( sprint.getId() != null ) {
            id = sprint.getId();
        }
        code = sprint.getCode();

        ObjectType type = ObjectType.SPRINT;
        Long parentId = null;

        NodeTo nodeTo = new NodeTo( id, code, type, parentId );

        nodeTo.setEnabled( sprint.isEnabled() );

        return nodeTo;
    }

    @Override
    public NodeTo fromTask(TaskTo task) {
        if ( task == null ) {
            return null;
        }

        long id = 0L;
        String code = null;
        Long parentId = null;

        if ( task.getId() != null ) {
            id = task.getId();
        }
        code = task.getCode();
        parentId = task.getParentId();

        ObjectType type = ObjectType.TASK;

        NodeTo nodeTo = new NodeTo( id, code, type, parentId );

        nodeTo.setEnabled( task.isEnabled() );

        return nodeTo;
    }
}
