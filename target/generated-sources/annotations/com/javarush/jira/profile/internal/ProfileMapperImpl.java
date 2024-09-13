package com.javarush.jira.profile.internal;

import com.javarush.jira.profile.ContactTo;
import com.javarush.jira.profile.ProfileTo;
import com.javarush.jira.profile.internal.model.Contact;
import com.javarush.jira.profile.internal.model.Profile;
import com.javarush.jira.profile.internal.web.ProfilePostRequest;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-13T12:03:22+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class ProfileMapperImpl implements ProfileMapper {

    @Override
    public ProfileTo toTo(Profile entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        Set<ContactTo> contacts = null;

        id = entity.getId();
        contacts = contactSetToContactToSet( entity.getContacts() );

        Set<String> mailNotifications = ProfileUtil.maskToNotifications(entity.getMailNotifications());

        ProfileTo profileTo = new ProfileTo( id, mailNotifications, contacts );

        profileTo.setLastLogin( entity.getLastLogin() );

        return profileTo;
    }

    @Override
    public Profile updateFromTo(Profile entity, ProfileTo to) {
        if ( to == null ) {
            return entity;
        }

        entity.setId( to.getId() );
        if ( entity.getContacts() != null ) {
            Set<Contact> set = contactToSetToContactSet( to.getContacts() );
            if ( set != null ) {
                entity.getContacts().clear();
                entity.getContacts().addAll( set );
            }
            else {
                entity.setContacts( null );
            }
        }
        else {
            Set<Contact> set = contactToSetToContactSet( to.getContacts() );
            if ( set != null ) {
                entity.setContacts( set );
            }
        }

        entity.setMailNotifications( ProfileUtil.notificationsToMask(to.getMailNotifications()) );

        return entity;
    }

    @Override
    public Contact toContact(ContactTo contact) {
        if ( contact == null ) {
            return null;
        }

        Contact contact1 = new Contact();

        contact1.setId( contact.getId() );
        contact1.setCode( contact.getCode() );
        contact1.setValue( contact.getValue() );

        return contact1;
    }

    @Override
    public ProfileTo fromPostToTo(ProfilePostRequest profilePostRequest) {
        if ( profilePostRequest == null ) {
            return null;
        }

        Set<ContactTo> contacts = null;
        Set<String> mailNotifications = null;

        contacts = contactToArrayToContactToSet( profilePostRequest.getContacts() );
        Set<String> set1 = profilePostRequest.getMailNotifications();
        if ( set1 != null ) {
            mailNotifications = new LinkedHashSet<String>( set1 );
        }

        Long id = null;

        ProfileTo profileTo = new ProfileTo( id, mailNotifications, contacts );

        return profileTo;
    }

    protected ContactTo contactToContactTo(Contact contact) {
        if ( contact == null ) {
            return null;
        }

        ContactTo contactTo = new ContactTo();

        contactTo.setId( contact.getId() );
        contactTo.setCode( contact.getCode() );
        contactTo.setValue( contact.getValue() );

        return contactTo;
    }

    protected Set<ContactTo> contactSetToContactToSet(Set<Contact> set) {
        if ( set == null ) {
            return null;
        }

        Set<ContactTo> set1 = new LinkedHashSet<ContactTo>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Contact contact : set ) {
            set1.add( contactToContactTo( contact ) );
        }

        return set1;
    }

    protected Set<Contact> contactToSetToContactSet(Set<ContactTo> set) {
        if ( set == null ) {
            return null;
        }

        Set<Contact> set1 = new LinkedHashSet<Contact>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ContactTo contactTo : set ) {
            set1.add( toContact( contactTo ) );
        }

        return set1;
    }

    protected Set<ContactTo> contactToArrayToContactToSet(ContactTo[] contactToArray) {
        if ( contactToArray == null ) {
            return null;
        }

        Set<ContactTo> set = new LinkedHashSet<ContactTo>( Math.max( (int) ( contactToArray.length / .75f ) + 1, 16 ) );
        for ( ContactTo contactTo : contactToArray ) {
            set.add( contactTo );
        }

        return set;
    }
}
