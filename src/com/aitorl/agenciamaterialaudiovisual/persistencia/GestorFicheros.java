package com.aitorl.agenciamaterialaudiovisual.persistencia;

import com.aitorl.agenciamaterialaudiovisual.modelo.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class GestorFicheros {
    public void exportarXML(Inventario inventario, File fichero) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        DOMImplementation dom = builder.getDOMImplementation();

        Document documento = dom.createDocument(null, "xml", null);

        Element raiz = documento.createElement("inventario");

        documento.getDocumentElement().appendChild(raiz);

        for (MaterialAudiovisual material: inventario.obtenerMateriales()) {
            Element nodoMaterial = documento.createElement("material");
            raiz.appendChild(nodoMaterial);
            nodoMaterial.setAttribute("tipo", material.getClass().getSimpleName());

            Element nodoDatos = documento.createElement("id");
            nodoMaterial.appendChild(nodoDatos);
            Text texto = documento.createTextNode(material.getId());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("marca");
            nodoMaterial.appendChild(nodoDatos);
            texto = documento.createTextNode(material.getMarca());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("modelo");
            nodoMaterial.appendChild(nodoDatos);
            texto = documento.createTextNode(material.getModelo());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("precio");
            nodoMaterial.appendChild(nodoDatos);
            texto = documento.createTextNode(String.valueOf(material.getPrecioPorDia()));
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("fechaRegistro");
            nodoMaterial.appendChild(nodoDatos);
            texto = documento.createTextNode(material.getFechaRegistro().toString());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("valoracion");
            nodoMaterial.appendChild(nodoDatos);
            texto = documento.createTextNode(String.valueOf(material.getValoracion()));
            nodoDatos.appendChild(texto);

            if(material instanceof Camara){
                Camara camara = (Camara) material;
                nodoDatos = documento.createElement("resolucion");
                nodoMaterial.appendChild(nodoDatos);
                texto = documento.createTextNode(camara.getResolucion());
                nodoDatos.appendChild(texto);
            }
            if(material instanceof Microfono){
                Microfono microfono = (Microfono) material;
                nodoDatos = documento.createElement("tipoConexion");
                nodoMaterial.appendChild(nodoDatos);
                texto = documento.createTextNode(microfono.getTipoConexion());
                nodoDatos.appendChild(texto);
            }

            if(material instanceof Foco){
                Foco foco = (Foco) material;
                nodoDatos = documento.createElement("potencia");
                nodoMaterial.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(foco.getPotenciaVatios()));
                nodoDatos.appendChild(texto);
            }

            if(material instanceof Altavoz){
                Altavoz altavoz = (Altavoz) material;
                nodoDatos = documento.createElement("potencia");
                nodoMaterial.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(altavoz.getPotenciaVatios()));
                nodoDatos.appendChild(texto);
            }
            if(material instanceof Proyector){
                Proyector proyector = (Proyector) material;
                nodoDatos = documento.createElement("luminosidad");
                nodoMaterial.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(proyector.getLuminosidad()));
                nodoDatos.appendChild(texto);
            }

            nodoDatos = documento.createElement("rutaImagen");
            nodoMaterial.appendChild(nodoDatos);
            texto = documento.createTextNode(material.getRutaImagen());
            nodoDatos.appendChild(texto);

        }

        Source src = new DOMSource(documento);

        Result resultado = new StreamResult(fichero);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();

        transformer.transform(src, resultado);

    }

    public Inventario importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document documento = builder.parse(fichero);

        NodeList materiales = documento.getElementsByTagName("material");
        Inventario inventarioImportado = new Inventario();

        for (int i = 0; i < materiales.getLength(); i++) {
            Element nodoMaterial = (Element) materiales.item(i);
            String tipo = nodoMaterial.getAttribute("tipo");
            String id = nodoMaterial.getElementsByTagName("id").item(0).getTextContent();
            String marca = nodoMaterial.getElementsByTagName("marca").item(0).getTextContent();
            String modelo = nodoMaterial.getElementsByTagName("modelo").item(0).getTextContent();
            Double precio = Double.parseDouble(nodoMaterial.getElementsByTagName("precio").item(0).getTextContent());
            LocalDate fechaRegistro = LocalDate.parse(nodoMaterial.getElementsByTagName("fechaRegistro").item(0).getTextContent());
            int valoracion = Integer.parseInt(nodoMaterial.getElementsByTagName("valoracion").item(0).getTextContent());
            String rutaImagen = nodoMaterial.getElementsByTagName("rutaImagen").item(0).getTextContent();

            if(tipo.equals("Camara")){
                String resolucion = nodoMaterial.getElementsByTagName("resolucion").item(0).getTextContent();
                Camara camara = new Camara(id, marca, modelo, precio, fechaRegistro, valoracion, rutaImagen, resolucion);
                inventarioImportado.agregarMaterial(camara);
            } else if (tipo.equals("Microfono")){
                String tipoConexion = nodoMaterial.getElementsByTagName("tipoConexion").item(0).getTextContent();
                Microfono microfono = new Microfono(id, marca, modelo, precio, fechaRegistro, valoracion, rutaImagen, tipoConexion);
                inventarioImportado.agregarMaterial(microfono);
            } else if (tipo.equals("Foco")){
                int potencia = Integer.parseInt(nodoMaterial.getElementsByTagName("potencia").item(0).getTextContent());
                Foco foco = new Foco(id, marca, modelo, precio, fechaRegistro, valoracion, rutaImagen, potencia);
                inventarioImportado.agregarMaterial(foco);
            } else if (tipo.equals("Altavoz")){
                int potencia = Integer.parseInt(nodoMaterial.getElementsByTagName("potencia").item(0).getTextContent());
                Altavoz altavoz = new Altavoz(id, marca, modelo, precio, fechaRegistro, valoracion, rutaImagen, potencia);
                inventarioImportado.agregarMaterial(altavoz);
            } else if (tipo.equals("Proyector")){
                int luminosidad = Integer.parseInt(nodoMaterial.getElementsByTagName("luminosidad").item(0).getTextContent());
                Proyector proyector = new Proyector(id, marca, modelo, precio, fechaRegistro, valoracion, rutaImagen, luminosidad);
                inventarioImportado.agregarMaterial(proyector);
            }
        }

        return inventarioImportado;
    }
}
