package com.javarush.jira.bugtracking.sprint;

import com.javarush.jira.bugtracking.project.Project;
import com.javarush.jira.bugtracking.sprint.to.SprintToFull;
import com.javarush.jira.common.to.CodeTo;
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
public class SprintMapperFullImpl implements SprintMapperFull {

    @Override
    public Sprint toEntity(SprintToFull to) {
        if ( to == null ) {
            return null;
        }

        Sprint sprint = new Sprint();

        sprint.setCode( to.getCode() );
        sprint.setStatusCode( to.getStatusCode() );
        sprint.setProject( codeToToProject( to.getProject() ) );
        if ( to.getProjectId() != null ) {
            sprint.setProjectId( to.getProjectId() );
        }

        return sprint;
    }

    @Override
    public List<Sprint> toEntityList(Collection<SprintToFull> tos) {
        if ( tos == null ) {
            return null;
        }

        List<Sprint> list = new ArrayList<Sprint>( tos.size() );
        for ( SprintToFull sprintToFull : tos ) {
            list.add( toEntity( sprintToFull ) );
        }

        return list;
    }

    @Override
    public Sprint updateFromTo(SprintToFull to, Sprint entity) {
        if ( to == null ) {
            return entity;
        }

        entity.setCode( to.getCode() );
        entity.setStatusCode( to.getStatusCode() );
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

        return entity;
    }

    @Override
    public SprintToFull toTo(Sprint entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String code = null;
        String statusCode = null;
        CodeTo project = null;

        id = entity.getId();
        code = entity.getCode();
        statusCode = entity.getStatusCode();
        project = projectToCodeTo( entity.getProject() );

        SprintToFull sprintToFull = new SprintToFull( id, code, statusCode, project );

        sprintToFull.setEnabled( entity.isEnabled() );

        return sprintToFull;
    }

    @Override
    public List<SprintToFull> toToList(Collection<Sprint> entities) {
        if ( entities == null ) {
            return null;
        }

        List<SprintToFull> list = new ArrayList<SprintToFull>( entities.size() );
        for ( Sprint sprint : entities ) {
            list.add( toTo( sprint ) );
        }

        return list;
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

    protected void codeToToProject1(CodeTo codeTo, Project mappingTarget) {
        if ( codeTo == null ) {
            return;
        }

        mappingTarget.setId( codeTo.getId() );
        mappingTarget.setEnabled( codeTo.isEnabled() );
        mappingTarget.setCode( codeTo.getCode() );
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
}
