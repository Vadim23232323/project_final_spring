package com.javarush.jira.login.internal;

import com.javarush.jira.login.Role;
import com.javarush.jira.login.User;
import com.javarush.jira.login.UserTo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-13T12:03:22+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public List<User> toEntityList(Collection<UserTo> tos) {
        if ( tos == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( tos.size() );
        for ( UserTo userTo : tos ) {
            list.add( toEntity( userTo ) );
        }

        return list;
    }

    @Override
    public UserTo toTo(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserTo userTo = new UserTo();

        userTo.setId( entity.getId() );
        userTo.setEmail( entity.getEmail() );
        userTo.setPassword( entity.getPassword() );
        userTo.setFirstName( entity.getFirstName() );
        userTo.setLastName( entity.getLastName() );
        userTo.setDisplayName( entity.getDisplayName() );

        return userTo;
    }

    @Override
    public List<UserTo> toToList(Collection<User> entities) {
        if ( entities == null ) {
            return null;
        }

        List<UserTo> list = new ArrayList<UserTo>( entities.size() );
        for ( User user : entities ) {
            list.add( toTo( user ) );
        }

        return list;
    }

    @Override
    public User toEntity(UserTo to) {
        if ( to == null ) {
            return null;
        }

        User user = new User();

        user.setId( to.getId() );
        user.setDisplayName( to.getDisplayName() );
        user.setEmail( to.getEmail() );
        user.setPassword( to.getPassword() );
        user.setFirstName( to.getFirstName() );
        user.setLastName( to.getLastName() );

        user.setRoles( EnumSet.of(Role.DEV) );

        return user;
    }

    @Override
    public User updateFromTo(UserTo to, User entity) {
        if ( to == null ) {
            return entity;
        }

        entity.setId( to.getId() );
        entity.setDisplayName( to.getDisplayName() );
        entity.setEmail( to.getEmail() );
        entity.setFirstName( to.getFirstName() );
        entity.setLastName( to.getLastName() );

        return entity;
    }
}
