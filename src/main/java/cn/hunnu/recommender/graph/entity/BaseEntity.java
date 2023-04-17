package cn.hunnu.recommender.graph.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础实体类
 */
public class BaseEntity implements Serializable {
    /**
     * 实体唯一id
     */
    private String id;
    /**
     * 实体name
     */
    private String name;
    /**
     * 实体label
     */
    private List<String> label;
    /**
     * 实体属性
     */
    private Map<String, List<String>> properties = new HashMap<String, List<String>>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLabel() {
        return label;
    }

    public void setLabel(List<String> label) {
        this.label = label;
    }

    public Map<String, List<String>> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, List<String>> properties) {
        this.properties = properties;
    }
}
