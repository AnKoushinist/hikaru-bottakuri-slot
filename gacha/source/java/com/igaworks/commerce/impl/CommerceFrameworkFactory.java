package com.igaworks.commerce.impl;

import com.igaworks.commerce.interfaces.CommerceInterface;

public class CommerceFrameworkFactory {
    private static CommerceInterface singleton = new CommerceImpl();

    static {
        if (singleton == null) {
        }
    }
}
