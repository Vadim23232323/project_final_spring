package com.javarush.jira.bugtracking.attachment;

import com.javarush.jira.bugtracking.attachment.to.AttachmentTo;
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
public class AttachmentMapperImpl implements AttachmentMapper {

    @Override
    public Attachment toEntity(AttachmentTo to) {
        if ( to == null ) {
            return null;
        }

        Attachment attachment = new Attachment();

        attachment.setId( to.getId() );
        attachment.setName( to.getName() );

        return attachment;
    }

    @Override
    public List<Attachment> toEntityList(Collection<AttachmentTo> tos) {
        if ( tos == null ) {
            return null;
        }

        List<Attachment> list = new ArrayList<Attachment>( tos.size() );
        for ( AttachmentTo attachmentTo : tos ) {
            list.add( toEntity( attachmentTo ) );
        }

        return list;
    }

    @Override
    public Attachment updateFromTo(AttachmentTo to, Attachment entity) {
        if ( to == null ) {
            return entity;
        }

        entity.setId( to.getId() );
        entity.setName( to.getName() );

        return entity;
    }

    @Override
    public AttachmentTo toTo(Attachment entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String name = null;

        id = entity.getId();
        name = entity.getName();

        AttachmentTo attachmentTo = new AttachmentTo( id, name );

        return attachmentTo;
    }

    @Override
    public List<AttachmentTo> toToList(Collection<Attachment> entities) {
        if ( entities == null ) {
            return null;
        }

        List<AttachmentTo> list = new ArrayList<AttachmentTo>( entities.size() );
        for ( Attachment attachment : entities ) {
            list.add( toTo( attachment ) );
        }

        return list;
    }
}
