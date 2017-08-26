package com.igaworks.dao;

public class CPEImpressionDAOFactory {
    public static AbstractCPEImpressionDAO getImpressionDAO(String str, String str2, int i) {
        if (!str.equals("impression")) {
            return null;
        }
        if (str2.equals("session_count")) {
            return CPESessionImpressionDAO.getInstance();
        }
        return CPEPersistImpressionDAO.getInstance();
    }
}
