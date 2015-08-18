package com.example.nisahu.myapplication.backend;
import com.sharedreaderbackbone.entity.backend.*;
import com.com.sharedreaderbackbone.processors.backend.XMLParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nisahu on 18/08/2015.
 * Handler .
 * Provide business logic to the APIs exposed.
 */
public class Slayer {
    public static String GetBookByQuery(String query)
    {
        StringBuilder response = new StringBuilder();
        HttpRequestHandler request = new HttpRequestHandler();
        String xml = request.Perform(query);
        List<Book> bookList = XMLParser.Parse(xml);
        for (Book book:bookList) {
            response.append(book.getName()+'\n');
        }
        return response.toString();
    }
}
