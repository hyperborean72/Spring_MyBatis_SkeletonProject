package stepanov.andrey.service;

import org.springframework.transaction.annotation.Transactional;

import stepanov.andrey.dao.CountryMapper;
import stepanov.andrey.model.Country;

import java.util.List;

/**
 * @author: a.stepanov
 */
@Transactional
public class AppService {
    private CountryMapper countryMapper;

    public void setCountryMapper(CountryMapper countryMapper) {
        this.countryMapper = countryMapper;
    }

    public void addCountry(Country country) {
        countryMapper.insert(country);
    }

    public void updateCountry(Country country) {
        countryMapper.updateByPrimaryKey(country);
    }

    public void deleteCountryById(Integer countryId) {
        countryMapper.deleteByPrimaryKey(countryId);
    }

    public Country getCountryById(Integer countryId) {
        return countryMapper.selectByPrimaryKey(countryId);
    }

    public List<Country> getAllCountries(){
        return countryMapper.getAll();
    }
}
