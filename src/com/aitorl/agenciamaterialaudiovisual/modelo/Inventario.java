package com.aitorl.agenciamaterialaudiovisual.modelo;

import java.util.Collection;
import java.util.LinkedHashMap;

/**
 * Gestiona el inventario de materiales audiovisuales.
 * Permite añadir, buscar, modificar y eliminar materiales.
 * Los materiales se almacenan en un LinkedHashMap.
 *
 */
public class Inventario {
    private LinkedHashMap<String, MaterialAudiovisual> materiales;

    public Inventario(){
        materiales = new LinkedHashMap<>();
    }
    /**
     * Añade un material al inventario si su ID no existe.
     *
     * @param material Material audiovisual que queremos añadir.
     * @return true si se añade correctamente, false si el ID ya existe.
     */
    public boolean agregarMaterial(MaterialAudiovisual material){
        if (materiales.containsKey(material.getId())){
            return false;
        }
        materiales.put(material.getId(), material);
        return true;
    }
    /**
     * Busca un material en el inventario usando su ID.
     *
     * @param id Identificador del material que queremos buscar.
     * @return El material encontrado o null si no existe.
     */
    public MaterialAudiovisual buscarMaterial(String id){

        return materiales.get(id);
    }
    /**
     * Elimina un material del inventario mediante su ID.
     *
     * @param id Identificador del material que queremos eliminar.
     * @return true si se ha eliminado, false si no se ha encontrado.
     */
    public boolean eliminarMaterial(String id){

        return materiales.remove(id) != null;
    }
    /**
     * Actualiza los datos de un material existente en el inventario.
     *
     * @param material Material audiovisual con los datos actualizados.
     * @return true si se actualiza correctamente, false si el ID no existe.
     */
    public boolean actualizarMaterial(MaterialAudiovisual material){
        if(!materiales.containsKey(material.getId())){
            return false;
        }
        materiales.put(material.getId(), material);
        return true;
    }
    /**
     * Obtiene todos los materiales almacenados en el inventario.
     *
     * @return Colección de materiales audiovisuales.
     */
    public Collection<MaterialAudiovisual> obtenerMateriales(){

        return materiales.values();
    }
}
