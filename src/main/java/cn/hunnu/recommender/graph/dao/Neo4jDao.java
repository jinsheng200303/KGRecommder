package cn.hunnu.recommender.graph.dao;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * neo4j数据库连接类
 */
@Component
public class Neo4jDao {

    /**
     * 数据库连接地址
     */
    @Value("${spring.neo4j.url}")
    private String URL;

    /**
     * 数据库连接用户名
     */
    @Value("${spring.neo4j.user}")
    private String USER;

    /**
     * 数据库连接密码
     */
    @Value("${spring.neo4j.password}")
    private String PASSWORD;

    /**
     * 加载的静态驱动
     */
    private static Driver driver;

    /**
     * 获取驱动
     *
     * @return
     */
    private Driver getNeo4jDriver() {
        if (driver == null) { // 若驱动不存在则重新初始化
            driver = GraphDatabase.driver(URL, AuthTokens.basic(USER, PASSWORD));
        }
        return driver;
    }

    /**
     * 获取数据库连接session
     *
     * @return
     */
    public Session getSession() {
        return getNeo4jDriver().session();
    }

    /**
     * 关闭驱动所有连接
     */
    public void close() {
        driver.close();
    }


}
