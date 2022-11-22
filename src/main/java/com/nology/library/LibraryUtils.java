package com.nology.library;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class LibraryUtils {
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
}
