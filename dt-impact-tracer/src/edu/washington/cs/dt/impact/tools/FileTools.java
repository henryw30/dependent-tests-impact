/**
 * Copyright 2014 University of Washington. All Rights Reserved.
 * @author Wing Lam
 * 
 * Used for debugging. Compares two test list and outputs the differences.
 */
package edu.washington.cs.dt.impact.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FileTools {

    public static void main(String[] args) {
        BufferedReader br;

        File f1 = new File(args[0]);
        List<String> f1Set = new LinkedList<String>();
        try {
            br = new BufferedReader(new FileReader(f1));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() == 0) {
                    continue;
                }
                f1Set.add(line.trim());
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File f2 = new File(args[1]);
        List<String> f2Set = new LinkedList<String>();
        try {
            br = new BufferedReader(new FileReader(f2));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() == 0) {
                    continue;
                }
                f2Set.add(line.trim());
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String s : f1Set) {
            f2Set.remove(s);
        }
        for (String s : f2Set) {
            System.out.println(s);
        }
    }

    // returns a list of Strings where each String is a line of the file
    public static List<String> parseFileToList(File orderFile) {
        if (orderFile == null) {
            return null;
        }
        List<String> tests = new ArrayList<String>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(orderFile));
            String line = br.readLine();

            while (line != null) {
                if (!line.equals("")) {
                    tests.add(line);
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tests;
    }

    // deletes the files in filesToDelete.
    // used to clear the environment of any temp files created by tests
    public static void clearEnv(List<String> filesToDelete) {
        for (String s : filesToDelete) {
            new File(s).delete();
        }
    }
}
