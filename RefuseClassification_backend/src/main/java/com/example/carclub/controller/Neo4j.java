//package com.example.carclub.controller;
//import org.neo4j.driver.*;
//
//public class Neo4jExample {
//    public static void main(String[] args) {
//        try (Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("username", "password"));
//             Session session = driver.session()) {
//
//            // 查询所有节点
//            StatementResult nodeResult = session.run("MATCH (n) RETURN n");
//
//            while (nodeResult.hasNext()) {
//                Record record = nodeResult.next();
//                System.out.println(record.get("n").asNode().asMap());
//            }
//
//            // 查询所有关系
//            StatementResult relationshipResult = session.run("MATCH ()-[r]->() RETURN r");
//
//            while (relationshipResult.hasNext()) {
//                Record record = relationshipResult.next();
//                System.out.println(record.get("r").asRelationship().asMap());
//            }
//        }
//    }
//}
//
