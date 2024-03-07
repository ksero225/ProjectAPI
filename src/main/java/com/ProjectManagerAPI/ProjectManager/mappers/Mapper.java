package com.ProjectManagerAPI.ProjectManager.mappers;

public interface Mapper<classA, classB> {
    classB mapTo(classA a);
    classA mapFrom(classB b);
}
