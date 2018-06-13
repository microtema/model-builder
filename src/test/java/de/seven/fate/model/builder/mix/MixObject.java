package de.seven.fate.model.builder.mix;


import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MixObject {

    String name;
    Long age;
    Class<String> type;
    Map<String, String> map;
    List<Object> list;
    EnumType enumType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Class<String> getType() {
        return type;
    }

    public void setType(Class<String> type) {
        this.type = type;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    public EnumType getEnumType() {
        return enumType;
    }

    public void setEnumType(EnumType enumType) {
        this.enumType = enumType;
    }

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
