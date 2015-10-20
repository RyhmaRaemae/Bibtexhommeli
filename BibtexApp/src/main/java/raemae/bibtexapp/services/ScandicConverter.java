/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raemae.bibtexapp.services;

/**
 *
 * @author Markku
 */
public class ScandicConverter {

    public static String scandToBibTex(String s) {
        String result = s;
        result = result.replace("å", "{\\'a}");
        result = result.replace("ä", "{\\\"a}");
        result = result.replace("ö", "{\\\"o}");
        result = result.replace("Å", "{\\'A}");
        result = result.replace("Ä", "{\\\"A}");
        result = result.replace("Ö", "{\\\"O}");
        return result;
    }

    public static String bibTexToScand(String s) {
        String result = s;
        result = result.replace("{\\'a}", "å");
        result = result.replace("{\\\"a}", "ä");
        result = result.replace("{\\\"o}", "ö");
        result = result.replace("{\\'A}", "Å");
        result = result.replace("{\\\"A}", "Ä");
        result = result.replace("{\\\"O}", "Ö");
        return result;
    }

    public static String cleanCitationKey(String s) {
        String result = s;
        result = result.toLowerCase();
        result = result.replace("å", "a");
        result = result.replace("ä", "a");
        result = result.replace("ö", "o");
        return result;
    }

}
