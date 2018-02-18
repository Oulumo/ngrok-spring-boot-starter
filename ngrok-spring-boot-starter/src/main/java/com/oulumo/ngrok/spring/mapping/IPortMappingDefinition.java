package com.oulumo.ngrok.spring.mapping;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public interface IPortMappingDefinition {
    public enum PortType {
        Http, Tcp
    }

    String getName();

    Integer getPort();

    PortType getPortType();
}
