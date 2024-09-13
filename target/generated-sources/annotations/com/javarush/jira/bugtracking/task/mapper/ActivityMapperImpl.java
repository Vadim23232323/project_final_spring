package com.javarush.jira.bugtracking.task.mapper;

import com.javarush.jira.bugtracking.task.Activity;
import com.javarush.jira.bugtracking.task.to.ActivityTo;
import com.javarush.jira.login.User;
import com.javarush.jira.login.UserTo;
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
public class ActivityMapperImpl implements ActivityMapper {

    @Override
    public Activity toEntity(ActivityTo to) {
        if ( to == null ) {
            return null;
        }

        Activity activity = new Activity();

        activity.setId( to.getId() );
        activity.setTitle( to.getTitle() );
        activity.setTaskId( to.getTaskId() );
        activity.setAuthor( userToToUser( to.getAuthor() ) );
        activity.setAuthorId( to.getAuthorId() );
        activity.setUpdated( to.getUpdated() );
        activity.setComment( to.getComment() );
        activity.setStatusCode( to.getStatusCode() );
        activity.setPriorityCode( to.getPriorityCode() );
        activity.setTypeCode( to.getTypeCode() );
        activity.setDescription( to.getDescription() );
        activity.setEstimate( to.getEstimate() );

        return activity;
    }

    @Override
    public List<Activity> toEntityList(Collection<ActivityTo> tos) {
        if ( tos == null ) {
            return null;
        }

        List<Activity> list = new ArrayList<Activity>( tos.size() );
        for ( ActivityTo activityTo : tos ) {
            list.add( toEntity( activityTo ) );
        }

        return list;
    }

    @Override
    public ActivityTo toTo(Activity entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        Long taskId = null;
        Long authorId = null;
        LocalDateTime updated = null;
        String comment = null;
        String statusCode = null;
        String priorityCode = null;
        String typeCode = null;
        String title = null;
        String description = null;
        Integer estimate = null;
        UserTo author = null;

        id = entity.getId();
        taskId = entity.getTaskId();
        authorId = entity.getAuthorId();
        updated = entity.getUpdated();
        comment = entity.getComment();
        statusCode = entity.getStatusCode();
        priorityCode = entity.getPriorityCode();
        typeCode = entity.getTypeCode();
        title = entity.getTitle();
        description = entity.getDescription();
        estimate = entity.getEstimate();
        author = userToUserTo( entity.getAuthor() );

        ActivityTo activityTo = new ActivityTo( id, taskId, authorId, updated, comment, statusCode, priorityCode, typeCode, title, description, estimate, author );

        return activityTo;
    }

    @Override
    public List<ActivityTo> toToList(Collection<Activity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ActivityTo> list = new ArrayList<ActivityTo>( entities.size() );
        for ( Activity activity : entities ) {
            list.add( toTo( activity ) );
        }

        return list;
    }

    @Override
    public Activity updateFromTo(ActivityTo activityTo, Activity activity) {
        if ( activityTo == null ) {
            return activity;
        }

        activity.setId( activityTo.getId() );
        activity.setTitle( activityTo.getTitle() );
        if ( activityTo.getAuthor() != null ) {
            if ( activity.getAuthor() == null ) {
                activity.setAuthor( new User() );
            }
            userToToUser1( activityTo.getAuthor(), activity.getAuthor() );
        }
        else {
            activity.setAuthor( null );
        }
        activity.setAuthorId( activityTo.getAuthorId() );
        activity.setUpdated( activityTo.getUpdated() );
        activity.setComment( activityTo.getComment() );
        activity.setStatusCode( activityTo.getStatusCode() );
        activity.setPriorityCode( activityTo.getPriorityCode() );
        activity.setTypeCode( activityTo.getTypeCode() );
        activity.setDescription( activityTo.getDescription() );
        activity.setEstimate( activityTo.getEstimate() );

        activity.setTaskId( ActivityMapper.checkTaskBelong(activityTo.getTaskId(), activity) );

        return activity;
    }

    protected User userToToUser(UserTo userTo) {
        if ( userTo == null ) {
            return null;
        }

        User user = new User();

        user.setId( userTo.getId() );
        user.setDisplayName( userTo.getDisplayName() );
        user.setEmail( userTo.getEmail() );
        user.setPassword( userTo.getPassword() );
        user.setFirstName( userTo.getFirstName() );
        user.setLastName( userTo.getLastName() );

        return user;
    }

    protected UserTo userToUserTo(User user) {
        if ( user == null ) {
            return null;
        }

        UserTo userTo = new UserTo();

        userTo.setId( user.getId() );
        userTo.setEmail( user.getEmail() );
        userTo.setPassword( user.getPassword() );
        userTo.setFirstName( user.getFirstName() );
        userTo.setLastName( user.getLastName() );
        userTo.setDisplayName( user.getDisplayName() );

        return userTo;
    }

    protected void userToToUser1(UserTo userTo, User mappingTarget) {
        if ( userTo == null ) {
            return;
        }

        mappingTarget.setId( userTo.getId() );
        mappingTarget.setDisplayName( userTo.getDisplayName() );
        mappingTarget.setEmail( userTo.getEmail() );
        mappingTarget.setPassword( userTo.getPassword() );
        mappingTarget.setFirstName( userTo.getFirstName() );
        mappingTarget.setLastName( userTo.getLastName() );
    }
}
