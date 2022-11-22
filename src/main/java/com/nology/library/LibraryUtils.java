package com.nology.library;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class LibraryUtils {

    private static ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public static List<String[]> csvToList(String file) throws IOException{
        List<String[]> retVal = new ArrayList<>();

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while ((line = br.readLine()) != null){
            retVal.add(line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1));
        }
        return retVal;
    }

    public static Book stringArrToBook(String[] s){
        for (int i = 0; i < s.length; i++) {
            if(s[i].contains("\"")){
                s[i] = s[i].substring(1, s[i].length()-1);
            }
        }

        return new Book(Integer.parseInt(s[0]),s[1],s[2],s[3],s[4],s[5]);
    }

    public static Book jsonToBook(String s) throws JsonProcessingException {
        return objectMapper.readValue(s,Book.class);
    }

    public static List<Book> jsonToBookList (File file) throws IOException {
        return objectMapper.readValue(file, new TypeReference<List<Book>>(){});
    }

    public static void toJson(Object o, String file) throws IOException {
        objectMapper.writeValue(new File(file), o);
    }
}
