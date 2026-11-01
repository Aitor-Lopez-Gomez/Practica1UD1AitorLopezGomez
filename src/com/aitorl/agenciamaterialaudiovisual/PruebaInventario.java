package com.aitorl.agenciamaterialaudiovisual;

import com.aitorl.agenciamaterialaudiovisual.modelo.Camara;
import com.aitorl.agenciamaterialaudiovisual.modelo.Inventario;

import java.time.LocalDate;

public class PruebaInventario {

    public static void main(String[] args) {

        Inventario inventario = new Inventario();

        Camara camara = new Camara(
                "MAT001",
                "Canon",
                "EOS R50",
                25.0,
                LocalDate.now(),
                4,
                "resource/camara.jpg",
                "24 MP"

        );
        Camara camara1 = new Camara(
                "MAT001",
                "Canon2",
                "EOS R50",
                25.0,
                LocalDate.now(),
                4,
                "resource/camara.jpg",
                "24 MP"

        );

        boolean resultado = inventario.agregarMaterial(camara);
        boolean duplicado = inventario.actualizarMaterial(camara1);

        System.out.println("Material añadido: " + resultado);
        System.out.println(inventario.buscarMaterial("MAT001"));


    }
}