package com.javarush.jira.bugtracking.sprint;

import com.javarush.jira.bugtracking.sprint.to.SprintTo;
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
public class SprintMapperImpl implements SprintMapper {

    @Override
    public Sprint toEntity(SprintTo to) {
        if ( to == null ) {
            return null;
        }

        Sprint sprint = new Sprint();

        sprint.setCode( to.getCode() );
        sprint.setStatusCode( to.getStatusCode() );
        if ( to.getProjectId() != null ) {
            sprint.setProjectId( to.getProjectId() );
        }

        return sprint;
    }

    @Override
    public List<Sprint> toEntityList(Collection<SprintTo> tos) {
        if ( tos == null ) {
            return null;
        }

        List<Sprint> list = new ArrayList<Sprint>( tos.size() );
        for ( SprintTo sprintTo : tos ) {
            list.add( toEntity( sprintTo ) );
        }

        return list;
    }

    @Override
    public SprintTo toTo(Sprint entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String code = null;
        String statusCode = null;
        Long projectId = null;

        id = entity.getId();
        code = entity.getCode();
        statusCode = entity.getStatusCode();
        projectId = entity.getProjectId();

        SprintTo sprintTo = new SprintTo( id, code, statusCode, projectId );

        sprintTo.setEnabled( entity.isEnabled() );

        return sprintTo;
    }

    @Override
    public List<SprintTo> toToList(Collection<Sprint> entities) {
        if ( entities == null ) {
            return null;
        }

        List<SprintTo> list = new ArrayList<SprintTo>( entities.size() );
        for ( Sprint sprint : entities ) {
            list.add( toTo( sprint ) );
        }

        return list;
    }

    @Override
    public Sprint updateFromTo(SprintTo sprintTo, Sprint sprint) {
        if ( sprintTo == null ) {
            return sprint;
        }

        sprint.setCode( sprintTo.getCode() );
        sprint.setStatusCode( sprintTo.getStatusCode() );

        sprint.setProjectId( SprintMapper.checkProjectBelong(sprintTo.getProjectId(), sprint) );

        return sprint;
    }
}
