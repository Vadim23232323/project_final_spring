package com.javarush.jira.bugtracking.project;

import com.javarush.jira.bugtracking.project.to.ProjectToFull;
import com.javarush.jira.common.to.CodeTo;
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
public class ProjectMapperFullImpl implements ProjectMapperFull {

    @Override
    public Project toEntity(ProjectToFull to) {
        if ( to == null ) {
            return null;
        }

        Project project = new Project();

        project.setTitle( to.getTitle() );
        project.setCode( to.getCode() );
        project.setDescription( to.getDescription() );
        project.setTypeCode( to.getTypeCode() );
        project.setParent( codeToToProject( to.getParent() ) );
        project.setParentId( to.getParentId() );

        return project;
    }

    @Override
    public List<Project> toEntityList(Collection<ProjectToFull> tos) {
        if ( tos == null ) {
            return null;
        }

        List<Project> list = new ArrayList<Project>( tos.size() );
        for ( ProjectToFull projectToFull : tos ) {
            list.add( toEntity( projectToFull ) );
        }

        return list;
    }

    @Override
    public Project updateFromTo(ProjectToFull to, Project entity) {
        if ( to == null ) {
            return entity;
        }

        entity.setTitle( to.getTitle() );
        entity.setCode( to.getCode() );
        entity.setDescription( to.getDescription() );
        entity.setTypeCode( to.getTypeCode() );
        if ( to.getParent() != null ) {
            if ( entity.getParent() == null ) {
                entity.setParent( new Project() );
            }
            codeToToProject1( to.getParent(), entity.getParent() );
        }
        else {
            entity.setParent( null );
        }
        entity.setParentId( to.getParentId() );

        return entity;
    }

    @Override
    public ProjectToFull toTo(Project entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String title = null;
        String code = null;
        String description = null;
        String typeCode = null;
        CodeTo parent = null;

        id = entity.getId();
        title = entity.getTitle();
        code = entity.getCode();
        description = entity.getDescription();
        typeCode = entity.getTypeCode();
        parent = projectToCodeTo( entity.getParent() );

        ProjectToFull projectToFull = new ProjectToFull( id, code, title, description, typeCode, parent );

        projectToFull.setEnabled( entity.isEnabled() );

        return projectToFull;
    }

    @Override
    public List<ProjectToFull> toToList(Collection<Project> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ProjectToFull> list = new ArrayList<ProjectToFull>( entities.size() );
        for ( Project project : entities ) {
            list.add( toTo( project ) );
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
