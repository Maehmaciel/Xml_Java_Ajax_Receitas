/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aula;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class MostraXML {
    Document doc;
    public MostraXML(String caminho)
    {
        try {
            DocumentBuilderFactory fabrica=DocumentBuilderFactory.newInstance();
            DocumentBuilder construtor=fabrica.newDocumentBuilder();
            doc=construtor.parse(caminho);
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            Logger.getLogger(MostraXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     
       public void mandaReceita(int id)
    {        Element data = null;
     Element raiz=doc.getDocumentElement();
     String[] receitaNome =this.pegaNomeReceita().split(",");
     NodeList filho = raiz.getChildNodes();
     Node fi = raiz.getChildNodes().item(id);
    for(int i=0;i<filho.getLength();i++){ 
         for(int j=0;j<filho.getLength();j++){ 
        
        raiz.removeChild(raiz.getFirstChild());
         }
         raiz.removeChild(raiz.getFirstChild());
        }
      
        raiz.appendChild(fi);
    }
      
      
    public String pegaNomeReceita()
    {
        String texto="";
        Element raiz=doc.getDocumentElement();
        NodeList nomesReceitas=doc.getElementsByTagName("nome");
        int quant=nomesReceitas.getLength();
        for(int i=0;i<quant;i++)
        {
            if(nomesReceitas.item(i).getNodeType()==Node.ELEMENT_NODE)
            {
                texto+=nomesReceitas.item(i).getParentNode().getAttributes().getNamedItem("categoria").getNodeValue()+":"
                        + ""+nomesReceitas.item(i).getFirstChild().getNodeValue()+",";
            }
        }
        return texto;
    }
 
    public void mandaNomeReceita()
    {      Element data = null;
     Element raiz=doc.getDocumentElement();
     String[] receitaNome =this.pegaNomeReceita().split(",");
     NodeList filho = raiz.getChildNodes();
     for(int i=0;i<filho.getLength();i++){ 
         for(int j=0;j<filho.getLength();j++){ 
        
        raiz.removeChild(raiz.getFirstChild());
         }
         raiz.removeChild(raiz.getFirstChild());
        }
        for(String nr :receitaNome){
        data=doc.createElement(nr.split(":")[0]);
        Text texto=doc.createTextNode(nr.split(":")[1]);
        data.appendChild(texto);
        raiz.appendChild(data);
        
        }
    }
    
    
    public void serealizar(PrintStream out)
    {
        try {
            TransformerFactory fabrica=TransformerFactory.newInstance();
            Transformer transformador=fabrica.newTransformer();
            DOMSource fonte=new DOMSource(doc);
            StreamResult saida=new StreamResult(out);
            transformador.transform(fonte, saida);
        } catch (TransformerException ex) {
            Logger.getLogger(MostraXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void serealizar(Writer out)
    {
        try {
            TransformerFactory fabrica=TransformerFactory.newInstance();
            Transformer transformador=fabrica.newTransformer();
            DOMSource fonte=new DOMSource(doc);
            StreamResult saida=new StreamResult(out);
            transformador.transform(fonte, saida);
        } catch (TransformerException ex) {
            Logger.getLogger(MostraXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
