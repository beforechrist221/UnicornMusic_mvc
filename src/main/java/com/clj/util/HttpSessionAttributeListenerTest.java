package com.clj.util;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class HttpSessionAttributeListenerTest implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) { // session.setAttribute("key", "value1");
        System.out.println("attributeAdded, key: " + event.getName() + ", value: " + event.getValue());
    }
    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) { // session.invalidate()
        System.out.println("attributeRemoved, key: " + event.getName() + ", value: " + event.getValue());
    }
    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) { // session.setAttribute("key", "value2");

        System.out.println("attributeReplaced, key: " + event.getName() + ", value: " + event.getValue());

    }
}
