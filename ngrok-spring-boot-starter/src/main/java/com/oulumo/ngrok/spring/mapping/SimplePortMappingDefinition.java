package com.oulumo.ngrok.spring.mapping;

import java.util.Objects;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class SimplePortMappingDefinition implements IPortMappingDefinition {
    private String name;
    private Integer port;
    private PortType portType;

    public SimplePortMappingDefinition(String name, Integer port, PortType portType) {
        this.name = name;
        this.port = port;
        this.portType = portType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getPort() {
        return port;
    }

    @Override
    public PortType getPortType() {
        return portType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SimplePortMappingDefinition)) {
            return false;
        }
        SimplePortMappingDefinition that = (SimplePortMappingDefinition) o;
        return Objects.equals(getName(), that.getName()) &&
               Objects.equals(getPort(), that.getPort()) &&
               getPortType() == that.getPortType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPort(), getPortType());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ngrok mapping {");

        sb.append("name='").append(name).append('\'');
        sb.append(", port=").append(port);
        sb.append(", portType=").append(portType);
        sb.append('}');

        return sb.toString();
    }
}
