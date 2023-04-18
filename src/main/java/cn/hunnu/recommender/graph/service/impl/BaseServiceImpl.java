package cn.hunnu.recommender.graph.service.impl;

import cn.hunnu.recommender.graph.dao.Neo4jDao;
import cn.hunnu.recommender.graph.entity.BaseEntity;
import cn.hunnu.recommender.graph.service.IBaseService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.neo4j.driver.Values.parameters;

@Slf4j
@Service
public class BaseServiceImpl implements IBaseService {

    @Autowired
    Neo4jDao neo4jDao;

    @Override
    public List<BaseEntity> getIdByNameAndLabel(String name, String label) {
        log.info("get entity by name and label:{}", name);
        List<BaseEntity> results = new ArrayList<BaseEntity>();
        try (Session session = neo4jDao.getSession()) {

            Result result = session.run(
                    "MATCH (a:" + label + ") WHERE a.name=$name RETURN id(a) AS id, labels(a) AS labels",
                    parameters("name", name));

            BaseEntity en = new BaseEntity();
            while (result.hasNext()) {
                Record record = result.next();
                Value recordId = record.get("id");
                Value recordLabels = record.get("labels");

                String jsonId = recordId.toString();
                String jsonLabels = recordLabels.toString();

                String entityId = jsonId; // 实体id
                List<String> entityLabels = (List<String>) JSONArray.parseArray(jsonLabels, String.class); // 实体label

                en.setId(entityId);
                en.setLabel(entityLabels);
                en.setName(name);
            }
            results.add(en);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (results.size() > 0) {
            return results;
        } else {
            return null;
        }
    }

    @Override
    public BaseEntity getById(String id) {
        log.info("get entity by id:{}", id);
        List<BaseEntity> results = new ArrayList<BaseEntity>();
        try (Session session = neo4jDao.getSession()) {

            Result result = session.run(
                    "MATCH (a)-[r]->(b) WHERE id(a)=$id RETURN id(a) AS id, labels(a) AS labels, " +
                            "properties(a) AS properties, type(r) AS predicate, id(b) AS object",
                    parameters("id", new Integer(id)));

            BaseEntity en = new BaseEntity();
            Map<String, List<String>> entityProperties = new HashMap<>(); // 实体properties
            while (result.hasNext()) {
                Record record = result.next();
                Value recordId = record.get("id");
                Value recordLabels = record.get("labels");
                Value recordProperties = record.get("properties");

                String jsonId = recordId.toString();
                String jsonLabels = recordLabels.toString();
                String jsonProperties = recordProperties.toString();

                String entityId = jsonId; // 实体id
                List<String> entityLabels = (List<String>) JSONArray.parseArray(jsonLabels, String.class); // 实体label
                JSONObject proObj = JSON.parseObject(jsonProperties);
                Iterator it = proObj.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
                    entityProperties.put(entry.getKey(), Arrays.asList(entry.getValue().toString()));
                }

                Value recordPre = record.get("predicate");
                Value recordObj = record.get("object");
                if (entityProperties.get(recordPre.asString()) == null) {
                    List<String> objs = new ArrayList<String>();
                    objs.add(recordObj.toString());
                    entityProperties.put(recordPre.asString(), objs);
                } else {
                    entityProperties.get(recordPre.asString()).add(recordObj.toString());
                }

                en.setId(entityId);
                en.setLabel(entityLabels);
                en.setName((String) entityProperties.get("name").get(0));
            }
            en.setProperties(entityProperties);
            results.add(en);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (results.size() > 0) {
            return results.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<BaseEntity> getByName(String name) {
        log.info("get entity by name:{}", name);
        List<BaseEntity> results = new ArrayList<BaseEntity>();
        try (Session session = neo4jDao.getSession()) {

            Result result = session.run(
                    "MATCH (a)-[r]->(b) WHERE a.name=$name RETURN id(a) AS id, labels(a) AS labels, " +
                            "properties(a) AS properties, type(r) AS predicate, id(b) AS object",
                    parameters("name", name));

            BaseEntity en = new BaseEntity();
            Map<String, List<String>> entityProperties = new HashMap<>(); // 实体properties
            while (result.hasNext()) {
                Record record = result.next();
                Value recordId = record.get("id");
                Value recordLabels = record.get("labels");
                Value recordProperties = record.get("properties");

                String jsonId = recordId.toString();
                String jsonLabels = recordLabels.toString();
                String jsonProperties = recordProperties.toString();

                String entityId = jsonId; // 实体id
                List<String> entityLabels = (List<String>) JSONArray.parseArray(jsonLabels, String.class); // 实体label
                JSONObject proObj = JSON.parseObject(jsonProperties);
                Iterator it = proObj.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
                    entityProperties.put(entry.getKey(), Arrays.asList(entry.getValue().toString()));
                }

                Value recordPre = record.get("predicate");
                Value recordObj = record.get("object");
                if (entityProperties.get(recordPre.asString()) == null) {
                    List<String> objs = new ArrayList<String>();
                    objs.add(recordObj.toString());
                    entityProperties.put(recordPre.asString(), objs);
                } else {
                    entityProperties.get(recordPre.asString()).add(recordObj.toString());
                }

                en.setId(entityId);
                en.setLabel(entityLabels);
                en.setName((String) entityProperties.get("name").get(0));
            }
            en.setProperties(entityProperties);
            results.add(en);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (results.size() > 0) {
            return results;
        } else {
            return null;
        }
    }

    @Override
    public List<BaseEntity> getByLabel(String name) {

        return null;
    }

    @Override
    public Boolean insert(BaseEntity entity) {
        log.info("insert entity {}", entity.getName());
        //插入新节点
        //CREATE (n:Person{name:‘CQR’,title:‘Helper’}) return n;

        try (Session session = neo4jDao.getSession()) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("name", entity.getName());
            Result result = session.run(
                    "CREATE (n:" + entity.getLabel().get(0) + "{name:$name}) return id(n) as id",
                    parameters("name", entity.getName()));

            while (result.hasNext()) {
                Record record = result.next();
                Value recordId = record.get("id");
                String entityId = recordId.toString();
                log.info("新增实体：{}", entityId);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean insertRelation(String s, String p, String o) {
        log.info("insert relation " + s + "-" + p + "->" + o);
        //给匹配的现有节点插入关系
        //match (n:Person{name:“Zhangjian”}),(m:Person{name:“Chenqiurui”}) create (n)-[r:Friend]->(m) return r;

        try (Session session = neo4jDao.getSession()) {
            Result result = session.run(
                    "match (s),(o) where id(s)=$sid and id(o)=$oid create (s)-[:" + p + "]->(o) return s,o",
                    parameters("sid", new Integer(s), "oid", new Integer(o)));
            while (result.hasNext()) {
                Record record = result.next();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean insertBatchRelation(List<HashMap<String, String>> batch, String relation) {
        log.info("insert batch relation size:{}", batch.size());
        //给匹配的现有节点插入关系
        //match (n:Person{name:“Zhangjian”}),(m:Person{name:“Chenqiurui”}) create (n)-[r:Friend]->(m) return r;

        String sql = "unwind " + JSONObject.toJSONString(batch) + " as row match (s),(o) where id(s)=row.sid and id(o)=row.oid create (s)-[:" + relation + "]->(o)";
        sql = sql.replaceAll("\"", ""); // 去除json字符串的双引号
        try (Session session = neo4jDao.getSession()) {
            Result result = session.run(sql);
            while (result.hasNext()) {
                Record record = result.next();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean insertBatchRelationWithKey(List<HashMap<String, String>> batch, String relation, String relationKey) {
        log.info("insert batch relation with key size:{}, relationKey:{}", batch.size(), relationKey);
        //给匹配的现有节点插入关系
        //match (n:Person{name:“Zhangjian”}),(m:Person{name:“Chenqiurui”}) create (n)-[r:Friend]->(m) return r;

        String sql = "unwind " + JSONObject.toJSONString(batch) + " as row match (s),(o) where id(s)=row.sid and id(o)=row.oid create (s)-[:" + relation + " {"
                + relationKey + ":row.times}" + "]->(o)";
        sql = sql.replaceAll("\"", ""); // 去除json字符串的双引号
        try (Session session = neo4jDao.getSession()) {
            Result result = session.run(sql);
            while (result.hasNext()) {
                Record record = result.next();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public BaseEntity update(BaseEntity entity) {
        return null;
    }

    @Override
    public boolean delete(BaseEntity entity) {
        log.info("delete entity {}", entity.getName());
        try (Session session = neo4jDao.getSession()) {
            Result result = session.run(
                    "MATCH (s) WHERE id(s)=$id DETACH DELETE s",
                    parameters("id", new Integer(entity.getId())));

            while (result.hasNext()) {
                Record record = result.next();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteRelation(String s, String p, String o) {
        log.info("delete relation " + " " + s + "-" + p + "->" + o);
        try (Session session = neo4jDao.getSession()) {
            Result result = session.run(
                    "MATCH (s)-[r:$p]->(o) WHERE id(s)=$sid and id(o)=$oid DELETE r",
                    parameters("sid", new Integer(s), "p", p, "oid", new Integer(o)));

            while (result.hasNext()) {
                Record record = result.next();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Map<String, List<String>> getKnowledges(String id) {
        return null;
    }

    @Override
    public Object getAllTriples() {
        log.info("get all triples!");

        try (Session session = neo4jDao.getSession()) {
            Result result = session.run(
                    "MATCH (n)-[r]->(m) RETURN id(n) as id_n,id(r) as id_r,id(m) as id_m");

//            String target = "/Users/derrickzhang/Downloads/EdgeList.csv";
//            BufferedWriter output = new BufferedWriter(new FileWriter(target));
//            output.write("id_n,id_r,id_m");
//            output.newLine();
//            while (result.hasNext()) {
//                Record record = result.next();
//                Value n = record.get("id_n");
//                Value r = record.get("id_r");
//                Value m = record.get("id_m");
//
//                output.write(n.toString() +" " + m.toString());
//                output.newLine();
//            }
//            output.close();

            return result.list().size();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
