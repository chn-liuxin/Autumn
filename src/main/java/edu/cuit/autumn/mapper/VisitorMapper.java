package edu.cuit.autumn.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorMapper {


    @Select("select number from visitor where type=\"total\"")
    int getVisitorNum();


    @Update("update visitor  set number=number+1 where type=\"total\"")
    void renewVisitorNum();

}