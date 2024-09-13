package com.javarush.jira.bugtracking.project;

import com.javarush.jira.bugtracking.project.to.ProjectTo;
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
public class ProjectMapperImpl implements ProjectMapper {

    @Override
    public Project toEntity(ProjectTo to) {
        if ( to == null ) {
            return null;
        }

        Project project = new Project();

        project.setTitle( to.getTitle() );
        project.setCode( to.getCode() );
        project.setDescription( to.getDescription() );
        project.setTypeCode( to.getTypeCode() );
        project.setParentId( to.getParentId() );

        return project;
    }

    @Override
    public List<Project> toEntityList(Collection<ProjectTo> tos) {
        if ( tos == null ) {
            return null;
        }

        List<Project> list = new ArrayList<Project>( tos.size() );
        for ( ProjectTo projectTo : tos ) {
            list.add( toEntity( projectTo ) );
        }

        return list;
    }

    @Override
    public Project updateFromTo(ProjectTo to, Project entity) {
        if ( to == null ) {
            return entity;
        }

        entity.setTitle( to.getTitle() );
        entity.setCode( to.getCode() );
        entity.setDescription( to.getDescription() );
        entity.setTypeCode( to.getTypeCode() );
        entity.setParentId( to.getParentId() );

        return entity;
    }

    @Override
    public ProjectTo toTo(Project entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String title = null;
        String code = null;
        String description = null;
        String typeCode = null;
        Long parentId = null;

        id = entity.getId();
        title = entity.getTitle();
        code = entity.getCode();
        description = entity.getDescription();
        typeCode = entity.getTypeCode();
        parentId = entity.getParentId();

        ProjectTo projectTo = new ProjectTo( id, code, title, description, typeCode, parentId );

        projectTo.setEnabled( entity.isEnabled() );

        return projectTo;
    }

    @Override
    public List<ProjectTo> toToList(Collection<Project> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ProjectTo> list = new ArrayList<ProjectTo>( entities.size() );
        for ( Project project : entities ) {
            list.add( toTo( project ) );
        }

        return list;
    }
}
