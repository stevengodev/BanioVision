package com.foliaco.bathrooms.infrastructure.annotations;

import java.lang.annotation.Annotation;

public class AuditEntityNameResolver {

    public static String getCustomEntityName(Class<?> entityClass ){

        if (entityClass.isAnnotationPresent(AuditedEntityName.class)) {
            AuditedEntityName annotation = entityClass.getAnnotation(AuditedEntityName.class);
            return annotation.value();
        }

        // Si no está la anotación, se devuelve el nombre de la clase
        return entityClass.getSimpleName();

    }
}
