package com.thoughtworks.acceptance;

import com.thoughtworks.xstream.XStream;

public class RelativeXPathNestedCircularReferenceTest extends AbstractNestedCircularReferenceTest {

    // inherits test from superclass
    protected void setUp() throws Exception {
        super.setUp();
        xstream.setMode(XStream.XPATH_RELATIVE_REFERENCES);
    }

}
