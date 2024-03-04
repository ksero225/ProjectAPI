package com.ProjectManagerAPI.ProjectManager.mappers;

import org.springframework.stereotype.Component;


public interface Mapper<classA, classB> {
    classB mapTo(classA a);
    classA mapFrom(classB b);
}
