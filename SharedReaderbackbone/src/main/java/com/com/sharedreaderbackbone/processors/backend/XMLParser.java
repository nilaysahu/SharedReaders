package com.com.sharedreaderbackbone.processors.backend;
import com.sharedreaderbackbone.entity.backend.Book;

import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


/**
 * Created by nisahu on 18/08/2015.
 */
public class XMLParser {
    public static Document loadXMLFromString(String xml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        return builder.parse(is);
    }
    public static List<Book> Parse(String xml) {
        List<Book> bookList = new ArrayList<Book>();
        try {
            Document doc = loadXMLFromString(xml);
            NodeList nodeList = doc.getElementsByTagName("*");
            for (int i = 0; i < nodeList.getLength(); i++)
            {
                Node node = nodeList.item(i);
                Book book = new Book();
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    // do something with the current element
                    //System.out.println(node.getNodeName());
                    if(node.getNodeName().equals("title")) {
                        book.setName(node.getTextContent());
                        bookList.add(book);
                    }
                }

            }
        } catch (Exception ex) {
        }
        return bookList;
    }
}
