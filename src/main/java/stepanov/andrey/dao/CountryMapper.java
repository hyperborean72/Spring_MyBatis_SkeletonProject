package stepanov.andrey.dao;

import stepanov.andrey.model.Country;

import java.util.List;

/**
 * @author: a.stepanov
 */

public interface CountryMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Country record);

    Country selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Country record);

    List<Country> getAll();


//    no xml approach

    /*@Select("SELECT * FROM Country WHERE id = #{id}")
    public Country selectByPrimaryKey(@Param("id") int uid);*/
}
