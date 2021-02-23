package de.microtema.model.builder.mix;


import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data
public class MixObject {

    String name;
    Long age;
    Class<String> type;
    Map<String, String> map;
    List<Object> list;
    EnumType enumType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MixObject)) return false;
        MixObject mixObject = (MixObject) o;
        return Objects.equals(name, mixObject.name) &&
                Objects.equals(age, mixObject.age) &&
                Objects.equals(type, mixObject.type) &&
                Objects.equals(map, mixObject.map) &&
                Objects.equals(list, mixObject.list) &&
                enumType == mixObject.enumType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, type, map, list, enumType);
    }

    enum EnumType {
        EASY, MIDDLE
    }
}
