package com.foliaco.bathrooms.domain.ports.in;

/**
 * Interfaz que define los métodos para gestionar el estado de eliminación lógica
 * de una entidad. Esta interfaz permite marcar registros como eliminados lógicamente
 * en lugar de eliminarlos físicamente de la base de datos, facilitando su
 * restauración y el mantenimiento de la integridad de los datos.
 */

public interface LogicallyDeletable {

    /**
     * Método para marcar un registro de la entidad como eliminado lógicamente
     * */
    void logicalDelete();

    /**
     * Método para verificar si el registro de la entidad está eliminado lógicamente
     * @return true si el registro de la entidad está eliminado
     * */
    boolean isDeleted();

    /**
     * Método para restaurar el registro de la entidad  */
    void restore();
}
