package com.javarush.jira.ref.internal;

import com.javarush.jira.ref.RefTo;
import com.javarush.jira.ref.RefType;
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
public class ReferenceMapperImpl implements ReferenceMapper {

    @Override
    public Reference toEntity(RefTo to) {
        if ( to == null ) {
            return null;
        }

        Reference reference = new Reference();

        reference.setTitle( to.getTitle() );
        reference.setRefType( to.getRefType() );
        reference.setCode( to.getCode() );
        reference.setAux( to.getAux() );

        return reference;
    }

    @Override
    public List<Reference> toEntityList(Collection<RefTo> tos) {
        if ( tos == null ) {
            return null;
        }

        List<Reference> list = new ArrayList<Reference>( tos.size() );
        for ( RefTo refTo : tos ) {
            list.add( toEntity( refTo ) );
        }

        return list;
    }

    @Override
    public Reference updateFromTo(RefTo to, Reference entity) {
        if ( to == null ) {
            return entity;
        }

        entity.setTitle( to.getTitle() );
        entity.setRefType( to.getRefType() );
        entity.setCode( to.getCode() );
        entity.setAux( to.getAux() );

        return entity;
    }

    @Override
    public RefTo toTo(Reference entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String title = null;
        RefType refType = null;
        String code = null;
        String aux = null;

        id = entity.getId();
        title = entity.getTitle();
        refType = entity.getRefType();
        code = entity.getCode();
        aux = entity.getAux();

        RefTo refTo = new RefTo( id, refType, code, title, aux );

        refTo.setEnabled( entity.isEnabled() );

        return refTo;
    }

    @Override
    public List<RefTo> toToList(Collection<Reference> entities) {
        if ( entities == null ) {
            return null;
        }

        List<RefTo> list = new ArrayList<RefTo>( entities.size() );
        for ( Reference reference : entities ) {
            list.add( toTo( reference ) );
        }

        return list;
    }
}
