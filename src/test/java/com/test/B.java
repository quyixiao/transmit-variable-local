package com.test;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

public class B extends A{


    @Override
    public void getProps(Person person) {
        person.setAge("10");
        person.setName("20");

        super.getProps(person);

    }
}
