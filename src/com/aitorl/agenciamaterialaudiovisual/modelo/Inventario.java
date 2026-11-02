package com.aitorl.agenciamaterialaudiovisual.modelo;

import java.util.Collection;
import java.util.LinkedHashMap;

public class Inventario {
    private LinkedHashMap<String, MaterialAudiovisual> materiales;

    public Inventario(){
        materiales = new LinkedHashMap<>();
    }

    public boolean agregarMaterial(MaterialAudiovisual material){
        if (materiales.containsKey(material.getId())){
            return false;
        }
        materiales.put(material.getId(), material);
        return true;
    }

    public MaterialAudiovisual buscarMaterial(String id){

        return materiales.get(id);
    }

    public boolean eliminarMaterial(String id){

        return materiales.remove(id) != null;
    }

    public boolean actualizarMaterial(MaterialAudiovisual material){
        if(!materiales.containsKey(material.getId())){
            return false;
        }
        materiales.put(material.getId(), material);
        return true;
    }

    public Collection<MaterialAudiovisual> obtenerMateriales(){

        return materiales.values();
    }
}
