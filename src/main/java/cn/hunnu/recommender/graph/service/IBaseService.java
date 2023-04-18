package cn.hunnu.recommender.graph.service;


import cn.hunnu.recommender.graph.entity.BaseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础实体概念服务
 * xuxiang.zhang
 */
public interface IBaseService {

    /**
     * 根据名称和类型获取实体id
     *
     * @param name  实体名
     * @param label 实体类型
     * @return
     */
    public List<BaseEntity> getIdByNameAndLabel(String name, String label);

    /**
     * 根据id获取实体信息
     *
     * @param id 实体id
     * @return
     */
    public BaseEntity getById(String id);

    /**
     * 根据name获取实体
     *
     * @param name 实体名
     * @return
     */
    public List<BaseEntity> getByName(String name);

    /**
     * 根据label获取实体
     *
     * @param name
     * @return
     */
    public List<BaseEntity> getByLabel(String name);

    /**
     * 插入实体
     *
     * @param entity 实体对象
     * @return
     */
    public Boolean insert(BaseEntity entity);

    /**
     * 插入关系
     *
     * @param s 关系主语id
     * @param p 关系谓语
     * @param o 关系宾语id
     * @return
     */
    public Boolean insertRelation(String s, String p, String o);

    /**
     * 批量插入关系
     *
     * @param batch    关系集合
     * @param relation 关系类型
     * @return
     */
    public Boolean insertBatchRelation(List<HashMap<String, String>> batch, String relation);

    /**
     * 批量插入关系，并带一特定关系属性
     *
     * @param batch       关系集合
     * @param relation    关系类型
     * @param relationKey 关系属性
     * @return
     */
    public Boolean insertBatchRelationWithKey(List<HashMap<String, String>> batch, String relation, String relationKey);

    /**
     * 更新实体
     *
     * @param entity 实体对象
     * @return
     */
    public BaseEntity update(BaseEntity entity);

    /**
     * 删除实体及其所有关系
     *
     * @param entity 实体对象
     * @return
     */
    public boolean delete(BaseEntity entity);

    /**
     * 删除两实体之间的特定关系
     *
     * @param s 主语id
     * @param p 谓语关系
     * @param o 宾语id
     * @return
     */
    public boolean deleteRelation(String s, String p, String o);

    /**
     * 获取实体关联知识
     *
     * @return
     */
    public Map<String, List<String>> getKnowledges(String id);

    /**
     * 获取所有三元组
     *
     * @return
     */
    public Object getAllTriples();

}
