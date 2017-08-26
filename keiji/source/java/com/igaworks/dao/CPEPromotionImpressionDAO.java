package com.igaworks.dao;

@Deprecated
public class CPEPromotionImpressionDAO {
    protected static CPEPromotionImpressionDAO singleton;

    private CPEPromotionImpressionDAO() {
    }

    public static CPEPromotionImpressionDAO getInstance() {
        if (singleton == null) {
            singleton = new CPEPromotionImpressionDAO();
        }
        return singleton;
    }
}
