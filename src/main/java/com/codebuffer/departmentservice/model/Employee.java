package com.codebuffer.departmentservice.model;

public record Employee(Long id, Long departmentId, String name, int age, String position) {

// will have all the getters, no setters
}
